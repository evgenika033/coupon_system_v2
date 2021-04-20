package org.jeco.coupon_system_v2.app.service;


import org.jeco.coupon_system_v2.app.beans.Company;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;


@Service
public class AdminService extends ClientService {
    private final String email = "admin@admin.com";
    private final String password = "admin";

    @Override
    public boolean login(String email, String password) {
        return false;
    }

    public void addCompany(Company company) {
        companyRepository.save(company);
    }
}
