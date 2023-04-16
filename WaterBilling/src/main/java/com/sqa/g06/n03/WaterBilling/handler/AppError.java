package com.sqa.g06.n03.WaterBilling.handler;

public class AppError extends RuntimeException{
    private int statusCode;

    public AppError(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public AppError(String message, Throwable cause, int statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public AppError(Throwable cause, int statusCode) {
        super(cause);
        this.statusCode = statusCode;
    }

    public AppError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int statusCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
