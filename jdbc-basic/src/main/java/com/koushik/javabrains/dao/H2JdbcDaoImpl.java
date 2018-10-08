package com.koushik.javabrains.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.Server;

import com.koushik.javabrains.model.Circle;

public class H2JdbcDaoImpl {

	public Circle getCircle(int circleId) {
		Connection conn = null;
		Server server = null;
		String driver = "org.h2.Driver";

		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection("jdbc:h2:./Database/testdb", "sa", "");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			br.read();
            // create Statement object
			Statement stmt = conn.createStatement();
                 // send sql command
			stmt.executeUpdate("create table CIRCLE (ID integer, name char(50))");
			stmt = conn.createStatement();
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
