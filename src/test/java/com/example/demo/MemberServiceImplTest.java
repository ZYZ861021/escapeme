package com.example.demo;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.Common.RandomNumber;
import com.example.demo.Common.RandomNumberImpl;
import com.example.demo.model.DB.Account;
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
import com.example.demo.serviceimpl.MemberServiceImpl;
import com.example.demo.serviceimpl.TaskformServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceImplTest {

	@Mock
	private Sha256Helper _sha256Helper;

	@Mock
	private MemberServiceRepository _memberServiceRepository;

	@Mock
	private PostingSystemRepository _postingSystemRepository;

	@Mock
	private MessageRepository _messageRepository;

	@Mock
	private TaskRepository _taskRepository;

	@Mock
	private MailService _mailService;

	@Mock
	private RandomNumber _randomNumber;

	@Mock
	private LikeFunctionRepository _likeFunctionRepository;

	private MemberService _sut;

	// Select中文字串，從TaskForm表單Content欄位搜尋資訊;
	@BeforeEach
	public void TestInitalize() {
		MockitoAnnotations.initMocks(this);

		// 初始化目標物件
		_sut = new MemberServiceImpl(_sha256Helper, _memberServiceRepository, _postingSystemRepository,
				_messageRepository, _taskRepository, _mailService, _randomNumber, _likeFunctionRepository);

	}

//	@ParameterizedTest
//	@ValueSource(strings = {"", "  "})
//	void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input) {
//	    assertTrue(Strings.isBlank(input));
//	}

	@Test
	public void signUp_輸入已註冊過的信箱_傳回字串信箱重複() {
		// arrange
		/*
		 * - 初始化目標物件 - 初始化方法參數 - 建立模擬物件行為 - 設定環境變數期望結果
		 */

		Account account = new Account();
		account.setEmail("asd990055@gmail.com");
		// 建立模擬物件行為
		when(_memberServiceRepository.findByEmail("asd990055@gmail.com")).thenReturn(account);

		// 設定環境變數期望結果
		String expected = null;

		// act
		// 實際呼叫測試目標物件的方法
		String actual = _sut.signUp(account);

		// assert
		// 驗證目標物件是否如同預期運作
		Assertions.assertEquals(expected, actual);
	}

	@Test
	public void signUp_輸入完整訊息_傳回字串完成註冊() {
		// arrange
		/*
		 * - 初始化目標物件 - 初始化方法參數 - 建立模擬物件行為 - 設定環境變數期望結果
		 */

		Account account = new Account();

		// 建立模擬物件行為
		when(_memberServiceRepository.findByEmail("asd990055@gmail.com")).thenReturn(account);

		account.setPassword(_sha256Helper.Encryption("21321321"));

		account.setMail("123456");

		when(_memberServiceRepository.save(account)).thenReturn(account);

		_mailService.sendMail("b@gmail.com", "ESCapeMe驗證信",
				"您好，" + "歡迎您加入ESCapeMe這個大家族以下為您的驗證碼" + "『" + "123456" + "』");

		// 設定環境變數期望結果
		String expected = "完成註冊";

		// act
		// 實際呼叫測試目標物件的方法
		String actual = _sut.signUp(account);

		// assert
		// 驗證目標物件是否如同預期運作
		verify(_mailService, times(1)).sendMail("b@gmail.com", "ESCapeMe驗證信",
				"您好，" + "歡迎您加入ESCapeMe這個大家族以下為您的驗證碼" + "『" + "123456" + "』");
		Assertions.assertEquals(expected, actual);
	}

	@Test
	public void findByEmailAndPassword_輸入前端傳回的信箱_傳回信箱空值() {
		// arrange
		/*
		 * - 初始化目標物件 - 初始化方法參數 - 建立模擬物件行為 - 設定環境變數期望結果
		 */

		Account account = new Account();
		
		// 建立模擬物件行為 // .thenReturn該方法你期望的回傳值 //verify return void
		when(_memberServiceRepository.findByEmail("b@gmail.com")).thenReturn(null);
		account.setEmail(null);
		// 設定環境變數期望結果
		 Account expected = account;

		// act
		// 實際呼叫測試目標物件的方法
		Account actual = _sut.findByEmailAndPassword(account);

		// assert
		// 驗證目標物件是否如同預期運作
		// 2個類別大約符合，現在是用在記憶體位置不同但回傳值相同的時候
		// Assertions.assertTrue(new ReflectionEquals(expected).matches(actual));
		Assertions.assertEquals(expected, actual);
		//Assertions.assertNull(actual);
	}

	@Test
	public void findByEmailAndPassword_輸入前端傳回的密碼_傳回密碼為null() {
		// Arrange
		// - 初始化目標物件 - 初始化方法參數 - 建立模擬物件行為 - 設定環境變數期望結果
		Account account = new Account();
		account.setPassword("123456");
		account.setEmail("b@gmail.com");

		Account DBresult = new Account();
		DBresult.setPassword("1234132");
		DBresult.setEmail("b@gmail.com");
		
		when(_memberServiceRepository.findByEmail("b@gmail.com")).thenReturn(DBresult);
		// 建立模擬物件行為
		when(_sha256Helper.Encryption("123456")).thenReturn("123132");

		// 設定環境變數期望結果
		 Account expected = account;

		// Act
		// 實際呼叫測試目標物件的方法
		Account actual = _sut.findByEmailAndPassword(account);

		// Assert
		// 驗證目標物件是否如同預期運作
		Assertions.assertEquals(expected, actual);
	}

	@Test
	public void findByEmailAndPassword_輸入前端傳回的正確信息_傳回正確的帳戶信息() {
		// arrange
		/*
		 * - 初始化目標物件 - 初始化方法參數 - 建立模擬物件行為 - 設定環境變數期望結果
		 */

		Account account = new Account();
		account.setPassword("a123456a");
		account.setEmail("b@gmail.com");

		Account DBresult = new Account();
		DBresult.setPassword("a123s456a");
		DBresult.setEmail("b@gmail.com");
		// 建立模擬物件行為
		when(_memberServiceRepository.findByEmail("b@gmail.com")).thenReturn(DBresult);

		when(_sha256Helper.Encryption("a123456a")).thenReturn("a123s456a");
		// 設定環境變數期望結果
		Account expected = DBresult;

		// act
		// 實際呼叫測試目標物件的方法
		Account actual = _sut.findByEmailAndPassword(account);

		// assert
		// 驗證目標物件是否如同預期運作

		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	public void CheckMail_輸入用戶信箱確認驗證碼是否驗證_傳回null() {
		// arrange
		/*
		 * - 初始化目標物件 - 初始化方法參數 - 建立模擬物件行為 - 設定環境變數期望結果
		 */

		Account account = new Account();
		account.setEmail("b@gmail.com");
		account.setMail("132465");

		Account DBresult = new Account();
		DBresult.setEmail("b@gmail.com");
		DBresult.setMail("111555");
		// 建立模擬物件行為
		when(_memberServiceRepository.findByEmail("b@gmail.com")).thenReturn(DBresult);
		
		account.setMail("165152");

		
		// 設定環境變數期望結果
		String expected = "錯誤驗證碼";

		// act
		// 實際呼叫測試目標物件的方法
		String actual = _sut.CheckMail(account);

		// assert
		// 驗證目標物件是否如同預期運作

		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	public void CheckMail_用戶信箱已驗證碼_刷新setMail等於1() {
		// arrange	
		// - 初始化目標物件 
		// - 初始化方法參數 
		// - 建立模擬物件行為 
		// - 設定環境變數期望結果
		Account account = new Account();
		account.setEmail("b@gmail.com");
		account.setMail("132465");

		Account DBresult = new Account();
		DBresult.setEmail("b@gmail.com");
		DBresult.setMail("132465");
		// 建立模擬物件行為
		when(_memberServiceRepository.findByEmail("b@gmail.com")).thenReturn(DBresult);
			
		// 設定環境變數期望結果
		String expected = "驗證成功";

		// act
		// 實際呼叫測試目標物件的方法
		String actual = _sut.CheckMail(account);

		// assert
		// 驗證目標物件是否如同預期運作
		Assertions.assertEquals(expected, actual);
	}

}
