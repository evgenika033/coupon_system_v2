package org.jeco.coupon_system_v2.app.service;

import org.springframework.stereotype.Service;

@Service
public class CustomerService extends ClientService {

    public CustomerService() {
    }

    @Override
    public boolean login(String email, String password) {
        return false;
    }
}