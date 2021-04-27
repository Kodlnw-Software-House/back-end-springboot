package com.int221.finalproject.exceptions;

import lombok.Getter;

public class CustomException extends RuntimeException{

    @Getter
    ExceptionResponse.ERROR_CODE errorCode;

    public CustomException(String e,ExceptionResponse.ERROR_CODE errorCode){
        super(e);
        this.errorCode = errorCode;
    }

}
