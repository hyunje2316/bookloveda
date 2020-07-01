package book.action.review;


import book.controller.Action;
import book.controller.ActionForward;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.model.BookDAO;

import book.model.BookReviewVO;


public class BookReviewModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		ActionForward forword = new ActionForward(); //객체생성
		boolean result = false;
		
		//<input type="hidden" name="num" value=${ vo.r_no }>
		int num = Integer.parseInt(request.getParameter("num"));
		// BOARD_NUM은 인트타입이기 때문에 형변환 해준다
		
		BookDAO dao = new BookDAO();
		BookReviewVO vo = new BookReviewVO();

		boolean userCheck = dao.isReviewBoardWriter(num, request.getParameter("id"));
		
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
		
		try {
			vo.setR_no(num);
			vo.setR_subject(request.getParameter("r_subject"));
			System.out.println("왓니");
			vo.setR_star(Integer.parseInt(request.getParameter("r_star")));
			System.out.println("왓니2");
			vo.setR_content(request.getParameter("r_content"));
			

			System.out.println("전");
			result = dao.BookReviewModify(vo);
			System.out.println("후");
			if(result == false) {
				System.out.println("게시판 수정 실패");
				return null;
			}
			System.out.println("게시판 수정 완료");
			
			forword.setRedirect(true);
			forword.setPath("./ReviewDetailAction.book?num=" +num);
			return forword;						
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return null;
	}

}
