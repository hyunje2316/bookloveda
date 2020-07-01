package book.action.member;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import book.model.BookDAO;
import book.model.BookReviewVO;
import book.controller.Action;
import book.controller.ActionForward;

public class BookMyListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		BookDAO dao = new BookDAO();
		List list = new ArrayList();
		
		int page = 1;
		int limit = 10;
		
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int listcount = 0;
		
		//list.jsp <a href="./BoardDetailAction.do?num=${vo.BOARD_NUM }">
		String id = (String) session.getAttribute("memID");
		String board = request.getParameter("board");
		System.out.println(board);
		
		listcount = dao.getMyListCount(board);

		list = dao.myReviewList(board, id, page, limit);
		request.setAttribute("board", board);

		// 총 페이지 수
		int maxPage = (int) ((double) listcount / limit + 0.95);// 0.95를 더해서 올림처리
		// 현재 페이지에 보여줄 시작 페이지 수
		int startPage = (((int) ((double) page / 10 + 0.95)) - 1) * 10 + 1;
		// 현재 페이지에 보여줄 마지막 페이지 수
		int endPage = startPage + 10 - 1;

		if (endPage > maxPage)
			endPage = maxPage;
		
		System.out.println(startPage);
		System.out.println(endPage);
		request.setAttribute("page", page);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("listcount", listcount);
		request.setAttribute("list", list);

		
		if(list == null) {
			System.out.println("내글 목록 실패");
			return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/booklove/mypageMyboardList.jsp");
		return forward;
	}

}
