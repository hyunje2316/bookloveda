package book.model;

import java.sql.Date;

public class BookBookVO {
	private String b_code, b_name, b_image, b_author, b_translate, b_publish,
	b_genre, b_category;
	private int b_price, b_total_deal;
	private Date b_pub_date;
	
	
	public String getB_code() {
		return b_code;
	}
	public void setB_code(String b_code) {
		this.b_code = b_code;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	public String getB_image() {
		return b_image;
	}
	public void setB_image(String b_image) {
		this.b_image = b_image;
	}
	public String getB_author() {
		return b_author;
	}
	public void setB_author(String b_author) {
		this.b_author = b_author;
	}
	public String getB_translate() {
		return b_translate;
	}
	public void setB_translate(String b_translate) {
		this.b_translate = b_translate;
	}
	public String getB_publish() {
		return b_publish;
	}
	public void setB_publish(String b_publish) {
		this.b_publish = b_publish;
	}
	public String getB_genre() {
		return b_genre;
	}
	public void setB_genre(String b_genre) {
		this.b_genre = b_genre;
	}
	public String getB_category() {
		return b_category;
	}
	public void setB_category(String b_category) {
		this.b_category = b_category;
	}
	public int getB_price() {
		return b_price;
	}
	public void setB_price(int b_price) {
		this.b_price = b_price;
	}
	public int getB_total_deal() {
		return b_total_deal;
	}
	public void setB_total_deal(int b_total_deal) {
		this.b_total_deal = b_total_deal;
	}
	public Date getB_pub_date() {
		return b_pub_date;
	}
	public void setB_pub_date(Date b_pub_date) {
		this.b_pub_date = b_pub_date;
	}
	
}
