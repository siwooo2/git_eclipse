package test.dao;

import test.domain.RequestTestDTO;
import test.domain.ResponseTestDTO;

// Dao 는 값을 반환하는 역할
public class TestDao {
	
	private ResponseTestDTO response;
	
	public TestDao() {
		response = new ResponseTestDTO();
	}
	
	
	public ResponseTestDTO loginRow(RequestTestDTO params) {
		System.out.println("debug >>> dao login params = " + params);
		if(params.getId().equals("jslim") && params.getPwd().equals("jslim")) {
			response.setName("섭섭해");
			return response;
		}else {
			return null;
		}
	}
}
