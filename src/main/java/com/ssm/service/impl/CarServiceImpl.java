package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.CarServiceMapper;
import com.ssm.model.CarServiceModel;
import com.ssm.service.CarService;

@Service("carService")
@Transactional
public class CarServiceImpl implements CarService{

	@Resource(name="carServiceMapper")
	private CarServiceMapper carServiceMapper;

	@Override
	public void insert(CarServiceModel attr) {
		carServiceMapper.insert(attr);
	}

	@Override
	public void deleteCarServiceModel(String id) {
		carServiceMapper.deleteCarService(id);
		
	}

	@Override
	public List<CarServiceModel> queryAllCarServiceModel() {
		
		return carServiceMapper.queryAllCarService();
	}

	@Override
	public CarServiceModel updateCarServiceModel(String id) {
		
		return carServiceMapper.updateCarServiceModel(id);
	}

	@Override
	public void saveMemberCarServiceModel(CarServiceModel attr) {
		carServiceMapper.saveMemberCarServiceModel(attr);
		
	}


	
}
