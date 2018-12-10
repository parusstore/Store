package com.padma.parus.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.padma.parus.store.model.CustomerOrder;
import com.padma.parus.store.model.ProductInfo;
import com.padma.parus.store.service.ProductInfoService;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api")
public class ProductInfoController {

	@Autowired
	private ProductInfoService productInfoService;
	
	@ApiOperation(value = "Checks if the given email is in use")
	@GetMapping("/info")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<?> getAllProductsInfo()
	{
		return ResponseEntity.ok(productInfoService.getAllProductsInfo());
	}
	
	
	@ApiOperation(value = "find product by id")
	@GetMapping("/findproduct/{productId}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> findorder(Authentication auth,@PathVariable("productId") String productId)
	{
		
		return ResponseEntity.ok(productInfoService.findById(productId).get()); 
	}
}
