package ru.otus.exception;

public class DetailRuntimeException extends RuntimeException {

    public DetailRuntimeException(String message){
        super(message);
    }

    public DetailRuntimeException(String message, Throwable cause){
        super(message,cause);
    }

    public DetailRuntimeException(Throwable cause){
        super(cause);
    }

}
