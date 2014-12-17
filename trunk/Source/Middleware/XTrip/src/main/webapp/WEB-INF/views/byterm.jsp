<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<!-- <script language="javascript" type="text/javascript"> -->
<!-- // 	function jumpto(x) { -->
<!-- // 		if (document.form2.jumpmenu.value != "null") { -->
<!-- // 			document.location.href = x -->
<!-- // 		} -->
<!-- // 	} -->
<!-- </script> -->
<title>Metaphor</title>
</head>
<body>
	<h1>Metaphor Challenge</h1>
	<a href="${pageContext.request.contextPath}/tweets">Tweets</a>
	<br><br>
	<form action="${pageContext.request.contextPath}/<c:out value="${domain}" escapeXml="false" />">
		Range: (from -50 to 50) 
		<br> 
		From: <input type="text" name="from" value="<c:out value="${from}" escapeXml="false" />">
		To: <input type="text" name="to" value="<c:out value="${to}" escapeXml="false" />"> 
		<br>
		<br>
		Search by word: <br> <input type="text" name="words" value="<c:out value="${words}" escapeXml="false" />"> 
		<br> 
		<input type="checkbox" name="matchcase" <c:out value="${checked}" escapeXml="false" />> Match Case
		<br> 
		<br>
		<select name="page">
			<option>Jump to...</option>
			<c:out value="${options}" escapeXml="false" />
		</select> 
		<br>
		<br>
		<input type="submit" value="Submit">
	</form>
	<br> <c:out value="${iframes}" escapeXml="false" />
</body>
</html>
