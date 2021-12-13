package com.example.demo.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.AccountNickName;
import com.example.demo.model.ChangePassWord;
import com.example.demo.model.Index;
import com.example.demo.model.MemberAndStarRank;
import com.example.demo.model.MemberPersonalPage;
import com.example.demo.model.DB.Account;

public interface MemberService {

	// 註冊時要比對email
	public String signUp(Account account);

	Account findByEmailAndPassword(Account account);

	String CheckMail(Account account);

	void save(String email);

	String base64ToFile(String email, String src) throws Exception;

	Account findByEmail(String email);
	
	Account queryById(Long id);

	Account ForgetThePassword(Account account);

	Account ChangePassword(Account account);

	Account ChangeNickName(Account account, String Email);

	Account ChangePasswordIn(Account account, String userName, ChangePassWord changePassWord);

	// 搜尋nickName顯示相關會員
	List<AccountNickName> findByNickNameLike(String nickName);
	
	ResponseEntity<List<Index>> SelectMemberContent(String email, Integer postNumber);
	
	ResponseEntity<List<MemberAndStarRank>> queryMemberRank();
	
	ResponseEntity<List<Index>> SelectMember(Long id , String userName,Integer postNumber);

	public void update();
	
	Integer countBymission(String member_email);

}
