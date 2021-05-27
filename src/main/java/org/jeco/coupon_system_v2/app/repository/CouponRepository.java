package org.jeco.coupon_system_v2.app.repository;

import org.jeco.coupon_system_v2.app.beans.Category;
import org.jeco.coupon_system_v2.app.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

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

    @Query(value = "select `coupons_id` from customers_coupons where `customers_id` = :customerID", nativeQuery = true)
    List<Integer> getCustomerCoupons(int customerID);

    @Query(value = "select `customers_id` from customers_coupons where `coupons_id` = :couponID", nativeQuery = true)
    List<Integer> getPurchaseCouponByCouponID(int couponID);

    @Query(value="DELETE FROM `customers_coupons` WHERE `customers_id`= :customerID and `coupons_id` = :couponID", nativeQuery = true)
    @Transactional
    @Modifying
    void deletePurchaseCoupon(int customerID, int couponID);

}
