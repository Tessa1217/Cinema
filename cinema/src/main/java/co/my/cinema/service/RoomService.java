package co.my.cinema.service;

import java.util.List;

import co.my.cinema.dto.RoomVO;

public interface RoomService {

	List<RoomVO> listRoom();

	RoomVO selectRoom(RoomVO room);

	int insertRoom(RoomVO room);

	int updateRoom(RoomVO room);

	int deleteRoom(RoomVO room);

}
