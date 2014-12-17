<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Metaphor Challenge</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/histogram-one-axis/raphael.css"
	type="text/css" media="screen">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/histogram-one-axis/raphael-print.css"
	type="text/css" media="print">
<script src="${pageContext.request.contextPath}/resources/js/histogram-one-axis/raphael.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/histogram-one-axis/raphael-popup.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/histogram-one-axis/raphael-jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/histogram-one-axis/raphael-analytics.js"></script>
<style type="text/css" media="screen">
#holder {
	height: 250px;
	margin: -150px 0 0 -420px;
	width: 800px;
}
</style>
</head>
<body>
	<table id="data">
		<tfoot>
			<tr>
				<c:out value="${heads}" escapeXml="false" />
			</tr>
		</tfoot>
		<tbody>
			<tr>
				<c:out value="${datas}" escapeXml="false" />
			</tr>
		</tbody>
	</table>
	<div id="holder"></div>
	<p id="copy">
		<a href="${pageContext.request.contextPath}/tweet?id=<c:out
			value="${id}" escapeXml="false" />"><font
			color="white"><c:out value="${words}" escapeXml="false" /></font></a> <br>
	</p>
</body>
</html>
