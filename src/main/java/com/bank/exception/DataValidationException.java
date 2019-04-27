package com.bank.exception;

/**
 * Created by cetin on 4/24/2019.
 */
public class DataValidationException extends Exception {
    private static final long serialVersionUID = -3128681006635769411L;

    public DataValidationException(String message) {
        super(message);
    }

}
