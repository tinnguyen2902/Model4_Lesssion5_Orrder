package com.example.lession5_order.repository;

import com.example.lession5_order.Model.DTO.OrderSummary;
import com.example.lession5_order.Model.Entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Tìm chính xác theo trạng thái
    List<Order> findByStatus(String status);

    // Tìm theo tên khách hàng có chứa từ khóa (LIKE %name%)
    List<Order> findByCustomerNameContaining(String name);
    //LS4 truy vấn vip
    @Query("SELECT o FROM Order o WHERE o.totalPrice > (SELECT AVG(o2.totalPrice) FROM Order o2)")
    List<Order> findHighValueOrders();
    //LS5
    @Query("SELECT new com.example.lession5_order.Model.DTO.OrderSummary(o.orderCode, o.customerName, o.totalPrice) FROM Order o")
    Page<OrderSummary> findAllAndPagination(Pageable pageable);
}