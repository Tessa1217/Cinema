package co.my.cinema.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import co.my.cinema.dto.MovieInfo;

public class MovieSearchService {

	public static final String key = "025dc7ef772dc93d0768f7bd33039006";

	public static MovieInfo getMovieInfo() {

		StringBuilder sb = new StringBuilder();
		String movieCd = "20212725";

		String serviceURL = "http://www.kobis.or.kr/kobisopenapi/" + "webservice/rest/movie/searchMovieInfo.json?";
		String paramURL = "key=" + key + "&movieCd=" + movieCd;

		try {

			String request = serviceURL + paramURL;

			URL url = new URL(request);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-type", "application/json");

			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				br.close();
			} else {
				System.out.println(con.getResponseMessage());
			}
			con.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String jsonResult = sb.toString();
		System.out.println(jsonResult);
		MovieInfo movie = getMovieInfo(jsonResult);

		return movie;
	}

	private static MovieInfo getMovieInfo(String jsonData) {
		MovieInfo movie = new MovieInfo();

		JSONParser parser = new JSONParser();
		JSONObject info = null;
		try {
			info = (JSONObject) parser.parse(jsonData);
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}

		JSONObject movieResult = (JSONObject) info.get("movieInfoResult");
		JSONObject movieInfo = (JSONObject) movieResult.get("movieInfo");

		for (int i = 0; i < movieInfo.size(); i++) {
			movie.setMovieCd((String) movieInfo.get("movieCd"));
			movie.setMovieNm((String) movieInfo.get("movieNm"));
			movie.setShowTm((String) movieInfo.get("showTm"));
			movie.setOpenDt((String) movieInfo.get("openDt"));
		}

		JSONArray genres = (JSONArray) movieInfo.get("genres");
		String genreStr = "";
		for (int i = 0; i < genres.size(); i++) {
			JSONObject genre = (JSONObject) genres.get(i);
			genreStr += (String) genre.get("genreNm");
			if (i < genres.size() - 1) {
				genreStr += ", ";
			}
		}
		movie.setGenreNm(genreStr);

		JSONArray audits = (JSONArray) movieInfo.get("audits");
		for (int i = 0; i < audits.size(); i++) {
			JSONObject audit = (JSONObject) audits.get(i);
			movie.setWatchGradeNm((String) audit.get("watchGradeNm"));
		}

		JSONArray directors = (JSONArray) movieInfo.get("directors");
		for (int i = 0; i < directors.size(); i++) {
			JSONObject director = (JSONObject) directors.get(i);
			movie.setDirectorNm((String) director.get("peopleNm"));
		}

		String actorList = "";
		JSONArray actors = (JSONArray) movieInfo.get("actors");
		for (int i = 0; i < actors.size(); i++) {
			JSONObject actor = (JSONObject) actors.get(i);

			actorList += (String) actor.get("peopleNm");
			if (i == 2) {
				break;
			}
			actorList += ", ";
		}
		movie.setActorNm(actorList);

		//
//
//
//				// directors
//				private String peopleNm;
		return movie;

	}

}
