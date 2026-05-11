package com.example.lession5_order.Service;

import com.example.lession5_order.Model.Order;
import com.example.lession5_order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
   @Autowired
    private OrderRepository or;
    public List<Order> getOrdersByStatus(String status) {
        return or.findByStatus(status);
    }
    public List<Order> getOrdersByCustomerName(String name) {
        return or.findByCustomerNameContaining(name);
    }
    //LS2 sắp xếp danh sách
    public List<Order> getAllOrdersSorted(String field, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(field).descending()
                : Sort.by(field).ascending();

        // Sử dụng hàm findAll(Sort sort) có sẵn của JpaRepository
        return or.findAll(sort);
    }
}