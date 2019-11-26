package com.kh.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.kh.dao.Dao;
import com.kh.vo.PagingDto;
import com.kh.vo.SearchDto;
import com.kh.vo.Vo;
import com.sun.prism.Image;

public class BoardAjaxList implements IBoardService {
	
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
		
		JSONArray array = new JSONArray();
		for(Vo data : list) {
			JSONObject object = new JSONObject();
			object.put("num", data.getNum());
			object.put("subject", data.getSubject());
			object.put("writer", data.getWriter());
			object.put("reg_date", data.getReg_date().toString());
			object.put("read_count", data.getRead_count());
			object.put("ip", data.getIp());
			
			array.add(object);
		}
		String data = array.toJSONString();
		
		request.setAttribute("data", data);
		
		return "data";
	}

}
