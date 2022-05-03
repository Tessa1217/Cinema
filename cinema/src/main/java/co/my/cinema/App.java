package co.my.cinema;

import java.util.List;

import co.my.cinema.dto.MemberVO;
import co.my.cinema.dto.SeatVO;
import co.my.cinema.service.MemberService;
import co.my.cinema.serviceImpl.MemberServiceImpl;
import co.my.cinema.serviceImpl.SeatServiceImpl;

public class App {
	public static void main(String[] args) {
//		TheaterServiceImpl tsml = new TheaterServiceImpl();
//
//		TheaterVO theater = new TheaterVO();
//		theater.setTheaterName("프리미엄만경점");
//		theater.setTheaterLocation("대구광역시 중구 종로");
//		int update = tsml.insertTheater(theater);
//		if (update != 0) {
//			System.out.println(update + "행이 추가되었습니다.");
//		}
//
//		RoomServiceImpl rsml = new RoomServiceImpl();
//		int cnt = 0;
//		for (int i = 1; i <= 2; i++) {
//			RoomVO room = new RoomVO();
//			room.setRoomNo(i);
//			room.setTheaterName("프리미엄만경점");
//			cnt += rsml.insertRoom(room);
//		}
//		System.out.println(cnt + "행이 삽입되었습니다.");

		SeatServiceImpl ssimpl = new SeatServiceImpl();
//		int cnt = 0;
//		SeatVO seat = new SeatVO();
//		seat.setSeatReserve("N");
//		for (int i = 1; i <= 2; i++) {
//			seat.setRoomNo(i);
//			for (int j = 70; j < 75; j++) {
//				for (int k = 1; k < 9; k++) {
//					char letter = (char) j;
//					String s = String.valueOf(letter) + k;
//					seat.setSeatNo(s);
//					cnt += ssimpl.insertSeat(seat);
//				}
//			}
//		}
//		System.out.println(cnt + "행 삽입");

		// 영화관 좌석 모습
		List<SeatVO> seats = ssimpl.seats();
		String avail = "";
		System.out.println("======================SCREEN=======================\n");
		for (SeatVO seat : seats) {
			if (seat.getRoomNo() == 1) {
				if (seat.getSeatReserve().equals("N")) {
					avail = "O";
				} else if (seat.getSeatReserve().equals("Y")) {
					avail = "X";
				}
				System.out.print(seat.getSeatNo() + "[" + avail + "] ");
				if (seat.getSeatNo().substring(1).equals("2") || seat.getSeatNo().substring(1).equals("6")) {
					System.out.print("  ");
				}
				if (Integer.parseInt(seat.getSeatNo().substring(1)) == 8) {
					System.out.println();
					if (seat.getSeatNo().equals("E8")) {
						System.out.println();
					}
				}
			}

		}
		System.out.println("\n===================================================");

		MemberService ms = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setMemberId("user1");
		vo.setMemberPwd("user1234");
		vo.setMemberName("권유진");
		vo.setMemberPhone("010-9717-9999");
		ms.signUp(vo);

	}

}