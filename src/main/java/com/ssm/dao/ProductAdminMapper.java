package com.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.model.OrderAdmin;
import com.ssm.model.ProductAdmin;



@Repository("productAdminMapper")
public interface ProductAdminMapper {
   
    void insert(ProductAdmin attr);
    
    void deleteProductAdmin(String id);
    
    List<ProductAdmin> queryAllProductAdmin();
    
    ProductAdmin updateProductAdmin(String id);
    
    void saveProductAdmin(ProductAdmin attr);
}