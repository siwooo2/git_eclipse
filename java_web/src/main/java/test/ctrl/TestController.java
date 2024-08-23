package test.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import test.domain.RequestTestDTO;
import test.domain.ResponseTestDTO;
import test.service.TestService;

@WebServlet("/index")
public class TestController extends HttpServlet {
	
	private TestService service ;
	
	public void init() {
		System.out.println("인스턴스 생성시 딱 한 번 호출되는 콜백함수입니다,");
		service = new TestService();
	}
	
	public void destory() {
		System.out.println("메모리상에서 allocation 될 때 한 번 호출되는 콜백함수입니다,");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		process(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		System.out.println("debug >>> client path : " + uri);
		System.out.println("debug >>> client request method : " + request.getMethod());
		////////////
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		RequestTestDTO params = new RequestTestDTO();
		params.setId(id); params.setPwd(pwd);
		////////////////////////////////////////////
		ResponseTestDTO result = service.login(params);
		if(result != null) {
			/////////// 데이터를 쉐어하기 위해서 세션 객체를 만들고 데이터를 심는다. 
			// (다른 jsp 파일에서 result 를 {loginUser} 로 대체해서 사용할 수 있음) ex) {loginUser.name} => result.name 과 동일
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", result);
			////////////
			response.sendRedirect("ok.jsp");
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("errMsg", "입력데이터 확인 부탁드립니다.");
			response.sendRedirect("error.jsp");
		}
	}
}
