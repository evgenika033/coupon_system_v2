package org.jeco.coupon_system_v2.app.clr;


import lombok.RequiredArgsConstructor;
import org.jeco.coupon_system_v2.app.beans.Category;
import org.jeco.coupon_system_v2.app.beans.Company;
import org.jeco.coupon_system_v2.app.beans.Coupon;
import org.jeco.coupon_system_v2.app.beans.Customer;
import org.jeco.coupon_system_v2.app.repository.CouponRepository;
import org.jeco.coupon_system_v2.app.service.AdminService;
import org.jeco.coupon_system_v2.app.service.CompanyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Order(2)
public class Test2 implements CommandLineRunner {
    private final AdminService adminService;
    private  final CompanyService companyService;
    private final CouponRepository couponRepository;

    // private  final CompanyRepository companyRepository;
    @Override
    public void run(String... args) throws Exception {
        companyService.setCompanyId(1);
        Company company = Company.builder()
                .email("Test2@test")
                .name("Test2")
                .password("Test2")
                .build();

        Customer customer = Customer.builder()
                .email("Test@customer5")
                .password("123456")
                .firstName("Adam")
                .lastName("Kravi")
                .build();

        Coupon coupon= Coupon.builder()
                .company(1)
                .category(Category.AUTOMOTIVE)
                .title("title")
                .description("description")
                .startDate(LocalDate.of(2021,01,01))
                .endDate(LocalDate.of(2021,02,01))
                .amount(5)
                .price(12.5)
                .image("image")
                .build();

        Coupon coupon1= Coupon.builder()
                .company(1)
                .category(Category.BABY)
                .title("title2")
                .description("description2")
                .startDate(LocalDate.of(2021,01,01))
                .endDate(LocalDate.of(2021,02,01))
                .amount(55)
                .price(12.5)
                .image("image")
                .build();

        adminService.addCompany(company);
        adminService.addCompany(company);

        //   companyRepository.findAll().forEach(System.out::println);
        Company c1 = adminService.getOneCompany(2);
        c1.setEmail("Test@test");
        adminService.updateCompany(c1);

        adminService.getAllCompanies().forEach(System.out::println);
        couponRepository.findAll().forEach(System.out::println);
        //adminService.deleteCompany(1);
        System.out.println("coupons after delete");
        couponRepository.findAll().forEach(System.out::println);
        System.out.println(adminService.getOneCustomer(1));
        adminService.addCustomer(customer);
        Customer customer1 = adminService.getOneCustomer(1);
        adminService.updateCustomer(customer1);
        customer1.setEmail("Test@customer5");
        adminService.updateCustomer(customer1);


        adminService.getAllCustomers().forEach(System.out::println);
        adminService.deleteCustomer(1);
        System.out.println("after del");
        adminService.getAllCustomers().forEach(System.out::println);

        companyService.addCoupon(coupon);
        companyService.addCoupon(coupon1);
        System.out.println( couponRepository.existsByCompanyAndTitleAndIdIsNot(coupon.getCompany(),coupon.getTitle(),3));
        companyService.updateCoupon(coupon);

        System.out.println(" delete coupon");
        //companyService.deleteCoupon(3);

        System.out.println(" get company coupons by categories ");
        companyService.getCompanyCoupons(Category.BABY).forEach(System.out::println);

        System.out.println("get company coupons ");
        companyService.getCompanyCoupons().forEach(System.out::println);

        System.out.println("get company coupons by price 13");
        companyService.getCompanyCoupons(13).forEach(System.out::println);;

        System.out.println("get company coupons by price 5");
        companyService.getCompanyCoupons(5).forEach(System.out::println);;

        System.out.println("get company details");
        System.out.println(companyService.getCompanyDetails());

    }
}
