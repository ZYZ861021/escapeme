package com.example.demo.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.DB.PostingSystem;

public interface PostingSystemRepository extends JpaRepository<PostingSystem, Integer> {
	PostingSystem save(PostingSystem postingSystem);

	void flush();

	// 搜尋主題任務tasktype跳出相關主題貼文
	@Query(value = "select * from postingsystem where task_Type = ?1 ORDER BY create_dt desc LIMIT 10; ", nativeQuery = true)
	List<PostingSystem> findByTaskType(String taskType);

	@Query(value = "select * from postingsystem where member_email = ?1 group by member_email order by create_dt desc limit 1 ;", nativeQuery = true)
	PostingSystem querymemberEmail(String memberEmail);

	// 找資料庫裡的一串model
	@Query(value = "SELECT * FROM PostingSystem WHERE post_id = ?1 ", nativeQuery = true)
	PostingSystem findBypoId(Integer poId);

	@Query(value = "SELECT * FROM PostingSystem ORDER BY suki DESC LIMIT 50", nativeQuery = true)
	List<PostingSystem> sumSukiRanking();

	// 依用戶名查找喜歡總數
	@Query(value = "SELECT SUM(likes) FROM PostingSystem WHERE post_id = ?1", nativeQuery = true)
	Long sumLike(Integer postId);
	
	@Query(value = "SELECT * FROM postingsystem order by likes desc limit 10;", nativeQuery = true)
	List<PostingSystem> queryPostingSystem();
	
	@Query(value = "select * from postingsystem where post_id = ?1 ", nativeQuery = true)
	PostingSystem queryPostid(Integer postid);
	
	@Query(value = "SELECT * FROM postingsystem  ORDER BY create_dt DESC LIMIT 5 OFFSET ?1", nativeQuery = true)
	List<PostingSystem> querypostList(Integer postNumber);
	
	@Query(value = "select * from postingsystem where member_email = ?1 order by create_dt desc limit 5 offset ?2", nativeQuery = true)
	List<PostingSystem> querymemberEmailList(String memberEmail,Integer postNumber);
	
	@Query(value = "select * from postingsystem where post_id = ?1 order by create_dt desc limit 5 offset ?2", nativeQuery = true)
	List<PostingSystem> querymemberPostIidList(Integer postid,Integer postNumber);
	
	@Query(value = "select * from postingsystem where task_Type = ?1 ORDER BY create_dt DESC LIMIT 5 OFFSET ?2 ", nativeQuery = true)
	List<PostingSystem> findByTaskType(String taskType,Integer postNumber);
	
	@Query(value = "SELECT count(post_id) FROM postingsystem where member_email = ?1 ", nativeQuery = true)
	Integer countBymission(String member_email);
	
	@Query(value = "SELECT COUNT(task_type) FROM postingsystem WHERE member_email = ?1 AND task_type = ?2 AND task_type = ?2 ORDER BY create_dt DESC; ", nativeQuery = true)
	Integer countByTaskType(String email,String taskType);
	
}
