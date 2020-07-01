package book.action.offline;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.model.*;
import book.controller.Action;
import book.controller.ActionForward;

public class BookOfflineAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BookDAO dao = new BookDAO();
		List list = new ArrayList();
		
		int page = 1;
		int limit = 6;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int listcount = dao.getOfflineListCount(); // 총 리스트(레코드) 수를 받아올 함수
		list = dao.getOfflineList(page, limit); // 리스트를 받아올 함수 - select
		
		


		//총 페이지 수
		int maxPage = (int)((double)listcount/limit+0.95); //0.95를 더해서 올림처리
			
		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21,...)
		int startPage = (((int)((double)page/limit+0.95)) -1)*10 +1;
		
		//현재 페이지에 부여줄 마지막 페이지 수(10, 20, 30,...)
		int endPage = startPage + limit - 1;
		
		if(endPage > maxPage) endPage = maxPage;
		
				
		
		request.setAttribute("page", page); //현재 페이지 수
		request.setAttribute("maxPage", maxPage); //최대 페이지수
		request.setAttribute("startPage", 1); //현재 페이지에 표시할 첫페이지
		request.setAttribute("endPage", maxPage); //현제 페이지에 표시할 끝페이지
		request.setAttribute("listcount", listcount); //글 수
		request.setAttribute("list", list);
		
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/booklove/offlineList.jsp");
		
		return forward;
	}

}
