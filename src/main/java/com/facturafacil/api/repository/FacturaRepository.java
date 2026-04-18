package com.facturafacil.api.repository;

import com.facturafacil.api.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {


    boolean existsByIdInterno(String idInterno);

}