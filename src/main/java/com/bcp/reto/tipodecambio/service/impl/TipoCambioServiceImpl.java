package com.bcp.reto.tipodecambio.service.impl;


import com.bcp.reto.tipodecambio.constant.Constant;
import com.bcp.reto.tipodecambio.dto.ResponseDto;
import com.bcp.reto.tipodecambio.dto.TipoCambioRequestDto;
import com.bcp.reto.tipodecambio.dto.TipoCambioResponseDto;
import com.bcp.reto.tipodecambio.entity.TipoCambioEntity;
import com.bcp.reto.tipodecambio.repository.TipoCambioRepository;
import com.bcp.reto.tipodecambio.service.TipoCambioService;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Single<ResponseDto> guardarLista(List<TipoCambioEntity> oTipoCambioLista) {

        return Single.create(subscriber ->{
            subscriber.onSuccess(guardardataLista(oTipoCambioLista));

        });
    }


    public ResponseDto guardardataLista(List<TipoCambioEntity> oTipoCambioLista) {
        ResponseDto oResponse = new ResponseDto();
        oResponse.OkData(tipoCambioRepository.saveAll(oTipoCambioLista));

        return oResponse;
    }

    public ResponseDto guardarCambio(TipoCambioEntity oTipoCambioEntityreq){
        ResponseDto oResponseDto= new ResponseDto();

        Optional<TipoCambioEntity> oTipoCambioEntity= Optional.ofNullable(tipoCambioRepository.findByMonedaOrigenAndMonedaDestino(oTipoCambioEntityreq.getMonedaOrigen(), oTipoCambioEntityreq.getMonedaDestino()));

        if(oTipoCambioEntity.isPresent()){
            oResponseDto.Error(Constant.ERROR_EXITE_DATO);
        }else{
            oResponseDto.OkData(tipoCambioRepository.save(oTipoCambioEntityreq));
        }
        return oResponseDto;
    }
    private ResponseDto calcularCambio(TipoCambioRequestDto oTipoCambioRequestDto){

        ResponseDto oResponseDto =new ResponseDto();

        Optional<TipoCambioEntity> oTipoCambioEntity= Optional.ofNullable(tipoCambioRepository.findByMonedaOrigenAndMonedaDestino(oTipoCambioRequestDto.getMonedaOrigenDto(), oTipoCambioRequestDto.getMonedadDestinoDto()));

        if(!oTipoCambioEntity.isPresent())
        {
            TipoCambioEntity oEntity= new TipoCambioEntity();
            oEntity.setTipoCambio(new BigDecimal(oTipoCambioRequestDto.getTipoCambioDto()==null?"3.5":oTipoCambioRequestDto.getTipoCambioDto().toString()));
            oEntity.setMonedaOrigen(oTipoCambioRequestDto.getMonedaOrigenDto());
            oEntity.setMonedaDestino(oTipoCambioRequestDto.getMonedadDestinoDto());
            TipoCambioEntity oEntitySave =  tipoCambioRepository.save(oEntity);

            oResponseDto.OkData(obtenerResponseCalcularTipoCambio(oEntitySave,oTipoCambioRequestDto));
            return oResponseDto;
        }

        oResponseDto.OkData(obtenerResponseCalcularTipoCambio(oTipoCambioEntity.get(),oTipoCambioRequestDto));
        return oResponseDto;
    }

    private TipoCambioResponseDto obtenerResponseCalcularTipoCambio(TipoCambioEntity oEntitySave,
                                                                    TipoCambioRequestDto oTipoCambioRequestDto){

        TipoCambioResponseDto oTipoCambioResponseDto=new TipoCambioResponseDto();
        oTipoCambioResponseDto.setTipoCambioDto(oEntitySave.getTipoCambio());
        oTipoCambioResponseDto.setMonedaOrigenDto(oEntitySave.getMonedaOrigen());
        oTipoCambioResponseDto.setMonedadDestinoDto(oEntitySave.getMonedaDestino());
        oTipoCambioResponseDto.setMontoDto(oTipoCambioRequestDto.getMontoDto());
        oTipoCambioResponseDto.setMontoCambioDto(oTipoCambioRequestDto.getMontoDto().multiply(oEntitySave.getTipoCambio()));

        return oTipoCambioResponseDto;
    }

}
