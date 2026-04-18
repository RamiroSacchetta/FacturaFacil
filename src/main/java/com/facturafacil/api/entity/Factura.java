package com.facturafacil.api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "facturas")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_interno", unique = true)
    private String idInterno; // Mapea con parsedData.id_interno (FF-...)

    private String cuitEmisor; // El CUIT de la factura

    private LocalDate fechaEmision;

    private Double importeTotal;

    private Double importeIva;

    private Double importeNeto; // El valor calculado para el contador

    @Column(length = 500)
    private String urlImagen; // Link al ticket original

    // Relación con el dueño de la factura
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}