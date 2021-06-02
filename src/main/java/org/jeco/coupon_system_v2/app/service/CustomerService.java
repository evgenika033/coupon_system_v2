package org.jeco.coupon_system_v2.app.service;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.jeco.coupon_system_v2.app.beans.Category;
import org.jeco.coupon_system_v2.app.beans.Coupon;
import org.jeco.coupon_system_v2.app.beans.Customer;
import org.jeco.coupon_system_v2.app.exception.CouponException;
import org.jeco.coupon_system_v2.app.exception.CustomerException;
import org.jeco.coupon_system_v2.app.exception.LoginException;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@Service
@Scope("prototype")
@Data
public class CustomerService extends ClientService {
    private int customerID;


    @Override
    public boolean login(String email, String password) throws LoginException {
        if (customerRepository.existsByEmailAndPassword(email, password)) {
            setCustomerID(customerRepository.getCustomerByEmailAndPassword(email, password).getId());
            return true;
        }
        throw new LoginException("login exception: login failed");

    }

    public void addPurchaseCoupon(Coupon coupon) throws CustomerException {
        if(coupon.getEndDate().isBefore(LocalDate.now())){
            throw new CustomerException("purchase coupon exception: coupon end date is over.");
        }
        couponRepository.addPurchaseCoupon(customerID, coupon.getId());
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

    public void deleteCoupon(int couponID) throws CouponException {
        try {
            // delete coupon purchase  history
            deleteCouponHistory(couponID);
            //delete coupon
            couponRepository.deleteById(couponID);
        } catch (EmptyResultDataAccessException e) {
            throw new CouponException("delete coupon exception: coupon id not found");
        }


    }

    // delete coupon purchase  history
    private void deleteCouponHistory(int couponID) {
        couponRepository.getPurchaseCouponByCouponID(couponID).forEach(cust -> {
            couponRepository.deletePurchaseCoupon(cust, couponID);
        });
    }


}