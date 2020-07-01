package book.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.controller.Action;
import book.controller.ActionForward;
import book.model.BookDAO;
import book.model.BookDealVO;
import book.model.BookOfflineVO;
import book.model.BookReviewVO;

public class SearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		
		// DEAL
				BookDAO dao = new BookDAO();
				List<BookDealVO> deallist = new ArrayList<BookDealVO>();
				List<BookReviewVO> reviewlist = new ArrayList<BookReviewVO>();
				List<BookOfflineVO> clublist = new ArrayList<BookOfflineVO>();
				
				String search = request.getParameter("search");
				
				
				
				deallist = dao.getBookDealListSearch(search);
				reviewlist = dao.getBookReviewListSearch(search);										
				clublist = dao.getBookOfflineListSearch(search);
				
				request.setAttribute("search", search);
				request.setAttribute("deallist", deallist);
				request.setAttribute("reviewlist", reviewlist);
				request.setAttribute("clublist", clublist);
				
				
				ActionForward forward = new ActionForward();
				System.out.println("list 출력 완료");
				
				
				forward.setRedirect(false);
				forward.setPath("/booklove/searchList.jsp");
				return forward;
			}
}
