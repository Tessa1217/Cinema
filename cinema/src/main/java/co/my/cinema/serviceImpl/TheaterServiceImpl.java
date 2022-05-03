package co.my.cinema.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.my.cinema.dao.DataSource;
import co.my.cinema.dto.TheaterVO;
import co.my.cinema.service.TheaterService;

public class TheaterServiceImpl implements TheaterService {
	private DataSource ds = DataSource.getInstance();
	private Connection conn = ds.getConnection();
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<TheaterVO> selectListTheater() {
		List<TheaterVO> theaters = new ArrayList<TheaterVO>();
		TheaterVO theater;
		String sql = "SELECT * FROM THEATER";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				theater = new TheaterVO();
				theater.setTheaterName("theater_name");
				theater.setTheaterLocation("theater_location");
				theaters.add(theater);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return theaters;

	}

	@Override
	public TheaterVO selectTheater(TheaterVO theater) {
		return null;
	}

	@Override
	public int insertTheater(TheaterVO theater) {
		TheaterVO newTheater = new TheaterVO();
		int update = 0;
		String sql = "INSERT INTO THEATER VALUES(?, ?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, theater.getTheaterName());
			psmt.setString(2, theater.getTheaterLocation());
			update = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return update;

	}

	@Override
	public int updateTheater(TheaterVO theater) {
		return 0;
	}

	@Override
	public int deleteTheater(TheaterVO theater) {
		return 0;
	}

}
