package com.bcp.reto.tipodecambio.dto;

import com.bcp.reto.tipodecambio.constant.Constant;
import lombok.Data;

@Data
public class ResponseDto {

    private int codigo;
    private String mensaje;
    private Object data;

    public void OkData(Object data){
        this.codigo = Constant.COD_OK;
        this.mensaje = Constant.DATA_OK;
        this.data = data;
    }
    public void Error(String mensaje){
        this.codigo = Constant.COD_ERR;
        this.mensaje = mensaje;
        this.data = new Object();
    }
    public void Empty(){
        this.codigo = Constant.COD_EMPTY;
        this.mensaje = Constant.DATA_EMPTY;
        this.data = new Object();
    }
}
