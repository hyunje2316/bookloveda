package book.action.review;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import book.model.BookCommentVO;
import book.model.BookDAO;

@WebServlet("/revcomm")
public class ReviewComm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookDAO dao = new BookDAO();
		response.setCharacterEncoding("utf-8");
		List list = new ArrayList();
		JSONObject cObject = new JSONObject();
		JSONArray cArray = new JSONArray();
		
		int num = Integer.parseInt(request.getParameter("num"));
		list = dao.commList(num);
		
		for(int i = 0; i < list.size(); i++) {
			JSONObject cInfo = new JSONObject();
			BookCommentVO vo = new BookCommentVO();
			vo = (BookCommentVO) list.get(i);
			cInfo.put("no", vo.getC_no());
			cInfo.put("comment", vo.getC_comment());
			cInfo.put("id", vo.getM_id());
			cInfo.put("date", vo.getC_date().toString());
			cArray.add(cInfo);
		}
		cObject.put("members", cArray);
		
		String jsonInfo = cObject.toJSONString();
		System.out.println(jsonInfo);
		PrintWriter writer = response.getWriter();
		writer.print(jsonInfo);
		writer.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
