package com.ENSF480.airlineBackend.reg_user;

public class Response{
    private String type;
    private Object object;

    public Response(){

    }

    public Response(Object object, String type){
        this.object = object;
        this.type = type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public void setObject(Object object){
        this.object = object;
    }

    public Object getObject(){
        return object;
    }
}

