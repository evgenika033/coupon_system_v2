package org.jeco.coupon_system_v2.app.clr;

import lombok.RequiredArgsConstructor;
import org.jeco.coupon_system_v2.app.beans.Category;
import org.jeco.coupon_system_v2.app.beans.Company;
import org.jeco.coupon_system_v2.app.beans.Coupon;
import org.jeco.coupon_system_v2.app.beans.Customer;
import org.jeco.coupon_system_v2.app.repository.CompanyRepository;
import org.jeco.coupon_system_v2.app.repository.CouponRepository;
import org.jeco.coupon_system_v2.app.repository.CustomerRepository;
import org.jeco.coupon_system_v2.app.service.CompanyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Order(3)
public class Test3 implements CommandLineRunner {
   private final ApplicationContext ctx;

    @Override
    public void run(String... args) throws Exception {
        CompanyService companyService=ctx.getBean(CompanyService.class);
        companyService.setCompanyId(1);
        System.out.println(companyService);
        CompanyService companyService1=ctx.getBean(CompanyService.class);
        companyService1.setCompanyId(2);
        System.out.println(companyService);
        System.out.println(companyService1);
    }
}
