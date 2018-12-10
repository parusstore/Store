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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.padma.parus.store.annotation.NullOrNotBlank;
import com.padma.parus.store.model.enums.PaymentState;
import com.padma.parus.store.model.enums.paymenyType;
import com.parus.store.audit.DateAudit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="paymentinfo")
@Table
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInfo  extends DateAudit{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "paymentId")
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String paymentId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "paymentState", unique = false)
	//@NullOrNotBlank(message = "paymentstate cannot be null")
	private PaymentState paymentState;
	
	@Column(name = "paymentData", unique = false)
	//@NullOrNotBlank(message = "paymentData cannot be null")
	private String paymentData;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "paymentType", unique = false)
	//@NullOrNotBlank(message = "paymentType cannot be null")
	private paymenyType paymentType;
	
	@OneToMany(mappedBy="paymentInfo",cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<CustomerOrder>  orders;
	

}
