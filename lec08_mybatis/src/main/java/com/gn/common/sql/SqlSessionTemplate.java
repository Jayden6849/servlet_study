package com.gn.common.sql;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionTemplate {
	public static SqlSession getSqlSession(boolean flag) {
		SqlSession session = null;
		
		try {
			// 1. mybatis-config.xml의 설정 정보를 읽어오기
			String path = "/mybatis-config.xml";
			InputStream is = Resources.getResourceAsStream(path);
			
			// 2. SqlSessionFactoryBuilder 객체 생성
			SqlSessionFactoryBuilder sfb = new SqlSessionFactoryBuilder();
			
			// 3. SqlSessionFactory 객체 생성
			SqlSessionFactory factory = sfb.build(is);
			
			// 4. SqlSession 객체 생성
			// openSession의 매개변수는 AutoCommit를 켜는지 끄는지 결정 -> true(default) || false
			// 1. 매개변수를 안 쓴다 = false :: AutoCommit을 안 하겠다 = 트랜잭션을 쓰겠다.
			// 2. 매개변수가 false :: 위와 같음
			// 3. 매개변수를 true :: AutoCommit을 하겠다
			session = factory.openSession(flag);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return session;
	}
}
