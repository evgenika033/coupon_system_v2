package org.jeco.coupon_system_v2.app.repository;

import org.jeco.coupon_system_v2.app.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon,Integer> {
}
