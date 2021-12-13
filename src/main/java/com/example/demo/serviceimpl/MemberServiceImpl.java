package com.example.demo.serviceimpl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Common.RandomNumber;
import com.example.demo.Common.RandomNumberImpl;
import com.example.demo.model.AccountModel;
import com.example.demo.model.AccountNickName;
import com.example.demo.model.ChangePassWord;
import com.example.demo.model.Index;
import com.example.demo.model.MemberAndStarRank;
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
import com.example.demo.service.MailService;
import com.example.demo.service.MemberService;
import com.example.demo.service.helper.Sha256Helper;
import com.example.demo.service.helper.Sha256HelperImpl;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private Sha256Helper _sha256Helper;

	@Autowired
	private MemberServiceRepository _memberServiceRepository;

	@Autowired
	private PostingSystemRepository _postingSystemRepository;

	@Autowired
	private MessageRepository _messageRepository;

	@Autowired
	private TaskRepository _taskRepository;

	@Autowired
	private MailService _mailService;

	@Autowired
	private RandomNumber _randomNumber;

	@Autowired
	private LikeFunctionRepository _likeFunctionRepository;

	public MemberServiceImpl(Sha256Helper _sha256Helper, MemberServiceRepository _memberServiceRepository,
			PostingSystemRepository _postingSystemRepository, MessageRepository _messageRepository,
			TaskRepository _taskRepository, MailService _mailService, RandomNumber _randomNumber,
			LikeFunctionRepository _likeFunctionRepository) {
		this._sha256Helper = _sha256Helper;
		this._memberServiceRepository = _memberServiceRepository;
		this._postingSystemRepository = _postingSystemRepository;
		this._messageRepository = _messageRepository;
		this._taskRepository = _taskRepository;
		this._mailService = _mailService;
		this._randomNumber = _randomNumber;
		this._likeFunctionRepository = _likeFunctionRepository;
	}

	public String signUp(Account account) {

		account.setSelfIntro("大家好，請多多指教ㄛ~~！");
		account.setSrc("/indexImage/20211123104728.png");
		account.setCreateBy("SYSTEM");
		_memberServiceRepository.findByEmail(account.getEmail());

		if ((_memberServiceRepository.findByEmail(account.getEmail())) != null) {

			account.setEmail(null);
			return account.getEmail();
		}

		account.setPassword(_sha256Helper.Encryption(account.getPassword()));
		String i = _randomNumber.getRandomString();
		_mailService.sendMail(account.getEmail(), "ESCapeMe驗證信", "您好，歡迎您加入ESCapeMe這個大家族以下為您的驗證碼" + "『" + i + "』");
		account.setMail(i);
		_memberServiceRepository.save(account);
		return "完成註冊";
	}

	public Account findByEmailAndPassword(Account account) {

		Account accountModel = _memberServiceRepository.findByEmail(account.getEmail());

		if (accountModel == null) {
			account.setEmail(null);
			return account;
		}

		if (!accountModel.getPassword().equals(_sha256Helper.Encryption(account.getPassword()))) {
			account.setPassword(null);
			return account;
		}
		return accountModel;
	}

	public String CheckMail(Account account) {

		Account accountModel = _memberServiceRepository.findByEmail(account.getEmail());

		if (!accountModel.getMail().equals(account.getMail())) {

			account.setMail(null);
			_memberServiceRepository.flush();
			return "錯誤驗證碼";

		}

		account.setMail("1");
		_memberServiceRepository.flush();

		return "驗證成功";
	}

	public void save(String email) {

		_memberServiceRepository.flush();

	}

	// base64tofile
	public String base64ToFile(String email, String src) throws Exception {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");

		Date date = new Date();

		formatter.format(date);

		String url = (src.split(",")[1].toString().replace("\r\n", "UTF-8"));

		byte[] bytes = DatatypeConverter.parseBase64Binary(url);

		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);

		BufferedImage bi1 = ImageIO.read(bais);

		String path = this.getClass().getResource("/indexImage/").getFile();

		String path2 = ".png";

		try {

			File w2 = new File(path + formatter.format(date) + path2);

			ImageIO.write(bi1, "jpg", w2);

		} catch (IOException e) {
			e.printStackTrace();

		} finally {

			bais.close();

		}

		return "/indexImage/" + formatter.format(date) + path2;
	}

	public Account findByEmail(String email) {

		return _memberServiceRepository.queryByEmail(email);
	}

	public Account ForgetThePassword(Account account) {

		Account account1 = _memberServiceRepository.queryByEmail(account.getEmail());
		String i = _randomNumber.getRandomString();
		_mailService.sendMail(account.getEmail(), "ESCapeMe忘記密碼", "您好，輸入以下驗證碼確認後更改密碼" + "『" + i + "』");
		account1.setMail(i);
		_memberServiceRepository.flush();
		return account;
	}

	public Account ChangePassword(Account account) {

		String str1 = _sha256Helper.Encryption(account.getPassword());
		Account account1 = _memberServiceRepository.findByEmail(account.getEmail());
		account1.setPassword(str1);
		_memberServiceRepository.flush();
		return account;

	}

	public Account ChangeNickName(Account account, String Email) {

		String nick = account.getNickName();
		Account account1 = _memberServiceRepository.findByEmail(Email);
		account1.setNickName(nick);
		_memberServiceRepository.flush();
		return account;
	}

	public Account ChangePasswordIn(Account account, String Email, ChangePassWord changePassWord) {

		account = _memberServiceRepository.findByEmail(Email);
		String str1 = _memberServiceRepository.findByEmail(account.getEmail()).getPassword();
		String str2 = _sha256Helper.Encryption(changePassWord.getPassword());
		if (!str1.equals(str2)) {
			account.setPassword(null);
			return account;
		}
		if (!changePassWord.getNewPassword().equals(changePassWord.getConfirmNewPassword())) {
			account.setPassword("1");
			return account;
		}

		Account account1 = _memberServiceRepository.findByEmail(Email);
		String strr = _sha256Helper.Encryption(changePassWord.getNewPassword());
		account1.setPassword(strr);
		_memberServiceRepository.flush();
		return account;
	}

	// 搜尋nickName顯示相關會員

	public List<AccountNickName> findByNickNameLike(String nickName) {

		List<Account> account = _memberServiceRepository.findByNickNameLike(nickName);

		List<AccountNickName> AccountNickNameList = new ArrayList<>();

		for (int i = 0; i < account.size(); i++) {
			AccountNickName accountNickName = new AccountNickName();
			accountNickName.setNickName(account.get(i).getNickName());
			accountNickName.setMemberId(account.get(i).getId());
			accountNickName.setSrc(account.get(i).getSrc());
			AccountNickNameList.add(accountNickName);
		}
		return AccountNickNameList;

	}

	// 個人頁面個人文章查詢
	public ResponseEntity<List<Index>> SelectMemberContent(String email, Integer postNumber) {

		List<Index> list = new ArrayList<>();
		postNumber = (postNumber - 1) * 5;

		Account account = _memberServiceRepository.findByEmail(email);

		List<PostingSystem> postingSystemlist = _postingSystemRepository.querymemberEmailList(account.getEmail(),
				postNumber);
		for (int i = 0; i < postingSystemlist.size(); i++) {
			List<Message> message = _messageRepository.queryPostid(postingSystemlist.get(i).getPostId());

			Taskform taskform = _taskRepository.queryByquest(postingSystemlist.get(i).getQuest());

			LikeFunction likeFunction = _likeFunctionRepository
					.SelectPostidAndUser(postingSystemlist.get(i).getPostId(), email);

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

	/**/
	public ResponseEntity<List<MemberAndStarRank>> queryMemberRank() {
		List<MemberAndStarRank> memberAndStarRankList = new ArrayList<>();
		List<Account> account = _memberServiceRepository.queryRank();
		for (int i = 0; i < account.size(); i++) {
			MemberAndStarRank memberAndStarRank = new MemberAndStarRank();
			memberAndStarRank.setRank(i + 1);
			memberAndStarRank.setMemberId(account.get(i).getId());
			memberAndStarRank.setNickName(account.get(i).getNickName());
			memberAndStarRank.setUserImgSrc(account.get(i).getSrc());
			memberAndStarRank.setStarCount(account.get(i).getRemainStar());
			memberAndStarRankList.add(memberAndStarRank);
		}

		return new ResponseEntity<List<MemberAndStarRank>>(memberAndStarRankList, HttpStatus.OK);
	}

//
	public ResponseEntity<List<Index>> SelectMember(Long id, String userName, Integer postNumber) {
		postNumber = 1;
		List<Index> list = new ArrayList<>();
		postNumber = (postNumber - 1) * 5;
		Account account = _memberServiceRepository.queryById(id);
		Account accountMember = _memberServiceRepository.findByEmail(account.getEmail());

		List<PostingSystem> postingSystemlist = _postingSystemRepository.querymemberEmailList(accountMember.getEmail(),
				postNumber);
		for (int i = 0; i < postingSystemlist.size(); i++) {
			List<Message> message = _messageRepository.queryPostid(postingSystemlist.get(i).getPostId());

			Taskform taskform = _taskRepository.queryByquest(postingSystemlist.get(i).getQuest());

			LikeFunction likeFunction = _likeFunctionRepository
					.SelectPostidAndUser(postingSystemlist.get(i).getPostId(), accountMember.getEmail());

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

	public Account queryById(Long id) {
		return _memberServiceRepository.queryById(id);
	}

	@Override
	public void update() {
		_memberServiceRepository.flush();
	}

	public Integer countBymission(String member_email) {
		return _postingSystemRepository.countBymission(member_email);
	}

}
