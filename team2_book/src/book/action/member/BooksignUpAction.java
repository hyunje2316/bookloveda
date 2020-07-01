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

public class BooksignUpAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		ActionForward forword = new ActionForward();
		
		BookDAO dao = new BookDAO();
		BookMemberVO vo = new BookMemberVO();
		
		dao.confirmID(request.getParameter("M_ID"));
		PrintWriter out = response.getWriter();
		
		try {
			
			int check = dao.confirmID(request.getParameter("M_ID"));
			System.out.println(check);
			
			if(check == 1){
				
				response.setContentType("text/html; charset=UTF-8");
				out.print("<script>");
				out.print("alert('이미 사용중인 아이디 입니다');");
				out.print("location.href='./signUp.book'"); //가입페이지로 다시 보냄
				out.print("</script>");
				out.close();


			}else {
			System.out.println("중복아님..");
			vo.setM_id(request.getParameter("M_ID"));
			vo.setM_pwd(request.getParameter("M_PWD"));
			vo.setM_name(request.getParameter("M_NAME"));
			vo.setM_address(request.getParameter("M_ADDRESS"));
			
			int result =  dao.BooksignUp(vo);
			

			if( result == 0) {
				System.out.println("회원 가입 실패");
				request.setAttribute("result", result);
				forword.setRedirect(true);
				forword.setPath("./signUp.book");
				
			}
			System.out.println("회원 가입 완료");
			response.setContentType("text/html; charset=UTF-8");
			
			out.print("<script>");
			out.print("alert('가입이 완료되었습니다');");
			out.print("location.href='./loginForm.book'"); //다시 리스트로 보낸다
			out.print("</script>");
			out.close();
			return forword;
			} //end if
		} catch (Exception e) {
			e.printStackTrace();
		} // try end

		return null;
	}

}
