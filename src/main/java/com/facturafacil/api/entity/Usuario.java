package com.facturafacil.api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import com.facturafacil.api.entity.Factura;

@Entity
@Table(name = "usuarios")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String whatsapp; // El identificador que viene de n8n (ej: 54911...)

    private String nombre;

    private boolean activo = true;

    // Relación bidireccional: un usuario tiene muchas facturas
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Factura> facturas;
}