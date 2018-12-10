package com.padma.parus.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padma.parus.store.model.DiscountInfo;

@Repository
public interface DiscountInfoRepository extends JpaRepository<DiscountInfo, String>{

}
