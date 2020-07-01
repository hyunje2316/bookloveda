package book.action.offline;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import book.model.BookDAO;
import book.model.BookOfflineVO;

import book.controller.Action;
import book.controller.ActionForward;

public class OfflineDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		boolean result = false;
		int num = Integer.parseInt(request.getParameter("num"));
		
		
		BookDAO dao = new BookDAO();
		BookOfflineVO vo = new BookOfflineVO();
		HttpSession session = request.getSession();
		
		boolean userCheck = dao.isOfflineBoardWriter(num, (String)session.getAttribute("memID"));
		
		if(userCheck == false) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('삭제할 권한이 없습니다.');");
			out.print(" history.go(-1);"); 
			out.print("</script>");
			out.close();
			return null;
		}
		
		result = dao.OfflineDelete(num);
		
		forward.setRedirect(true);
		forward.setPath("./BookOfflineList.book");
		
		return forward;
	}

}
