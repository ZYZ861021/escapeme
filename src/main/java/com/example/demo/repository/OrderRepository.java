package com.example.demo.repository;

import com.example.demo.model.BuyCommodityReqDTO;
import com.example.demo.model.DB.AccountOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<AccountOrder, Integer> {

    void save(BuyCommodityReqDTO buyCommodityReqDTO);

}
