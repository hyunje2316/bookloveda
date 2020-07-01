package book.action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import book.model.BookDAO;

import book.model.BookReviewVO;
import book.controller.Action;
import book.controller.ActionForward;
public class BookReviewInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BookDAO dao = new BookDAO();
		BookReviewVO vo = new BookReviewVO();
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		
		String realFolder ="";
		String saveFolder="/reviewUpload";
		
		int fileSize = 5*1024*1024;
		
		realFolder = request.getRealPath(saveFolder);
		boolean result = false;
		
		try {
			MultipartRequest multi =null;
			multi = new MultipartRequest(request, realFolder,fileSize,"UTF-8",new DefaultFileRenamePolicy());


			vo.setM_id((String)session.getAttribute("memID"));

			vo.setR_subject(multi.getParameter("r_subject"));
			
			vo.setR_star(Integer.parseInt(multi.getParameter("r_star")));
			vo.setR_content(multi.getParameter("r_content"));
			vo.setR_image(multi.getFilesystemName((String)multi.getFileNames().nextElement() ) );


			result = dao.bookReviewInsert(vo);
			
			if( result == false) {
				System.out.println("게시판 등록 실패");
				return null;
			}
			System.out.println("게시판 등록 완료");
			
			forward.setRedirect(true);
			forward.setPath("./bookReviewList.book");
			return forward;
			
		} catch (Exception e) {
			e.printStackTrace();
		} // try end
		return null;
	}

}
