package book.action.deal;




import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import book.controller.Action;
import book.controller.ActionForward;
import book.model.BookDAO;
import book.model.BookDealVO;


public class DealDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		BookDAO dao = new BookDAO();
		BookDealVO vo = new BookDealVO();		
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		
		boolean result = false; //최종  db에 delete 성공여부
		
		int num = Integer.parseInt(request.getParameter("num"));
		String category = request.getParameter("category");
		System.out.println("category"+category);

		boolean userCheck = dao.isDealBoardWriter(num, (String) session.getAttribute("memID"));

		if(userCheck == false) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('삭제할 권한이 없습니다.');");
			out.print(" history.go(-1);"); 
			out.print("</script>");
			out.close();
			return null;
		}

		
		String path = "./bookDealList.book?category="+category;
		
		try {		
			result = dao.bookdealDelete(num);
		
			if (result == false) {
				System.out.println("deal delete fail....");
				return null;
			}
			System.out.println("deal delete success....");
						
			forward.setRedirect(true);
			forward.setPath(path);
			
			return forward;
			
		} catch (Exception e) {
			System.out.println("DealDeleteAction error : " + e);
		}
		return null;                                           
		
	}

}