package co.my.cinema.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.my.cinema.dao.DataSource;
import co.my.cinema.dto.RoomVO;
import co.my.cinema.service.RoomService;

public class RoomServiceImpl implements RoomService {
	DataSource ds = DataSource.getInstance();
	Connection conn = ds.getConnection();
	PreparedStatement psmt;
	ResultSet rs;

	@Override
	public List<RoomVO> listRoom() {
		String sql = "SELECT * FRON ROOM";
		List<RoomVO> rooms = new ArrayList<RoomVO>();
		RoomVO room = new RoomVO();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				room.setRoomNo(rs.getInt("room_no"));
				room.setTheaterName(rs.getString("theater_name"));
				rooms.add(room);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rooms;
	}

	@Override
	public RoomVO selectRoom(RoomVO room) {
		String sql = "SELECT * FROM ROOM WHERE THEATER_NAME = ?";
		RoomVO tRoom = new RoomVO();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, room.getTheaterName());
			rs = psmt.executeQuery();

			while (rs.next()) {
				tRoom.setRoomNo(rs.getInt("room_no"));
				tRoom.setTheaterName(rs.getString("theater_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tRoom;
	}

	@Override
	public int insertRoom(RoomVO room) {
		int insert = -1;
		String sql = "INSERT INTO ROOM VALUES(?, ?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, room.getRoomNo());
			psmt.setString(2, room.getTheaterName());
			insert = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insert;
	}

	@Override
	public int updateRoom(RoomVO room) {
		int update = -1;
		String sql = "UPDATE ROOM SET ROOM_NO = ? WHERE THEATER_NAME = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, room.getRoomNo());
			psmt.setString(2, room.getTheaterName());
			update = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return update;
	}

	@Override
	public int deleteRoom(RoomVO room) {
		int delete = -1;
		String sql = "DELETE FROM ROOM WHERE ROOM_NO = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, room.getRoomNo());
			delete = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return delete;
	}

}
