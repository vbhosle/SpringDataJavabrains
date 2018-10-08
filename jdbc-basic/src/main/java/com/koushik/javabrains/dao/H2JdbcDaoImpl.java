package com.koushik.javabrains.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.koushik.javabrains.model.Circle;

@Component
public class H2JdbcDaoImpl {

	@Autowired
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
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
			//stmt.executeUpdate("insert into CIRCLE values(1, 'First Circle')");
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
	
	public int getCircleCount() {
		String sql = "select count(*) from circle";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}
	
	public String getCircleName(int circleId) {
		String sql = "select name from circle where id = ?";
		String name = jdbcTemplate.queryForObject(sql, String.class, 1);
		return name;
	}
	
	public Circle getCircleById(int circleId) {
		String sql = "select * from circle where id = ?";
		Circle circle = jdbcTemplate.queryForObject(sql, new Object[] {circleId}, new CircleMapper());
		return circle;
	}
	
	public List<Circle> getAllCircles(){
		String sql = "select * from circle";
		return jdbcTemplate.query(sql, new CircleMapper());
	}

	private static final class CircleMapper implements RowMapper<Circle>{

		public Circle mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Circle(rs.getInt("id"), rs.getString("name"));
		}
		
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
}
