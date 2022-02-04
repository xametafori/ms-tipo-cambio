package com.bcp.reto.tipodecambio.repository;

import com.bcp.reto.tipodecambio.entity.TipoCambioEntity;
import org.springframework.data.repository.CrudRepository;

public interface TipoCambioRepository extends CrudRepository<TipoCambioEntity, Long> {

    TipoCambioEntity findByMonedaOrigenAndMonedaDestino(String monedaOrigen, String monedaDestino);

}
