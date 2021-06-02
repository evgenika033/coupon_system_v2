package org.jeco.coupon_system_v2.app.exception;

public class CouponException extends Exception{
    public CouponException(String message) {
        super(message);
    }

    public CouponException(String message, Throwable cause) {
        super(message, cause);
    }
}
