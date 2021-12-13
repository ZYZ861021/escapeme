package com.example.demo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.DB.LikeFunction;
import com.example.demo.repository.LikeFunctionRepository;
import com.example.demo.service.LikeFunctionService;

@Service
public class LikeFunctionServiceImpl implements LikeFunctionService {

	@Autowired
	private LikeFunctionRepository _likeFunctionRepository;

	public LikeFunctionServiceImpl(LikeFunctionRepository likeFunctionRepository) {
		_likeFunctionRepository = likeFunctionRepository;
	}

	@Override
	public LikeFunction SaveLike(LikeFunction likeFunction,String userName) {
		
		String i = "true" ;
		String x = "false" ;
			
		if(_likeFunctionRepository.SelectPostidAndUser(likeFunction.getPostId(), userName) == null) {
			
			likeFunction.setUserEmail(userName);		
			_likeFunctionRepository.save(likeFunction);
			likeFunction.setIsLike(false);
			_likeFunctionRepository.flush();
		
		}
		
		LikeFunction likeFunctionJudge =_likeFunctionRepository.SelectPostidAndUser(likeFunction.getPostId(), userName);
		
		if(likeFunctionJudge.getIsLike() == true ) {
			
			likeFunctionJudge.setIsLike(false);	
			_likeFunctionRepository.flush();
			return null;
			
		}
		
		if(likeFunctionJudge.getIsLike() == false) {
			likeFunctionJudge.setIsLike(true);
			_likeFunctionRepository.flush();
			return null;
		}
		
		return null;
	}
}
