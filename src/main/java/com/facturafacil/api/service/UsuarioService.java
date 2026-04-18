package com.facturafacil.api.service;

import com.facturafacil.api.dto.UsuarioDTORequest;
import com.facturafacil.api.entity.Usuario;
import com.facturafacil.api.exception.EntityNotFoundException;
import com.facturafacil.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioDTORequest findByWhatsapp(String whatsapp) {
        Usuario usuario = usuarioRepository.findByWhatsapp(whatsapp)
                .orElseThrow(() -> new EntityNotFoundException("El usuario NO encontrado con el WhatsApp: " + whatsapp));

       UsuarioDTORequest usuarioDTORequest = new UsuarioDTORequest();
       usuarioDTORequest.setWhatsapp(whatsapp);
     return usuarioDTORequest;
    }
}
