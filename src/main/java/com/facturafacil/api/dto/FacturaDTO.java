package com.facturafacil.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FacturaDTO {

    @NotBlank(message = "El CUIT del emisor no puede estar vacío")
    private String cuit_emisor;

    @NotNull(message = "El importe total es obligatorio")
    @Min(value = 0, message = "El importe no puede ser negativo")
    private Double importe_total;

    // Estos no los ponemos como obligatorios porque la IA podría fallar en sacarlos
    // o podrías tener tickets sin IVA detallado.
    private Double importe_iva;
    private Double importe_neto;

    @NotBlank(message = "La fecha de emisión es obligatoria")
    private String fecha_emision;

    @NotBlank(message = "Falta el ID interno generado por n8n")
    private String id_interno;

    @NotBlank(message = "El número de WhatsApp del remitente es obligatorio")
    private String whatsapp_remitente;
}