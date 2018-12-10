package com.padma.parus.store.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.padma.parus.store.model.CustomerOrder;
import com.padma.parus.store.model.PaymentInfo;
import com.padma.parus.store.model.enums.OrderState;
import com.padma.parus.store.model.enums.PaymentState;
import com.padma.parus.store.model.enums.paymenyType;
import com.padma.parus.store.repository.CustomeOrderRepository;
import com.padma.parus.store.repository.PaymentInfoRepository;

@Service
public class PaymentService {

	@Autowired
	private CustomeOrderRepository customeOrderRepository;
	
	@Autowired
	private PaymentInfoRepository paymentInfoRepository;
	
	public void getCheckoutDetails(List<CustomerOrder> orders) 
	{
		// TODO Auto-generated method stub
		float totalAmount = (float) 0.0;
		for (CustomerOrder eachorder : orders) 
		{
			totalAmount = totalAmount+eachorder.getAmount();
		}
		
	}

	public Boolean updatePaymentInfoForOrder(List<CustomerOrder> orders) {
		// TODO Auto-generated method stub
		PaymentInfo paymentInfo = new PaymentInfo();
		paymentInfo.setCreatedAt(Instant.now());
		paymentInfo.setUpdatedAt(Instant.now());
		paymentInfo.setPaymentState(PaymentState.Completed);
		paymentInfo.setPaymentType(paymenyType.Cash);
		paymentInfo.setPaymentData("Cash type hence no data");
		paymentInfoRepository.save(paymentInfo);
		for (CustomerOrder customerOrder : orders) {
			customerOrder.setPaymentInfo(paymentInfo);
			customerOrder.setOrderState(OrderState.Completed);
		}
		customeOrderRepository.saveAll(orders);
		return true;
		
	}
	
	

}
