package com.br.MS_Payment.core.enums;

public enum EnumCode {
    PAY0000("Payment Id not found in database", "PAY-0000"),
    PAY0001("Dont have balance suficient", "PAY-0001");

    private String message;
    private String code;

    EnumCode(String message, String code){
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
