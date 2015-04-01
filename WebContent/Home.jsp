<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Yago-AIDA</title>
</head>
<body>
<f:view>
<form action="Yago" METHOD="post">
<h2>please enter your text: </h2>
<input type= "text" name= "Enteredtext" style="width: 300px;"/>
<br></br>
<br>
<input type="submit" value="submit"/>
<input type ="reset" value="reset"/>
</br>
<%if(request.getAttribute("output") !=null){ %>
<br>
</br>
<p>Text Runner Output<br> <%=request.getAttribute("kdId")  %></p>
<%} %>
</form>
</f:view>
</body>
</html>