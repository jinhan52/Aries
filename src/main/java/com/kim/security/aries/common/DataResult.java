package com.kim.security.aries.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataResult implements Serializable {

    private static final String SUCCESS_RESULT ="ok";
    private static final String ERROR_RESULT ="NotOk";

    private int status;
    private String sucess;
    private String message;
    private Object data;
    private IToken tokens;

    public static DataResult success(){
        return new DataResult(ResultStatus.SUCCESS,SUCCESS_RESULT,null,null,null);
    }

    public static DataResult success(String message){
        return new DataResult(ResultStatus.SUCCESS,SUCCESS_RESULT,message,null,null);
    }

    public static DataResult successWithData(Object data){
        return new DataResult(ResultStatus.SUCCESS,SUCCESS_RESULT,null,null,null);
    }

    public static DataResult success(String message,Object data){
        return new DataResult(ResultStatus.SUCCESS,SUCCESS_RESULT,message,data,null);
    }

    public static DataResult successWithToken(String message,Object data,IToken token){
        return new DataResult(ResultStatus.SUCCESS,SUCCESS_RESULT,message,data,token);
    }

    public static DataResult success(String message,Object data,int status){
        return new DataResult(status,SUCCESS_RESULT,message,data,null);
    }

    public static DataResult failure(String message){
        return new DataResult(ResultStatus.ERROR,ERROR_RESULT,message,null,null);
    }

    public static DataResult failure(String message,int status){
        return new DataResult(status,ERROR_RESULT,message,null,null);
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IToken implements Serializable{
        private String token;
        private String refreshToken;
    }
}
