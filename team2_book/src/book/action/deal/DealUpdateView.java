package book.action.deal;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import book.controller.Action;
import book.controller.ActionForward;
import book.model.BookDAO;
import book.model.BookDealVO;

public class DealUpdateView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();	
		BookDAO dao = new BookDAO();
		BookDealVO vo = new BookDealVO();
		HttpSession session = request.getSession();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		boolean userCheck = dao.isDealBoardWriter(num, (String) session.getAttribute("memID"));

		if(userCheck == false) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('수정할 권한이 없습니다.');");
			out.print(" history.go(-1);"); 
			out.print("</script>");
			out.close();
			return null;
		}
		
		vo=dao.getDetail(num);
		
		if (vo == null) {
			System.out.println("update page 이동 실패");
			return null;
			
		}else {
			System.out.println("update page 이동 성공");
			request.setAttribute("vo", vo);
			forward.setRedirect(false);
			forward.setPath("./booklove/dealUpdateForm.jsp");

			return forward;
		}

		
	}

}
