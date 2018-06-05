package com.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.model.Merchant;
import com.ssm.model.Personal;;

@Repository("personalMapper")
public interface PersonalMapper {
	
	void insertPersonal(Personal member);
	
	Personal queryExtendPersonal (String idNumber);
	
	List<Personal> selectAllPersonal();
	
	void deletePersonalInfo(String id);
	

	
	Personal updatePersonal(String id);
	
	void savePersonal(Personal member);
}
