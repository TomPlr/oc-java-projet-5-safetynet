package org.safetynet.exception;

public class BlankInputException extends Exception{

    public BlankInputException(){
        super();
    }

    public BlankInputException(String message){
        super(message);
    }
}
