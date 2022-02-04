package com.bcp.reto.tipodecambio.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "tipocambio")
public class TipoCambioEntity implements Serializable {

    private static final long serialVersionUID = 4467531611801172710L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "Moneda origen", example = "PEN/USD")
    @NotNull
    @Valid
    @Column(nullable = false, name = "MONEDAORIGEN")
    private String monedaOrigen;

    @ApiModelProperty(value = "Moneda destino", example = "PEN/USD")
    @NotNull
    @Valid
    @Column(nullable = false, name = "MONEDADESTINO")
    private String monedaDestino;

    @ApiModelProperty(value = "Tipo de cambio", example = "3.82")
    @NotNull
    @Column(name = "TIPOCAMBIO",nullable = false)
    private BigDecimal tipoCambio;
}
