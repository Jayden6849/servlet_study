package com.gn.common.filter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class PassWordEncodingWrapper extends HttpServletRequestWrapper{
	public PassWordEncodingWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		if(name.contains("member_pw")) {
			String ori = super.getParameter(name);
			System.out.println("암호화 전 : " + ori);
			String enc = getSHA512(ori);
			System.out.println("암호화 후 : " + enc);
			return enc;
		}
			return super.getParameter(name);
	}
	
	// 단방향 암호화 메소드를 하나 만들 것임
	private String getSHA512(String str) {
		// 1. 암호화 처리 클래스 선언
		MessageDigest md = null;
		
		try {
			// 2. 적용할 알고리즘을 선택해서 인스턴스화
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		// 3. String을 byte[]로 잘라서 담음
		byte[] strByte = str.getBytes();
		
		// 4.자른 데이터를 암호화 처리 
		md.update(strByte);
		
		// 5. 암호화 처리된 값을 byte[]로 가져옴
		byte[] encrytByte = md.digest(strByte);
		
		// 6. Base64인코더를 이용해서 byte[]를 String으로 바꾼 후 반환함
		return Base64.getEncoder().encodeToString(encrytByte);
	}
}
