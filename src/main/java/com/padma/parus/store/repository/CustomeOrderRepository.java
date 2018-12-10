package com.padma.parus.store.repository;

import org.hibernate.id.uuid.CustomVersionOneStrategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.padma.parus.store.model.CustomerOrder;
import com.padma.parus.store.model.ProductInfo;
import com.padma.parus.store.model.enums.OrderState;

import java.lang.String;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomeOrderRepository extends JpaRepository<CustomerOrder, String>{
	
	@Query("select o from customerorder o where o.productInfo=:productInfo and o.userName=:userName")
	Optional<CustomerOrder> findByProductIdAndUserName(@Param("productInfo")ProductInfo productInfo,@Param("userName")String userName);
	
	@Query("select o from customerorder o where o.orderId=:orderId and o.userName=:userName")
	Optional<CustomerOrder> findByOrderIdAndUserName(@Param("orderId")String orderId,@Param("userName")String userName);
	
	@Query("select o from customerorder o where o.orderState=:orderState and o.userName=:userName")
	List<CustomerOrder> findByIncompleteOrderByUsername(@Param("userName")String userName,@Param("orderState")OrderState orderState);
	
	@Query("select o from customerorder o where (o.orderState=:completed or o.orderState=:cancelled) and o.userName=:userName")
	List<CustomerOrder> findUserOrdersCancelledAndCmpleted(@Param("userName")String userName,@Param("completed")OrderState completed,@Param("cancelled")OrderState cancelled);
	
	List<CustomerOrder> findByUserName(String username);

}
