package com.example.lession5_order.repository;

import com.example.lession5_order.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Tìm chính xác theo trạng thái
    List<Order> findByStatus(String status);

    // Tìm theo tên khách hàng có chứa từ khóa (LIKE %name%)
    List<Order> findByCustomerNameContaining(String name);

}