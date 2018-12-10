package com.padma.parus.store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.padma.parus.store.model.ProductInfo;
import com.padma.parus.store.repository.ProductInfoRepository;

@Service
public class ProductInfoService {

	@Autowired
	private ProductInfoRepository productInfoRepository;
	
	public List<ProductInfo> getAllProductsInfo()
	{
		return productInfoRepository.findAll();
	}
	
	public Optional<ProductInfo> findById(String productId)
	{
		return productInfoRepository.findById(productId);
	}
	
}
