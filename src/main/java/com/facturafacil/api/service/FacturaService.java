package com.facturafacil.api.service;

import com.facturafacil.api.dto.FacturaDTO;
import com.facturafacil.api.entity.Factura;
import com.facturafacil.api.entity.Usuario;
import com.facturafacil.api.exception.BusinessException;
import com.facturafacil.api.exception.EntityNotFoundException;
import com.facturafacil.api.repository.FacturaRepository;
import com.facturafacil.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public FacturaDTO guardarFactura(FacturaDTO dto) {
        // 1. Regla de Negocio: Idempotencia (No duplicar lo que manda n8n)
        if (facturaRepository.existsByIdInterno(dto.getId_interno())) {
            throw new BusinessException("La factura con ID " + dto.getId_interno() + " ya fue procesada.");
        }

        // 2. Buscar al Usuario dueño de la factura
        Usuario usuario = usuarioRepository.findByWhatsapp(dto.getWhatsapp_remitente())
                .orElseThrow(() -> new EntityNotFoundException("No existe un usuario con el WhatsApp: " + dto.getWhatsapp_remitente()));

        // 3. Mapear DTO a Entidad
        Factura factura = new Factura();
        factura.setIdInterno(dto.getId_interno());
        factura.setCuitEmisor(dto.getCuit_emisor());
        factura.setImporteTotal(dto.getImporte_total());
        factura.setImporteIva(dto.getImporte_iva());
        factura.setImporteNeto(dto.getImporte_neto());

        if (dto.getFecha_emision() != null) {
            factura.setFechaEmision(LocalDate.parse(dto.getFecha_emision()));
        }

        factura.setUsuario(usuario);

        // 4. Guardar
        Factura facturaGuardada = facturaRepository.save(factura);

        // Opcional: Podrías retornar un DTO convertido aquí, pero por ahora devolvemos el mismo que entró
        return dto;
    }
}