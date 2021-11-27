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
			<span class="add-new-film"><a href="">Thêm Mới</a></span>
			<section>
				<div class="filter-action">
					<select name="" id="action">
						<option value="">Action</option>
						<option value="">Delete</option>
					</select> <select name="" id="status">
						<option value="">Status</option>
						<option value="">Trash</option>
					</select> <select name="" id="category">
						<option value="">Category</option>
						<option value="">Action</option>
						<option value="">Shounen</option>
					</select>

					<style>
.filter-action {
	margin: 10px;
}

.filter-action>select {
	width: 100px;
	text-align: center;
}
</style>
				</div>


			</section>
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