package com.example.demo.controller;

import com.example.demo.Common.RandomNumber;
import com.example.demo.model.BuyCommodityReqDTO;
import com.example.demo.model.BuyCommodityResDTO;
import com.example.demo.model.FindCommodityReqDTO;
import com.example.demo.model.DB.Account;
import com.example.demo.model.DB.AccountOrder;
import com.example.demo.model.DB.Shopping;

import com.example.demo.repository.MemberServiceRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ShoppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/shopping")
public class ShoppingController {

    @Autowired
    private MemberServiceRepository memberServiceRepository;

    @Autowired
    private ShoppingRepository shoppingRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RandomNumber randomNumber;

    /**
     * 動態搜尋商品
     */
    @PostMapping("/findCommodity")
    public @ResponseBody
    List<Shopping> findByCommodity(@RequestBody FindCommodityReqDTO req) {
        Specification<Shopping> specification = (root, query, criteriaBuilder) -> {
            //所有的斷言及條件
            List<Predicate> predicateList = new ArrayList<>();
            //找到req條件
            if (req.getStar() != null) {
                predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("star"), req.getStar()));
            }
            if (req.getType() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("type"), req.getType()));
            }
            if (req.getTitle() != null && !req.getTitle().equals("")) {
                predicateList.add(criteriaBuilder.like(root.get("title"), "%" + req.getTitle() + "%"));
            }
            if (req.getStarTop() != null && req.getStarTop() == 1000) {
                req.setStar(1000);
                predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("star"), req.getStar()));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
        return shoppingRepository.findAll(specification);
    }

    @PostMapping("/buyCommodity")
    public @ResponseBody
    ResponseEntity<BuyCommodityResDTO> buyCommodity(@RequestBody BuyCommodityReqDTO buyCommodityReqDTO, @CookieValue(value = "userName", required = false) String userName) {
        int i = 1123;
        BuyCommodityResDTO buyCommodityResDTO = new BuyCommodityResDTO();
        if (null == userName || userName.isEmpty()) {
            buyCommodityResDTO.setCode(400);
            buyCommodityResDTO.setMessage("尚未登入");
            return new ResponseEntity<BuyCommodityResDTO>(buyCommodityResDTO, HttpStatus.OK);
        }

        Account account = memberServiceRepository.findByEmail(userName);
        Shopping shopping = shoppingRepository.queryById(buyCommodityReqDTO.getId());


        if (null == account.getRemainStar() || account.getRemainStar() < shopping.getStar()) {
            buyCommodityResDTO.setCode(400);
            buyCommodityResDTO.setMessage("星星數不夠");
            buyCommodityResDTO.setNickName(account.getNickName());
            buyCommodityResDTO.setStar(account.getRemainStar());
            return new ResponseEntity<BuyCommodityResDTO>(buyCommodityResDTO, HttpStatus.OK);
        }
        if (null == shopping.getInventory() || shopping.getInventory() < 1) {
            buyCommodityResDTO.setCode(400);
            buyCommodityResDTO.setMessage("庫存不夠");
            buyCommodityResDTO.setNickName(account.getNickName());
            buyCommodityResDTO.setStar(account.getRemainStar());
            return new ResponseEntity<BuyCommodityResDTO>(buyCommodityResDTO, HttpStatus.OK);
        }

        if (account.getRemainStar() > shopping.getStar() &&
                shopping.getInventory() >= 1) {
            account.setRemainStar(account.getRemainStar() - shopping.getStar());
            memberServiceRepository.save(account);
            shopping.setInventory(shopping.getInventory() - 1);
            shoppingRepository.flush();
        }

        if (buyCommodityReqDTO.getAddress() == "" || buyCommodityReqDTO.getAddress() == null) {
            buyCommodityResDTO.setCode(400);
            buyCommodityResDTO.setMessage("地址錯誤");
            return new ResponseEntity<BuyCommodityResDTO>(buyCommodityResDTO, HttpStatus.OK);
        }
        if (buyCommodityReqDTO.getOrderName() == "" || buyCommodityReqDTO.getOrderName() == null) {
            buyCommodityResDTO.setCode(400);
            buyCommodityResDTO.setMessage("姓名錯誤");
            return new ResponseEntity<BuyCommodityResDTO>(buyCommodityResDTO, HttpStatus.OK);
        }
        if (buyCommodityReqDTO.getPhone() == "" || buyCommodityReqDTO.getPhone() == null || buyCommodityReqDTO.getPhone().length() != 10) {
            buyCommodityResDTO.setCode(400);
            buyCommodityResDTO.setMessage("電話錯誤");
            return new ResponseEntity<BuyCommodityResDTO>(buyCommodityResDTO, HttpStatus.OK);
        }

        AccountOrder order1 = new AccountOrder();
        order1.setAddress(buyCommodityReqDTO.getAddress());
        order1.setOrderName(buyCommodityReqDTO.getOrderName());
        order1.setShoppingId(buyCommodityReqDTO.getId());
        order1.setEmail(userName);
        order1.setPhone(buyCommodityReqDTO.getPhone());
        order1.setOrderId(i + randomNumber.getRandomString());
        orderRepository.save(order1);


        buyCommodityResDTO.setCode(200);
        buyCommodityResDTO.setMessage("感謝您的購物");
        buyCommodityResDTO.setNickName(account.getNickName());
        buyCommodityResDTO.setStar(account.getRemainStar());
        return new ResponseEntity<BuyCommodityResDTO>(buyCommodityResDTO, HttpStatus.OK);
    }
}