package book.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import book.connect.ConnectionClose;


public class BookDAO {
	
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public BookDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/team2:Book");
			System.out.println("connection success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
		//***************************************************************************************
		//								공용 method - Comment
		//***************************************************************************************
	

	//review comment list
			public List commList(int num) {
				String sql = "SELECT * FROM T2_COMMENT WHERE C_BOARD_NO = ? ORDER BY C_NO";
				BookCommentVO vo;
				List list = new ArrayList();
				try {
					conn = ds.getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, num);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						vo = new BookCommentVO();
						vo.setM_id(rs.getString("M_ID"));
						vo.setC_board_no(rs.getInt("C_BOARD_NO"));
						vo.setC_no(rs.getInt("C_NO"));
						vo.setC_comment(rs.getString("C_COMMENT"));
						vo.setC_date(rs.getDate("C_DATE"));

						list.add(vo);
					}

				} catch (Exception e) {
					System.out.println("revCommList : " + e);
				} finally {
					ConnectionClose.close(rs);
					ConnectionClose.close(pstmt);
					ConnectionClose.close(conn);
				}
				return list;
			}

			
			//maxcomment
			public int getCommentMax(int num) {
				int result = 0;
				String sql = "SELECT MAX(C_NO) FROM T2_COMMENT WHERE C_BOARD_NO = ?";
				try {
					conn = ds.getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, num);
					rs = pstmt.executeQuery();

					if(rs.next()) result = rs.getInt(1);
					
					return result;

				} catch (Exception e) {
					System.out.println("maxcomment error : " + e);
				} finally {
					ConnectionClose.close(rs);
					ConnectionClose.close(pstmt);
					ConnectionClose.close(conn);
				}
				
				return 0;
			}
			
			//comment insert
			public boolean commentInsert(BookCommentVO vo) {
				int result = 0;
				String sql = "INSERT INTO T2_COMMENT VALUES(?, ?, ?, ?, SYSDATE)";
				try {
					conn = ds.getConnection();
					pstmt = conn.prepareStatement(sql);

					pstmt.setInt(1, vo.getC_board_no());
					pstmt.setInt(2, vo.getC_no());
					pstmt.setString(3, vo.getM_id());
					pstmt.setString(4, vo.getC_comment());
					result = pstmt.executeUpdate();

					if (result == 0)
						return false;
					else
						return true;

				} catch (Exception e) {
					System.out.println("ReviewCommentInsert error : " + e);
				} finally {
					ConnectionClose.close(rs);
					ConnectionClose.close(pstmt);
					ConnectionClose.close(conn);
				}

				return false;
			}
			
			//delete comment
			public boolean commentDelete(int num, int no) {
				String sql = "DELETE T2_COMMENT WHERE C_BOARD_NO = ? AND C_NO = ?";
				System.out.println("num : " + num);
				System.out.println("no : " + no);
				try {
					conn = ds.getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, num);
					pstmt.setInt(2, no);

					int result = pstmt.executeUpdate();

					if (result == 1) {
						System.out.println("삭제 성공");
						return true;
					}
					System.out.println("삭제 실패");
				} catch (Exception e) {
					System.out.println("OfflineCommDel error : " + e);
				} finally {
					ConnectionClose.close(rs);
					ConnectionClose.close(pstmt);
					ConnectionClose.close(conn);
				}
				
				return false;
			}

			
			//***************************************************************************************
			//								BOOK OFFLINE - 오프라인 모임 게시판
			//***************************************************************************************
				
			
			
			//게시글 갯수
				public int getOfflineListCount() {
					int x = 0;
					try {
						conn = ds.getConnection();
						pstmt = conn.prepareStatement("SELECT COUNT(*) FROM T2_OFFLINE");
						rs = pstmt.executeQuery();
						
						if(rs.next()) x = rs.getInt(1);
						return x;
					} catch (Exception e) {
						System.out.println("getListCount() error : " + e);
					}
					finally {
						ConnectionClose.close(rs);
						ConnectionClose.close(pstmt);
						ConnectionClose.close(conn);
					}
					return 0;
				}
				
				//게시글 목록
				public List getOfflineList(int page, int limit) {
					String sql = "SELECT * FROM (SELECT ROWNUM RNUM, O_NO, O_SUBJECT, M_ID, "
							+ "O_ADDRESS, O_CONTENT, O_DATE, O_REF  FROM "
							+ "(SELECT * FROM T2_OFFLINE ORDER BY O_NO DESC)) WHERE RNUM>=? AND RNUM<=?";

					List list = new ArrayList();
					int startrow = (page - 1) * limit + 1;
					int endrow = startrow + limit - 1;
					try {
						conn = ds.getConnection();
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, startrow);
						pstmt.setInt(2, endrow);
						rs = pstmt.executeQuery();

						while (rs.next()) {
							BookOfflineVO vo = new BookOfflineVO();
							vo.setO_no(rs.getInt("O_NO"));
							vo.setO_subject(rs.getString("O_SUBJECT"));
							vo.setM_id(rs.getString("M_ID"));
							vo.setO_address(rs.getString("O_ADDRESS"));
							vo.setO_content(rs.getString("O_CONTENT"));
							vo.setO_date(rs.getDate("O_DATE"));
							vo.setO_ref(rs.getInt("O_REF"));

							list.add(vo);
						}

					} catch (Exception e) {
						System.out.println("getOfflineList : " + e);
					} finally {
						ConnectionClose.close(rs);
						ConnectionClose.close(pstmt);
						ConnectionClose.close(conn);
					}
					return list;
				}
				
				// 조회수 업데이트
				public void setOfflineReadCountUpdate(int num) {
					String sql = "UPDATE T2_OFFLINE SET O_REF = O_REF + 1 WHERE O_NO = " + num;

					try {
						conn = ds.getConnection();
						pstmt = conn.prepareStatement(sql);
						pstmt.executeUpdate();
					} catch (Exception e) {
						System.out.println("setOfflineReadCountUpdate error : " + e);
					} finally {
						ConnectionClose.close(rs);
						ConnectionClose.close(pstmt);
						ConnectionClose.close(conn);
					}
				}
				
				//게시글 보기
				public BookOfflineVO getOfflineView(int num) {
					BookOfflineVO vo = null;
					try {
						conn = ds.getConnection();
						pstmt = conn.prepareStatement("SELECT * FROM T2_OFFLINE WHERE O_NO = ?");
						pstmt.setInt(1, num);
						rs = pstmt.executeQuery();
						
						if(rs.next()) {
							vo = new BookOfflineVO();
							
							vo.setO_no(rs.getInt("O_NO"));
							vo.setO_subject(rs.getString("O_SUBJECT"));
							vo.setM_id(rs.getString("M_ID"));
							vo.setO_address(rs.getString("O_ADDRESS"));
							vo.setO_content(rs.getString("O_CONTENT"));
							vo.setO_date(rs.getDate("O_DATE"));
							vo.setO_ref(rs.getInt("O_REF"));
						}
						return vo;
					} catch (Exception e) {
						System.out.println("getOfflineView error : " + e);
					}finally {
						ConnectionClose.close(rs);
						ConnectionClose.close(pstmt);
						ConnectionClose.close(conn);
					}
					return null;
				}
				
				// 글쓰기
				public boolean OfflineInsert(BookOfflineVO vo) {
					int result = 0;
					String sql = "INSERT INTO T2_OFFLINE VALUES(BOARD_INC.NEXTVAL, ?, ?, ?, ?, SYSDATE, 0)";
					try {
						conn = ds.getConnection();
						pstmt = conn.prepareStatement(sql);

						pstmt.setString(1, vo.getO_subject());
						pstmt.setString(2, vo.getM_id());
						pstmt.setString(3, vo.getO_address());
						pstmt.setString(4, vo.getO_content());
						result = pstmt.executeUpdate();

						if (result == 0)
							return false;
						else
							return true;

					} catch (Exception e) {
						System.out.println("OfflineInsert error : " + e);
					} finally {
						ConnectionClose.close(rs);
						ConnectionClose.close(pstmt);
						ConnectionClose.close(conn);
					}

					return false;
				}
				
				//글 수정
				public boolean OfflineUpdate(BookOfflineVO vo) {
					String sql = "UPDATE T2_OFFLINE SET O_SUBJECT = ?, O_CONTENT = ? WHERE O_NO = ?";
					
					try {
						conn = ds.getConnection();
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, vo.getO_subject());
						pstmt.setString(2, vo.getO_content());
						pstmt.setInt(3, vo.getO_no());
						pstmt.executeUpdate();
						
						return true;
					} catch (Exception e) {
						System.out.println("OfflineUpdate error : " + e);
					} finally {
						ConnectionClose.close(rs);
						ConnectionClose.close(pstmt);
						ConnectionClose.close(conn);
					}
					
					return false;
				}
				
				//글 삭제
				public boolean OfflineDelete(int num) {
					String sql = "DELETE T2_OFFLINE WHERE O_NO = ?";
					try {
						conn = ds.getConnection();
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, num);

						int result = pstmt.executeUpdate();

						if (result == 1) {
							System.out.println("삭제 성공");
							return true;
						}
						System.out.println("삭제 실패");
					} catch (Exception e) {
						System.out.println("boardDelete error : " + e);
					} finally {
						ConnectionClose.close(rs);
						ConnectionClose.close(pstmt);
						ConnectionClose.close(conn);
					}
					
					return false;
				}
				
				public boolean isOfflineBoardWriter(int num, String id) throws SQLException {
					String sql = "select * from T2_offline where O_NO=?";
					try {
						conn = ds.getConnection();
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, num);
						rs = pstmt.executeQuery();
						rs.next();
						System.out.println("isBoardWriter" + id);
						if(id.equals(rs.getString("M_ID"))) { return true;}
						
					} catch (Exception e) {
						System.out.println("isBoardWriter 에러 : " + e);
						
					}finally {
						rs.close();
						pstmt.close();
						conn.close();
					}
					
					return false;
				}
				
				
				
				
	
	//***************************************************************************************
	//								BOOK REVIW - 후기 게시판
	//***************************************************************************************
		
	// 회원 삭제
	public boolean BookUserDelete(BookMemberVO vo) throws SQLException {
		String sql = "delete from T2_MEMBER where M_ID =? and m_pwd = ?";
		System.out.println(vo.getM_id());
		System.out.println(vo.getM_pwd());
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getM_id());
			pstmt.setString(2, vo.getM_pwd());
			int result = pstmt.executeUpdate();
			if(result == 1) return true;
		} catch (Exception e) {
			System.out.println("bookUserDelete error : " + e);
		} finally {
			pstmt.close();
			conn.close();
		}

		return false;
	}

	// 회원정보 수정
	public int BookUserUpdate(BookMemberVO vo) throws Exception {

		StringBuffer sb = new StringBuffer();
		sb.append("update T2_MEMBER set ");
		sb.append("M_PWD =?, M_NAME =?, M_ADDRESS=? where M_ID=?");
		System.out.println(vo.getM_id());
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sb.toString());

		pstmt.setString(1, vo.getM_pwd());
		pstmt.setString(2, vo.getM_name());
		pstmt.setString(3, vo.getM_address());
		pstmt.setString(4, vo.getM_id());

		int result = pstmt.executeUpdate();
		pstmt.close(); // 원래잇음
		conn.close();

		return result;

	}
				
			//userCheck(id,pwd) - 로그인시 사용할 메소드 id/pwd 체크
			public int userCheck(String id, String pwd) throws Exception{
				String sql="select M_PWD from T2_MEMBER where M_ID = ?";
				String dbpwd = "";
				int result = -1;
				
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) { // id 체크
					dbpwd = rs.getString("M_PWD");
					if(dbpwd.equals(pwd)) result = 1; // 인증성공
					else result = 0;
				} else {
					result = -1; // 초기값이니 해당 id가 없음
				} // out if end
				rs.close();
				pstmt.close();
				conn.close();
				return result;
			}
	
	//insert(vo) - 회원가입처리 메소드 
		public int BooksignUp(BookMemberVO vo) throws Exception{

					
			StringBuffer sb = new StringBuffer();
			sb.append("insert into T2_MEMBER (M_ID, M_PWD, M_NAME, M_ADDRESS)");
			sb.append(" values( ?, ?, ?, ?)");
			
			conn=ds.getConnection();
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, vo.getM_id());
			pstmt.setString(2, vo.getM_pwd());
			pstmt.setString(3, vo.getM_name());
			pstmt.setString(4, vo.getM_address());

			
			int result = pstmt.executeUpdate();
			System.out.println();
			pstmt.close(); //원래잇음
			conn.close();
			
			return result;
			
		}
		//confirmID(id) 아이디 중복 확인 체크
		public int confirmID(String id) throws Exception{
			String sql = "select M_ID from T2_MEMBER where M_ID = ?";
			int result = -1;
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) result = 1; // 해당 아이딕 ㅏ있다
			else result = -1;
			rs.close();
			pstmt.close();
			conn.close();
			return result;
			
		}
		
		
		//글쓴이 인지 확인 메소드 - isBoardWriter
			public boolean isReviewBoardWriter(int num, String id) throws SQLException {
				String sql = "select * from T2_REVIEW where R_NO=?";
				try {
					conn = ds.getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, num);
					rs = pstmt.executeQuery();
					rs.next();
					System.out.println("isBoardWriter" + id);
					if(id.equals(rs.getString("M_ID"))) { return true;}
					
				} catch (Exception e) {
					System.out.println("isBoardWriter 에러 : " + e);
					
				}finally {
					rs.close();
					pstmt.close();
					conn.close();
				}
				
				return false;
			}
		
		public boolean BookReviewDelete(BookReviewVO vo) throws SQLException{
			String sql = "delete from T2_REVIEW where R_NO =?";
			
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, vo.getR_no());
				pstmt.executeUpdate();
				return true;
			} catch (Exception e) {
				System.out.println("bookDelete error : "+e);
			}finally {
//				rs.close();
				pstmt.close();
				conn.close();
			}
			
			
			return false;
		}
			// 글 수정 - update
			public boolean BookReviewModify(BookReviewVO vo) throws SQLException {
				String sql ="update T2_REVIEW set R_SUBJECT =?, R_CONTENT=?, R_STAR=? where R_NO=?";
				
				try {
					conn = ds.getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, vo.getR_subject());
					pstmt.setString(2, vo.getR_content());
					pstmt.setInt(3, vo.getR_star());
					pstmt.setInt(4, vo.getR_no());
					pstmt.executeUpdate();
					return true;
				} catch (Exception e) {
					System.out.println("BookReviewModify error : "+e);
				}finally {
//					rs.close();
					pstmt.close();
					conn.close();
				}
				
				return false;
			}
		
		//글 내용 상세보기getDetail
		public BookReviewVO getReviewDetail(int num) throws SQLException {
			BookReviewVO vo = null;

			try {
				conn = ds.getConnection();
				pstmt =conn.prepareStatement("select * from T2_REVIEW where R_NO =?");
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				

				if(rs.next()) {
					vo = new BookReviewVO();
					
					vo.setR_no(rs.getInt("R_NO"));
					vo.setR_subject(rs.getString("R_SUBJECT"));
					vo.setM_id(rs.getString("M_ID"));
					vo.setR_star(rs.getInt("R_STAR"));
					vo.setR_image(rs.getString("R_IMAGE"));
					vo.setR_content(rs.getString("R_CONTENT"));
					vo.setR_date(rs.getDate("R_DATE"));
					vo.setR_ref(rs.getInt("R_REF"));
					
									
//					if (rs.getString("R_IMAGE") == null ) {
//					//if (rs.getString("R_IMAGE").equals(null) ) {
//						vo.setR_image(null);
//					}else {
//						vo.setR_image(rs.getString("R_IMAGE"));
//					}
												
				} // if end
				return vo;			
			} catch (Exception e) {
				System.out.println("getDetail" +e);
			}finally {
				rs.close();
				pstmt.close();
				conn.close();
			}
			return null;
		}
		
		public int getListCount() throws SQLException {
			int x = 0;
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement("select count(*) from T2_REVIEW");
				rs = pstmt.executeQuery();
				
				if(rs.next() ) x= rs.getInt(1);
				
			} catch (Exception e) {
				System.out.println("getListCount() error " + e);
			}finally {
				rs.close();
				pstmt.close();
				conn.close();
			}
			return x;
		}
		
		//글 목록 보기 select
		public List getBoardList(int page, int limit) throws SQLException {
			String sql = "select * from "+
					  "(select rownum rnum,R_NO,M_ID,R_SUBJECT,"+
					  "R_STAR,R_IMAGE,R_CONTENT,"+
					  "R_DATE,R_REF from "+
					  "(select * from T2_REVIEW order by R_NO desc)) "+
					  "where rnum>=? and rnum<=?";
			List list = new ArrayList();

			
			int startrow = (page - 1)* limit +1; // 해당페이지의 출력하고자하는 첫번째 record의 row 번호
			int endrow = startrow + limit -1;	//// 해당페이지의 출력하고자하는 마지막 record의 row 번호

			try {
				conn = ds.getConnection();
				pstmt= conn.prepareStatement(sql);
				pstmt.setInt(1, startrow);
				pstmt.setInt(2, endrow);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					BookReviewVO vo = new BookReviewVO();
					
					vo.setR_no(rs.getInt("R_NO"));
					vo.setM_id(rs.getString("M_ID"));
					vo.setR_subject(rs.getString("R_SUBJECT"));
					vo.setR_star(rs.getInt("R_STAR"));
					vo.setR_image(rs.getString("R_IMAGE"));
					vo.setR_content(rs.getString("R_CONTENT"));
					vo.setR_date(rs.getDate("R_DATE"));
					vo.setR_ref(rs.getInt("R_REF"));
					

					
					list.add(vo);
				} // while end
				
				return list;
			} catch (Exception e) {
				System.out.println("book list 에러"+e);
			} finally {
				book.connect.ConnectionClose.close(rs);
				book.connect.ConnectionClose.close(pstmt);
				book.connect.ConnectionClose.close(conn);
			}
							
			return null;
		}
		
		//글 등록
		public boolean bookReviewInsert(BookReviewVO vo) throws Exception{
			int num = 0, result = 0;
			StringBuffer sb = new StringBuffer();
			
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement("select max(R_NO) from T2_REVIEW");
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					num = rs.getInt(1) + 1;
				} else num=1;
				
				sb.append("insert into t2_review VALUES(BOARD_INC.NEXTVAL,?,?,?,?,?,sysdate,0)");
				


				pstmt = conn.prepareStatement(sb.toString());
				//pstmt.setInt(1, num); //원본글의 번호 R_NO
				pstmt.setString(1, vo.getR_subject()); //R_SUBJECT
				pstmt.setString(2, vo.getM_id()); //M_ID


				pstmt.setInt(3, vo.getR_star()); //R_STAR
				pstmt.setString(4, vo.getR_image()); //R_IMAGE
				pstmt.setString(5, vo.getR_content()); //R_CONTENT
				
				result = pstmt.executeUpdate();

				if(result == 0) {
					return false;
				}return true;
			} catch (Exception e) {
				System.out.println("bookReviewInsert  에러" + e);
			} finally {
				book.connect.ConnectionClose.close(rs);
				book.connect.ConnectionClose.close(pstmt);
				book.connect.ConnectionClose.close(conn);
			}
			return false;
		} // boardInsert() end
		
		
		//조회수증가
		public void setReviewReadCountUpdate(int num) {
			String sql ="update t2_review set R_REF = R_REF +1 where R_NO ="+num;
			
			System.out.println(num);
			
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("setReadCountUpdate() error : " + e);
			} finally {
				ConnectionClose.close(pstmt);
				ConnectionClose.close(conn);
			}
		}
		
		
		
	
	
	
	
	
	//***************************************************************************************
	//								BOOK DEAL - 중고거래 게시판
	//***************************************************************************************
	
	//==========================================================
	//				insert
	//==========================================================

	public int bookdealInsert(BookDealVO vo) {

		int num = 0 , result = 0;
		
		String sql;
		
		try {
			conn = ds.getConnection();
			
			sql = "insert into t2_deal (D_NO,D_SUBJECT,M_ID,D_CATEGORY,D_STATE,D_STATE_IMAGE,D_CONTENT,D_SHAPE,D_COMPLETE,D_DATE,D_REF,D_PRICE ) "
					+ "VALUES (BOARD_INC.NEXTVAL,?,?,?,?,?,?,?,1,sysdate,0,?)";
			
			pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1, num);							// 원본 글의 번호
			pstmt.setString(1, vo.getD_subject());
			pstmt.setString(2, vo.getM_id());
			pstmt.setInt(3, vo.getD_category());
			pstmt.setInt(4, vo.getD_state());
			pstmt.setString(5, vo.getD_state_image());
			pstmt.setString(6, vo.getD_content());				
			pstmt.setInt(7, vo.getD_shape());
			pstmt.setInt(8, vo.getD_price());
			
			result = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("select max(D_NO) from t2_deal");
			rs = pstmt.executeQuery();
			
			
			if (rs.next()) {
				num = rs.getInt(1);
			
			}
			
			if (result ==0) {
				return 0;
			}else {
				return num;
			}
			
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("bookdeal insert error : " + e);
		} finally {
			//db connect close하기위해..
			ConnectionClose.close(rs);
			ConnectionClose.close(pstmt);
			ConnectionClose.close(conn);
		}
		
		return 0;
	}
	
	//==========================================================
	//				List = select - 2개 함수 필요.
	//==========================================================
	
	
	// 게시판의 총 record 수를 가져오는 함수.. : 즉 테이블 count..
	public int getListCount(int category) {
		int listcount = 0;
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select count(*) from t2_deal where d_category=?");
			pstmt.setInt(1, category);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {		//select count(*) 문 실행결과 record 수가 출력된다. 
				listcount = rs.getInt(1);	//실행결과인 총 record 수를 listcount 에 저장..
			}
		
		} catch (Exception e) {
			System.out.println("list count error : " + e);
		} finally {
			ConnectionClose.close(rs);
			ConnectionClose.close(pstmt);
			ConnectionClose.close(conn);
		}
		return listcount;
	}

	//해당페이지의 원하는 record 수만큼 출력하는 함수
	public List getBookDealList(int page, int limit,int category) {
	
		
  //      select * from (select rownum rnum,d_no,d_subject,m_id,b_code,d_state,d_state_image,d_content,d_shape,d_complete,d_date,d_complete_date,d_ref
   //             from (select * from t2_deal order by D_NO desc)) where rnum>=1 and rnum<=5;
		String sql = "select * from (select rownum rnum,d_no,d_subject,m_id,d_category,d_state,d_state_image,d_price,d_content,d_shape,d_complete,d_date,d_complete_date,d_ref from ( select * from t2_deal where d_category=? order by D_NO desc)) where rnum>=? and rnum<=?";
		
		List<BookDealVO> list = new ArrayList<BookDealVO>();
		int startrow = (page - 1)* limit +1; // 해당페이지의 출력하고자하는 첫번째 record의 row 번호
		int endrow = startrow + limit -1;	//// 해당페이지의 출력하고자하는 마지막 record의 row 번호

		System.out.println( startrow +"," +endrow);
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, category);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {		//출력하고자하는 테이블이 rs에 저장되었고 하나의 record를 vo에 저장하고 이를 list에 추가하는것을 첫번째 record 부터 마지막 record까지 반복
				BookDealVO vo = new BookDealVO();
				vo.setD_no(rs.getInt("d_no"));
				vo.setD_subject(rs.getString("d_subject"));
				vo.setM_id(rs.getString("m_id"));
				vo.setD_category(rs.getInt("d_category"));
				vo.setD_state(rs.getInt("d_state"));
				vo.setD_state_image(rs.getString("d_state_image"));
				vo.setD_price(rs.getInt("d_price"));
				vo.setD_content(rs.getString("d_content"));
				vo.setD_shape(rs.getInt("d_shape"));
				vo.setD_complete(rs.getInt("d_complete"));
				vo.setD_date(rs.getDate("d_date"));
				vo.setD_complete_date(rs.getDate("d_complete_date"));
				vo.setD_ref(rs.getInt("d_ref"));
				
				list.add(vo);			
			}
			
		} catch (Exception e) {
			System.out.println("getBookDealList() error : " + e);
		} finally {
			ConnectionClose.close(rs);
			ConnectionClose.close(pstmt);
			ConnectionClose.close(conn);
		}
		return list;
	}

	//==========================================================
	//				view - 상세보기 : 조회수증가함수 + 
	//==========================================================
		
	//조회수 증가 함수
	public void setReadCountUpdate(int num) {
		String sql ="update t2_deal set D_REF = D_REF +1 where D_NO ="+num;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("setReadCountUpdate() error : " + e);
		} finally {
			ConnectionClose.close(pstmt);
			ConnectionClose.close(conn);
		}
	}

	// 해당 게시글 상세보기..
	public BookDealVO getDetail(int num) {
		BookDealVO vo = new BookDealVO();
		String sql = "select * from t2_deal where D_NO ="+num;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				vo.setD_no(rs.getInt("D_NO"));
				vo.setD_subject(rs.getString("D_SUBJECT"));
				vo.setM_id(rs.getString("M_ID"));
				vo.setD_category(rs.getInt("D_CATEGORY"));
				vo.setD_state(rs.getInt("D_STATE"));
				vo.setD_state_image(rs.getString("D_STATE_IMAGE"));
				vo.setD_price(rs.getInt("D_PRICE"));
				vo.setD_content(rs.getString("D_CONTENT"));
				vo.setD_shape(rs.getInt("D_SHAPE"));
				vo.setD_complete(rs.getInt("D_COMPLETE"));
				vo.setD_date(rs.getDate("D_DATE"));
				vo.setD_complete_date(rs.getDate("D_COMPLETE_DATE"));
				vo.setD_ref(rs.getInt("D_REF"));
				
				return vo;
			}
		} catch (Exception e) {
			System.out.println("getDetail() error : " + e);
			return null;
		} finally {
			ConnectionClose.close(rs);
			ConnectionClose.close(pstmt);
			ConnectionClose.close(conn);
		}
		return vo;
		
	}
	

	//==========================================================
	//				modify
	//==========================================================
