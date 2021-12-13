package com.example.demo.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Index;
import com.example.demo.model.PostNumber;
import com.example.demo.model.ResultViewModel;
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
import com.example.demo.service.indexService;

@Service
public class indexServiceImpl implements indexService {

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

	public ResponseEntity<List<Index>> HomePost(String userName,Integer postNumber)  {
		
		List<Index> list = new ArrayList<>();
		postNumber = (postNumber)*5;

		List<PostingSystem> postingSystemlist = _postingSystemRepository.querypostList(postNumber);
		for (int i = 0; i < postingSystemlist.size(); i++) {
			Account account = _memberServiceRepository.findByEmail(postingSystemlist.get(i).getMemberEmail());
			List<Message> message = _messageRepository.queryPostid(postingSystemlist.get(i).getPostId());

			Taskform taskform = _taskRepository.queryByquest(postingSystemlist.get(i).getQuest());

			LikeFunction likeFunction = _likeFunctionRepository
					.SelectPostidAndUser(postingSystemlist.get(i).getPostId(), userName);

			Integer likeFunction1 = _likeFunctionRepository.SelectIslike(postingSystemlist.get(i).getPostId());

			Integer liketotal = _messageRepository.querySumCount(postingSystemlist.get(i).getPostId());
			Index index = new Index();
			index.setPostId(postingSystemlist.get(i).getPostId());
			index.setNickName(account.getNickName());
			index.setUserImgSrc(account.getSrc());
			index.setMemberId(account.getId());
			index.setType(postingSystemlist.get(i).getTaskType());
			index.setTitle(taskform.getTitle());
			index.setQuest(taskform.getQuest());
			index.setPostImgSrc(postingSystemlist.get(i).getPostImage());
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
			index.setPostTime(postingSystemlist.get(i).getCreateDt());
			index.setPostContent(postingSystemlist.get(i).getPostContent());
			index.setComments(message);
			list.add(index);

		}
		return new ResponseEntity<List<Index>>(list, HttpStatus.OK);
	}
}
