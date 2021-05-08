package org.jeco.coupon_system_v2.app.repository;

import org.jeco.coupon_system_v2.app.beans.Category;
import org.jeco.coupon_system_v2.app.beans.Company;
import org.jeco.coupon_system_v2.app.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon,Integer> {
    List<Coupon>findByCompany(int company);
    void deleteByCompany(int company);
    List<Coupon>findByCompanyAndCategory(int company, Category category);
    List<Coupon>findByCompanyAndPriceLessThan(int company,double price);

    
    
    @Query(value = "select count(*) from coupons where title=:title and not id =:id", nativeQuery = true)
    int countOfOtherCouponByTitle(int id,String title);

    boolean existsByTitle(String title);

    boolean existsByCompanyAndTitleAndIdIsNot(int company, String title, int id);



}
