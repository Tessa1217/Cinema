package co.my.cinema.dto;

import lombok.Data;

@Data

public class MovieInfo {

	// movieInfo
	private String movieCd;
	private String movieNm;
	private String showTm;
	private String openDt;

	// genres
	private String genreNm;

	// directors
	private String directorNm;

	// actors
	private String actorNm;

	// audits
	private String watchGradeNm;

	@Override
	public String toString() {
		String info = "=========== 영화 정보 ===========\n";
		info += "영화 제목: " + movieNm;
		info += "\n상영시간 : " + showTm + "분";
		info += "\n장르 : " + genreNm;
		info += "\n개봉일: " + openDt.substring(0, 4) + "년 " + openDt.substring(4, 6) + "월 " + openDt.substring(6) + "일, "
				+ watchGradeNm;
		info += "\n배우: " + actorNm;
		info += "\n감독: " + directorNm;
		info += "\n===============================\n";
		return info;

	}

}
