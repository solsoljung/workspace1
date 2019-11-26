package com.kh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.dao.Dao;
import com.kh.vo.Vo;

public class BoardUpdatePro implements IBoardService {
	
	Dao dao = Dao.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int num = Integer.parseInt(request.getParameter("num"));
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String passwd = request.getParameter("passwd");
		
		Vo vo = new Vo();
		
		vo.setNum(num);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPasswd(passwd);
		
		dao.update(vo);
		
		return IBoardService.STR_RE + "content.board?num=" + num;
	}

}
