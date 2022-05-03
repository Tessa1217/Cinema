package co.my.cinema.service;

import java.util.List;

import co.my.cinema.dto.TheaterVO;

public interface TheaterService {

	List<TheaterVO> selectListTheater();

	TheaterVO selectTheater(TheaterVO theater);

	int insertTheater(TheaterVO theater);

	int updateTheater(TheaterVO theater);

	int deleteTheater(TheaterVO theater);

}
