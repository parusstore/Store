package com.padma.parus.store.model;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import com.padma.parus.store.annotation.NullOrNotBlank;
import com.padma.parus.store.model.enums.OrderState;
import com.parus.store.audit.DateAudit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="customerorder")
@Table
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrder  extends DateAudit{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "orderId")
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String orderId;
	
	@Column(name = "amount", unique = false)
	//@NullOrNotBlank(message = "amount cannot be null")
	private float amount;
	
	@Column(name = "quantity", unique = false)
	//@NullOrNotBlank(message = "quantity cannot be null")
	private float quantity;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "orderState", unique = false)
	//@NullOrNotBlank(message = "orderState cannot be null")
	private OrderState orderState;


	@Column(name = "userName", unique = false)
	//@NullOrNotBlank(message = "userName cannot be null")
	private String userName;
	
	@Column(name = "addressId", unique = false)
	//@NullOrNotBlank(message = "addressId cannot be null")
	private String addressId;
	
	@ManyToOne
	@JoinColumn(name="paymentId")
	private PaymentInfo paymentInfo;
	
	@ManyToOne
	@JoinColumn(name = "productId")
	//@NullOrNotBlank(message = "productId cannot be null")
	private ProductInfo productInfo;
	

}
