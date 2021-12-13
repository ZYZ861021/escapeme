package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ChangePassWord;
import com.example.demo.model.Index;
import com.example.demo.model.MemberAndStarRank;
import com.example.demo.model.MemberPersonalPage;
import com.example.demo.model.ResultViewModel;
import com.example.demo.model.DB.Account;
import com.example.demo.service.MemberService;
import com.example.demo.service.PersonalTaskService;

@RestController
@RequestMapping(value = "/api1/custo")
public class MemberController {

	@Autowired
	private PersonalTaskService _personalTaskService;
	
	@Autowired
	private MemberService _memberAccountService;

	// 會員註冊
	@PostMapping(value = "/signUp")
	public ResponseEntity<ResultViewModel> signUp(@RequestBody Account account, HttpServletResponse response) {

		ResultViewModel resultViewModel = new ResultViewModel();
		
		if (account.getEmail() == "" || account.getEmail() == null) {
			resultViewModel.setCode(400);
			resultViewModel.setMessage("Email to Enter");
			resultViewModel.setData("/signup.html");
			return new ResponseEntity<ResultViewModel>(resultViewModel, HttpStatus.OK);
		}
	
		if (account.getPassword() == "" || account.getPassword() == null) {
			resultViewModel.setCode(400);
			resultViewModel.setMessage("password to Enter");
			resultViewModel.setData("/signup.html");
			return new ResponseEntity<ResultViewModel>(resultViewModel, HttpStatus.OK);
		}
		if (account.getNickName() == "" || account.getNickName() == null) {
			resultViewModel.setCode(400);
			resultViewModel.setMessage("nickname to Enter");
			resultViewModel.setData("/signup.html");
			return new ResponseEntity<ResultViewModel>(resultViewModel, HttpStatus.OK);
		}
		if (account.getPhone() == "" || account.getPhone() == null) {
			resultViewModel.setCode(400);
			resultViewModel.setMessage("phone to Enter");
			resultViewModel.setData("/signup.html");
			return new ResponseEntity<ResultViewModel>(resultViewModel, HttpStatus.OK);
		}

		_memberAccountService.signUp(account);
		if(account.getEmail()== "" || account.getEmail()==null){
			resultViewModel.setCode(400);
			resultViewModel.setMessage("此信箱已註冊過");
			resultViewModel.setData("/signup.html");
			return new ResponseEntity<ResultViewModel>(resultViewModel, HttpStatus.OK);
		}
		resultViewModel.setCode(200);
		resultViewModel.setMessage("帳號");
		resultViewModel.setData("/login.html");
		return new ResponseEntity<ResultViewModel>(resultViewModel, HttpStatus.OK);

	}

	// 信箱確認
	@PostMapping(value = "/check")
	public ResponseEntity<ResultViewModel> check(@RequestBody Account account) {

		ResultViewModel rvm = new ResultViewModel();
		_memberAccountService.CheckMail(account);
		rvm.setCode(200);
		rvm.setMessage("帳號");
		rvm.setData("/login.html");
		return new ResponseEntity<ResultViewModel>(rvm, HttpStatus.OK);
	}

