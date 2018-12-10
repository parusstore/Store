package com.padma.parus.store.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.padma.parus.store.annotation.NullOrNotBlank;
import com.padma.parus.store.model.enums.DiscountType;
import com.parus.store.audit.DateAudit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="discountinfo")
@Table
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountInfo  extends DateAudit{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "discountId")
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String discountId;
	
	@Column(name = "discounString", unique = true)
	@NullOrNotBlank(message = "discounString cannot be null")
	private String discounString;
	
	@Column(name = "description", unique = false)
	@NullOrNotBlank(message = "description cannot be null")
	private String description;
	
	@Column(name = "percent", unique = false)
	@NullOrNotBlank(message = "percent cannot be null")
	private int percent;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "discountType", unique = false)
	@NullOrNotBlank(message = "discountType cannot be null")
	private DiscountType discountType;

	@OneToMany(mappedBy="discountInfo",cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<ProductInfo> producInfos;
}
