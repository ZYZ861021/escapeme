package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.DB.LikeFunction;
import com.example.demo.model.DB.PostingSystem;

public interface LikeFunctionRepository extends JpaRepository<LikeFunction, Long> {
	
	@Query(value = "SELECT id,postid,islike,user FROM islike WHERE postid = ?1 and user = ?2 " , nativeQuery = true)
	LikeFunction SelectPostidAndUser(Integer postid, String user);
	
	
	@Query(value = "SELECT count(islike) user FROM islike WHERE islike = true and postid = ?1" , nativeQuery = true)
	Integer SelectIslike(Integer postid);
	
	@Query(value = "SELECT id,postid,islike,user FROM islike WHERE postid = ?1" , nativeQuery = true)
	LikeFunction SelectIslikeIsTrueOrFalse(Integer postid);
	
	@Query(value = "SELECT *,count(islike) as c FROM escapeme.islike where islike = 1 group by postid  order by c desc limit 10;", nativeQuery = true)
	List<LikeFunction> queryPostIsLikeRank();
}
