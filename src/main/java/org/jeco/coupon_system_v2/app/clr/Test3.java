package org.jeco.coupon_system_v2.app.clr;

import lombok.RequiredArgsConstructor;
import org.jeco.coupon_system_v2.app.beans.Category;
import org.jeco.coupon_system_v2.app.repository.CouponRepository;
import org.jeco.coupon_system_v2.app.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(3)
public class Test3 implements CommandLineRunner {
   private final ApplicationContext ctx;
   private final CouponRepository couponRepository;
   private  final CustomerService customerService;

    @Override
    public void run(String... args) throws Exception {
//        CompanyService companyService=ctx.getBean(CompanyService.class);
//        companyService.setCompanyId(1);
//        System.out.println(companyService);
//        CompanyService companyService1=ctx.getBean(CompanyService.class);
//        companyService1.setCompanyId(2);
//        System.out.println(companyService);
//        System.out.println(companyService1);

        System.out.println("get all purchase coupon");
        couponRepository.getCustomerCoupons(2).forEach(System.out::println);
        System.out.println("delete purchase coupon");
        try {
            couponRepository.deletePurchaseCoupon(2, 1);
        }catch (Exception e){
            System.out.println("purchase coupon exception: "+e);
        }
        System.out.println("get all purchase coupon");
        couponRepository.getCustomerCoupons(2).forEach(System.out::println);
        System.out.println("add purchase coupon");
        couponRepository.addPurchaseCoupon(2,1);
        System.out.println("get all purchase coupon");
        couponRepository.getCustomerCoupons(2).forEach(System.out::println);

        System.out.println("get all customer coupon");
        customerService.setCustomerID(2);
        customerService.getCustomerCoupons().forEach(System.out::println);

        System.out.println("get all customer coupon by category");
        customerService.getCustomerCoupons(Category.ELECTRIC).forEach(System.out::println);

        System.out.println("get all customer coupon by maxPrice");
        customerService.getCustomerCoupons(2.4).forEach(System.out::println);

    }
}
