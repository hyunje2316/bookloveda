package book.action.offline;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import book.model.BookDAO;
import book.model.BookOfflineVO;
import book.controller.Action;
import book.controller.ActionForward;

public class OfflineUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		boolean result = false;
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		BookDAO dao = new BookDAO();
		BookOfflineVO vo = new BookOfflineVO();
		HttpSession session = request.getSession();
		
		//글쓴이가 맞는지 확인
		boolean userCheck = dao.isOfflineBoardWriter(num, (String)session.getAttribute("memID"));
		if(userCheck == false) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('수정할 권한이 없습니다.');");
			out.print(" history.go(-1)");
			out.print("</script>");
			out.close();
			return null;
		}
		
		try {
			vo.setO_no(num);
			vo.setO_subject(request.getParameter("o_subject"));
			vo.setO_content(request.getParameter("o_content"));
			
			result = dao.OfflineUpdate(vo);
			if(result == false) {
				System.out.println("게시글 수정 실패");
				return null;
			}
			System.out.println("게시글 수정 완료");
			
			forward.setRedirect(true);
			forward.setPath("./OfflineViewAction.book?num="+num);
			return forward;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
