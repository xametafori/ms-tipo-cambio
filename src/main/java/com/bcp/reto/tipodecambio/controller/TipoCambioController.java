package com.bcp.reto.tipodecambio.controller;


import com.bcp.reto.tipodecambio.dto.ResponseDto;
import com.bcp.reto.tipodecambio.dto.TipoCambioRequestDto;
import com.bcp.reto.tipodecambio.entity.TipoCambioEntity;
import com.bcp.reto.tipodecambio.service.TipoCambioService;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RefreshScope
@RestController
@RequestMapping("api/v1/tipoCambio")
@Api(tags = { "servicio de cambio de moneda" })
public class TipoCambioController {

    @Autowired
    private TipoCambioService tipoCambioService;

    @ApiOperation(value = "Calcular el monto acorde al tipo de cambio")
    @PostMapping("calcularCambio")
    public Single<ResponseEntity<ResponseDto>> calcularTipoCambio(
            @RequestBody TipoCambioRequestDto dto) {
        return this.tipoCambioService.calcular(dto).subscribeOn(Schedulers.io())
                .map(ResponseEntity::ok);
    }

    @ApiOperation(value = "Guardar el tipo de cambio")
    @PostMapping("guardar")
    public Single<ResponseEntity<ResponseDto>> guardar(
            @RequestBody TipoCambioEntity dto) {
        return this.tipoCambioService.guardar(dto).subscribeOn(Schedulers.io())
                .map(ResponseEntity::ok);
    }
}
