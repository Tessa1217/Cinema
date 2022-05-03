package co.my.cinema.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {

	private static DataSource ds = new DataSource();

	private DataSource() {

	}

	public static DataSource getInstance() {
		return ds;
	}

	private Connection conn;
	private String driver;
	private String url;
	private String id;
	private String pwd;

	public Connection getConnection() {
		Properties properties = new Properties();
		String resource = getClass().getResource("/db.properties").getPath();
		try {
			properties.load(new FileReader(resource));
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			id = properties.getProperty("id");
			pwd = properties.getProperty("pwd");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pwd);
			System.out.println("DB 연결 성공");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("DB not connected");
			e.printStackTrace();
		}
		return conn;
	}

}
