package org.jeco.coupon_system_v2.app.service;

import org.jeco.coupon_system_v2.app.exception.LoginException;
import org.jeco.coupon_system_v2.app.repository.CompanyRepository;
import org.jeco.coupon_system_v2.app.repository.CouponRepository;
import org.jeco.coupon_system_v2.app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class ClientService {
    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected CouponRepository couponRepository;
    @Autowired
    protected CustomerRepository customerRepository;

    public ClientService() {
    }

    abstract boolean login(String email, String password) throws LoginException;

}
