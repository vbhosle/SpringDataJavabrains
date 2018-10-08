package com.koushik.javabrains.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.koushik.javabrains.model.Circle;

@Component
public class H2JdbcDaoImpl {

	@Autowired
	private DataSource dataSource;
	
	public Circle getCircle(int circleId) {
		Connection conn = null;
		Server server = null;

		try {
			conn = dataSource.getConnection();
			// create Statement object
			Statement stmt = conn.createStatement();

			// // send sql command
			// stmt.executeUpdate("create table CIRCLE (ID integer, name char(50))");
			// stmt = conn.createStatement();
			// send sql command
			stmt.executeUpdate("insert into CIRCLE values(1, 'First Circle')");
			PreparedStatement ps = conn.prepareStatement("select * from circle where id = ?");
			ps.setInt(1, circleId);

			Circle circle = null;
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				circle = new Circle(circleId, rs.getString("name"));
			}

			rs.close();
			ps.close();

			return circle;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
}
