package com.ada.microsservicestorage.erros;

public class ProductStorageNotFoundError extends Exception{

    private String message = "Product Storage not found";

    @Override
    public String getMessage() {
        return this.message;
    }
}
