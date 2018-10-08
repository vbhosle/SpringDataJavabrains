package com.koushik.javabrains;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
			
			System.out.println(dao.getCircleCount());
			System.out.println(dao.getCircleName(1));
			System.out.println(dao.getCircleById(1).getName());
			
//			dao.insertCircle(new Circle(2,"Second Circle"));
//			System.out.println(dao.getAllCircles().size());
			dao.createTriangleTable();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
