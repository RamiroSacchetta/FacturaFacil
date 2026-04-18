package com.facturafacil.api.repository;

import com.facturafacil.api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Este metéodo busca al usuario por su número de celular
    Optional<Usuario> findByWhatsapp(String whatsapp);
}