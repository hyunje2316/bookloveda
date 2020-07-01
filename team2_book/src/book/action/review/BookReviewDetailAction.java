package book.action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.model.BookDAO;
import book.model.BookReviewVO;
import book.controller.Action;
import book.controller.ActionForward;

public class BookReviewDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		BookDAO dao = new BookDAO();
		BookReviewVO vo = new BookReviewVO();
		
		//list.jsp <a href="./BoardDetailAction.do?num=${vo.BOARD_NUM }">
		int num = Integer.parseInt(request.getParameter("num"));
		dao.setReviewReadCountUpdate(num);
		vo = dao.getReviewDetail(num);
		int maxcom = dao.getCommentMax(num);
		
		if(vo == null) {
			System.out.println("상세 보기 실패");
			return null;
		}
		
		request.setAttribute("vo", vo);
		request.setAttribute("maxcom", maxcom);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/booklove/reviewListView.jsp");
		return forward;
	}

}