/*
	// 해당 게시글을 작성한 사람이 수정하려고 하는지 비밀번호 일치여부 확인 함수 - 수정/삭제 시 사용..
	public boolean isBookDealWriter(int num, String pass) {
		String sql = "select * from filebookdeal where bookdeal_num ="+num;
		boolean result = false;
		try { 
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {	//db 출력화면에서 한줄내려가보장..
				
				if (pass.equals(rs.getString("BOARD_PASS"))) {	//출력결과의 pass 와 파라미터값의pass 가 일치한는가
					result =  true;
				}else {
					result =  false;
				}
			} else {
				result =  false;
			}
			
		} catch (Exception e) {
			System.out.println("isBookDealWriter error : " + e);
		} finally {
				ConnectionClose.close(rs);
				ConnectionClose.close(pstmt);
				ConnectionClose.close(conn);
		}
		return result;
	}
*/
	//update db에서.. 
	public boolean bookdealUpdate(BookDealVO vo) {
		String sql = "update t2_deal set D_SUBJECT = ?,D_CATEGORY=?,D_STATE=?,D_CONTENT=?, D_SHAPE=?,D_COMPLETE=?,D_DATE=sysdate, D_PRICE=? where d_no =?";
		boolean result = false;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
		
			pstmt.setString(1, vo.getD_subject());
			pstmt.setInt(2, vo.getD_category());
			pstmt.setInt(3, vo.getD_state());
			pstmt.setString(4, vo.getD_content());
			pstmt.setInt(5, vo.getD_shape());
			pstmt.setInt(6, vo.getD_complete());
			pstmt.setInt(7, vo.getD_price());
			pstmt.setInt(8, vo.getD_no());	
			
	
			
			pstmt.executeUpdate();
			result = true;
			
		
		}catch (Exception e) {
			System.out.println("bookdealUpdate() error : " + e);
		}finally {
			
			ConnectionClose.close(pstmt);
			ConnectionClose.close(conn);
		}
		return result;
	}
	
	//==========================================================
	//				delete
	//==========================================================


	public boolean bookdealDelete(int num) {
		String sql = " delete from t2_deal where d_no="+num;
		boolean result = false;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			result = true;
			
		
		}catch (Exception e) {
			System.out.println("bookdealDelete() error : " + e);
		}finally {
			ConnectionClose.close(pstmt);
			ConnectionClose.close(conn);
		}
		return result;
	}
	
	
	//==========================================================
	//				home 화면..
	//==========================================================

	
	
	
	// home 화면에 최근 상품 4개 가져오기
	public List<BookDealVO> getBookDealListHome() {
		String sql = "select * from (select * from t2_deal order by d_no desc) WHERE ROWNUM <= 4";
		
		List<BookDealVO> list = new ArrayList<BookDealVO>();
		
	
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {		//출력하고자하는 테이블이 rs에 저장되었고 하나의 record를 vo에 저장하고 이를 list에 추가하는것을 첫번째 record 부터 마지막 record까지 반복
				BookDealVO vo = new BookDealVO();
				vo.setD_no(rs.getInt("d_no"));
				vo.setD_subject(rs.getString("d_subject"));
				vo.setM_id(rs.getString("m_id"));
				vo.setD_category(rs.getInt("d_category"));
				vo.setD_state(rs.getInt("d_state"));
				vo.setD_state_image(rs.getString("d_state_image"));
				vo.setD_price(rs.getInt("d_price"));
				vo.setD_content(rs.getString("d_content"));
				vo.setD_shape(rs.getInt("d_shape"));
				vo.setD_complete(rs.getInt("d_complete"));
				vo.setD_date(rs.getDate("d_date"));
				vo.setD_complete_date(rs.getDate("d_complete_date"));
				vo.setD_ref(rs.getInt("d_ref"));
				
				list.add(vo);			
			}
			
		} catch (Exception e) {
			System.out.println("getBookDealListHome() error : " + e);
		} finally {
			ConnectionClose.close(rs);
			ConnectionClose.close(pstmt);
			ConnectionClose.close(conn);
		}
		return list;
	}

	

	//home 화면에 최근 모임 공지 2개 가져오기
	public List<BookDealVO> getBookOfflineListHome() {
		String sql = "select * from (select * from T2_OFFLINE order by o_no desc) WHERE ROWNUM <= 2";

		List list = new ArrayList();
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BookOfflineVO vo = new BookOfflineVO();
				vo.setO_no(rs.getInt("O_NO"));
				vo.setO_subject(rs.getString("O_SUBJECT"));
				vo.setM_id(rs.getString("M_ID"));
				vo.setO_address(rs.getString("O_ADDRESS"));
				vo.setO_content(rs.getString("O_CONTENT"));
				vo.setO_date(rs.getDate("O_DATE"));
				vo.setO_ref(rs.getInt("O_REF"));

				list.add(vo);
			}

		} catch (Exception e) {
			System.out.println("getBookOfflineListHome : " + e);
		} finally {
			ConnectionClose.close(rs);
			ConnectionClose.close(pstmt);
			ConnectionClose.close(conn);
		}
		return list;
	}
	
	public boolean isDealBoardWriter(int num, String id) throws SQLException {
		String sql = "select * from T2_deal where d_NO=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			rs.next();
			System.out.println("isBoardWriter" + id);
			if(id.equals(rs.getString("M_ID"))) { return true;}
			
		} catch (Exception e) {
			System.out.println("isBoardWriter 에러 : " + e);
			
		}finally {
			ConnectionClose.close(rs);
			ConnectionClose.close(pstmt);
			ConnectionClose.close(conn);
		}
		
		return false;
	}
	

	
		//==========================================================
		//				Mypage
		//==========================================================
	
	
	//listcount
	public int getMyListCount(String board) throws SQLException {
		int x = 0;
		String sql = null;
		if(board.equals("T2_REVIEW")) sql = "select count(*) from T2_REVIEW";
		else if(board.equals("T2_DEAL")) sql = "select count(*) from T2_DEAL";
		else if(board.equals("T2_OFFLINE")) sql = "select count(*) from T2_OFFLINE";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next() ) x= rs.getInt(1);
			
		} catch (Exception e) {
			System.out.println("getListCount() error " + e);
		}finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return x;
	}
	
	// 게시글
	public List myReviewList(String board, String id, int page, int limit) throws Exception{
		String sql = null;
		if(board.equals("T2_REVIEW")) {
		sql = "select * from "
				+ "(select rownum rnum, R_SUBJECT, R_DATE, R_IMAGE, R_NO, R_REF from "
				+ "(select * from T2_REVIEW where M_ID = ? order by R_NO desc)) where rnum>=? and rnum<=?";
		}
		else if(board.equals("T2_DEAL")) {
			sql = "select * "
					+ "from (select rownum rnum, D_SUBJECT, D_DATE, D_STATE_IMAGE, D_NO, D_REF from "
					+ "(select * from T2_DEAL where M_ID = ? order by D_NO desc)) where rnum>=? and rnum<=?";
		}
		else if(board.equals("T2_OFFLINE")) {
			sql = "select * from "
					+ "(select rownum rnum, O_SUBJECT, O_DATE, O_ADDRESS, O_NO, O_REF from "
					+ "(select * from T2_OFFLINE where M_ID = ? order by O_NO desc)) where rnum>=? and rnum<=?";
		}
		
		int startrow = (page - 1)* limit +1; // 해당페이지의 출력하고자하는 첫번째 record의 row 번호
		int endrow = startrow + limit -1;	//// 해당페이지의 출력하고자하는 마지막 record의 row 번호

		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			List list = new ArrayList();
			
			if (board.equals("T2_REVIEW")) {
				while (rs.next()) {

					BookReviewVO vo = new BookReviewVO();
					vo.setR_subject(rs.getString("R_SUBJECT"));
					vo.setR_date(rs.getDate("R_DATE"));
					vo.setR_image(rs.getString("R_IMAGE"));
					vo.setR_no(rs.getInt("R_NO"));
					vo.setM_id("ReviewDetailAction");
					vo.setR_ref(rs.getInt("R_REF"));
					list.add(vo);
				}
			}
			else if(board.equals("T2_DEAL")) {
				while (rs.next()) {
					
					BookReviewVO vo = new BookReviewVO();
					vo.setR_subject(rs.getString("D_SUBJECT"));
					vo.setR_date(rs.getDate("D_DATE"));
					vo.setR_image(rs.getString("D_STATE_IMAGE"));
					vo.setR_no(rs.getInt("D_NO"));
					vo.setM_id("DealDetailAction");
					vo.setR_ref(rs.getInt("D_REF"));

					list.add(vo);
				}
			}
			else if(board.equals("T2_OFFLINE")) {
				while (rs.next()) {
					
					BookReviewVO vo = new BookReviewVO();
					vo.setR_subject(rs.getString("O_SUBJECT"));
					vo.setR_date(rs.getDate("O_DATE"));
					vo.setR_image(rs.getString("O_ADDRESS"));
					vo.setR_no(rs.getInt("O_NO"));
					vo.setM_id("OfflineViewAction");
					vo.setR_ref(rs.getInt("O_REF"));
					list.add(vo);
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			ConnectionClose.close(rs);
			ConnectionClose.close(pstmt);
			ConnectionClose.close(conn);
		}
		return null;
		
	}
	
	
	
	public List<BookDealVO> getBookDealListSearch(String search) {
		String sql = "select * from T2_DEAL WHERE d_subject like '%"+search+"%' ORDER BY d_no DESC";

		List list = new ArrayList();
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			
			rs = pstmt.executeQuery();

			
			if (rs.next()) {
				do {
					BookDealVO vo = new BookDealVO();
					vo.setD_no(rs.getInt("d_no"));
					vo.setD_subject(rs.getString("d_subject"));
					vo.setM_id(rs.getString("m_id"));
					vo.setD_category(rs.getInt("d_category"));
					vo.setD_state(rs.getInt("d_state"));
					vo.setD_state_image(rs.getString("d_state_image"));
					vo.setD_price(rs.getInt("d_price"));
					vo.setD_content(rs.getString("d_content"));
					vo.setD_shape(rs.getInt("d_shape"));
					vo.setD_complete(rs.getInt("d_complete"));
					vo.setD_date(rs.getDate("d_date"));
					vo.setD_complete_date(rs.getDate("d_complete_date"));
					vo.setD_ref(rs.getInt("d_ref"));

					list.add(vo);
				}while(rs.next());
			}
			else {
				return null;
			}
			return list;
			

		} catch (Exception e) {
			System.out.println("getBookDealListSearch : " + e);
		} finally {
			ConnectionClose.close(rs);
			ConnectionClose.close(pstmt);
			ConnectionClose.close(conn);
		}
		return null;
	}


	public List<BookReviewVO> getBookReviewListSearch(String search) {
		
		String sql = "select * from T2_REVIEW WHERE r_subject like '%"+search+"%' ORDER BY r_no DESC";
		
		List list = new ArrayList();

	
		try {
			conn = ds.getConnection();
			pstmt= conn.prepareStatement(sql);
	
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				do {
				BookReviewVO vo = new BookReviewVO();				
				vo.setR_no(rs.getInt("R_NO"));
				vo.setM_id(rs.getString("M_ID"));
				vo.setR_subject(rs.getString("R_SUBJECT"));
				vo.setR_star(rs.getInt("R_STAR"));
				vo.setR_image(rs.getString("R_IMAGE"));
				vo.setR_content(rs.getString("R_CONTENT"));
				vo.setR_date(rs.getDate("R_DATE"));
				vo.setR_ref(rs.getInt("R_REF"));
	
				list.add(vo);
				}while(rs.next());
			}
			else {
				return null;
			}
			return list;
			
			
		} catch (Exception e) {
			System.out.println("getBookReviewListSearch 에러"+e);
		} finally {
			book.connect.ConnectionClose.close(rs);
			book.connect.ConnectionClose.close(pstmt);
			book.connect.ConnectionClose.close(conn);
		}
						
		return null;
	}

	public List<BookOfflineVO> getBookOfflineListSearch(String search) {
		
		String sql = "select * from T2_OFFLINE WHERE O_SUBJECT like '%"+search+"%' ORDER BY O_NO DESC";

		List list = new ArrayList();

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				do {
				BookOfflineVO vo = new BookOfflineVO();
				vo.setO_no(rs.getInt("O_NO"));
				vo.setO_subject(rs.getString("O_SUBJECT"));
				vo.setM_id(rs.getString("M_ID"));
				vo.setO_address(rs.getString("O_ADDRESS"));
				vo.setO_content(rs.getString("O_CONTENT"));
				vo.setO_date(rs.getDate("O_DATE"));
				vo.setO_ref(rs.getInt("O_REF"));

				list.add(vo);
				}while(rs.next());
			}
			else {
				return null;
			}
			return list;
			
		} catch (Exception e) {
			System.out.println("getBookOfflineListSearch : " + e);
		} finally {
			ConnectionClose.close(rs);
			ConnectionClose.close(pstmt);
			ConnectionClose.close(conn);
		}
		return null;
	}
	
}
