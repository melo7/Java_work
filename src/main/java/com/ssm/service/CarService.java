package com.ssm.service;


import java.util.List;

import com.ssm.model.CarServiceModel;
import com.ssm.model.Member;

public interface CarService {
	
	void insert(CarServiceModel attr);
    
    void deleteCarServiceModel(String id);
    
    List<CarServiceModel> queryAllCarServiceModel();
	
    public CarServiceModel updateCarServiceModel(String id);
    
    void saveMemberCarServiceModel(CarServiceModel attr);
}
