package com.fakecompany.micro.image.exception;

public class PersonJustOneImageException extends GeneralRuntimeException{

    private static final long serialVersionUID = 1L;

    public PersonJustOneImageException(String message){
        super(PersonJustOneImageException.class, message);
    }

}
