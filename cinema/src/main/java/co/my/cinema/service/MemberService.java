package co.my.cinema.service;

import co.my.cinema.dto.MemberVO;

public interface MemberService {

	int signUp(MemberVO vo);

	MemberVO LogIn(MemberVO vo);

	boolean LogOut(MemberVO vo);

}
