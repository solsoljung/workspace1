package com.kh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.dao.Dao;
import com.kh.vo.Vo;

public class BoardReplyForm implements IBoardService {
	
	Dao dao = Dao.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int num = Integer.parseInt(request.getParameter("num"));
		
		Vo vo = dao.selectArticleByNum(num);
		
		request.setAttribute("vo", vo);
		
		return "reply_form";
	}

}
