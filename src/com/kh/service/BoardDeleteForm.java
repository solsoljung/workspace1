package com.kh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.dao.Dao;

public class BoardDeleteForm implements IBoardService {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String num = request.getParameter("num");
		
		request.setAttribute("num", num);
		
		return "delete_form";
	}

}
