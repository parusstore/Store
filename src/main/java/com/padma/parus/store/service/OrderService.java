package com.padma.parus.store.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.css.CSSUnknownRule;

import com.padma.parus.store.model.CustomerOrder;
import com.padma.parus.store.model.enums.OrderState;
import com.padma.parus.store.payload.OrderRequest;
import com.padma.parus.store.repository.CustomeOrderRepository;
import com.padma.parus.store.repository.ProductInfoRepository;

@Service
public class OrderService {
	
	@Autowired
	private CustomeOrderRepository customeOrderRepository;
	
	@Autowired
	private ProductInfoRepository productInfoRepository;
	

	public Boolean addOrder(float amount, String userName, @Valid OrderRequest orderRequest) {
		// TODO Auto-generated method stub
		CustomerOrder customerOrder =  new CustomerOrder();
		customerOrder.setAddressId(orderRequest.getAddressId());
		customerOrder.setAmount(amount);
		customerOrder.setCreatedAt(Instant.now());
		customerOrder.setOrderState(OrderState.Initiated);
		customerOrder.setPaymentInfo(null);
		customerOrder.setProductInfo(productInfoRepository.findById(orderRequest.getProductId()).get());
		//customerOrder.setProductId(orderRequest.getProductId());
		customerOrder.setQuantity(Float.parseFloat(orderRequest.getQuantity()));
		customerOrder.setUpdatedAt(Instant.now());
		customerOrder.setUserName(userName);
		customeOrderRepository.save(customerOrder);	
		return true;
	}
	
	public Optional<CustomerOrder> findbyProductIdAndUserName(String productId,String userName)
	{
		return customeOrderRepository.findByProductIdAndUserName(productInfoRepository.findById(productId).get(), userName);
	}
	


	public Boolean updateOrder(CustomerOrder customerOrder, float amount, String quantity) {
		customerOrder.setAmount(amount);
		customerOrder.setQuantity(Float.parseFloat(quantity));
		customeOrderRepository.save(customerOrder);
		return true;
		
	}

	public  List<CustomerOrder> findbyUserName(String userName) {
		return customeOrderRepository.findByUserName(userName);
	}
	public  List<CustomerOrder> findByIncompleteOrderByUsername(String userName,OrderState orderState)
	{
		return customeOrderRepository.findByIncompleteOrderByUsername(userName, orderState);
	}
	

	public CustomerOrder findByOrderIdAndUserName(String orderid, String userName) {
		// TODO Auto-generated method stub
		return customeOrderRepository.findByOrderIdAndUserName(orderid, userName).get();
	}
	
	public List<CustomerOrder> findUserOrdersCancelledAndCmpleted(String userName,OrderState completed,OrderState Cancelled)
	{
		return customeOrderRepository.findUserOrdersCancelledAndCmpleted(userName,completed,Cancelled);
	}

	public Boolean cancelOrder(CustomerOrder customerOrder) {
		// TODO Auto-generated method stub
		customerOrder.setOrderState(OrderState.Cancelled);
		customeOrderRepository.save(customerOrder);
		return true;
	}
	

}
