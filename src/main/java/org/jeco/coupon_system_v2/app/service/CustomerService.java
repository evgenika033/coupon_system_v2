package org.jeco.coupon_system_v2.app.service;

import org.jeco.coupon_system_v2.app.beans.Category;
import org.jeco.coupon_system_v2.app.beans.Coupon;
import org.jeco.coupon_system_v2.app.beans.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService extends ClientService {
    private int customerID;

    public CustomerService() {
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    @Override
    public boolean login(String email, String password) {
        return false;
    }

    public void purchaseCoupon(Coupon coupon) {

    }

    public List<Coupon> getCustomerCoupons() {
        List<Coupon> coupons = new ArrayList<>();
//        List<Integer> couponIDs = couponRepository.getCustomerCoupons(customerID);
//        for (int couponID:couponIDs) {
//            Coupon coupon= couponRepository.getOne(couponID);
//            coupons.add(coupon);

//        }

        //get customer coupons method return list
        couponRepository.getCustomerCoupons(customerID).forEach(cID -> {
            coupons.add(couponRepository.getOne(cID));
        });
        return coupons;
    }


    public List<Coupon> getCustomerCoupons(Category category) {
        return couponRepository.getCustomerCoupons(category.ordinal(), customerID);
    }

    public List<Coupon> getCustomerCoupons(double maxPrice) {
        return couponRepository.getCustomerCoupons(maxPrice, customerID);
    }

    public Customer getCustomerDetails() {
        return customerRepository.getOne(customerID);
    }


}