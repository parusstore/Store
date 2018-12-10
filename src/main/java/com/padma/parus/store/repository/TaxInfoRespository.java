package com.padma.parus.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padma.parus.store.model.TaxInfo;

@Repository
public interface TaxInfoRespository extends JpaRepository<TaxInfo, String>{

}
