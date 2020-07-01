package book.action.member;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.model.BookDAO;
import book.model.BookMemberVO;
import book.model.BookReviewVO;

import book.controller.Action;
import book.controller.ActionForward;


public class BookUserDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		BookDAO dao = new BookDAO();
		BookMemberVO vo = new BookMemberVO();
		PrintWriter out = response.getWriter();
		boolean result;
		

		String id = request.getParameter("M_ID2");
		String pwd = request.getParameter("M_PWD");
		



		try {
		vo.setM_id(id);
		vo.setM_pwd(pwd);
		result = dao.BookUserDelete(vo);
		if(result == false) {
			System.out.println("유저 삭제 실패");
			out.print("<script>");
			out.print("alert('비밀번호가 일치하지 않습니다');");
			out.print("location.href='./userdel.book'");
			out.print("</script>");
			out.close();
		}
		System.out.println("유저 삭제 완료");
		out.print("<script>");
		out.print("alert('탈퇴가 완료되었습니다');");
		out.print("location.href='./logout.book'"); //로그아웃 보내서 세션초기화
		out.print("</script>");
		out.close();
					
	} catch (Exception e) {
		e.printStackTrace();
		
	}
		
		
		return null;
	}

}
