package book.model;

import java.sql.Date;


public class BookDealVO {
	private int d_no;					//게시글 고유번호 -pk
	private String d_subject;			//제목
	private String m_id;				//작성자 id - fk
	//private String b_code;				//판매책 code -fk
	
	private int d_category;			//판매 책 카테고리
	
	private int d_state;				//상품 상태 등급 - 1~5등급
	
	private String d_state_image;		//상픔 상태 img 파일
	private int d_price;
	
	private String d_content;			//내용
	
	private int d_shape;				//거래방법 	- 1: 택배,	 2: 직거래
	private int d_complete;				//거래완료 여부 - 1: 거래미완료,	 2:거래완료
	
	private Date d_date;				//게시글작성날짜
	private Date d_complete_date;		//거래 완료 날짜
	private int d_ref;					//조회수
	
	
	
	
	public int getD_category() {
		return d_category;
	}
	public void setD_category(int d_category) {
		this.d_category = d_category;
	}
	public int getD_price() {
		return d_price;
	}
	public void setD_price(int d_price) {
		this.d_price = d_price;
	}
	public int getD_no() {
		return d_no;
	}
	public void setD_no(int d_no) {
		this.d_no = d_no;
	}
	public String getD_subject() {
		return d_subject;
	}
	public void setD_subject(String d_subject) {
		this.d_subject = d_subject;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	
	public int getD_state() {
		return d_state;
	}
	public void setD_state(int d_state) {
		this.d_state = d_state;
	}
	public String getD_state_image() {
		return d_state_image;
	}
	public void setD_state_image(String d_state_image) {
		this.d_state_image = d_state_image;
	}
	public String getD_content() {
		return d_content;
	}
	public void setD_content(String d_content) {
		this.d_content = d_content;
	}
	public int getD_shape() {
		return d_shape;
	}
	public void setD_shape(int d_shape) {
		this.d_shape = d_shape;
	}
	public int getD_complete() {
		return d_complete;
	}
	public void setD_complete(int d_complete) {
		this.d_complete = d_complete;
	}
	public Date getD_date() {
		return d_date;
	}
	public void setD_date(Date d_date) {
		this.d_date = d_date;
	}
	public Date getD_complete_date() {
		return d_complete_date;
	}
	public void setD_complete_date(Date d_complete_date) {
		this.d_complete_date = d_complete_date;
	}
	public int getD_ref() {
		return d_ref;
	}
	public void setD_ref(int d_ref) {
		this.d_ref = d_ref;
	}
	
    
}
