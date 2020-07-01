package book.action.offline;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import book.model.BookDAO;
import book.model.BookOfflineVO;
import book.controller.Action;
import book.controller.ActionForward;

public class OfflineUpdateView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		
		BookDAO dao = new BookDAO();
		BookOfflineVO vo = new BookOfflineVO();
		HttpSession session = request.getSession();
		
		
		int num = Integer.parseInt(request.getParameter("num"));
		vo = dao.getOfflineView(num);
		

		
		//글쓴이가 맞는지 확인
		boolean userCheck = dao.isOfflineBoardWriter(num, (String)session.getAttribute("memID"));
		if(userCheck == false) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('수정할 권한이 없습니다.');");
			out.print(" history.go(-1)");
			out.print("</script>");
			out.close();
			return null;
		}
		
		if(vo == null) {
			System.out.println("[수정] 상세보기 실패");
			return null;
		}
		System.out.println("[수정] 상세보기 성공");
		
		request.setAttribute("vo", vo);
		
		forward.setRedirect(false);
		forward.setPath("./booklove/offlineUpdateForm.jsp");
		return forward;
	}
}
