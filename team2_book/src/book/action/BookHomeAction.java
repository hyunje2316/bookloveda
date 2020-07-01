package book.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.controller.Action;
import book.controller.ActionForward;
import book.model.BookDAO;
import book.model.BookDealVO;

public class BookHomeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		// DEAL
		BookDAO dao = new BookDAO();
		List<BookDealVO> deallist = new ArrayList<BookDealVO>();
		List<BookDealVO> clublist = new ArrayList<BookDealVO>();
		
		deallist = dao.getBookDealListHome();	
		clublist = dao.getBookOfflineListHome();										
		
		request.setAttribute("deallist", deallist);
		request.setAttribute("clublist", clublist);
		
		
		ActionForward forward = new ActionForward();
		System.out.println("list 출력 완료");
		
		
		forward.setRedirect(false);
		forward.setPath("/booklove/home.jsp");
		return forward;
	}

}
