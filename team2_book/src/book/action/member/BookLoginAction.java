package book.action.member;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import book.controller.Action;
import book.controller.ActionForward;
import book.model.BookDAO;
import book.model.BookMemberVO;
import book.model.BookReviewVO;

public class BookLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		ActionForward forword = new ActionForward();
		
		BookDAO dao = new BookDAO();
		BookMemberVO vo = new BookMemberVO();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memID");
		String pwd = (String)session.getAttribute("memPWD");
		dao.userCheck(id,pwd);
		PrintWriter out = response.getWriter();
		
		try {
			
			int check = dao.userCheck(id,pwd);
			System.out.println(check);
			
			if(check == 1){
				response.setContentType("text/html; charset=UTF-8");
				out.print("<script>");
				out.print("location.href='./home.book'"); //메인으로
				out.print("</script>");
				out.close();


			}else {
				response.setContentType("text/html; charset=UTF-8");
				session.invalidate(); // 받아온 아이디 세션을 초기화 한 후 보냄
				out.print("<script>");
				out.print("alert('ID 또는 PassWard가 다릅니다');");
				out.print("location.href='./loginForm.book'"); //가입페이지로 다시 보냄
				out.print("</script>");
				out.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} // try end

		return null;
	}

}
