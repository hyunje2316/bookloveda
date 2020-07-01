package book.action.offline;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.model.*;
import book.controller.Action;
import book.controller.ActionForward;


public class OfflineViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		BookDAO dao = new BookDAO();
		BookOfflineVO vo = new BookOfflineVO();
		
		
		
		int num = Integer.parseInt(request.getParameter("num"));
		dao.setOfflineReadCountUpdate(num); //조회수
		vo = dao.getOfflineView(num);
		int maxcom = dao.getCommentMax(num);			
		
		if(vo == null) {
			System.out.println("상세보기 실패");
			return null;
		}
		System.out.println("상세보기 성공");
		
		request.setAttribute("vo", vo);
		ActionForward forward = new ActionForward();
		request.setAttribute("maxcom", maxcom);		
		
		forward.setRedirect(false);
		forward.setPath("./booklove/offlineListView.jsp");
		
		return forward;
	}

}
