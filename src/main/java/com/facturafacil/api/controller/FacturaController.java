package com.facturafacil.api.controller;

import com.facturafacil.api.dto.FacturaDTO;
import com.facturafacil.api.service.FacturaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @PostMapping("/webhook/n8n")
    public ResponseEntity<FacturaDTO> recibirDeN8n(@Valid @RequestBody FacturaDTO dto) {

        // El Controller no sabe NADA de mapeos, ni de bases de datos.
        // Solo delega.
        FacturaDTO facturaGuardada = facturaService.guardarFactura(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(facturaGuardada);
    }
}