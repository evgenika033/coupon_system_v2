package org.jeco.coupon_system_v2.app.exception;

public class CompanyException extends Exception{
    public CompanyException(String message) {
        super(message);
    }

    public CompanyException(String message, Throwable cause) {
        super(message, cause);
    }
}
