package org.jeco.coupon_system_v2.app.clr;

import lombok.RequiredArgsConstructor;
import org.jeco.coupon_system_v2.app.repository.CouponRepository;
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

    @Override
    public void run(String... args) throws Exception {
//        CompanyService companyService=ctx.getBean(CompanyService.class);
//        companyService.setCompanyId(1);
//        System.out.println(companyService);
//        CompanyService companyService1=ctx.getBean(CompanyService.class);
//        companyService1.setCompanyId(2);
//        System.out.println(companyService);
//        System.out.println(companyService1);


        couponRepository.getCustomerCoupons(2).forEach(System.out::println);
        System.out.println("delete purchase coupon");
        try {
            couponRepository.deletePurchaseCoupon(2, 1);
        }catch (Exception e){
            System.out.println("purchase coupon exception: "+e);
        }
        couponRepository.getCustomerCoupons(2).forEach(System.out::println);

    }
}
