package com.zoloz.api.sdk.client.exception;

/**
 * api gateway response parsing exception
 *
 * @author Zhang Fang
 */
public class ApigwParseException extends Exception {

    /**
     * ctor
     * @param message error message
     */
    public ApigwParseException(String message) {
        this(message, null);
    }

    /**
     * ctor
     * @param message error message
     * @param cause internal cause
     */
    public ApigwParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
