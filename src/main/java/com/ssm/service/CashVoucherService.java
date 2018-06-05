package com.ssm.service;


import java.util.List;

import com.ssm.model.CashVoucher;




public interface CashVoucherService {
	
	void insert(CashVoucher attr);
    
    void deleteCashVoucher(String id);
    
    List<CashVoucher> queryAllCashVoucher();
	
    public CashVoucher updateCashVoucher(String id);
    
    void saveCashVoucher(CashVoucher attr);
}
