package com.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.model.CashVoucher;
import com.ssm.model.Singin;


@Repository("singinMapper")
public interface SinginMapper {
   
    
    List<Singin> queryAllSingin();
}