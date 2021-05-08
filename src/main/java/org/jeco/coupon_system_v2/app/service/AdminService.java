package org.jeco.coupon_system_v2.app.service;


import org.jeco.coupon_system_v2.app.beans.Company;
import org.jeco.coupon_system_v2.app.beans.Customer;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class AdminService extends ClientService {
    private final String email = "admin@admin.com";
    private final String password = "admin";

    @Override
    public boolean login(String email, String password) {

        return false;
    }

    public void addCompany(Company company) {
        if (isValidAddCompany(company)) {
            companyRepository.save(company);
            System.out.println("company added " + company);
            return;
        }
        System.err.println("company not added " + company);
    }

    // testing that company ,name,email not null
    //Name or email - the company not exist in the DB
    private boolean isValidAddCompany(Company company) {
        return company != null && company.getName() != null && company.getEmail() != null
                && !companyRepository.existsByNameOrEmail(company.getName(), company.getEmail());
    }


    public void updateCompany(Company company) {
        if (isValidUpdateCompany(company)) {
            companyRepository.saveAndFlush(company);
            System.out.println("company updated " + company);
            return;
        }
        System.err.println("company not updated " + company);
    }

    //1.testing that company,name,email not null
    //2.check findById in DB not null
    //3.name of company equals s name of DBCompany
    //4.email of company exists in other company (id != companyId)
    private boolean isValidUpdateCompany(Company company) {
        if (company != null && company.getName() != null && company.getEmail() != null) {
            Company dbCompany = companyRepository.getOne(company.getId());
            return dbCompany != null && company.getName().equals(dbCompany.getName()) &&
                    companyRepository.countOfOtherCompanyByEmail(company.getId(), company.getEmail()) == 0;
        }
        return false;
    }

    //delete company with her coupons
    public void deleteCompany(int companyID) {
        couponRepository.deleteByCompany(companyID);
        companyRepository.deleteById(companyID);
    }

    public List<Company> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        companies.forEach(
                c -> {
                    //set company coupons
                    c.setCoupons(couponRepository.findByCompany(c.getId()));
                });
        return companies;
    }

    public Company getOneCompany(int companyID) {
        Company company = companyRepository.getOne(companyID);
        company.setCoupons(couponRepository.findByCompany(companyID));
        return company;
    }

    public void addCustomer(Customer customer) {
        if (isValidAddCustomer(customer)) {
            customerRepository.save(customer);
            System.out.println("customer added " + customer);
            return;
        }
        System.err.println("customer not  added " + customer);

    }


    private boolean isValidAddCustomer(Customer customer) {
        //  1.check customer !=null
        //  2.check  customer email != null
        //  3.check customer not exists by email
        return customer != null && customer.getEmail() != null
                && !customerRepository.existsByEmail(customer.getEmail());
    }


    public void updateCustomer(Customer customer) {
        if (isValidUpdateCustomer(customer)) {
            customerRepository.saveAndFlush(customer);
            System.out.println("customer updated " + customer);
            return;
        }
        System.err.println("customer not updated " + customer);
    }

    // 1.check customer !=null
    // 2.check  customer email != null
    // 3. check findById in DB not null
    // 4. email of customer exists in other customer (id != customerId)
    public boolean isValidUpdateCustomer(Customer customer) {
        if (customer != null && customer.getEmail() != null) {
            Customer dbCustomer = customerRepository.getOne(customer.getId());
            return dbCustomer != null &&
                    customerRepository.countOfOtherCustomerByEmail(customer.getId(), customer.getEmail()) == 0;
        }
        return false;
    }

    public void deleteCustomer(int customerID) {
        customerRepository.deleteById(customerID);

    }

    public List<Customer> getAllCustomers() {
        return  customerRepository.findAll();

    }
//    public Company getOneCompany(int companyID) {
//        Company company = companyRepository.getOne(companyID);
//        company.setCoupons(couponRepository.findByCompany(companyID));
//        return company;
//    }

    public Customer getOneCustomer(int customerID) {
        Customer customer = customerRepository.getOne(customerID);
        //  customer.setCoupons(couponRepository.);
        return customer;
    }


}
