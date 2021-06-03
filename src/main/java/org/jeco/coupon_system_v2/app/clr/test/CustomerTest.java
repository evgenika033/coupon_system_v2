package org.jeco.coupon_system_v2.app.clr.test;

import lombok.RequiredArgsConstructor;
import org.jeco.coupon_system_v2.app.beans.Category;
import org.jeco.coupon_system_v2.app.beans.Coupon;
import org.jeco.coupon_system_v2.app.login.ClientType;
import org.jeco.coupon_system_v2.app.login.LoginManager;
import org.jeco.coupon_system_v2.app.repository.CouponRepository;
import org.jeco.coupon_system_v2.app.service.CustomerService;
import org.jeco.coupon_system_v2.app.utils.TestUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(5)
public class CustomerTest implements CommandLineRunner {

    private final LoginManager loginManager;
    private  final CouponRepository couponRepository;

    @Override
    public void run(String... args) throws Exception {

        //customer login
        TestUtils.testPrimaryInfo("customer service");
        TestUtils.testSecondaryInfo("login bad");
        CustomerService customerService = null;
        try {
            customerService = (CustomerService) loginManager.login("aaa@rry8","123", ClientType.CUSTOMER);
        } catch (Exception e) {
            System.out.println(e);
        }
        TestUtils.testSecondaryEnd();

        TestUtils.testSecondaryInfo("login success");
        customerService = (CustomerService) loginManager.login("tom@rry","123456", ClientType.CUSTOMER);
        TestUtils.testSecondaryEnd();


        //add purchase coupon good
        TestUtils.testSecondaryInfo("add purchase coupon good");
        System.out.println("\r\nget all purchase coupons");
        customerService.getCustomerCoupons().forEach(System.out::println);
        System.out.println("\r\nadd purchase coupon");
        try {
            Coupon coupon=couponRepository.getOne(18);
        customerService.addPurchaseCoupon(coupon);
            customerService.addPurchaseCoupon(couponRepository.getOne(19));
            customerService.addPurchaseCoupon(couponRepository.getOne(42));
            }
        catch (Exception e){
            System.out.println(e);
        }
        System.out.println("get all purchase coupons");
        customerService.getCustomerCoupons().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        //add purchase coupon bad
        TestUtils.testSecondaryInfo("add purchase coupon bad");
        System.out.println("\r\nget all purchase coupons");
        customerService.getCustomerCoupons().forEach(System.out::println);
        System.out.println("\r\nadd purchase coupon");
        try {
           // Coupon coupon=couponRepository.getOne(19);
           // customerService.addPurchaseCoupon(coupon);
            customerService.addPurchaseCoupon(couponRepository.getOne(19));
        }
        catch (Exception e){
            System.out.println(e);
        }
        System.out.println("get all purchase coupons");
        customerService.getCustomerCoupons().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        //get all customer coupon by category
        TestUtils.testSecondaryInfo("get all customer coupon by category");
        customerService.getCustomerCoupons(Category.BABY).forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        //get all customer coupon by maxPrice
        TestUtils.testSecondaryInfo("get all customer coupon by maxPrice");
        customerService.getCustomerCoupons(18).forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        //get customer details
        TestUtils.testSecondaryInfo("get customer details");
        System.out.println( customerService.getCustomerDetails());
        TestUtils.testSecondaryEnd();



    }
}
