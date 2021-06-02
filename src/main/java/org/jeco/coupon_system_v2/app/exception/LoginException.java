package org.jeco.coupon_system_v2.app.exception;

public class LoginException extends Exception{
    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
