package com.lta.systempayments.sistema_pagos_backend.repository;

import com.lta.systempayments.sistema_pagos_backend.entities.Pago;
import com.lta.systempayments.sistema_pagos_backend.enums.PagoStatus;
import com.lta.systempayments.sistema_pagos_backend.enums.TypePagos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    List<Pago> findByEstudianteCodigo(String codigo);

    List<Pago> findByStatus(PagoStatus status);

    List<Pago> findByType(TypePagos type);
}
