package com.bcp.reto.tipodecambio.service.impl;


import com.bcp.reto.tipodecambio.dto.ResponseDto;
import com.bcp.reto.tipodecambio.dto.TipoCambioRequestDto;
import com.bcp.reto.tipodecambio.dto.TipoCambioResponseDto;
import com.bcp.reto.tipodecambio.entity.TipoCambioEntity;
import com.bcp.reto.tipodecambio.repository.TipoCambioRepository;
import com.bcp.reto.tipodecambio.service.TipoCambioService;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class TipoCambioServiceImpl implements TipoCambioService {

    @Autowired
    private TipoCambioRepository tipoCambioRepository;

    @Override
    public Single<ResponseDto> calcular(TipoCambioRequestDto oTipoCambioRequestDto) {

       return Single.create( subscriber -> {
           subscriber.onSuccess(calcularCambio(oTipoCambioRequestDto));
       });
    }

    @Override
    public Single<ResponseDto> guardar(TipoCambioEntity oTipoCambioEntity) {
        return Single.create( subscriber -> {
            subscriber.onSuccess(guardarCambio(oTipoCambioEntity));
        });
    }

    public ResponseDto guardarCambio(TipoCambioEntity oTipoCambioEntity){
        ResponseDto oResponseDto= new ResponseDto();
        oResponseDto.OkData(tipoCambioRepository.save(oTipoCambioEntity));
        return oResponseDto;
    }
    private ResponseDto calcularCambio(TipoCambioRequestDto oTipoCambioRequestDto){

        TipoCambioResponseDto oTipoCambioResponseDto=new TipoCambioResponseDto();
        ResponseDto oResponseDto =new ResponseDto();

            TipoCambioEntity oTipoCambioEntity=  tipoCambioRepository.findByMonedaOrigenAndMonedaDestino(oTipoCambioRequestDto.getMonedaOrigenDto(),oTipoCambioRequestDto.getMonedadDestinoDto());

            if(Objects.isNull(oTipoCambioEntity))
            {
                oResponseDto.Empty();
            }else{

                oTipoCambioResponseDto.setTipoCambioDto(oTipoCambioEntity.getTipoCambio());
                oTipoCambioResponseDto.setMonedaOrigenDto(oTipoCambioEntity.getMonedaOrigen());
                oTipoCambioResponseDto.setMonedadDestinoDto(oTipoCambioEntity.getMonedaDestino());
                oTipoCambioResponseDto.setMontoDto(oTipoCambioRequestDto.getMontoDto());
                oTipoCambioResponseDto.setMontoCambioDto(oTipoCambioRequestDto.getMontoDto().multiply(oTipoCambioEntity.getTipoCambio()));

                oResponseDto.OkData(oTipoCambioResponseDto);
            }

        return oResponseDto;
    }
}
