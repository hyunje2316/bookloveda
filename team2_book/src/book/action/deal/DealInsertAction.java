package book.action.deal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import book.controller.Action;
import book.controller.ActionForward;
import book.model.BookDAO;
import book.model.BookDealVO;

public class DealInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BookDAO dao = new BookDAO();
		BookDealVO vo = new BookDealVO();
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		
		String realFolder = "";
		String saveFolder = "/dealUpload";
		
	
		
		int fileSize = 5 * 1024 * 1024;				//=5mb
		realFolder = request.getRealPath(saveFolder);
		int result = 0;
		
		try {
			MultipartRequest multi = null;
			multi = new MultipartRequest(request, realFolder, fileSize,"utf-8",new DefaultFileRenamePolicy());
			
			vo.setD_state_image(multi.getFilesystemName((String)multi.getFileNames().nextElement() ));
			vo.setD_category(Integer.parseInt(multi.getParameter("d_category")));
			System.out.println("fliename : " + vo.getD_state_image());
			System.out.println("asdf " + vo.getD_state_image().contains("*.PNG"));
			if(vo.getD_state_image().toLowerCase().contains(".jpg")
					|| vo.getD_state_image().toLowerCase().contains(".jpeg")
					|| vo.getD_state_image().toLowerCase().contains(".png")
					|| vo.getD_state_image().toLowerCase().contains(".gif")
					|| vo.getD_state_image().toLowerCase().contains(".bmp")) {

				vo.setD_subject(multi.getParameter("d_subject"));
				vo.setM_id((String) session.getAttribute("memID"));
				
				vo.setD_state(Integer.parseInt(multi.getParameter("d_state")));
				vo.setD_shape(Integer.parseInt(multi.getParameter("d_shape")));
				vo.setD_complete(Integer.parseInt(multi.getParameter("d_complete")));
				vo.setD_price(Integer.parseInt(multi.getParameter("d_price")));
				vo.setD_content(multi.getParameter("d_content"));

				vo.setD_state_image(multi.getFilesystemName((String) multi.getFileNames().nextElement()));
				
				result = dao.bookdealInsert(vo); // insert함수 호출..

				if (result == 0) {
					System.out.println("중고거래 게시판 등록 실패!");
					return null;
				} else {
					System.out.println("중고거래 게시판 등록 완료");

					forward.setRedirect(true);
					forward.setPath("./DealDetailAction.book?num=" + result);

					return forward;
				}
			}
			else {
				forward.setRedirect(true);
				forward.setPath("./bookDealList.book?category=" + vo.getD_category());

				return forward;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	

}
