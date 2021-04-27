package com.int221.finalproject.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ExceptionResponse {
    public static enum ERROR_CODE {
        PRODUCT_DOES_NOT_EXIST(1001), PRODUCT_ALREADY_EXIST(1002),IMAGE_DOES_NOT_EXIST(1003),IO_ERROR(1004);
        private int value;

        ERROR_CODE(int value) {
            this.value = value;
        }
    }

    @Getter @Setter
    private ERROR_CODE errorCode;
    @Getter @Setter
    private String message;
    @Getter @Setter
    private LocalDateTime dateTime;

}
