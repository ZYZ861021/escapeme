package com.example.demo.repository;

import com.example.demo.model.DB.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ShoppingRepository extends JpaRepository<Shopping, Integer>, JpaSpecificationExecutor<Shopping> {

    @Query(value = "select * from Shopping where id = ?1", nativeQuery = true)
    Shopping queryById(Integer id);

}
