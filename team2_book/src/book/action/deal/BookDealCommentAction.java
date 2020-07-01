package book.action.deal;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import book.controller.Action;
import book.controller.ActionForward;
import book.model.BookCommentVO;
import book.model.BookDAO;

public class BookDealCommentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		BookDAO dao = new BookDAO();
		BookCommentVO vo = new BookCommentVO();
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		
		String num = request.getParameter("c_board_no");
		String c_no = request.getParameter("c_no");
		String m_id = (String)session.getAttribute("memID");
		String comment = request.getParameter("deal_comment");
		
		if((String)session.getAttribute("memID") == null) { 
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('로그인 후 이용 가능합니다');");
			out.print(" history.go(-1)"); 
			out.print("</script>");
			out.close();
			return null;
		}
		try {
			vo.setC_board_no(Integer.parseInt(num));
			vo.setC_no(Integer.parseInt(c_no) + 1);
			vo.setM_id(m_id);
			vo.setC_comment(comment);

			boolean result = dao.commentInsert(vo);
			if (result == false) {
				System.out.println("댓글 등록 실패");
				return null;
			}
			System.out.println("댓글 등록 완료");
			forward.setRedirect(true);
			forward.setPath("./DealDetailAction.book?num=" + num);
			return forward;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
