package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import com.example.demo.model.AccountModel;
import com.example.demo.model.MemberAndStarRank;
import com.example.demo.model.DB.Account;

public interface MemberServiceRepository extends JpaRepository<Account, Long> {
	
	Account findByEmail(String email);
	
	@Query(value = "select * from customer where id = ?1", nativeQuery = true)
	Account queryById(Long id);

	@Query(value = "select * from customer where email = ?1", nativeQuery = true)
	Account queryByEmail(String email);

	Account findByEmailAndPassword(String email, String password);

	Account findByPassword(String password);

	Account save(String mail);

	void flush();

	// 搜尋nickName顯示相關會員
	@Query(value = "select * from customer where nickname Like ?1", nativeQuery = true)
	List<Account> findByNickNameLike(String nickName);
	
	@Query(value = "SELECT * FROM customer ORDER BY RAND() LIMIT 1", nativeQuery = true)
	Account queryMember();
	
	
	@Query(value = "SELECT * FROM customer order by remain_star desc limit 10;", nativeQuery = true)
	List<Account> queryRank();
	
	@Query(value = "SELECT * FROM customer Where Email in (?1)", nativeQuery = true)
	List<Account> GetByEmail(List<String> Emails);
}
