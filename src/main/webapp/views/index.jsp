<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/views/layout/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/views/layout/modal_input.jsp"></jsp:include>
	<header>
		<jsp:include page="/views/layout/header.jsp"></jsp:include>
	</header>

	<div class="body-container">
		<jsp:include page="/views/layout/videoList.jsp"></jsp:include>
	</div>

	<footer>
		<jsp:include page="/views/layout/footer.jsp"></jsp:include>
	</footer>
</body>
</html>