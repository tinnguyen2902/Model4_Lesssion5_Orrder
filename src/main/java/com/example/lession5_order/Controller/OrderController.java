package com.example.lession5_order.Controller;

import com.example.lession5_order.Model.DTO.OrderSummary;
import com.example.lession5_order.Model.DTO.PaginationResponse;
import com.example.lession5_order.Model.Entity.Order;
import com.example.lession5_order.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService os;
    @GetMapping("/searchStatus")
    public ResponseEntity<List<Order>> searchStatus(@RequestParam String status) {
        return ResponseEntity.ok(os.getOrdersByStatus(status));
    }

    @GetMapping("/searchByCustomer")
    public ResponseEntity<List<Order>> searchByCustomer(@RequestParam String customerName) {
        return ResponseEntity.ok(os.getOrdersByCustomerName(customerName));
    }
    //LS2 sắp xếp ds
    @GetMapping("/sort")
    public ResponseEntity<List<Order>> getSortedOrders(
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String dir) {

        List<Order> sortedOrders = os.getAllOrdersSorted(sortBy, dir);
        return ResponseEntity.ok(sortedOrders);
    }
    //LS3 phân trang
    @GetMapping("/paging")
    public ResponseEntity<Page<Order>> getOrdersPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(os.getOrdersPaged(page, size));
    }
    // LS4: truy vấn báo cáo VIP
    @GetMapping("/high-value")
    public ResponseEntity<List<Order>> getHighValueOrders() {
        List<Order> highValueOrders = os.getHighValueOrders();
        return ResponseEntity.ok(highValueOrders);
    }
    //LS5
    @GetMapping("/findAllAndSearch")
    public ResponseEntity<PaginationResponse<OrderSummary>> findAllAndSearch(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(os.findAllAndPagination(pageable));
    }
    //LS6
    @GetMapping("/filter")
    public ResponseEntity<Page<OrderSummary>> filterOrders(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Double minPrice,
            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        return ResponseEntity.ok(os.filterOrders(status, minPrice, pageable));
    }
}