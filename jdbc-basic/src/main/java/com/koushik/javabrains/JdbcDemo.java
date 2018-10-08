package com.koushik.javabrains;

import java.io.IOException;

import org.h2.tools.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.koushik.javabrains.dao.H2JdbcDaoImpl;
import com.koushik.javabrains.model.Circle;

public class JdbcDemo {

	public static void main(String[] args) throws IOException {
		ApplicationContext context = null;
		try {
			context = new ClassPathXmlApplicationContext("spring.xml");
			
			//get the h2 console url
			Server server = context.getBean("h2WebServer", Server.class);
			System.out.println(server.getURL());
			
			//get DAO
			H2JdbcDaoImpl dao = context.getBean("h2JdbcDaoImpl", H2JdbcDaoImpl.class);
			Circle circle = dao.getCircle(1);
			System.out.println(circle.getName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
