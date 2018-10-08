package com.koushik.javabrains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.koushik.javabrains.dao.SimpleJdbcDaoImpl;

public class JdbcDAODemo {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		SimpleJdbcDaoImpl dao = context.getBean("simpleJdbcDaoImpl", SimpleJdbcDaoImpl.class);
		System.out.println(dao.getCircleCount());
	}

}
