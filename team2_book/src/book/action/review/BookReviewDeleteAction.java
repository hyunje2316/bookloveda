package book.action.review;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.model.BookDAO;
import book.model.BookReviewVO;

import book.controller.Action;
import book.controller.ActionForward;


public class BookReviewDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		ActionForward forword = new ActionForward(); //객체생성
		boolean result = false;
		

		int num = Integer.parseInt(request.getParameter("num") ); 
		//int num = Integer.parseInt(request.getParameter("BOARD_NUM") ); 
		
		BookDAO dao = new BookDAO();
		BookReviewVO vo = new BookReviewVO();
		System.out.println(num+ request.getParameter("id"));
		boolean userCheck = dao.isReviewBoardWriter(num, request.getParameter("id"));
		System.out.println(userCheck);
		
		if(userCheck == false) { //가져온게 안맞다면 패스워드가 다르다면
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('삭제할 권한이 없슴니다');");
			out.print("alert('꺄아아아악');");
			out.print(" history.go(-1);"); //다시 리스트로 보낸다
			out.print("</script>");
			out.close();
			return null;
		} // end if

		try {
		vo.setR_no(num);
		result = dao. BookReviewDelete(vo);
		if(result == false) {
			System.out.println("BookReviewDeleteAction 삭제 실패");
			return null;
		}
		System.out.println("BookReviewDeleteAction 삭제 완료");
		
		forword.setRedirect(true);
		//forword.setPath("./BoardDetailAction.do?num=" +vo.getBOARD_NUM());
		forword.setPath("./bookReviewList.book");
		return forword;						
	} catch (Exception e) {
		e.printStackTrace();
		
	}
		
		
		return null;
	}

}
