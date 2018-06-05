package com.ssm.service;


import java.util.List;

import com.ssm.model.AdderssAdmin;
import com.ssm.model.OrderAdmin;



public interface OrderAdminService {
	
	void insert(OrderAdmin attr);
    
    void deleteOrderAdmin(String id);
    
    List<OrderAdmin> queryAllOrderAdmin();
	
    public OrderAdmin updateOrderAdmin(String id);
    
    void saveOrderAdmin(OrderAdmin attr);
}
