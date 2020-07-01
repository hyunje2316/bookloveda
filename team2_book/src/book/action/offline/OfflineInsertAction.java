package book.action.offline;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import book.model.BookDAO;
import book.model.BookOfflineVO;

import book.controller.Action;
import book.controller.ActionForward;

public class OfflineInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		BookDAO dao = new BookDAO();
		BookOfflineVO vo = new BookOfflineVO();
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		
		String realFolder = "";
		String saveFolder = "/offlineUpload";
		
		int fileSize = 5*1024*1024;
		
		realFolder = request.getRealPath(saveFolder);
		System.out.println(realFolder);
		boolean result = false;
		try {
			MultipartRequest multi = null;
			multi = new MultipartRequest(request, realFolder, fileSize, "utf-8", new DefaultFileRenamePolicy());
			vo.setO_subject(multi.getParameter("o_subject"));
			vo.setM_id((String)session.getAttribute("memID"));
			vo.setO_address(multi.getFilesystemName((String)multi.getFileNames().nextElement()));
			vo.setO_content(multi.getParameter("o_content"));
			
			result = dao.OfflineInsert(vo);
			
			if(result == false) {
				System.out.println("게시판 등록 실패");
				return null;
			}
			System.out.println("게시판 등록 완료");
			forward.setRedirect(true);
			forward.setPath("./BookOfflineList.book");
			return forward;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
