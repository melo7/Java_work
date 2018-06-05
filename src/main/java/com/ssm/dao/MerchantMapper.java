package com.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.model.Merchant;

@Repository("merchantMapper")
public interface MerchantMapper {
	
	void insertMerchant(Merchant member);
	
	Merchant queryExtendm (String idNumber);
	
	List<Merchant> selectAllMerchant();
	
	void deleteMerchantInfo(String id);
	
	Merchant updateMerchant(String id);
	
	void saveMerchant(Merchant member);
}
