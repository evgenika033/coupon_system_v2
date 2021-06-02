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
@Order(4)
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


        //add purchase coupon
        TestUtils.testSecondaryInfo("add purchase coupon");
        System.out.println("\r\nget all purchase coupons");
        customerService.getCustomerCoupons().forEach(System.out::println);
        System.out.println("\r\nadd purchase coupon");
        try {
            Coupon coupon=couponRepository.getOne(1);
        customerService.addPurchaseCoupon(coupon);
            customerService.addPurchaseCoupon(couponRepository.getOne(2));
            }
        catch (Exception e){
            System.out.println(e);
        }
        System.out.println("get all purchase coupons");
        customerService.getCustomerCoupons().forEach(System.out::println);
        TestUtils.testSecondaryEnd();


        System.out.println("get all customer coupon by category");
        customerService.getCustomerCoupons(Category.BABY).forEach(System.out::println);

        System.out.println("get all customer coupon by maxPrice");
        customerService.getCustomerCoupons(13).forEach(System.out::println);

//
//
//        TestUtils.testSecondaryInfo("add coupon bad");
//        System.out.println("\r\nget all coupons");
//        companyService.getCompanyCoupons().forEach(System.out::println);
//        System.out.println("\r\nadd coupon");
//        try {
//            companyService.addCoupon(Coupon.builder()
//                    .company(1)
//                    .category(Category.AUTOMOTIVE)
//                    .description("description")
//                    .startDate(LocalDate.of(2021, 01, 01))
//                    .endDate(LocalDate.of(2021, 02, 01))
//                    .amount(5)
//                    .price(12.5)
//                    .image("image")
//                    .build());
//
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        System.out.println("\r\nget all coupons");
//        companyService.getCompanyCoupons().forEach(System.out::println);
//        TestUtils.testSecondaryEnd();

    }
}
