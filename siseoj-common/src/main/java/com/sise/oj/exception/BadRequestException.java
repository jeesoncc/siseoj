package com.sise.oj.exception;

import com.sise.oj.enums.ResultCode;
import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException{

    private ResultCode code;

    public BadRequestException(String msg){
        super(msg);
    }

    public BadRequestException(ResultCode code, String msg){
        super(msg);
        this.code = code;
    }
}