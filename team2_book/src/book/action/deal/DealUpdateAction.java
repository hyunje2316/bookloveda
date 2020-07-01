package book.action.deal;



import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import book.controller.Action;
import book.controller.ActionForward;
import book.model.BookDAO;
import book.model.BookDealVO;


public class DealUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		
		// dealUpdateForm.jsp :: <input type="hidden" name="num" value=${ vo.d_no }>
		int num = Integer.parseInt(request.getParameter("num"));
	
		BookDAO dao = new BookDAO();
		BookDealVO vo = new BookDealVO();
		HttpSession session = request.getSession();
		
		boolean result = false;
		
		boolean userCheck = dao.isDealBoardWriter(num, (String) session.getAttribute("memID"));

		if(userCheck == false) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('수정할 권한이 없습니다.');");
			out.print(" history.go(-1);"); 
			out.print("</script>");
			out.close();
			return null;
		}
		
		try {
			vo.setD_no(num);
			vo.setD_subject(request.getParameter("d_subject"));
			
			
			vo.setD_category(Integer.parseInt(request.getParameter("d_category")));	
			vo.setD_state(Integer.parseInt(request.getParameter("d_state")));
			vo.setD_shape(Integer.parseInt(request.getParameter("d_shape")));
			vo.setD_complete(Integer.parseInt(request.getParameter("d_complete")));
			vo.setD_price(Integer.parseInt(request.getParameter("d_price")));
			vo.setD_content(request.getParameter("d_content"));
			
			
			
			result = dao.bookdealUpdate(vo);
		
			if (result == false) {
				System.out.println("modify fail....");
				return null;
			}
			System.out.println("modify success....");
			
			forward.setRedirect(true);
			forward.setPath("./DealDetailAction.book?num="+num);
			
			return forward;
			
		} catch (Exception e) {
			System.out.println("DealUpdateAction error : " + e);
		}
		return null;
	}

}