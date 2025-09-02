package com.lta.systempayments.sistema_pagos_backend.entities;

import com.lta.systempayments.sistema_pagos_backend.enums.PagoStatus;
import com.lta.systempayments.sistema_pagos_backend.enums.TypePagos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private double cantidad;

    private TypePagos type;

    private PagoStatus status;

    private String file;

    @ManyToOne
    private Estudiante estudiante;
}
