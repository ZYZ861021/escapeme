package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.DB.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{
	
	@Query(value = "SELECT * FROM message where post_id =?1", nativeQuery = true)
	List<Message> queryPostid(Integer postId);
	
	
	@Query(value = " SELECT count(post_id) FROM message where post_id =?1", nativeQuery = true)
	Integer querySumCount(Integer postId);
	
	@Query(value = "SELECT * FROM message where post_id in(?1)", nativeQuery = true)
	List<Message> GetByPostIds(List<Integer> postIds);
}
