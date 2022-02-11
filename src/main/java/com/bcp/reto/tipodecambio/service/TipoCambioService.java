package com.bcp.reto.tipodecambio.service;

import com.bcp.reto.tipodecambio.dto.ResponseDto;
import com.bcp.reto.tipodecambio.dto.TipoCambioRequestDto;
import com.bcp.reto.tipodecambio.entity.TipoCambioEntity;

import java.util.List;

import io.reactivex.Single;


public interface TipoCambioService {
    Single<ResponseDto> calcular(TipoCambioRequestDto oTipoCambioRequestDto);
    Single<ResponseDto> guardar(TipoCambioEntity oTipoCambioEntity);
    Single<ResponseDto> guardarLista(List<TipoCambioEntity> oTipoCambioEntity);

}
