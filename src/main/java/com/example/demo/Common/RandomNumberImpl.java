package com.example.demo.Common;

import java.util.Random;

import org.springframework.stereotype.Service;



@Service
public class RandomNumberImpl implements RandomNumber{
	
	public String getRandomString() {
        int length = 6;
        // 定義一個字串（A-Z，a-z，0-9，扣除0OI1）即58位；
        String str = "0123456789";
        // 由Random生成亂數
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        // 長度為幾就回圈幾次
        for (int i = 0; i < length; ++i) {
            // 產生0-58的數字
            int number = random.nextInt(str.length());
            // 將產生的數字通過length次承載到sb中
            sb.append(str.charAt(number));
        }
        // 將承載的字符轉換成字串
        return sb.toString();
    }
}


