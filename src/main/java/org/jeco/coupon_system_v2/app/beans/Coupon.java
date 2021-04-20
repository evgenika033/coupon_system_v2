package org.jeco.coupon_system_v2.app.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "coupons")
public class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//@ManyToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name="company_id")
	private int company;

	@Column(name = "category_id")
	@Enumerated(EnumType.ORDINAL)
	private Category category;

	private String title;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	private int amount;
	private double price;
	private String image;



}