	// 會員登入
	@PostMapping(value = "/login")
	public ResponseEntity<ResultViewModel> logIn(@RequestBody Account account, HttpServletResponse response) {

		ResultViewModel rvm = new ResultViewModel();
		if (account.getEmail() == "" || account.getEmail() == null) {
			rvm.setCode(400);
			rvm.setMessage("請輸入信箱!!");
			rvm.setData("/index.html");
			return new ResponseEntity<ResultViewModel>(rvm, HttpStatus.OK);
		}
		if (account.getPassword() == "" || account.getPassword() == null) {
			rvm.setCode(400);
			rvm.setMessage("請輸入密碼!!");
			rvm.setData("/index.html");
			return new ResponseEntity<ResultViewModel>(rvm, HttpStatus.OK);
		}

		_memberAccountService.findByEmailAndPassword(account);
	
		if (account.getEmail() == null) {
			rvm.setCode(150);
			rvm.setMessage("信箱有誤");
			rvm.setData("/login.html");
			return new ResponseEntity<ResultViewModel>(rvm, HttpStatus.OK);
		}
		if (account.getPassword() == null) {
			rvm.setCode(150);
			rvm.setMessage("密碼有誤");
			rvm.setData("/login.html");
			return new ResponseEntity<ResultViewModel>(rvm, HttpStatus.OK);
		}

		String hashCookie = account.getEmail();
		Cookie cookie = new Cookie("userName", hashCookie);
		cookie.setMaxAge(1800 + (60 * 60 * 8));
		cookie.setPath("/");
		response.addCookie(cookie);
		_personalTaskService.questIn(hashCookie);
		rvm.setCode(200);
		rvm.setMessage("ok");
		rvm.setData("/index.html");
		return new ResponseEntity<ResultViewModel>(rvm, HttpStatus.OK);
	}

	// 會員更新圖式及內容
	@PostMapping("/add")
	public ResponseEntity<ResultViewModel> add(@RequestBody Account account,
			@CookieValue(value = "userName", defaultValue = "Atta") String userName) {

		ResultViewModel rvm = new ResultViewModel();

		Account account1 = _memberAccountService.findByEmail(userName);

		try {

			account1.setSrc(_memberAccountService.base64ToFile(userName, account.getSrc()));

			account1.setSelfIntro(account.getSelfIntro());

			_memberAccountService.save(userName);

		} catch (Exception e) {

			e.printStackTrace();
		}

		rvm.setCode(200);
		rvm.setMessage("更新個人資料");
		rvm.setData("/personal_page.html");
		return new ResponseEntity<ResultViewModel>(rvm, HttpStatus.OK);
	}

	@PostMapping("/ChangeNickName")
	public ResponseEntity<ResultViewModel> ChangeNickName(@RequestBody Account account,
			@CookieValue(value = "userName", defaultValue = "Atta") String userName, HttpServletResponse response) {

		ResultViewModel rvm = new ResultViewModel();
		_memberAccountService.ChangeNickName(account, userName);
		rvm.setCode(200);
		rvm.setMessage("更新個人資料");
		rvm.setData("/personal_page.html");
		return new ResponseEntity<ResultViewModel>(rvm, HttpStatus.OK);
	}

	// 更改密碼
	@PostMapping("/ChangePassword")
	public ResponseEntity<ResultViewModel> ChangePassword(@RequestBody ChangePassWord changePassWord, Account account,
			@CookieValue(value = "userName", defaultValue = "Atta") String userName, HttpServletResponse response) {

		ResultViewModel rvm = new ResultViewModel();
		Account account1 = _memberAccountService.ChangePasswordIn(account, userName, changePassWord);

		if (account1.getPassword() == null) {
			rvm.setCode(150);
			rvm.setMessage("帳號錯誤");
			rvm.setData("/personal_page.html");
			return new ResponseEntity<ResultViewModel>(rvm, HttpStatus.OK);
		}
		if (account1.getPassword() == "1") {
			rvm.setCode(155);
			rvm.setMessage("帳號錯誤");
			rvm.setData("/personal_page.html");
			return new ResponseEntity<ResultViewModel>(rvm, HttpStatus.OK);
		}

		rvm.setCode(200);
		rvm.setMessage("更新個人資料");
		rvm.setData("/personal_page.html");
		return new ResponseEntity<ResultViewModel>(rvm, HttpStatus.OK);
	}

	@PostMapping("/LogOut")
	public ResponseEntity<ResultViewModel> LogOut(HttpServletResponse response) {
		ResultViewModel rvm = new ResultViewModel();

		Cookie cookie = new Cookie("userName", null);
		cookie.setPath("/");
		response.addCookie(cookie);
		rvm.setCode(200);
		rvm.setMessage("更新個人資料");
		rvm.setData("/signup.html");
		return new ResponseEntity<ResultViewModel>(rvm, HttpStatus.OK);
	}

