package com.example.root.sgc_dbflow.conector;


import java.util.EventObject;


public class Response extends EventObject {

    private int responseCode;
    private String str_error_message;
    private String str_json;

    public Response(Object source) {
        super(source);
    }

    public String getResponse(){
        if(str_json!= null)
            return str_json;
        else
            return "";
    }

    public String getErrorMessage(){
        if(str_error_message!=null)
            return str_error_message;
        else
            return "";
    }

    protected int getResponseCode(){
        return responseCode;
    }

    protected void setResponseCode(int responseCode){
        this.responseCode = responseCode;
    }

    protected void setResponse(String str_json){
        this.str_json = str_json;
    }

    protected void setErrorMessage(String str_error_message){
        this.str_error_message = str_error_message;
    }

}