package com.bcp.reto.tipodecambio.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TipoCambioRequestDto {

    private BigDecimal montoDto;
    private String monedaOrigenDto;
    private String monedadDestinoDto;

}
