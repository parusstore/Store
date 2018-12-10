package com.padma.parus.store.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.padma.parus.store.model.CustomerOrder;
import com.padma.parus.store.model.PaymentInfo;
import com.padma.parus.store.model.enums.OrderState;
import com.padma.parus.store.model.enums.PaymentState;
import com.padma.parus.store.model.enums.paymenyType;
import com.padma.parus.store.payload.ApiResponse;
import com.padma.parus.store.repository.CustomeOrderRepository;
import com.padma.parus.store.service.GenericService;
import com.padma.parus.store.service.OrderService;
import com.padma.parus.store.service.PaymentService;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/payment")
public class PaymentController {
	
	@Autowired
	private GenericService genericService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/checkout/cashondelivery")
	@PreAuthorize("hasRole('USER')")
	@ApiOperation(value = "checkout api which returns all the data for order")
	public ResponseEntity<?> checkout(Authentication auth)
	{
		String userName= genericService.getUsernameFromToken(auth);
		List <CustomerOrder> orders= orderService.findByIncompleteOrderByUsername(userName, OrderState.Initiated);
		if(orders.size() <=0)
		{
			return ResponseEntity.ok(new ApiResponse("There aer no orders to update", false));
		}
		paymentService.updatePaymentInfoForOrder(orders);
		paymentService.getCheckoutDetails(orders);	
		return ResponseEntity.ok(new ApiResponse("Orders has been updated succesfully", true)); 
	}

}
