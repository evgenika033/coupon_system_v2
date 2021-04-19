package org.jeco.coupon_system_v2.app.beans;

import lombok.*;

import javax.persistence.*;
import java.util.List;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "companies")
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String password;
	@Singular
	@OneToMany(cascade = CascadeType.ALL)
	private List<Coupon> coupons;



}
