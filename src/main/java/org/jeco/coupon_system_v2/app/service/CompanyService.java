package org.jeco.coupon_system_v2.app.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.jeco.coupon_system_v2.app.beans.Category;
import org.jeco.coupon_system_v2.app.beans.Company;
import org.jeco.coupon_system_v2.app.beans.Coupon;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Scope("prototype")
@Data
public class CompanyService extends ClientService {
    private int companyId;


    @Override
    public boolean login(String email, String password) {
        return false;
    }

    public void addCoupon(Coupon coupon) {
        // testing that company not null
        // Testing the coupon title not the same
        // Coupon title  can be the same in different company
        if (isValidAddCoupon(coupon)) {
            couponRepository.save(coupon);
            System.out.println("coupon added " + coupon);
            return;
        }


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
        System.err.println("coupon not updated " + coupon);
    }

    // testing that coupon not null
    // validate that company id is not 0
    // can not update coupon id: get coupon from DB(existsById)=true
    // can not update company id: coupon.comp==companyId
    // check for title in other coupons on current company
    private boolean isValidUpdateCoupon(Coupon coupon) {
        if (coupon != null && coupon.getCompany() > 0 && coupon.getTitle() != null
                && couponRepository.existsById(coupon.getId())
                && coupon.getCompany() == companyId
                && !couponRepository.existsByCompanyAndTitleAndIdIsNot(coupon.getCompany(), coupon.getTitle(), coupon.getId())) {
            return true;
        }
        return false;
    }

    public void deleteCoupon(int couponID) {
        // TODO: delete coupon history
        couponRepository.deleteById(couponID);

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
        Company company=companyRepository.getOne(companyId);
        //company.setCoupons( getCompanyCoupons());
        return company;
    }


}
