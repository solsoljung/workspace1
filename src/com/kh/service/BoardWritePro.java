package com.kh.service;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.dao.Dao;
import com.kh.util.MyFileUploder;
import com.kh.vo.Vo;
import com.oreilly.servlet.MultipartRequest;

public class BoardWritePro implements IBoardService {
	
	Dao dao = Dao.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MultipartRequest multi = MyFileUploder.upload(request);
		
		String writer = multi.getParameter("writer");
		String email = multi.getParameter("email");
		String subject = multi.getParameter("subject");
		String content = multi.getParameter("content");
		String passwd = multi.getParameter("passwd");
		String ip = request.getRemoteAddr();
		
		Enumeration<?> enumer = multi.getFileNames();
		String file1 = (String)enumer.nextElement();
		String filesystemName = multi.getFilesystemName(file1);
		
		Vo vo = new Vo();
		vo.setWriter(writer);
		vo.setEmail(email);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPasswd(passwd);
		vo.setIp(ip);
		vo.setFile_name(filesystemName);
		
		dao.insert(vo);
		
		return IBoardService.STR_RE + "list.board";
	}

}
