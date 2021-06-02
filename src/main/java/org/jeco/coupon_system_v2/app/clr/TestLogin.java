package org.jeco.coupon_system_v2.app.clr;

import lombok.RequiredArgsConstructor;
import org.jeco.coupon_system_v2.app.login.ClientType;
import org.jeco.coupon_system_v2.app.login.LoginManager;
import org.jeco.coupon_system_v2.app.service.AdminService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

//@Component
@RequiredArgsConstructor
@Order(3)
public class TestLogin implements CommandLineRunner {

    private final LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {

        AdminService clientService = (AdminService) loginManager.login("admin@admin.com","admin", ClientType.ADMINISTRATOR);
        clientService.getAllCompanies().forEach(System.out::println);

//        CompanyService companyService= (CompanyService) loginManager.login("Test@test","test", ClientType.COMPANY);
//        System.out.println("company Details "+ companyService.getCompanyDetails());



    }
}
