package org.jeco.coupon_system_v2.app.clr.test;

import lombok.RequiredArgsConstructor;
import org.jeco.coupon_system_v2.app.beans.Category;
import org.jeco.coupon_system_v2.app.beans.Coupon;
import org.jeco.coupon_system_v2.app.login.ClientType;
import org.jeco.coupon_system_v2.app.login.LoginManager;
import org.jeco.coupon_system_v2.app.service.CompanyService;
import org.jeco.coupon_system_v2.app.utils.TestUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Order(3)
public class CompanyTest implements CommandLineRunner {

    private final LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {

        //company login
        TestUtils.testPrimaryInfo("company service");
        TestUtils.testSecondaryInfo("login bad");
        CompanyService companyService = null;
        try {
            companyService = (CompanyService) loginManager.login("test@company", "abc124", ClientType.COMPANY);
        } catch (Exception e) {
            System.out.println(e);
        }
        TestUtils.testSecondaryEnd();

        TestUtils.testSecondaryInfo("login success");
        companyService = (CompanyService) loginManager.login("test@company1", "1234567", ClientType.COMPANY);
        TestUtils.testSecondaryEnd();

        //add coupon bad
        TestUtils.testSecondaryInfo("add coupon bad");
        System.out.println("\r\nget all coupons");
        companyService.getCompanyCoupons().forEach(System.out::println);
        System.out.println("\r\nadd coupon");
        try {
            companyService.addCoupon(Coupon.builder()
                    .company(companyService.getCompanyId())
                    .category(Category.AUTOMOTIVE)
                    .description("description")
                    .startDate(LocalDate.of(2021, 01, 01))
                    .endDate(LocalDate.of(2021, 02, 01))
                    .amount(5)
                    .price(12.5)
                    .image("image")
                    .build());



        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("\r\nget all coupons");
        companyService.getCompanyCoupons().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        // add coupon success
        //TODO need check companyID before hagasha
        TestUtils.testSecondaryInfo("add coupon success");
        System.out.println("\r\nget all coupons");
        companyService.getCompanyCoupons().forEach(System.out::println);
        System.out.println("\r\nadd coupon");
        try {
            companyService.addCoupon(Coupon.builder()
                    .company(companyService.getCompanyId())
                    .category(Category.AUTOMOTIVE)
                    .title("title")
                    .description("description")
                    .startDate(LocalDate.of(2021, 01, 01))
                    .endDate(LocalDate.of(2021, 10, 01))
                    .amount(5)
                    .price(12.5)
                    .image("image")
                    .build());
            companyService.addCoupon(Coupon.builder()
                    .company(companyService.getCompanyId())
                    .category(Category.BABY)
                    .title("title2")
                    .description("description2")
                    .startDate(LocalDate.of(2021, 01, 01))
                    .endDate(LocalDate.of(2021, 7, 01))
                    .amount(51)
                    .price(17.9)
                    .image("image")
                    .build());
            companyService.addCoupon(Coupon.builder()
                    .company(companyService.getCompanyId())
                    .category(Category.AUTOMOTIVE)
                    .title("title3")
                    .description("description3")
                    .startDate(LocalDate.of(2021, 01, 01))
                    .endDate(LocalDate.of(2022, 9, 01))
                    .amount(51)
                    .price(15.9)
                    .image("image")
                    .build());
            companyService.addCoupon(Coupon.builder()
                    .company(companyService.getCompanyId())
                    .category(Category.BABY)
                    .title("title4")
                    .description("description4")
                    .startDate(LocalDate.of(2021, 01, 01))
                    .endDate(LocalDate.of(2021, 10, 01))
                    .amount(51)
                    .price(11)
                    .image("image")
                    .build());

        } catch (Exception e) {
            System.out.println(e);

        }
        System.out.println("\r\nget all coupons");
        companyService.getCompanyCoupons().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        //update coupon bad
        TestUtils.testSecondaryInfo("update coupon bad");
        System.out.println("\r\nget all coupons");
        companyService.getCompanyCoupons().forEach(System.out::println);
        System.out.println("\r\nupdate coupon");
        try {
            Coupon coupon = companyService.getOneCoupon(2);
            coupon.setId(50);
            companyService.updateCoupon(coupon);

        } catch (Exception e) {
            System.out.println(e);

        }
        System.out.println("\r\nget all coupons");
        companyService.getCompanyCoupons().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        //update coupon success
        TestUtils.testSecondaryInfo("update coupon success");
        System.out.println("\r\nget all coupons");
        companyService.getCompanyCoupons().forEach(System.out::println);
        System.out.println("\r\nupdate coupon");
        try {
            Coupon coupon = companyService.getOneCoupon(2);
            coupon.setId(2);
            companyService.updateCoupon(coupon);

        } catch (Exception e) {
            System.out.println(e);

        }
        System.out.println("\r\nget all coupons");
        companyService.getCompanyCoupons().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        // delete coupon
        TestUtils.testSecondaryInfo("delete coupon");
        System.out.println("\r\nget all coupons");
        companyService.getCompanyCoupons().forEach(System.out::println);
        System.out.println("\r\ndelete coupon");
        try {
            companyService.deleteCoupon(5);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("\r\nget all coupons");
        companyService.getCompanyCoupons().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        //get company coupons by categories
        TestUtils.testSecondaryInfo("get company coupons by categories");
        companyService.getCompanyCoupons(Category.BABY).forEach(System.out::println);
        TestUtils.testSecondaryEnd();


        //get company coupons filtered by max price
        TestUtils.testSecondaryInfo("get company coupons filtered by max price");
        companyService.getCompanyCoupons(13).forEach(System.out::println);
        TestUtils.testSecondaryEnd();


        //get company  details
        TestUtils.testSecondaryInfo("get company details");
        System.out.println(companyService.getCompanyDetails());
        TestUtils.testSecondaryEnd();


    }
}
