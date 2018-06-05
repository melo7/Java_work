package com.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.model.Member;
import com.ssm.model.Merchant;

@Repository("memberMapper")
public interface MemberMapper {
	
	void insertMember(Member member);
	
	Member queryExtendm (String idNumber);
	
	List<Member> selectAllMember();
	
	void deleteMemberInfo(String id);
	
	Member updateMember(String id);
	
	void saveMember(Member member);
}
