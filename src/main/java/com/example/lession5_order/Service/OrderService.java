package com.example.lession5_order.Service;

import com.example.lession5_order.Model.Order;
import com.example.lession5_order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

        // Sử dụng hàm findAll(Sort sort)
        return or.findAll(sort);
    }
    //LS3 phân trang ds đơn hàng
    public Page<Order> getOrdersPaged(int page, int size) {
        // Tạo đối tượng Pageable:
        // -page:số trang (bắt đầu từ 0)
        // -size:số lượng phần tử mỗi trang
        // -Sắp xếp theo createdAt giảm dần để lấy đơn hàng mới nhất lên đầu
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        // repository.findAll(pageable) sẽ trả về đối tượng Page chứa dữ liệu và thông tin phân trang
        return or.findAll(pageable);
    }
    //LS4 : truy vấn vip
    public List<Order> getHighValueOrders() {
        return or.findHighValueOrders();
    }
}