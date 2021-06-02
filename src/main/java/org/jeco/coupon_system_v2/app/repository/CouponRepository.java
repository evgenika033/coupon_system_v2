package org.jeco.coupon_system_v2.app.repository;

import org.jeco.coupon_system_v2.app.beans.Category;
import org.jeco.coupon_system_v2.app.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface CouponRepository extends JpaRepository<Coupon,Integer> {
    List<Coupon>findByCompany(int company);
    void deleteByCompany(int company);
    List<Coupon>findByCompanyAndCategory(int company, Category category);
    List<Coupon>findByCompanyAndPriceLessThan(int company,double price);




    boolean existsByTitle(String title);

    /**
     * for check existing company coupons with the same title and other id
     * @param company
     * @param title
     * @param id
     * @return
     */
    boolean existsByCompanyAndTitleAndIdIsNot(int company, String title, int id);



    /**
     * get list Coupons id by customerID from purchaseCoupons
     * @param customerID
     * @return
     */
    @Query(value = "select `coupons_id` from customers_coupons where `customers_id` = :customerID", nativeQuery = true)
    List<Integer> getCustomerCoupons(int customerID);

    /**
     * get list Customers id by couponID from purchaseCoupons
     * @param couponID
     * @return
     */
    @Query(value = "select `customers_id` from customers_coupons where `coupons_id` = :couponID", nativeQuery = true)
    List<Integer> getPurchaseCouponByCouponID(int couponID);

    /**
     * delete purchase coupon
     * @param customerID
     * @param couponID
     */
    @Query(value="DELETE FROM `customers_coupons` WHERE `customers_id`= :customerID and `coupons_id` = :couponID", nativeQuery = true)
    @Modifying
    void deletePurchaseCoupon(int customerID, int couponID);

    /**
     * add purchase coupon
     * @param customerID
     * @param couponID
     */
    @Query(value="INSERT INTO `customers_coupons` (`customers_id`,`coupons_id`)VALUES(:customerID, :couponID)", nativeQuery = true)
    @Modifying
    void addPurchaseCoupon(int customerID,int couponID);

    @Query(value = "select * from `coupons` as c join `customers_coupons` as cc on c.id=cc.coupons_id where cc.customers_id=:customerID and c.category_id=:categoryID", nativeQuery = true)
    List<Coupon> getCustomerCoupons(int categoryID, int customerID);

    @Query(value = "select * from `coupons` as c join `customers_coupons` as cc on c.id=cc.coupons_id where cc.customers_id=:customerID and c.price <= :maxPrice", nativeQuery = true)
    List<Coupon> getCustomerCoupons(double maxPrice, int customerID);
}
