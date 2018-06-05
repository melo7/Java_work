package com.ssm.service;

import java.util.List;

import com.ssm.model.Member;
import com.ssm.model.Merchant;


public interface MemberService {
	
	void insertMember(Member member);
	
	Member queryExtendm (String idNumber);

	int checkIdentifyInfomation(String idNumber, String phone, String emall);
	
	List<Member> selectAllMember();
	
	void deleteMemberInfo(String id);
	

	public Member updateMember(String id);
	
	public void saveMember(Member member);
}
