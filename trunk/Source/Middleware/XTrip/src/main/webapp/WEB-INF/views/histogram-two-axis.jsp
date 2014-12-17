<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset='utf-8'>
<title>Metaphor Challenge</title>
<link
	href="${pageContext.request.contextPath}/resources/css/histogram-two-axis/normalize-e465cb86.css"
	media="screen" rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/resources/css/histogram-two-axis/foundation.min-978d4ce8.css"
	media="screen" rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/resources/css/histogram-two-axis/tomorrow-d7cf0921.css"
	media="screen" rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/resources/css/histogram-two-axis/c3-0a1ea9c6.css"
	media="screen" rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/resources/css/histogram-two-axis/style-725352cb.css"
	media="screen" rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/resources/css/histogram-two-axis/simple_multiple-da39a3ee.css"
	media="screen" rel="stylesheet" type="text/css" />
<script
	src="${pageContext.request.contextPath}/resources/js/histogram-two-axis/modernizr-2.6.1.min-68fdcc99.js"
	type="text/javascript"></script>
<script type="text/javascript">
	
</script>

</head>
<body class='antialiased'>
	<div class='container'>
		<h1 class='title'>
			<c:out value="${word}" escapeXml="false" />
		</h1>
		<div class='chart'>
			<div id='chart'></div>
		</div>
	</div>
	<script
		src="${pageContext.request.contextPath}/resources/js/histogram-two-axis/jquery-1.11.0.min-910066fb.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/histogram-two-axis/foundation.min-1dfe8110.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/histogram-two-axis/highlight.pack-4af5004d.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/histogram-two-axis/d3.min-3bff8220.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/histogram-two-axis/c3.min-e216ed25.js"
		type="text/javascript"></script>
	<script>
		hljs.initHighlightingOnLoad();
		$(document).foundation();
	</script>
	<script>
		$(function() {
			$('a[href^=#]')
					.click(
							function() {
								var href = $(this).attr("href"), target = $('.column-content a[href^='
										+ href + ']'), position;
								if (!target) {
									return false;
								}
								position = target.offset().top - 60;
								$("html, body").animate({
									scrollTop : position
								}, 350, "swing");
								return false;
							});
		});
	</script>
	<script type="text/javascript">
		var chart = c3
				.generate({
					data : {
						xs : {
							'<c:out value="${type_data}" escapeXml="false" />' : 'heads',
						},
						columns : [
								[ 'heads',
										<c:out value="${heads}" escapeXml="false" /> ],
								[ '<c:out value="${type_data}" escapeXml="false" />',
										<c:out value="${datas}" escapeXml="false" /> ], ]
					}
				});
	</script>
</body>
</html>
