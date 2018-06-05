package com.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.model.CashVoucher;


@Repository("cashVoucherMapper")
public interface CashVoucherMapper {
   
    void insert(CashVoucher attr);
    
    void deleteCashVoucher(String id);
    
    List<CashVoucher> queryAllCashVoucher();
    
    CashVoucher updateCashVoucher(String id);
    
    void saveCashVoucher(CashVoucher attr);
}