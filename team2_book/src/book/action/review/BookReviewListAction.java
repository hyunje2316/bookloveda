package book.action.review;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.controller.Action;
import book.controller.ActionForward;
import book.model.BookDAO;



public class BookReviewListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BookDAO dao = new BookDAO();
		List list = new ArrayList();
		
		int page = 1;
		int limit = 5;
		
	
		
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int listcount = dao.getListCount(); // 총 리스트(레코드)수를 받아올 함수
		list = dao.getBoardList(page,limit); // 리스트를 받아올 함수 - select
		
		//총 페이지 수
		int maxPage=(int)((double)listcount / limit +0.95);//0.95를 더해서 올림처리
		// 현재 페이지에 보여줄 시작 페이지 수
		int startPage = (( (int)((double)page / 10 + 0.95))-1)*10+1;
		// 현재 페이지에 보여줄 마지막 페이지 수
		int endPage = startPage + 10-1;
		
		if(endPage > maxPage) endPage= maxPage;
		
		System.out.println(startPage);
		System.out.println(endPage);
		request.setAttribute("page", page);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("listcount", listcount);
		request.setAttribute("list", list);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/booklove/reviewList.jsp");
		
		return forward;
	}

}
