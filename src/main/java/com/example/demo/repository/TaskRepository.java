package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.DB.Taskform;

public interface TaskRepository extends JpaRepository<Taskform, Integer>{
	
	@Query(value = "select * from task where mission_content Like ?1", nativeQuery = true)
	List<Taskform> queryByContent(String Content);
	
	@Query(value = "select * from task where quest = ?1", nativeQuery = true)
	Taskform queryByquest(String quest);
	
	@Query(value = "select * from task where mission_id < 99 order by rand() limit 3", nativeQuery = true)
	List<Taskform> queryByQuestid();
	
	//--------------
	

	@Query(value = "select * from task where star = ?1", nativeQuery = true)
	List<Taskform> selectStar(Integer star);
	
	@Query(value = "select * from task where mission_content like ?1 ", nativeQuery = true)
	List<Taskform> selectcontent(String content);
	
	@Query(value = "select * from task where mission_id < 99 order by rand() limit 1", nativeQuery = true)
	Taskform randomquest();
	
	@Query(value = "select mission_id from task where mission_id < 99", nativeQuery = true)
	List<Integer> totalquestid();
	
	@Query(value = "select mission_id from task where mission_id >= 99", nativeQuery = true)
	List<Integer> lifequestid();
		
	@Query(value = "select * from task where mission_id = ?1", nativeQuery = true)
	Taskform findByQuest(Integer questId);
	
	@Query(value = "select * from task where title = '人生成就'  ", nativeQuery = true)
	List<Taskform> Achievement();

}
