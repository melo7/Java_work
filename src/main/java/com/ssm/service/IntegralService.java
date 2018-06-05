package com.ssm.service;


import java.util.List;

import com.ssm.model.Integral;


public interface IntegralService {
	
    
    void deleteIntegral(String id);
    
    List<Integral> queryAllIntegral();
	
    public Integral updateIntegral(String id);
    
    void saveIntegral(Integral attr);
}
