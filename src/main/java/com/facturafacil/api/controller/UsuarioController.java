package com.facturafacil.api.controller;

import com.facturafacil.api.dto.UsuarioDTORequest;
import com.facturafacil.api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private  UsuarioService usuarioService;



    @PostMapping("/verificar-whatsapp")
    public ResponseEntity<UsuarioDTORequest> verificarParaSecuencia(@Valid @RequestBody UsuarioDTORequest dto) {

        UsuarioDTORequest resultado = usuarioService.findByWhatsapp(dto.getWhatsapp());
        return ResponseEntity.ok(resultado);
    }
}
