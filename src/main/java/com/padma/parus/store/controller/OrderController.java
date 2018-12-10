package com.padma.parus.store.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.padma.parus.store.exception.AddOrderException;
import com.padma.parus.store.model.CustomerOrder;
import com.padma.parus.store.model.ProductInfo;
import com.padma.parus.store.model.enums.OrderState;
import com.padma.parus.store.payload.ApiResponse;
import com.padma.parus.store.payload.OrderRequest;
import com.padma.parus.store.repository.ProductInfoRepository;
import com.padma.parus.store.service.GenericService;
import com.padma.parus.store.service.OrderService;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	private GenericService genericService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductInfoRepository productInfoRepository;
	
	private float MAX_QUANTITY_VALUE= 10;
	
	@ApiOperation(value = "Add an item to the cart")
	@PostMapping("/addtocart")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> addToCart(Authentication auth,@Valid @RequestBody OrderRequest orderRequest)
	{
		//Danger: Consider all exceptions that might actually occur here
		String userName = genericService.getUsernameFromToken(auth);
		Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(orderRequest.getProductId());
		productInfoOptional.orElseThrow(() -> new AddOrderException(
				"Missing Product in database"));
		if(Float.parseFloat(orderRequest.getQuantity()) <= 0 || Float.parseFloat(orderRequest.getQuantity()) > 10)
		{
			throw new AddOrderException(
					"Quantity should be between 0 and 10. Unit is in KG."); 
		}
		float amount = Float.parseFloat(orderRequest.getQuantity()) * productInfoOptional.get().getUnitPrice();
		Optional<CustomerOrder> customerOrder = orderService.findbyProductIdAndUserName(orderRequest.getProductId(), userName);
		if(customerOrder.isPresent())
		{
			orderService.updateOrder(customerOrder.get(),amount,orderRequest.getQuantity());
		}
		else
		{
		//Danger: check if addressid belongs to user this si also very important only then place the order finally
			orderService.addOrder(amount,userName,orderRequest);
		}
		return ResponseEntity.ok(new ApiResponse("Item has been added to the cart succesfully", true)); 
	}
	
	@ApiOperation(value = "get all pending orders in cart")
	@GetMapping("/pendingorders")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<CustomerOrder>> getAllPendingOrders(Authentication auth)
	{
		String userName = genericService.getUsernameFromToken(auth);
		return ResponseEntity.ok(orderService.findByIncompleteOrderByUsername(userName,OrderState.Initiated));
	}
	
	@ApiOperation(value = "get all cancelled and completed orders in user account")
	@GetMapping("/userorders")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<CustomerOrder>> getAllUserOrdersCancelledAndCmpleted(Authentication auth)
	{
		String userName = genericService.getUsernameFromToken(auth);
		return ResponseEntity.ok(orderService.findUserOrdersCancelledAndCmpleted(userName,OrderState.Cancelled,OrderState.Completed));
	}
	
	@ApiOperation(value = "find order by id")
	@GetMapping("/findorder/{orderid}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> findorder(Authentication auth,@PathVariable("orderid") String orderid)
	{
		String userName = genericService.getUsernameFromToken(auth);
		CustomerOrder customerOrder= orderService.findByOrderIdAndUserName(orderid,userName);
		return ResponseEntity.ok(customerOrder); 
	}
	
	@ApiOperation(value = "cancel order by id")
	@GetMapping("/cancelorder/{orderid}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> cancelorder(Authentication auth,@PathVariable("orderid") String orderid)
	{
		String userName = genericService.getUsernameFromToken(auth);
		CustomerOrder customerOrder= orderService.findByOrderIdAndUserName(orderid,userName);
		orderService.cancelOrder(customerOrder);
		return ResponseEntity.ok(new ApiResponse("Order has been cancelled",true)); 
	}
	

}
