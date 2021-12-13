package com.example.demo.service;

import com.example.demo.model.DB.LikeFunction;

public interface LikeFunctionService {
	
	LikeFunction SaveLike(LikeFunction likeFunction,String userName);
}
