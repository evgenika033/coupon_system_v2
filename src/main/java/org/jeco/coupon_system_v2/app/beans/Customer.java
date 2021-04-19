package org.jeco.coupon_system_v2.app.beans;

import lombok.*;

import javax.persistence.*;
import java.util.List;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "customers")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	@Singular
	@ManyToMany()
	@JoinTable(name = "customer_vs_coupons",joinColumns = @JoinColumn(name = "costomer_id"),inverseJoinColumns = @JoinColumn(name = "coupon_id"))
	private List<Coupon> coupons;



}
