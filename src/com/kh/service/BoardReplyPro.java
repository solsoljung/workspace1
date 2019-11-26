package com.kh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.dao.Dao;
import com.kh.vo.Vo;

public class BoardReplyPro implements IBoardService {
	
	Dao dao = Dao.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String writer = request.getParameter("writer");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String passwd = request.getParameter("passwd");
		String ip = request.getRemoteAddr();
		int ref = Integer.parseInt(request.getParameter("ref"));
		int re_step = Integer.parseInt(request.getParameter("re_step"));
		int re_level = Integer.parseInt(request.getParameter("re_level"));
		
		Vo vo = new Vo();
		vo.setWriter(writer);
		vo.setEmail(email);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPasswd(passwd);
		vo.setIp(ip);
		vo.setRef(ref);
		vo.setRe_step(re_step);
		vo.setRe_level(re_level);
		
		dao.reply(vo);
		
		return IBoardService.STR_RE + "list.board";
	}

}
