package book.action.deal;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.controller.Action;
import book.controller.ActionForward;
import book.model.BookDAO;
import book.model.BookDealVO;

public class DealDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		BookDAO dao = new BookDAO();
		BookDealVO vo = new BookDealVO();
		
		//  <a href="./DealDetailAction.book?num=${ vo.d_no }">
		int num = Integer.parseInt(request.getParameter("num"));
		dao.setReadCountUpdate(num);			//조회수 증가 함수 : num 을 가져가야 해당 필드의 readcount를 증가시킬수있다.
		vo = dao.getDetail(num);		// num값에 해당하는 record를 찾아  해당 튜플을 가져오기..
		int maxcom = dao.getCommentMax(num);
		
		if (vo == null) {
			System.out.println("deal 상세보기 실패");
			return null;
		}else {
			
			request.setAttribute("vo", vo);
			request.setAttribute("maxcom", maxcom);
			ActionForward forward = new ActionForward();
			System.out.println("deal 상세보기 성공");
			forward.setRedirect(false);
			forward.setPath("/booklove/dealListView.jsp");
			return forward;
		}
		
	}

}
