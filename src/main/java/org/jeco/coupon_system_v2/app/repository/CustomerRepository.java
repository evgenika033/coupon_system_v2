package org.jeco.coupon_system_v2.app.repository;

import org.jeco.coupon_system_v2.app.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByEmail(String email);

//    @Query(value = "select coupons_id customers_coupons from coupon_system2.customers_coupons where customer_id =:customerId",nativeQuery = true)
//    List<Integer>getPurchaseCouponsByCustomer(int customerId, String email);

    @Query(value = "select count(*) from coupon_system2.customers where email=:email and id !=:id", nativeQuery = true)
    int countOfOtherCustomerByEmail(int id, String email);
    


}
