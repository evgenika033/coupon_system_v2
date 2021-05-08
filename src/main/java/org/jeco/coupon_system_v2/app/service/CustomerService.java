package org.jeco.coupon_system_v2.app.service;

import org.jeco.coupon_system_v2.app.beans.Category;
import org.jeco.coupon_system_v2.app.beans.Coupon;
import org.jeco.coupon_system_v2.app.beans.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService extends ClientService {
    private int customerID;
    public CustomerService() {
    }

    @Override
    public boolean login(String email, String password) {
        return false;
    }
    public void purchaseCoupon(Coupon coupon){

    }
    public List<Coupon> getCustomerCoupons(){

        return null;
    }
    public List<Coupon> getCustomerCoupons(Category category){

        return null;
    }
    public List<Coupon> getCustomerCoupons(double maxPrice){

        return null;
    }
    public Customer getCustomerDetails(){

        return null;
    }










}