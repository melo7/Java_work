package com.ssm.service;

import java.util.List;

import com.ssm.model.Merchant;


public interface MerchantService {
	
	void insertMerchant(Merchant member);
	
	Merchant queryExtendm (String idNumber);

	int checkIdentifyInfomation(String idNumber, String phone, String emall);
	
	List<Merchant> selectAllMerchant();
	
	void deleteMerchantInfo(String id);
	
	public Merchant updateMerchant(String id);
	
	public void saveMerchant(Merchant member);
}
