package org.jeco.coupon_system_v2.app.clr;

import lombok.RequiredArgsConstructor;
import org.jeco.coupon_system_v2.app.beans.Category;
import org.jeco.coupon_system_v2.app.beans.Company;
import org.jeco.coupon_system_v2.app.beans.Coupon;
import org.jeco.coupon_system_v2.app.beans.Customer;
import org.jeco.coupon_system_v2.app.repository.CompanyRepository;
import org.jeco.coupon_system_v2.app.repository.CouponRepository;
import org.jeco.coupon_system_v2.app.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Order(1)
public class Test implements CommandLineRunner {
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;
    private final CouponRepository couponRepository;
    @Override
    public void run(String... args) throws Exception {
        Company company=Company.builder()
                .email("Test@test")
                .name("Test")
                .password("Test")
                .build();
        Customer customer=Customer.builder()
                .email("Test@customer")
                .password("123456")
                .firstName("Adam")
                .lastName("Kravi")
                .build();
        Coupon coupon= Coupon.builder()
                .amount(2)
                .category(Category.ACCESSORIES)
                .company(1)
                .description("des")
                .image("image")
                .price(2.2)
                .title("Title")
                .startDate(LocalDate.of(2021,03,18))
                .endDate(LocalDate.of(2021,07,18))
                .build();


       companyRepository.save(company);
        customerRepository.save(customer);
        customerRepository.findAll().forEach(System.out::println);

        couponRepository.save(coupon);
        couponRepository.findAll().forEach(System.out::println);
        companyRepository.findAll().forEach(System.out::println);
    }
}
