package org.jeco.coupon_system_v2.app.repository;

import org.jeco.coupon_system_v2.app.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    boolean existsByNameOrEmail(String name, String email);

    @Query(value = "select count(*) from companies where email=:email and not id =:id", nativeQuery = true)
    int countOfOtherCompanyByEmail(int id, String email);


}
