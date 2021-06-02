package org.jeco.coupon_system_v2.app.login;


import lombok.RequiredArgsConstructor;
import org.jeco.coupon_system_v2.app.exception.LoginException;
import org.jeco.coupon_system_v2.app.service.AdminService;
import org.jeco.coupon_system_v2.app.service.ClientService;
import org.jeco.coupon_system_v2.app.service.CompanyService;
import org.jeco.coupon_system_v2.app.service.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginManager {
    private final ApplicationContext ctx;

    private ClientService adminLogin(String email, String password) throws LoginException {
        AdminService adminService = ctx.getBean(AdminService.class);
        if (adminService.login(email, password)) {
            System.out.println("login admin success: " + email);
            return adminService;
        }
        System.out.println("login admin failed: " + email);
        return null;
    }

    private ClientService companyLogin(String email, String password)
            throws LoginException {
        CompanyService companyService =  ctx.getBean(CompanyService.class);
        if (companyService.login(email, password)) {
            System.out.println("login company success: " + email);
            return companyService;
        }
        System.out.println("login company failed: " + email);
        return null;
    }

    private ClientService customerLogin(String email, String password)
            throws LoginException {
       CustomerService customerService = ctx.getBean(CustomerService.class);
        if (customerService.login(email, password)) {
            System.out.println("login customer success: " + email);
            return customerService;
        }
        System.out.println("login customer failed: " + email);
        return null;
    }

    public ClientService login(String email, String password, ClientType clientTipe)
            throws LoginException {
        switch (clientTipe) {
            case ADMINISTRATOR:
                return adminLogin(email, password);
            case COMPANY:
                return companyLogin(email, password);
            case CUSTOMER:
                return customerLogin(email, password);
            default:
                throw new LoginException("LoginException: LoginType is incorrect");
        }

    }



}
