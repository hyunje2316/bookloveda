package book.model;

import java.sql.Date;

public class BookReviewVO {
	private int r_no, r_star, r_ref;
	private String r_subject, m_id, r_image, r_content;
	private Date r_date;
	
	
	public int getR_no() {
		return r_no;
	}
	public void setR_no(int r_no) {
		this.r_no = r_no;
	}
	public int getR_star() {
		return r_star;
	}
	public void setR_star(int r_star) {
		this.r_star = r_star;
	}
	public int getR_ref() {
		return r_ref;
	}
	public void setR_ref(int r_ref) {
		this.r_ref = r_ref;
	}
	public String getR_subject() {
		return r_subject;
	}
	public void setR_subject(String r_subject) {
		this.r_subject = r_subject;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getR_image() {
		return r_image;
	}
	public void setR_image(String r_image) {
		this.r_image = r_image;
	}
	public String getR_content() {
		return r_content;
	}
	public void setR_content(String r_content) {
		this.r_content = r_content;
	}
	public Date getR_date() {
		return r_date;
	}
	public void setR_date(Date r_date) {
		this.r_date = r_date;
	}
	
}
