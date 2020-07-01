package book.model;

import java.sql.Date;

public class BookOfflineVO {
	private int o_no, o_ref;
	private String o_subject, m_id, o_address, o_content;
	private Date o_date;
	
	
	public int getO_no() {
		return o_no;
	}
	public void setO_no(int o_no) {
		this.o_no = o_no;
	}
	public int getO_ref() {
		return o_ref;
	}
	public void setO_ref(int o_ref) {
		this.o_ref = o_ref;
	}
	public String getO_subject() {
		return o_subject;
	}
	public void setO_subject(String o_subject) {
		this.o_subject = o_subject;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getO_address() {
		return o_address;
	}
	public void setO_address(String o_address) {
		this.o_address = o_address;
	}
	public String getO_content() {
		return o_content;
	}
	public void setO_content(String o_content) {
		this.o_content = o_content;
	}
	public Date getO_date() {
		return o_date;
	}
	public void setO_date(Date o_date) {
		this.o_date = o_date;
	}
	
}
