<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="layout/header.jsp"></jsp:include>
	<div class="body-content">
		<jsp:include page="layout/aside.jsp"></jsp:include>
		<div class="main-content">
			<section>
				<h2>Statistics</h2>
				<div class="menu-container">
					<div class="menu-item" style="background: thistle !important">
						<a href="">Favourites Video</a>
					</div>
					<div class="menu-item">
						<a href="">Favourites Users</a>
					</div>
					<div class="menu-item">
						<a href="">Shared Friends</a>
					</div>
				</div>
				<style>
section {
	background-color: white;
	padding: 5px;
	margin: 10px 0;
}

.menu-container .menu-item {
	display: inline-block;
	margin: 10px 3px;
	background-color: papayawhip;
	padding: 5px;
	border-radius: 3px;
	transition: 0.3s ease-in-out;
}

.menu-container .menu-item:hover {
	background: thistle;
}

.menu-container .menu-item a {
	font-size: 18px;
}
</style>
			</section>

			<jsp:include page="layout/sectionStats.jsp"></jsp:include>
			<jsp:include page="layout/tableList.jsp"></jsp:include>
		</div>
	</div>

	<jsp:include page="layout/footer.jsp"></jsp:include>
</body>
</html>
<style>
.body-content {
	width: 100%;
	min-height: 800px;
	display: flex;
}

.body-content aside {
	width: 15%;
	background-color: white;
	border-right: 1px solid black;
}

.body-content .main-content {
	width: 85%;
	background-color: skyblue;
	padding: 10px;
}

aside .action-container {
	
}

aside .action-item {
	padding: 10px;
	border-top: 1px solid black;
	font-weight: bold;
}

aside .action-item:hover {
	background-color: powderblue;
}
</style>