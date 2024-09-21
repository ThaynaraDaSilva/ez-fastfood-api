package br.com.fiap.ez.fastfood.adapters.out.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.ez.fastfood.domain.model.Order;
import br.com.fiap.ez.fastfood.infrastructure.persistence.OrderEntity;

public interface JpaOrderRepository  extends JpaRepository<OrderEntity, Long> {
	

	@Query(nativeQuery = true, value = "SELECT * FROM EZ_FASTFOOD.ORDER WHERE ORDER_STATUS IN ('RECEIVED', 'IN_PREPARATION')")
	List<OrderEntity> listUnfinishedOrders();
	
	@Query(nativeQuery = true, value = "SELECT * FROM ez_fastfood.order WHERE DATE(order_time) = CURRENT_DATE ORDER BY order_time DESC LIMIT 1")
	OrderEntity  findLastOrder();
	

	
}