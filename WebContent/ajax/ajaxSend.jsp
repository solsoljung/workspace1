<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.vo.SampleDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
/* int v = Integer.parseInt(request.getParameter("v"));
out.println("안녕"+v); */

	List<SampleDto> list = new ArrayList<>();
	list.add(new SampleDto("홍길동", 12));
	list.add(new SampleDto("김길동", 22));
	list.add(new SampleDto("최길동", 32));
	list.add(new SampleDto("유길동", 42));
	/* out.println(list); */
	
	JSONArray array = new JSONArray();
	for(SampleDto dto : list){
		JSONObject object = new JSONObject();
		object.put("name", dto.getName());
		object.put("age", dto.getAge());
		array.add(object);
	}
	
	String data = array.toJSONString();
	out.println(data);
%>