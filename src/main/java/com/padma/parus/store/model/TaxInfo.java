package com.padma.parus.store.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.padma.parus.store.annotation.NullOrNotBlank;
import com.padma.parus.store.model.enums.TaxType;
import com.parus.store.audit.DateAudit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="taxInfo")
@Table
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaxInfo  extends DateAudit{
	
	@Id
	@Column(name = "taxId")
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String taxId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "taxtype", unique = false)
	@NullOrNotBlank(message = "taxtype cannot be null")
	private TaxType taxtype;
	
	@Column(name = "taxPercent", unique = false)
	@NullOrNotBlank(message = "taxPercent cannot be null")
	private int taxPercent;
	
	@Column(name = "description", unique = false)
	@NullOrNotBlank(message = "description cannot be null")
	private String description;

	@ManyToMany(mappedBy = "applicableTaxes", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<ProductInfo> applicableProducts = new HashSet<>();
}
