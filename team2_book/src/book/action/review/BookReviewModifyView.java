package book.action.review;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.model.BookDAO;
import book.model.BookReviewVO;
import book.controller.Action;
import book.controller.ActionForward;

public class BookReviewModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forword = new ActionForward(); //객체생성		
		BookDAO dao = new BookDAO();
		BookReviewVO vo = new BookReviewVO();
		
		int num = Integer.parseInt(request.getParameter("num") );				
		String id = request.getParameter("id");
		
		System.out.println("모파이뷰"+num+id);


		boolean userCheck = dao.isReviewBoardWriter(num, id);
		System.out.println(userCheck);
		if(userCheck == false) { //가져온게 안맞다면 
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('수정할 권한이 없슴니다');");
			out.print("alert('꺄아아아악');");
			out.print(" history.go(-1)"); //다시 리스트로 보낸다
			out.print("</script>");
			out.close();
			return null;
		} // end if
		
		vo = dao.getReviewDetail(num);
		
		if(vo == null) {
			System.out.println("[수정] 상세보기 실패");
			return null;
		}
		System.out.println("[수정] 상세보기 성공");
		
		request.setAttribute("vo", vo);
		
		forword.setRedirect(false);
		forword.setPath("./booklove/reviewUpdateForm.jsp");
		return forword;		

	}

}
