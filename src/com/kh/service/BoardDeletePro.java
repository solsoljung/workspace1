package com.kh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.dao.Dao;

public class BoardDeletePro implements IBoardService {
	Dao dao = Dao.getInstance();
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int num = Integer.parseInt(request.getParameter("num"));
		String passwd = request.getParameter("passwd");
		
		dao.delete(num, passwd);
		return IBoardService.STR_RE + "list.board";
	}

}
