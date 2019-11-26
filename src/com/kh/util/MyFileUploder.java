package com.kh.util;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


public class MyFileUploder {
	private static final String SAVE_DIRECTORY = "upload";
	private static final int MAX_SIZE = 1024*1024*10;
	private static final String ENCODING = "UTF-8";

	public static MultipartRequest upload(HttpServletRequest request) throws Exception {
		ServletContext application = request.getServletContext();
		String contextPath = application.getRealPath("/");
		String uploadPath = contextPath + SAVE_DIRECTORY;
		System.out.println(uploadPath);
		
		File f = new File(uploadPath);
		if(!f.exists()) {
			f.mkdir();
		}
		MultipartRequest multi = new MultipartRequest(request,
													  uploadPath,
													  MAX_SIZE,
													  ENCODING,
													  new DefaultFileRenamePolicy());
		return multi;
	}
}
