<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Metaphor Challenge</title>
</head>
<body>
	<h1>Tweet Detail</h1>
	<font size="5"><b>Tweet ID:</b> <c:out value="${id}" escapeXml="false" /></font>
	<br>
	<br>
	<font size="5"><b>Content:</b> <c:out value="${text}" escapeXml="false" /></font>
	<br>
	<br>
	<font size="5"><b>Int Score:</b> <c:out value="${intscore}"
			escapeXml="false" /></font>
	<br>
	<br>

	<font size="5"><b>Real Score:</b> <c:out value="${realscore}"
			escapeXml="false" /></font>
	<br>
	<br>

	<font size="5"><b>Probability:</b></font>
	<br>
	<table border="1" style="width: 100%;">
		<tr>
			<th align="center"></th>
			<c:forEach var="value" items="${terms}">
				<th align="center">${value.word}</th>
			</c:forEach>
		</tr>
		<tr>
			<td align="center">histogram</td>
			<c:forEach var="value" items="${terms}">
				<c:forEach var="histogram" items="${value.histogram}">
					<td align="center" style="vertical-align: middle;"><c:out
							value="${histogram}" /></td>
				</c:forEach>
			</c:forEach>
		</tr>
		<tr>
			<td align="center">p_of_term_given_score</td>
			<c:forEach var="value" items="${terms}">
				<c:forEach var="p_of_term_given_score"
					items="${value.p_of_term_given_score}">
					<td align="center" style="vertical-align: middle;"><c:out
							value="${p_of_term_given_score}" /></td>
				</c:forEach>
			</c:forEach>
		</tr>
		<tr>
			<td align="center">p_of_score_given_term</td>
			<c:forEach var="value" items="${terms}">
				<c:forEach var="p_of_score_given_term"
					items="${value.p_of_score_given_term}">
					<td align="center" style="vertical-align: middle;"><c:out
							value="${p_of_score_given_term}" /></td>
				</c:forEach>
			</c:forEach>
		</tr>
	</table>
	<br>
	<br>
	<font size="5"><b>Histogram:</b></font>
	<c:out value="${iframes}" escapeXml="false" />
</body>
</html>
