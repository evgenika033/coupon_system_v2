package org.jeco.coupon_system_v2.app.clr.test;

import lombok.RequiredArgsConstructor;
import org.jeco.coupon_system_v2.app.beans.Company;
import org.jeco.coupon_system_v2.app.beans.Customer;
import org.jeco.coupon_system_v2.app.login.ClientType;
import org.jeco.coupon_system_v2.app.login.LoginManager;
import org.jeco.coupon_system_v2.app.service.AdminService;
import org.jeco.coupon_system_v2.app.utils.TestUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(3)
public class AdminTest implements CommandLineRunner {

    private final LoginManager loginManager;
    private final ApplicationContext ctx;

    @Override
    public void run(String... args) throws Exception {

        //admin login
        TestUtils.testPrimaryInfo("admin service");
        TestUtils.testSecondaryInfo("login bad");
        AdminService adminService = null;
        try {
            adminService = (AdminService) loginManager.login("admln@admin.com", "admin", ClientType.ADMINISTRATOR);
        } catch (Exception e) {
            System.out.println(e);
        }
        TestUtils.testSecondaryEnd();

        TestUtils.testSecondaryInfo("login success");
        adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
        TestUtils.testSecondaryEnd();

        //add company bad
        TestUtils.testSecondaryInfo("add company bad");
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        System.out.println("\r\nadd company");
        try {
            adminService.addCompany(Company.builder()
                    .email(null)
                    .name("testCompany")
                    .password("123456")
                    .build());

        } catch (Exception e) {
            System.out.println(e);

        }
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        //add company success
        TestUtils.testSecondaryInfo("add company success");
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        System.out.println("\r\nadd company");
        try {
            adminService.addCompany(Company.builder()
                    .email("test@company")
                    .name("testCompany")
                    .password("123456")
                    .build());

            adminService.addCompany(Company.builder()
                    .email("test@company1")
                    .name("testCompany1")
                    .password("1234567")
                    .build());

        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        //update company bad
        TestUtils.testSecondaryInfo("update company bad");
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        System.out.println("\r\nupdate company");
        try {
            Company company = adminService.getOneCompany(1);
            company.setId(50);
            adminService.updateCompany(company);

        } catch (Exception e) {
            System.out.println(e);

        }
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        //update company success
        TestUtils.testSecondaryInfo("update company success");
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        System.out.println("\r\nupdate company");
        try {
            Company company = adminService.getOneCompany(1);
            company.setPassword("abc123");
            adminService.updateCompany(company);

        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        TestUtils.testSecondaryEnd();


        //delete company success
        TestUtils.testSecondaryInfo("delete company success");
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        System.out.println("\r\ndelete company");
        adminService.deleteCompany(1);
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        //get one company by id--bad
        TestUtils.testSecondaryInfo("get one company bad");
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        System.out.println("\r\nget one company");
        try {
            adminService.getOneCompany(50);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        //get one company by id--success
        TestUtils.testSecondaryInfo("get one company success ");
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        System.out.println("\r\nget one company");
        try {
            Company company = adminService.getOneCompany(1);
            company.setId(1);
            adminService.getOneCompany(1);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("\r\nget all companies");
        adminService.getAllCompanies().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        //add customer success
        TestUtils.testSecondaryInfo("add customer success");
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        System.out.println("\r\nadd customer");
        try {
            adminService.addCustomer(Customer.builder()
                    .email("aaa@rry")
                    .password("1234")
                    .firstName("Mu")
                    .lastName("Bu")
                    .build());

            adminService.addCustomer(Customer.builder()
                    .email("Vir@rry")
                    .password("12345")
                    .firstName("Murzik")
                    .lastName("Bur")
                    .build());

            adminService.addCustomer(Customer.builder()
                    .email("tom@rry")
                    .password("123456")
                    .firstName("Moshe")
                    .lastName("Kaz")
                    .build());

        } catch (Exception e) {
            System.out.println(e);

        }
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        //add customer bad
        TestUtils.testSecondaryInfo("add customer bad");
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        System.out.println("\r\nadd customer");
        try {
            adminService.addCustomer(Customer.builder()
                    .email("aaa@rry")
                    .password("1234")
                    .firstName("Kut")
                    .lastName("But")
                    .build());

        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        //update customer bad
        TestUtils.testSecondaryInfo("update customer bad");
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        System.out.println("\r\nupdate customer");
        try {
            Customer customer = adminService.getOneCustomer(1);
            customer.setId(50);
            adminService.updateCustomer(customer);

        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        //update customer success
        TestUtils.testSecondaryInfo("update customer success");
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        System.out.println("\r\nupdate customer");
        try {
            Customer customer = adminService.getOneCustomer(1);
            customer.setPassword("123");
            adminService.updateCustomer(customer);

        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

      //delete customer success
        TestUtils.testSecondaryInfo("delete customer success");
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        System.out.println("\r\ndelete customer");
        adminService.deleteCustomer(1);
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        TestUtils.testSecondaryEnd();


       //get one customer by id--bad
        TestUtils.testSecondaryInfo("get one customer bad");
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        System.out.println("\r\nget one customer");

        try {

            Customer customer = adminService.getOneCustomer(1);
            customer.setId(50);
            adminService.getOneCustomer(50);

        } catch (Exception e) {
            System.out.println(e);

        }
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        TestUtils.testSecondaryEnd();

        //get one customer by id--success
        TestUtils.testSecondaryInfo("get one customer success ");
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        try {

            Customer customer = adminService.getOneCustomer(2);
            customer.setId(2);
            adminService.getOneCustomer(2);

        } catch (Exception e) {
            System.out.println(e);

        }
        System.out.println("\r\nget all customers");
        adminService.getAllCustomers().forEach(System.out::println);
        TestUtils.testSecondaryEnd();


    }

}
