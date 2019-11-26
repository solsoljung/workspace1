package com.kh.vo;

import com.kh.dao.Dao;

public class PagingDto {

	private int nowPage;
	private int startRow;
	private int endRow;
	private int startPage;
	private int endPage;
	private int totalPage;
	private int perPage = 10;
	public PagingDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PagingDto(int nowPage, SearchDto searchDto) {
		
		Dao dao = Dao.getInstance();
		int count = dao.getCount(searchDto);
		totalPage = (int)(Math.ceil((double)count / perPage));
		
		this.nowPage = nowPage;
		startRow = nowPage * perPage - (perPage - 1);
		endRow = nowPage * perPage;
		
		startPage = ((nowPage - 1) / 10) * 10 + 1;
		endPage = (startPage + (10 - 1));
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		/*System.out.println("nowPage:"+nowPage);
		System.out.println("startRow:"+startRow);
		System.out.println("endRow:"+endRow);
		System.out.println("startPage:"+startPage);
		System.out.println("endPage:"+endPage);
		System.out.println("totalPage:"+totalPage);*/
		System.out.println("totalPage:"+totalPage);
	}
	
	public PagingDto(int nowPage, int startRow, int endRow, int startPage, int endPage, int totalPage, int perPage) {
		super();
		this.nowPage = nowPage;
		this.startRow = startRow;
		this.endRow = endRow;
		this.startPage = startPage;
		this.endPage = endPage;
		this.totalPage = totalPage;
		this.perPage = perPage;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	@Override
	public String toString() {
		return "PagingDto [nowPage=" + nowPage + ", startRow=" + startRow + ", endRow=" + endRow + ", startPage="
				+ startPage + ", endPage=" + endPage + ", totalPage=" + totalPage + ", perPage=" + perPage + "]";
	}
}
