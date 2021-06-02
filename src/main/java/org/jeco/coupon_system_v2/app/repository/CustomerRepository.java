package org.jeco.coupon_system_v2.app.repository;

import org.jeco.coupon_system_v2.app.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    /**
     * needs for add new customer
     * @param email
     * @return
     */
    boolean existsByEmail(String email);

//    @Query(value = "select coupons_id customers_coupons from coupon_system2.customers_coupons where customer_id =:customerId",nativeQuery = true)
//    List<Integer>getPurchaseCouponsByCustomer(int customerId, String email);

//    @Query(value = "select count(*) from coupon_system2.customers where email=:email and id !=:id", nativeQuery = true)
//    int countOfOtherCustomerByEmail(int id, String email);

    /**
     * for update customer check
     * for check existing customer  with the same email and other id
     * @param email
     * @param id
     * @return
     */
    boolean existsByEmailAndIdIsNot( String email,int id);

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
    Customer getCustomerByEmailAndPassword(String email,String password);

}
