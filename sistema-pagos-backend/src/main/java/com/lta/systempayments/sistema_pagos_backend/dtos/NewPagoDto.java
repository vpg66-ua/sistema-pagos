package com.lta.systempayments.sistema_pagos_backend.dtos;

import com.lta.systempayments.sistema_pagos_backend.enums.TypePagos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPagoDto {

    private double cantidad;

    private TypePagos type;

    private LocalDate fecha;

    private String codigoEstudiante;
}

