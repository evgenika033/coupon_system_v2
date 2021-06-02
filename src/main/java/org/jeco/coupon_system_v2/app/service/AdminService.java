package org.jeco.coupon_system_v2.app.service;


import org.jeco.coupon_system_v2.app.beans.Company;
import org.jeco.coupon_system_v2.app.beans.Customer;
import org.jeco.coupon_system_v2.app.exception.CompanyException;
import org.jeco.coupon_system_v2.app.exception.CustomerException;
import org.jeco.coupon_system_v2.app.exception.LoginException;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@Scope("prototype")
public class AdminService extends ClientService {


    @Override
    public boolean login(String email, String password) throws LoginException {
        final String email1 = "admin@admin.com";
        final String password1 = "admin";
        if (email == email1 && password == password1) {
            return true;
        }
        throw new LoginException("login exception: login failed");
    }

    public void addCompany(Company company) throws CompanyException {
        if (isValidAddCompany(company)) {
            companyRepository.save(company);
            System.out.println("company added " + company);
            return;
        }
        System.out.println("company not added " + company);
        throw new CompanyException("add company Exception: company not valid ");
    }

    // testing that company ,name,email not null
    //Name or email - the company not exist in the DB
    private boolean isValidAddCompany(Company company) {
        return company != null && company.getName() != null && company.getEmail() != null
                && !companyRepository.existsByNameOrEmail(company.getName(), company.getEmail());
    }


    public void updateCompany(Company company) throws CompanyException {
        if (isValidUpdateCompany(company)) {
            companyRepository.saveAndFlush(company);
            System.out.println("company updated " + company);
            return;
        }
        System.out.println("company not updated " + company);
        throw new CompanyException("update company Exception: company not valid ");
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
    public void deleteCompany(int companyID) throws CompanyException {
        try {
            System.out.println("delete company: delete coupon history");
            couponRepository.findByCompany(companyID).forEach(c -> deleteCouponHistory(c.getId()));
            System.out.println("delete company: delete coupons ");
            couponRepository.deleteByCompany(companyID);
            System.out.println("delete company");
            companyRepository.deleteById(companyID);
        } catch (
                EmptyResultDataAccessException e) {
            throw new CompanyException("delete company exception: company id not found");
        }
    }


    // delete coupon purchase  history
    private void deleteCouponHistory(int couponID) {
        couponRepository.getPurchaseCouponByCouponID(couponID).forEach(cust -> {
            couponRepository.deletePurchaseCoupon(cust, couponID);
        });
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

    public Company getOneCompany(int companyID) throws CompanyException {
        Company company = companyRepository.findById(companyID).orElseThrow(() -> new CompanyException("get company exception: company not found"));
        company.setCoupons(couponRepository.findByCompany(companyID));
        return company;


    }

    public void addCustomer(Customer customer) throws CustomerException {
        if (isValidAddCustomer(customer)) {
            customerRepository.save(customer);
            System.out.println("customer added " + customer);
            return;
        }
        System.out.println("customer not  added " + customer);
        throw new CustomerException("add customer Exception: customer not valid ");

    }


    private boolean isValidAddCustomer(Customer customer) {
        //  1.check customer !=null
        //  2.check  customer email != null
        //  3.check customer not exists by email
        return customer != null && customer.getEmail() != null
                && !customerRepository.existsByEmail(customer.getEmail());
    }


    public void updateCustomer(Customer customer) throws CustomerException {
        if (isValidUpdateCustomer(customer)) {
            customerRepository.saveAndFlush(customer);
            System.out.println("customer updated " + customer);
            return;
        }
        System.out.println("customer not updated " + customer);
        throw new CustomerException("update customer Exception:update customer not success ");
    }

    // 1.check customer !=null
    // 2.check  customer email != null
    // 3. check findById in DB not null
    // 4. email of customer exists in other customer (id != customerId)
    public boolean isValidUpdateCustomer(Customer customer) {
        if (customer != null && customer.getEmail() != null) {
            Customer dbCustomer = customerRepository.getOne(customer.getId());
            return dbCustomer != null &&
                    !customerRepository.existsByEmailAndIdIsNot(customer.getEmail(), customer.getId());
        }
        return false;
    }

    public void deleteCustomer(int customerID) throws CustomerException {
        try {
            System.out.println("delete customer: delete customer coupons");
            deleteCustomerCoupons(customerID);
            System.out.println("delete customer");
            customerRepository.deleteById(customerID);
        } catch (Exception e) {
            throw new CustomerException("delete customer exception: customer not found");
        }
    }

    private void deleteCustomerCoupons(int customerID) {
        couponRepository.getCustomerCoupons(customerID).forEach(cId -> couponRepository.deletePurchaseCoupon(customerID, cId));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();

    }


    public Customer getOneCustomer(int customerID) throws CustomerException {
        return customerRepository.findById(customerID)
                .orElseThrow(() -> new CustomerException("get customer exception: customer not found"));


    }


}
