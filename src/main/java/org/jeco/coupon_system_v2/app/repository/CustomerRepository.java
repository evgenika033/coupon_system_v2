package org.jeco.coupon_system_v2.app.repository;

import org.jeco.coupon_system_v2.app.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository <Customer,Integer>{
}
