package book.action.deal;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import book.controller.Action;
import book.controller.ActionForward;
import book.model.BookCommentVO;
import book.model.BookDAO;

public class DealCommDelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		boolean result = false;
		int num = Integer.parseInt(request.getParameter("num"));
		int no = Integer.parseInt(request.getParameter("no"));
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		
		BookDAO dao = new BookDAO();

		if(!(id.equals((String)session.getAttribute("memID"))) || (String)session.getAttribute("memID") == null) { //가져온게 안맞다면 
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('삭제할 권한이 없습니다');");
			out.print(" history.go(-1)"); 
			out.print("</script>");
			out.close();
			return null;
		} 
		
		result = dao.commentDelete(num, no);
		
		forward.setRedirect(true);
		forward.setPath("./DealDetailAction.book?num=" + num);
		
		return forward;
	}

}
