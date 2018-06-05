package com.ssm.service;

import java.util.List;

import com.ssm.model.Member;
import com.ssm.model.Personal;


public interface PersonalService {
	
	void insertPersonal(Personal member);
	
	Personal queryExtendPersonal (String idNumber);

	int checkIdentifyInfomation(String idNumber, String phone, String emall);
	
	List<Personal> selectAllPersonal();
	
	void deletePersonalInfo(String id);
	
	public Personal updatePersonal(String id);
	
	public void savePersonal(Personal member);
}
