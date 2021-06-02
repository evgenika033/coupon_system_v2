package org.jeco.coupon_system_v2.app.service;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.jeco.coupon_system_v2.app.beans.Category;
import org.jeco.coupon_system_v2.app.beans.Company;
import org.jeco.coupon_system_v2.app.beans.Coupon;
import org.jeco.coupon_system_v2.app.exception.CouponException;
import org.jeco.coupon_system_v2.app.exception.LoginException;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@Service
@Scope("prototype")
@Data
public class CompanyService extends ClientService {
    private int companyId;


    @Override
    public boolean login(String email, String password) throws LoginException {
        if(companyRepository.existsByEmailAndPassword(email, password)){
          setCompanyId(companyRepository.getCompanyByEmailAndPassword(email, password).getId());
            return true;
        }
        throw new LoginException("login exception: login failed");

    }



    public void addCoupon(Coupon coupon) throws CouponException {
        // testing that company not null
        // Testing the coupon title not the same
        // Coupon title  can be the same in different company
        if (isValidAddCoupon(coupon)) {
            couponRepository.save(coupon);
            System.out.println("coupon added " + coupon);
            return;


        }
        System.out.println("coupon not added " + coupon);
        throw new CouponException("add coupon Exception: coupon is not valid ");

    }

    private boolean isValidAddCoupon(Coupon coupon) {
        return coupon != null && coupon.getTitle() != null && !couponRepository.existsByTitle(coupon.getTitle());
    }


    public void updateCoupon(Coupon coupon) {
        if (isValidUpdateCoupon(coupon)) {
            couponRepository.saveAndFlush(coupon);
            System.out.println("coupon updated " + coupon);
            return;
        }
        System.out.println("coupon not updated " + coupon);
    }

    // testing that coupon not null
    // validate that company id is not 0
    // can not update coupon id: get coupon from DB(existsById)=true
    // can not update company id: coupon.comp==companyId
    // check for title in other coupons on current company
    private boolean isValidUpdateCoupon(Coupon coupon) {
        return coupon != null && coupon.getCompany() > 0 && coupon.getTitle() != null
                && couponRepository.existsById(coupon.getId())
                && coupon.getCompany() == companyId
                && !couponRepository.existsByCompanyAndTitleAndIdIsNot(coupon.getCompany(), coupon.getTitle(), coupon.getId());
    }

    public Coupon getOneCoupon(int couponId) throws CouponException {
        return couponRepository.findById(couponId).orElseThrow(()->new CouponException("get coupon exception: coupon id not found"));
    }

    public void deleteCoupon(int couponID) throws CouponException {
        try {
            // delete coupon purchase  history
            deleteCouponHistory(couponID);
            //delete coupon
            couponRepository.deleteById(couponID);
        }catch (EmptyResultDataAccessException e){
            throw new CouponException("delete coupon exception: coupon id not found");
        }

    }
    // delete coupon purchase  history
    private void deleteCouponHistory( int couponID){
        couponRepository.getPurchaseCouponByCouponID(couponID).forEach(cust->{
            couponRepository.deletePurchaseCoupon(cust,couponID);
        });
    }

    public List<Coupon> getCompanyCoupons() {
        return couponRepository.findByCompany(companyId);
    }

    public List<Coupon> getCompanyCoupons(Category category) {
        return couponRepository.findByCompanyAndCategory(companyId,category);

    }

    public List<Coupon> getCompanyCoupons(double maxPrice) {
        return couponRepository.findByCompanyAndPriceLessThan(companyId,maxPrice);
    }

    public Company getCompanyDetails() {
        return companyRepository.getOne(companyId);
    }


}
