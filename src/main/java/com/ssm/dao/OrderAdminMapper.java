package com.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.model.OrderAdmin;



@Repository("orderAdminMapper")
public interface OrderAdminMapper {
   
    void insert(OrderAdmin attr);
    
    void deleteOrderAdmin(String id);
    
    List<OrderAdmin> queryAllOrderAdmin();
    
    OrderAdmin updateOrderAdmin(String id);
    
    void saveOrderAdmin(OrderAdmin attr);
}