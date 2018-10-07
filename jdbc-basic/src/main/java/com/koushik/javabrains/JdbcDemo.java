package com.koushik.javabrains;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.koushik.javabrains.dao.JdbcDaoImpl;
import com.koushik.javabrains.model.Circle;

public class JdbcDemo {

	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext context = null;
		try {
		context = new ClassPathXmlApplicationContext("spring.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		Circle circle = new JdbcDaoImpl().getCircle(1);
		System.out.println(circle.getName());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if(context!=null) {
				context.close();
			}
		}
	}

}
