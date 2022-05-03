package co.my.cinema.service;

import java.util.List;

import co.my.cinema.dto.SeatVO;

public interface SeatService {

	List<SeatVO> seats();

	SeatVO selectSeat(SeatVO seat);

	int insertSeat(SeatVO seat);

	int updateSeat(SeatVO seat);

}
