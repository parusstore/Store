package com.padma.parus.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padma.parus.store.model.ProductInfo;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String>{

}
