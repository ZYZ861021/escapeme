package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.DB.Taskform;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskformService;
import com.example.demo.serviceimpl.TaskformServiceImpl;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskformServiceImplTest {
	
	@Mock
	private TaskRepository _taskRepository;
	private TaskformService _sut;
		
	//Select中文字串，從TaskForm表單Content欄位搜尋資訊;
	@Before
	public void TestInitalize()
	{
		 MockitoAnnotations.initMocks(this);
		
	}
	
	@Test
	public void getContentTest_輸入空白字串_傳回空白List() {
		//arrange
		/* - 初始化目標物件
		   - 初始化方法參數
           - 建立模擬物件行為
           - 設定環境變數期望結果
		 */
		
		//建立模擬物件行為
		when(_taskRepository.queryByContent(""))
		 .thenReturn(new ArrayList<Taskform>());
		
		//初始化目標物件
		_sut = new TaskformServiceImpl(_taskRepository);
		 
		//設定環境變數期望結果
		List<Taskform> expected = new ArrayList<Taskform>();
		 
		//act
		//實際呼叫測試目標物件的方法
		List<Taskform> actual =  _sut.selectContent("");
		
		//assert
		//驗證目標物件是否如同預期運作
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	public void getContentTest_輸入自我介紹_傳回包含內容自我介紹的資料()
	{
		//arrange
		List<Taskform> repoResult = new  ArrayList<Taskform>();
		Taskform taskform = new Taskform();
		taskform.setMissionContent("自我介紹");
		repoResult.add(taskform);
		
		when(_taskRepository.queryByContent("自我介紹"))
		 .thenReturn(repoResult);
		
		_sut = new TaskformServiceImpl(_taskRepository);
		
		List<Taskform> expected =repoResult;
		
		//act
		List<Taskform> actual =  _sut.selectContent("自我介紹");
		
		//assert
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	public void getContentTest_輸入null_拋出NullPointerException()
	{
		//arrange
		//act
		//assert
		Assertions.assertThrows(NullPointerException.class, () ->  _sut.selectContent(null));
	}
	
}
