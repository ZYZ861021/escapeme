package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.DB.PersonalTask;

public interface PersonalTaskRepository extends JpaRepository<PersonalTask,Integer>{


	@Query(value = "SELECT * FROM personal_task WHERE cr_date = CURDATE() and  email = ?1",nativeQuery = true)
	PersonalTask todayemail(String email);

	@Query(value = "SELECT id FROM personal_task WHERE cr_date < DATE_SUB(CURDATE(),INTERVAL 30 DAY)",nativeQuery = true)
	List<Integer> overday();

	@Query(value = "SELECT true_quest FROM personal_task WHERE email = ?1",nativeQuery = true)
	List<String> allTrue(String email);
	 
	PersonalTask getByEmail(String email);

	@Query(value = "SELECT MAX(id) FROM personal_task", nativeQuery = true)
	Integer findMaxId();

	PersonalTask findAllById(Integer id);
	
	@Query(value = "SELECT * FROM personal_task WHERE DATE(cr_date) = ?1", nativeQuery = true)
	PersonalTask QueryCrDate(String cr_date);
	
	@Query(value = "SELECT * FROM personal_task where email = ?1 order by cr_date DESC limit 1", nativeQuery = true)
	PersonalTask Queryemail(String email);
	@Query(value = "select mission_id from postingsystem where member_email = ?1 and mission_id>98;", nativeQuery = true)
	List<Integer> Querypersonallife(String email);
	

}