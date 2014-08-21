<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Alice | web service</title>
</head>
<body>
<h4 align="center">
Hello, you've reached kites web service (Alice)!<br>
<%= LocalDateTime.now() %>
</h4>
</body>
</html>