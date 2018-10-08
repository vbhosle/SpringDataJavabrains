package com.koushik.javabrains;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.h2.tools.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.koushik.javabrains.dao.H2JdbcDaoImpl;
import com.koushik.javabrains.dao.JdbcDaoImpl;
import com.koushik.javabrains.model.Circle;

public class JdbcDemo {

	public static void main(String[] args) throws IOException {
		ApplicationContext context = null;
		try {
		context = new ClassPathXmlApplicationContext("spring.xml");
		Server server = context.getBean("h2WebServer", Server.class);
		System.out.println(server.getURL());
		Circle circle = new H2JdbcDaoImpl().getCircle(1);
		System.out.println(circle.getName());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
