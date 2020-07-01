package book.model;

import java.sql.Date;

public class BookCommentVO {
	private int c_board_no, c_no;
	private String m_id, c_comment;
	private Date c_date;
	
	
	public Date getC_date() {
		return c_date;
	}
	public void setC_date(Date c_date) {
		this.c_date = c_date;
	}
	public int getC_board_no() {
		return c_board_no;
	}
	public void setC_board_no(int c_board_no) {
		this.c_board_no = c_board_no;
	}
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getC_comment() {
		return c_comment;
	}
	public void setC_comment(String c_comment) {
		this.c_comment = c_comment;
	}
	
}
