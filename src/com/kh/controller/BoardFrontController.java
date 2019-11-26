package com.kh.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.kh.service.BoardContent;
import com.kh.service.BoardDeleteForm;
import com.kh.service.BoardDeletePro;
import com.kh.service.BoardList;
import com.kh.service.BoardUpdateForm;
import com.kh.service.BoardUpdatePro;
import com.kh.service.BoardWriteForm;
import com.kh.service.BoardWritePro;
import com.kh.service.IBoardService;

@WebServlet("*.board")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Map<String, Object> commandMap = new HashMap<>(); //Command.properties의 키 값 여기에 넣을 것임
	
    public BoardFrontController() {
        super();
    }

    //doget,dopost와 달리 한번만 실행된다!
	@Override
	public void init() throws ServletException {
		super.init();
		loadProperties();
	}

	private void loadProperties() {
		ResourceBundle bundel = ResourceBundle.getBundle("com.kh.properties.Command"); //뒤의 properties는 자동으로 채워주기때문에 생략
		Enumeration<String> keys = bundel.getKeys();
		
		while(keys.hasMoreElements() == true) {
			String command = keys.nextElement(); //list
			String className = bundel.getString(command); //com.kh.service.BoardList
			try {
				Class<?> commandClass = Class.forName(className);
				Object obj = commandClass.newInstance();
				// >>new BoardList() 한 것
				commandMap.put(command, obj);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("commandMap:"+commandMap);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = getText(request);
		IBoardService service = (IBoardService)commandMap.get(command);
		System.out.println(command);
		
		try {
			String page = service.execute(request, response);
			if(page.startsWith(IBoardService.STR_RE)) {
				String redirect = page.substring(IBoardService.STR_RE.length());
				response.sendRedirect(redirect);
			}else {
				String forwardPage = "WEB-INF/views/board/" + page + ".jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPage);
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getText(HttpServletRequest request) {
		String uri = request.getRequestURI();
//		System.out.println(uri);
		String contextpath = request.getContextPath();
//		System.out.println(contextpath);
		int startPoint = contextpath.length() + 1;
		int endPoint = uri.lastIndexOf(".");
		String command = uri.substring(startPoint, endPoint);
		return command;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
