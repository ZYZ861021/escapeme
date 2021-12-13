package com.example.demo.serviceimpl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.CountTaskType;
import com.example.demo.model.Index;
import com.example.demo.model.PostNumber;
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
import com.example.demo.service.PostingSystemService;

@Service
public class PostingSystemServiceImpl implements PostingSystemService {

	@Autowired
	private PostingSystemRepository _postingSystemRepository;

	@Autowired
	private MemberServiceRepository _memberServiceRepository;

	@Autowired
	private MessageRepository _messageRepository;

	@Autowired
	private TaskRepository _taskRepository;
	
	@Autowired 
	private LikeFunctionRepository _likeFunctionRepository;

	public PostingSystem save(PostingSystem postingSystem) {
		return _postingSystemRepository.save(postingSystem);
	}



	public void update() {
		_postingSystemRepository.flush();
	}

	public PostingSystem findAllBypostId(Integer postId) {
		return _postingSystemRepository.findBypoId(postId);
	}

	// 搜尋貼文(客製化)

	public ResponseEntity<List<Index>> findByTaskType(String taskType,String userName) {
		List<Index> list = new ArrayList<>();


		List<PostingSystem> postingSystemlist =  _postingSystemRepository.findByTaskType(taskType);
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



	public ResponseEntity<List<Index>> SelectPost(String userName) {

		List<Index> list = new ArrayList<>();

		List<LikeFunction> likeFunction = _likeFunctionRepository.queryPostIsLikeRank();
		
		for (int i = 0; i < likeFunction.size(); i++) {
			System.out.println(likeFunction.get(i));
			
			Account account = new Account();
			
			List<Message> message = new ArrayList<>();
			
			Taskform taskform = new Taskform();
			
			PostingSystem postingSystem = new PostingSystem();
			
			postingSystem = _postingSystemRepository.queryPostid(likeFunction.get(i).getPostId());
			System.out.println(postingSystem);
			LikeFunction likeSum = _likeFunctionRepository.SelectPostidAndUser(postingSystem.getPostId(), userName);
			
			Integer likeFunction1 = _likeFunctionRepository.SelectIslike(postingSystem.getPostId());
			
			account = _memberServiceRepository.findByEmail(postingSystem.getMemberEmail());

			message = _messageRepository.queryPostid(postingSystem.getPostId());

			taskform = _taskRepository.queryByquest(postingSystem.getQuest());

			Integer liketotal = _messageRepository.querySumCount(postingSystem.getPostId());
			Index index = new Index();
			index.setPostId(postingSystem.getPostId());
			index.setNickName(account.getNickName());
			index.setUserImgSrc(account.getSrc());
			index.setMemberId(account.getId());
			index.setType(postingSystem.getTaskType());
			index.setTitle(taskform.getTitle());
			index.setQuest(taskform.getQuest());
			index.setPostImgSrc(postingSystem.getPostImage());
			//這邊
			if(likeFunction1 == null || likeFunction1 == 0) {
				index.setLikeCount(0);
			}else {
				index.setLikeCount(likeFunction1);
			}	
			
			if(likeFunction.get(i) == null) {
				index.setIsLike(false);
			}else {
				index.setIsLike(likeFunction.get(i).getIsLike());
			}
			index.setCommentCount(liketotal);
			index.setPostTime(postingSystem.getCreateDt());
			index.setPostContent(postingSystem.getPostContent());
			index.setComments(message);
			list.add(index);
		}

		return new ResponseEntity<List<Index>>(list, HttpStatus.OK);
	}
	
	public CountTaskType countTaskType(Long member_id){
		Account account = _memberServiceRepository.getById(member_id);
		CountTaskType countTaskType = new CountTaskType();
		countTaskType.setMember_id(member_id);
		countTaskType.setNickname(account.getNickName());
		countTaskType.setsrc(account.getSrc());
		countTaskType.setMission_type_1(_postingSystemRepository.countByTaskType(account.getEmail(), "mission-tag-1"));
		countTaskType.setMission_type_2(_postingSystemRepository.countByTaskType(account.getEmail(), "mission-tag-2"));
		countTaskType.setMission_type_3(_postingSystemRepository.countByTaskType(account.getEmail(), "mission-tag-3"));
		countTaskType.setMission_type_4(_postingSystemRepository.countByTaskType(account.getEmail(), "mission-tag-4"));
		countTaskType.setMission_type_5(_postingSystemRepository.countByTaskType(account.getEmail(), "mission-tag-5"));
		countTaskType.setMission_type_6(_postingSystemRepository.countByTaskType(account.getEmail(), "mission-tag-6"));
		countTaskType.setMission_type_7(_postingSystemRepository.countByTaskType(account.getEmail(), "mission-tag-7"));
		countTaskType.setMission_type_8(_postingSystemRepository.countByTaskType(account.getEmail(), "mission-tag-8"));
		countTaskType.setMission_type_9(_postingSystemRepository.countByTaskType(account.getEmail(), "mission-tag-9"));
		countTaskType.setMission_type_10(_postingSystemRepository.countByTaskType(account.getEmail(), "mission-tag-10"));
		countTaskType.setMission_type_11(_postingSystemRepository.countByTaskType(account.getEmail(), "mission-tag-11"));
		
		return countTaskType;
	}

}