	@GetMapping("/SelectMemberPersonalPage")
	public ResponseEntity<MemberPersonalPage> SelectMemberPersonalPage(
			@CookieValue(value = "userName", defaultValue = "Atta") String userName) {
		MemberPersonalPage memberPersonalPage = new MemberPersonalPage();
		Account account = _memberAccountService.findByEmail(userName);
		memberPersonalPage.setMemberId(account.getId());
		memberPersonalPage.setNickName(account.getNickName());
		memberPersonalPage.setUserImgSrc(account.getSrc());

		if (_memberAccountService.countBymission(account.getEmail()) == null) {
			memberPersonalPage.setMissionCount(0);
		}
		memberPersonalPage.setMissionCount(_memberAccountService.countBymission(userName));

		if (account.getRemainStar() == null) {
			account.setRemainStar(0);
		}
		memberPersonalPage.setStarCount(account.getRemainStar());
		memberPersonalPage.setFansCount(0);
		memberPersonalPage.setFollowCount(0);
		memberPersonalPage.setEmail(account.getEmail());
		memberPersonalPage.setPhone(account.getPhone());
		memberPersonalPage.setIntroduction(account.getSelfIntro());

		return new ResponseEntity<MemberPersonalPage>(memberPersonalPage, HttpStatus.OK);
	}

	@GetMapping("/SelectMemberContent")
	public ResponseEntity<List<Index>> SelectMemberContent(
			@CookieValue(value = "userName", defaultValue = "Atta") String userName, Integer postNumber) {
		postNumber = 1;
		ResponseEntity<List<Index>> selectMemberContent = _memberAccountService.SelectMemberContent(userName,
				postNumber);
		return selectMemberContent;
	}

	@GetMapping("/queryMemberRank")
	public ResponseEntity<List<MemberAndStarRank>> queryMemberRank() {
		return _memberAccountService.queryMemberRank();
	}

	@GetMapping(value = "/findContent")
	public ResponseEntity<List<Index>> queryContent(@RequestParam("id") Long id,
			@CookieValue(value = "userName", defaultValue = "Atta") String userName, Integer postNumber) {
		ResponseEntity<List<Index>> selectMemberContent = _memberAccountService.SelectMember(id, userName, postNumber);
		return selectMemberContent;
	}

	@GetMapping("/findMember")
	public ResponseEntity<MemberPersonalPage> queryMember(@RequestParam("id") Long id, Integer postNumber) {

		MemberPersonalPage memberPersonalPage = new MemberPersonalPage();
		Account account = _memberAccountService.queryById(id);

		memberPersonalPage.setMemberId(account.getId());
		memberPersonalPage.setNickName(account.getNickName());
		memberPersonalPage.setUserImgSrc(account.getSrc());
		if (_memberAccountService.countBymission(account.getEmail()) == null) {
			memberPersonalPage.setMissionCount(0);
		}
		memberPersonalPage.setMissionCount(_memberAccountService.countBymission(account.getEmail()));
		if (account.getRemainStar() == null) {
			account.setRemainStar(0);
		}
		memberPersonalPage.setStarCount(account.getRemainStar());
		memberPersonalPage.setFansCount(0);
		memberPersonalPage.setFollowCount(0);
		memberPersonalPage.setEmail(account.getEmail());
		memberPersonalPage.setPhone(account.getPhone());
		memberPersonalPage.setIntroduction(account.getSelfIntro());

		return new ResponseEntity<MemberPersonalPage>(memberPersonalPage, HttpStatus.OK);
	}

	// 索取個人大頭貼
	@GetMapping(value = "/findMemberImage")
	public Index queryMemberImage(@CookieValue(value = "userName", defaultValue = "Atta") String userName) {
		Account account = _memberAccountService.findByEmail(userName);

		Index index = new Index();
		index.setNickName(account.getNickName());
		index.setUserImgSrc(account.getSrc());
		return index;
	}

}
