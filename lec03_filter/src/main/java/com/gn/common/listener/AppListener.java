package com.gn.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//@WebListener
public class AppListener implements ServletContextListener {
	public AppListener() {
		super();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("====== 웹 애플리케이션이 종료되는 시점 ======");
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("====== 웹 애플리케이션이 시작되는 시점 ======");
	}
}
