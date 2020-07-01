package book.action.member;


import book.controller.Action;
import book.controller.ActionForward;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.model.BookDAO;
import book.model.BookMemberVO;
import book.model.BookReviewVO;


public class BookUserUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		ActionForward forword = new ActionForward(); //객체생성
		int result ;
		
		
		BookDAO dao = new BookDAO();
		BookMemberVO vo = new BookMemberVO();
		
		String id= request.getParameter("M_ID");
		String pwd= request.getParameter("M_PWD");

		int userCheck = dao.userCheck(id, pwd);
		
		
		
		if(userCheck == 0) { //가져온게 안맞다면 
			response.setContentType("text/html; charset=UTF-8");
			out.print("<script>");
			out.print("alert('패스워드가 틀립니다');");
			out.print(" history.go(-1)"); 
			out.print("</script>");
			out.close();
			return null;
		} // end if
		
		try {
			vo.setM_name(request.getParameter("M_NAME"));
			vo.setM_pwd(request.getParameter("M_UPDATEPWD"));
			vo.setM_address(request.getParameter("M_ADDRESS"));
			vo.setM_id(request.getParameter("M_ID"));

			result = dao.BookUserUpdate(vo);

			if(result == 0) {
				System.out.println("회원정보 수정 실패");
				out.print("<script>");
				out.print("alert('회원정보 수정 실패');");
				out.print("history.go(-1)"); //다시 리스트로 보낸다
				out.print("</script>");
				out.close();
				return null;
			}
			System.out.println("회원정보 수정 완료");
			out.print("<script>");
			out.print("alert('회원정보 수정 완료');");
			out.print("location.href='./loginForm.book'"); //다시 리스트로 보낸다
			out.print("</script>");
			out.close();
				
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return null;
	}

}
