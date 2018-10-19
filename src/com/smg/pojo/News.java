package com.smg.pojo;

import java.util.Date;

public class News {
	private int n_id;
	private int news_id;
	private String title;
	private String n_context;
	private String author;
	private Date news_date;
	private String news_img_path;
	public News() {
		super();
	}
	public News(int n_id, int news_id, String title, String n_context, String author, Date news_date,
			String news_img_path) {
		super();
		this.n_id = n_id;
		this.news_id = news_id;
		this.title = title;
		this.n_context = n_context;
		this.author = author;
		this.news_date = news_date;
		this.news_img_path = news_img_path;
	}
	public int getN_id() {
		return n_id;
	}
	public void setN_id(int n_id) {
		this.n_id = n_id;
	}
	public int getNews_id() {
		return news_id;
	}
	public void setNews_id(int news_id) {
		this.news_id = news_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getN_context() {
		return n_context;
	}
	public void setN_context(String n_context) {
		this.n_context = n_context;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getNews_date() {
		return news_date;
	}
	public void setNews_date(Date news_date) {
		this.news_date = news_date;
	}
	public String getNews_img_path() {
		return news_img_path;
	}
	public void setNews_img_path(String news_img_path) {
		this.news_img_path = news_img_path;
	}
	@Override
	public String toString() {
		return "News [n_id=" + n_id + ", news_id=" + news_id + ", title=" + title + ", n_context=" + n_context
				+ ", author=" + author + ", news_date=" + news_date + ", news_img_path=" + news_img_path + "]";
	}
}
