package test.dao;

import test.domain.RequestJoinDTO;

public class JoinDao {
	
	public JoinDao() {
	}
	
	public String joinRow(RequestJoinDTO params){
		System.out.println("Debug >>> params : " + params);
		return "회원가입이 완료되었습니다...";
	}

}
