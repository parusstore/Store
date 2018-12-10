package com.padma.parus.store.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.padma.parus.store.annotation.NullOrNotBlank;
import com.parus.store.audit.DateAudit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="productInfo")
@Table
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo  extends DateAudit{
	
	@Id
	@Column(name = "productId")
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String productId;
	
	@Column(name = "productName", unique = true)
	@NullOrNotBlank(message = "productName cannot be null")
	private String productName;
	
	@Column(name = "productDescription", unique = false)
	@NullOrNotBlank(message = "productDescription cannot be null")
	private String productDescription;
	
	@Column(name = "unitPrice", unique = false)
	@NullOrNotBlank(message = "unitPrice cannot be null")
	private float unitPrice;
	
	@Column(name = "productImageUrls", unique = false)
	@NullOrNotBlank(message = "productImageUrls cannot be null")
	private String productImageUrls;
	
	@Column(name = "isStockOut", unique = false)
	@NullOrNotBlank(message = "isStockOut cannot be null")
	private float isStockOut;
	
	@Column(name = "availableUnits", unique = false)
	@NullOrNotBlank(message = "availableUnits cannot be null")
	private String availableUnits;
	
	@ManyToOne
	@JoinColumn(name="categoryId")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="discountId")
	private DiscountInfo discountInfo;
	
	@OneToMany(mappedBy="productInfo",cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<CustomerOrder> orders;
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "productinfo_tax_map", joinColumns = {
			@JoinColumn(name = "productId", referencedColumnName = "productId") }, inverseJoinColumns = {
					@JoinColumn(name = "taxId", referencedColumnName = "taxId") })
	private Set<TaxInfo> applicableTaxes = new HashSet<>();

}
