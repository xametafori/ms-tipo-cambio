package com.bcp.reto.tipodecambio.constant;

public final class Constant {

    private Constant() {
        throw new IllegalStateException("TipoCambioMensajeConstant class");
    }

    public static final Integer COD_OK = 0;
    public static final String DATA_OK = "OK";

    public static final Integer COD_EMPTY = 1;
    public static final String DATA_EMPTY = "NO HAY DATA PARA ESTA OPERACIÓN";

    public static final Integer COD_ERR = -1;
    public static final String DATA_ERR = "ERROR";
    public static final String ERROR_EXITE_DATO="El dato que se intenta persistir, ya existe";

}
