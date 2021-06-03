package org.jeco.coupon_system_v2.app.jobs;

import lombok.RequiredArgsConstructor;
import org.jeco.coupon_system_v2.app.beans.Coupon;
import org.jeco.coupon_system_v2.app.exception.CouponException;
import org.jeco.coupon_system_v2.app.repository.CouponRepository;
import org.jeco.coupon_system_v2.app.service.CompanyService;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(2)
public class Job {
    private final CouponRepository couponRepository;
    private final CompanyService companyService;

    @Scheduled(fixedRate =86400000)//1000*60*60*24
    private void jobNow(){
        System.out.println("************  delete old coupons job  ************");


        List<Coupon> coupons = couponRepository.findCouponsByEndDateLessThan(LocalDate.now());
        for (Coupon coupon:coupons) {
            try {
                companyService.deleteCoupon(coupon.getId());
            } catch (CouponException e) {
                e.printStackTrace();
            }
        }
        System.out.println("old coupons deleted\r\n******************************************************");
    }
}
