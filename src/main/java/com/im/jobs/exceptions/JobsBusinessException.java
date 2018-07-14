package com.im.jobs.exceptions;

/**
 * @author imaltsev
 * @since 14/07/18
 * <p>
 * Exception class stands for issues arising in the process of Jobs selecting
 */
public class JobsBusinessException extends Exception {

    private String message;

    public JobsBusinessException(String message) {
        super(message);
        this.message = message;
    }

    public JobsBusinessException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
