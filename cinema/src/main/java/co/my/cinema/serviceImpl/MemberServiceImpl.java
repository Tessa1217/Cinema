package co.my.cinema.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.my.cinema.dao.DataSource;
import co.my.cinema.dto.MemberVO;
import co.my.cinema.service.MemberService;

public class MemberServiceImpl implements MemberService {

	private DataSource ds = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public int signUp(MemberVO vo) {
		int insert = -1;
		vo.setMemberCode(memberCode());
		String sql = "INSERT INTO MEMBER VALUES(?, ?, ?, ?, ?, DEFAULT)";
		try {
			conn = ds.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberCode());
			psmt.setString(2, vo.getMemberId());
			psmt.setString(3, vo.getMemberPwd());
			psmt.setString(4, vo.getMemberName());
			psmt.setString(5, vo.getMemberPhone());
			insert = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return insert;

	}

	private String memberCode() {
		String code = "";
		String sql = "SELECT DBMS_RANDOM.STRING('X', 10) FROM dual";
		try {
			conn = ds.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				code = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return code;
	}

	@Override
	public MemberVO LogIn(MemberVO vo) {
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_PWD = ?";
		try {
			conn = ds.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			psmt.setString(2, vo.getMemberPwd());
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo = new MemberVO();
				vo.setMemberCode(rs.getString("MEMBER_CODE"));
				vo.setMemberId(rs.getString("MEMBER_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	@Override
	public boolean LogOut(MemberVO vo) {
		boolean logout = false;
		return logout;
	}

	private void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
