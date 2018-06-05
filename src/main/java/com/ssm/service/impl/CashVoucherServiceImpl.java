package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.AttrMapper;
import com.ssm.dao.CashVoucherMapper;
import com.ssm.dao.ServicePackageMapper;
import com.ssm.dao.ServiceVoucherMapper;
import com.ssm.model.Attr;
import com.ssm.model.CashVoucher;
import com.ssm.model.ServicePackage;
import com.ssm.model.ServiceVoucher;
import com.ssm.service.AttrService;
import com.ssm.service.CashVoucherService;
import com.ssm.service.ServicePackageService;
import com.ssm.service.ServiceVoucherService;

@Service("cashVoucherService")
@Transactional
public class CashVoucherServiceImpl implements CashVoucherService{
	
	@Resource(name="cashVoucherMapper")
	private CashVoucherMapper cashVoucherMapper;

	@Override
	public void insert(CashVoucher attr) {
		cashVoucherMapper.insert(attr);
		
	}

	@Override
	public void deleteCashVoucher(String id) {
		cashVoucherMapper.deleteCashVoucher(id);
		
	}

	@Override
	public List<CashVoucher> queryAllCashVoucher() {
		
		return cashVoucherMapper.queryAllCashVoucher();
	}

	@Override
	public CashVoucher updateCashVoucher(String id) {
		
		return cashVoucherMapper.updateCashVoucher(id);
	}

	@Override
	public void saveCashVoucher(CashVoucher attr) {
		cashVoucherMapper.saveCashVoucher(attr);
		
	}

}
