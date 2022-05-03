package co.my.cinema.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.my.cinema.dao.DataSource;
import co.my.cinema.dto.SeatVO;
import co.my.cinema.service.SeatService;

public class SeatServiceImpl implements SeatService {

	DataSource ds = DataSource.getInstance();
	Connection conn = ds.getConnection();
	PreparedStatement psmt;
	ResultSet rs;

	@Override
	public List<SeatVO> seats() {

		List<SeatVO> seats = new ArrayList<SeatVO>();
		String sql = "SELECT * FROM SEAT";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				SeatVO seat = new SeatVO();
				seat.setSeatNo(rs.getString("seat_no"));
				seat.setRoomNo(rs.getInt("room_no"));
				seat.setSeatReserve(rs.getString("seat_reserve"));
				seat.setPaymentNo(rs.getString("payment_no"));
				seats.add(seat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seats;

	}

	@Override
	public SeatVO selectSeat(SeatVO seat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertSeat(SeatVO seat) {
		int insert = -1;
		String sql = "INSERT INTO SEAT VALUES (?, ?, ?, ?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, seat.getSeatNo());
			psmt.setInt(2, seat.getRoomNo());
			psmt.setString(3, seat.getSeatReserve());
			psmt.setString(4, seat.getPaymentNo());
			insert = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insert;
	}

	@Override
	public int updateSeat(SeatVO seat) {
		// TODO Auto-generated method stub
		return 0;
	}

}
