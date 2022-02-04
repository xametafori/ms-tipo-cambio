package com.bcp.reto.tipodecambio.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TipoCambioResponseDto extends TipoCambioRequestDto{

    private BigDecimal montoCambioDto;
    private BigDecimal tipoCambioDto;
}
