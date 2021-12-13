package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Index;
import com.example.demo.model.DB.Account;
import com.example.demo.model.DB.LikeFunction;
import com.example.demo.model.DB.Message;
import com.example.demo.model.DB.PostingSystem;
import com.example.demo.model.DB.Taskform;
import com.example.demo.repository.LikeFunctionRepository;
import com.example.demo.repository.MemberServiceRepository;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.PostingSystemRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository _messMessageRepository;

	@Autowired
	private MemberServiceRepository _memberServiceRepository;

	@Autowired
	private PostingSystemRepository _postingSystemRepository;

	@Autowired
	private MessageRepository _messageRepository;

	@Autowired
	private TaskRepository _taskRepository;

	@Autowired
	private LikeFunctionRepository _likeFunctionRepository;

	public ResponseEntity<Index> SaveMessageAndReturnContent(Message message, String userName) {
		System.out.println(message);

		Account account = _memberServiceRepository.queryByEmail(userName);
		message.setNickName(account.getNickName());
		message.setUserImgSrc(account.getSrc());
		message.setMemberId(account.getId());
		_messMessageRepository.save(message);

		PostingSystem postingSystem = _postingSystemRepository.queryPostid(message.getPostId());
		
		Account accounts = _memberServiceRepository.queryByEmail(postingSystem.getMemberEmail());
		
		List<Message> messages = _messageRepository.queryPostid(postingSystem.getPostId());
		System.out.println(messages);
		Taskform taskform = _taskRepository.queryByquest(postingSystem.getQuest());

		LikeFunction likeFunction = _likeFunctionRepository.SelectPostidAndUser(postingSystem.getPostId(), userName);

		Integer likeFunction1 = _likeFunctionRepository.SelectIslike(postingSystem.getPostId());

		Integer liketotal = _messageRepository.querySumCount(postingSystem.getPostId());
		Index index = new Index();
		index.setPostId(postingSystem.getPostId());
		index.setNickName(accounts.getNickName());
		index.setUserImgSrc(accounts.getSrc());
		index.setMemberId(accounts.getId());
		index.setType(postingSystem.getTaskType());
		index.setTitle(taskform.getTitle());
		index.setQuest(taskform.getQuest());
		index.setPostImgSrc(postingSystem.getPostImage());
		if (likeFunction1 == null || likeFunction1 == 0) {
			index.setLikeCount(0);
		} else {
			index.setLikeCount(likeFunction1);
		}
		index.setCommentCount(liketotal);

		if (likeFunction == null) {
			index.setIsLike(false);
		} else {
			index.setIsLike(likeFunction.getIsLike());
		}
		index.setCommentCount(liketotal);
		index.setPostTime(postingSystem.getCreateDt());
		index.setPostContent(postingSystem.getPostContent());
		index.setComments(messages);
		return new ResponseEntity<Index>(index, HttpStatus.OK);
	}

}
