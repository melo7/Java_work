package com.ssm.service;


import java.util.List;

import com.ssm.model.ProductAdmin;




public interface ProductAdminService {
	
	void insert(ProductAdmin attr);
    
    void deleteProductAdmin(String id);
    
    List<ProductAdmin> queryAllProductAdmin();
	
    public ProductAdmin updateProductAdmin(String id);
    
    void saveProductAdmin(ProductAdmin attr);
}
