package com.microsoft.samples.nexo.openprotocol;

/**
 * NexoCommException
 */
public class NexoCommException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NexoCommException() {
    }

    public NexoCommException(String message) {
        super(message);
    }

    public NexoCommException(Throwable cause) {
        super(cause);
    }

    public NexoCommException(String message, Throwable cause) {
        super(message, cause);
    }

    public NexoCommException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    
}