package com.koushik.javabrains.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.koushik.javabrains.model.Circle;

public class JdbcDaoImpl {

	public Circle getCircle(int circleId) {
		Connection conn = null;

		String driver = "org.h2.Driver";

		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
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
			throw new RuntimeException(ex);
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
