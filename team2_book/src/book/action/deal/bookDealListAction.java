package book.action.deal;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.controller.Action;
import book.controller.ActionForward;
import book.model.BookDAO;
import book.model.BookDealVO;


public class bookDealListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BookDAO dao = new BookDAO();
		List<BookDealVO> list = new ArrayList<BookDealVO>();
		int category = Integer.parseInt(request.getParameter("category"));

		
		int page = 1;
		int limit = 9;
		
		if (request.getParameter("page")!= null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int listcount = dao.getListCount(category);		//dao.getListCount() :게시판의 총 record 수를 가져오는 함수..
		
		
		
		
		
		list = dao.getBookDealList(page,limit,category);	//dao.getBoardList(page,limit) 
												//page:몇번째 페이지의 limit :보여주고자하는 게시판글의수 
												
		//총 페이지수 : 총등록된 게시글과 한페이지에 보여주고자하는 게시글수를 알면 총 페이지수를 알수 있다.
		int maxPage = (int)((double)listcount/limit+0.95);
												// +0.95 : 올림 처리..
												// ex) listcount=32 , limit=10
												// 32/10 => 3페이지를 만들고 2개의 게시글이남는다. 2개의 게시글을 보여주기 위해 총 4페이지가 필요하기때문에 올림하기 위해..
		//
		int startPage = (((int)((double)page/limit+0.95)) -1)*10 +1;
		int endPage = startPage +limit -1;
		
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		
		
		
		request.setAttribute("page", page);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", maxPage);
		request.setAttribute("listcount", listcount);
		request.setAttribute("list", list);
		request.setAttribute("category", category);
		
		
		ActionForward forward = new ActionForward();
		System.out.println("list 출력 완료");
		
		
		forward.setRedirect(false);
		forward.setPath("/booklove/dealList.jsp");
		return forward;
	}

}
