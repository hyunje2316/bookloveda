package book.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.action.BookHomeAction;
import book.action.SearchAction;
import book.action.deal.*;
import book.action.member.BookLoginAction;
import book.action.member.BookMyListAction;
import book.action.member.BookUserDeleteAction;
import book.action.member.BookUserUpdate;
import book.action.member.BooksignUpAction;
import book.action.review.*;
import book.action.offline.*;

@WebServlet("*.book")
public class BookFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html;charset=utf-8");
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;
		

		
		
		if( command.equals("/home.book")) { //오프라인게시판 글쓰기 창 이동
			 action = new BookHomeAction();
				try {
					forward = action.execute(request, response);
					System.out.println("forward  : " + forward);
				} catch (Exception e) {
					e.printStackTrace();
				}
		} else if (command.equals("/search.book")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/booklove/search.jsp");
		}

		else if (command.equals("/searchAction.book")) {
			action = new SearchAction();
			try {
				forward = action.execute(request, response);
				System.out.println("forward  : " + forward);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/searchBook.book")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/booklove/searchbook.jsp");
		}
		else if( command.equals("/contact.book")) { 
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("booklove/mypageUpdate.jsp");
			}
		else if( command.equals("/userdel.book")) { 
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("booklove/mypageDelete.jsp");
			}
		else if( command.equals("/userboard.book")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("booklove/mypageMyboard.jsp");
			}
		else if (command.equals("/BookMyListAction.book")) {
			action = new BookMyListAction();
			try {
				forward = action.execute(request, response);
				System.out.println("forward : " + forward);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		 
		
		
		//***************************************************************************************
		//								BOOK OFFLINE - 오프라인 모임 게시판
		//***************************************************************************************
		
		else if (command.equals("/BookOfflineList.book")) { //오프라인게시판 리스트
			action = new BookOfflineAction();
			try {
				forward = action.execute(request, response);
				System.out.println("forward  : " + forward);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/OfflineViewAction.book")) { //오프라인게시판 게시글 보기
			action = new OfflineViewAction();
			try {
				forward = action.execute(request, response);
				System.out.println("forward  : " + forward);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if( command.equals("/OfflineWrite.book")) { //오프라인게시판 글쓰기 창 이동
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("booklove/offlineInsertForm.jsp");
		}
		else if (command.equals("/OfflineInsertAction.book")) { //오프라인게시판 글쓰기 수행
			action = new OfflineInsertAction();
			try {
				forward = action.execute(request, response);
				System.out.println("forward  : " + forward);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/OfflineUpdate.book")) { //오프라인게시판 글 수정 창 이동
			action = new OfflineUpdateView();
			try {
				forward = action.execute(request, response);
				System.out.println("forward  : " + forward);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/OfflineUpdateAction.book")) { //오프라인게시판 글 수정 수행
			action = new OfflineUpdateAction();
			try {
				forward = action.execute(request, response);
				System.out.println("forward  : " + forward);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*
		 * else if (command.equals("/delete.book")) { forward = new ActionForward();
		 * forward.setRedirect(false); forward.setPath("/jsp/offlinedelete.jsp");
		 * request.setAttribute("num", request.getParameter("num")); }
		 */
		else if (command.equals("/OfflineDelete.book")) { 
			action = new OfflineDeleteAction();
			try {
				forward = action.execute(request, response);
				System.out.println("forward  : " + forward);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/OfflineCommentSubmit.book")) {
			action = new BookOfflineCommentAction();
			try {
				forward = action.execute(request, response);
				System.out.println("forward  : " + forward);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/OfflineCommDelAction.book")) { //오프라인게시판 글 수정 수행
			action = new OfflineCommDelAction();
			try {
				forward = action.execute(request, response);
				System.out.println("forward  : " + forward);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		//***************************************************************************************
		//								BOOK REVIEW - 후기 게시판
		//***************************************************************************************
		
		else if (command.equals("/ReviewWrite.book")) {	
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("booklove/reviewInsertForm.jsp");
			
		}else if (command.equals("/bookReviewList.book")) {	
			action = new BookReviewListAction();	
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/ReviewInsertAction.book")) {	
			action = new BookReviewInsertAction();	
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/ReviewDetailAction.book")) {	
			action = new BookReviewDetailAction();	
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/ReviewUpdate.book")) {	
			action = new BookReviewModifyView();	
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/ReviewUpdateAction.book")) {	
			action = new BookReviewModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}else if (command.equals("/ReviewDelete.book")) {	
			action = new BookReviewDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/* 수정중.. */
		else if (command.equals("/BookUserUpdate.book")) {
			action = new BookUserUpdate();
			try {
				forward = action.execute(request, response);
				System.out.println("forward : " + forward);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BookUserDelete.book")) {
			action = new BookUserDeleteAction();
			try {
				forward = action.execute(request, response);
				System.out.println("forward : " + forward);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		  else if( command.equals("/loginForm.book")) {
			   forward = new ActionForward();
			   forward.setRedirect(false);
			   forward.setPath("/booklove/login.jsp");
		  }
		  else if( command.equals("/signUp.book")) {
			   forward = new ActionForward();
			   forward.setRedirect(false);
			   forward.setPath("/booklove/signUp.jsp");
		  }
		  else if( command.equals("/loginPro.book")) {
			   forward = new ActionForward();
			   forward.setRedirect(false);
			   forward.setPath("/booklove/loginPro.jsp");
		  }
		  else if( command.equals("/BookLoginAction.book")) {
			   action = new BookLoginAction();
			   try {
			    forward = action.execute(request, response);
			    System.out.println("forward  : " + forward );
			   } catch (Exception e) {
			    e.printStackTrace();
			   }
		  }
		  else if( command.equals("/main.book")) {
			   forward = new ActionForward();
			   forward.setRedirect(false);
			   forward.setPath("/booklove/main.jsp");
		  }
		  else if( command.equals("/logout.book")) {
			   forward = new ActionForward();
			   forward.setRedirect(false);
			   forward.setPath("/booklove/logout.jsp");
		  }
		  else if (command.equals("/ReviewCommentSubmit.book")) {
				action = new BookReviewCommentAction();
				try {
					forward = action.execute(request, response);
					System.out.println("forward  : " + forward);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if (command.equals("/ReviewCommDelAction.book")) { 
				action = new ReviewCommDelAction();
				try {
					forward = action.execute(request, response);
					System.out.println("forward  : " + forward);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if( command.equals("/signUpAction.book")) {
				action = new BooksignUpAction();
				try {
				forward = action.execute(request, response);
				System.out.println("forward : " + forward );
				} catch (Exception e) {
				e.printStackTrace();
				}
				}
		//***************************************************************************************
		//								BOOK DEAL - 중고거래 게시판
		//***************************************************************************************
		
		
		else if (command.equals("/DealWrite.book")) {	
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("booklove/dealInsertForm.jsp");
		}else if (command.equals("/bookDealList.book")) {	
			action = new bookDealListAction();	
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/DealInsertAction.book")) {	
			action = new DealInsertAction();	
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/DealDetailAction.book")) {	
			action = new DealDetailAction();	
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/DealUpdate.book")) {	
			action = new DealUpdateView();	
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/DealUpdateAction.book")) {	
			action = new DealUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}else if (command.equals("/DealDelete.book")) {	
			action = new DealDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/DealCommentSubmit.book")) {
			action = new BookDealCommentAction();
			try {
				forward = action.execute(request, response);
				System.out.println("forward  : " + forward);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/DealCommDelAction.book")) { 
			action = new DealCommDelAction();
			try {
				forward = action.execute(request, response);
				System.out.println("forward  : " + forward);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*
		else if (command.equals("/DealDelteAction.book")) {	//delete.jsp에서 삭제버튼을 submit 했을때 db에 delete 하러 가자!
	//		action = new BoardDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		*/
		
		
		
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}
			else {
				RequestDispatcher d = request.getRequestDispatcher(forward.getPath());
				d.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
