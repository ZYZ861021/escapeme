package com.example.demo.service.helper;

public interface Sha256Helper {
	//public String Encryption(String str);
	
	public byte[] getSHA(String input);
	
	public String toHexString(byte[] hash);
	
	public String Encryption(String str);
	
	
}
