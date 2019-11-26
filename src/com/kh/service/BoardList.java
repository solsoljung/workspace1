package com.kh.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.dao.Dao;
import com.kh.vo.PagingDto;
import com.kh.vo.SearchDto;
import com.kh.vo.Vo;
import com.sun.prism.Image;

public class BoardList implements IBoardService {
	
	Dao dao = Dao.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int nowPage = 1;
		String strPage = request.getParameter("nowPage");
		if(strPage != null && !strPage.equals("")) {
			nowPage = Integer.parseInt(strPage);
		}

		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("keyword");
		
		SearchDto searchDto = new SearchDto(searchType, keyword);
		PagingDto pagingDto = new PagingDto(nowPage, searchDto);
		
		System.out.println("nowPage:"+nowPage);
		
		List<Vo> list = dao.getArticles(pagingDto, searchDto);
		request.setAttribute("list", list);
		request.setAttribute("pagingDto", pagingDto);
		request.setAttribute("searchDto", searchDto);
		
//		return "list";
		return "ajax_list";
	}

}
