package org.jeco.coupon_system_v2.app.repository;

import org.jeco.coupon_system_v2.app.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    /**
     * needs for add new company
     * @param email
     * @return
     */
    boolean existsByNameOrEmail(String name, String email);

    @Query(value = "select count(*) from companies where email=:email and not id =:id", nativeQuery = true)
    int countOfOtherCompanyByEmail(int id, String email);

    /**
     * for login check
     * @param email
     * @param password
     * @return
     */
    boolean existsByEmailAndPassword(String email,String password);


    /**
     * return logged in user
     * @param email
     * @param password
     * @return
     */
    Company getCompanyByEmailAndPassword(String email, String password);






}
