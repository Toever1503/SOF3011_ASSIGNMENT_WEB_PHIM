<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Users</title>
</head>
<body>
	<jsp:include page="layout/header.jsp"></jsp:include>

	<div class="body-content">
		<jsp:include page="layout/aside.jsp"></jsp:include>
		<div class="main-content">
			<!-- for delete video -->
			<c:if test="${actionDelete =='success' }">
				<script type="text/javascript">
						document.addEventListener('DOMContentLoaded', (event) => {
							setTimeout(() => {
								alert("Delete successfully!");
							}, 500);
						});
					</script>
			</c:if>
			<!--  end delete video -->
			
			<span class="add-new-film"><a href="/SOF3011_ASSIGNMENT/admin/user/new">Thêm</a></span>
			<style>
.add-new-film {
	border: 1px solid gray;
	padding: 5px;
	border-radius: 5px;
	background-color: white;
}
</style>
			<div class="section">
				<table>
					<thead>
						<tr>
							<th><input type="checkbox"></th>
							<th>Username</th>
							<th>Fullname</th>
							<th>Email</th>
							<th>Role</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${userList }">
							<tr>
								<td style="padding: 5px;"><input type="checkbox"></td>
								<td>
									<div class="column-title">${user.username }</div>
									<div class="column-action">
										<span><a href="/SOF3011_ASSIGNMENT/admin/user/edit?id=${user.id }">Sửa</a></span> 
										<span><a class="confirmation" href="/SOF3011_ASSIGNMENT/admin/user/delete?id=${user.id }">Xóa</a></span>
									</div>
								</td>
								<td>${user.fullname }</td>
								<td>${user.email }</td>
								<td>${user.admin == 1 ?'Admin': 'User' }</td>
							</tr>
						</c:forEach>


						<tr class="page-action">
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<!-- divide page -->
							<jsp:include page="layout/pageTable.jsp"></jsp:include>
						</tr>
					</tbody>
				</table>
			</div>

			<style>
.section {
	border: 1px solid gray;
	background-color: white;
	margin: 10px 0;
}

.add-new-film {
	border: 1px solid gray;
	padding: 5px;
	border-radius: 5px;
	background-color: white;
}

.section table {
	width: 100%;
	border-collapse: collapse;
	border: 1px solid black;
	text-align: center;
}

.section table thead {
	width: 100%;
	margin: 10px 0;
	border: 1px solid gray;
}

.section table tbody {
	width: 100%;
	margin: 10px 0;
	border: 1px solid gray;
}

.section table tr {
	line-height: 35px;
}

.section table tbody tr:nth-child(odd) {
	background: #fff0f0;
}

.section table td:nth-child(1) input {
	display: block;
	margin: 0 auto;
}

.section table td:nth-child(2), .section table td:nth-child(3), .section table td:nth-child(4),
	.section table td:nth-child(5) {
	width: 300px;
}

.column-action {
	font-size: 12px;
	height: 30px;
}

.column-action span {
	display: none;
	transition: 0.4s ease-in-out;
}

.section table td:nth-child(2):hover .column-action>span {
	display: inline;
}

.column-action span:nth-child(2) {
	border-left: 1px solid gray;
	border-right: 1px solid gray;
	padding: 2px;
}

.column-action span:hover a {
	color: teal;
}

.section table tbody tr.page-action {
	line-height: unset;
}

.section table tbody tr.page-action span {
	display: inline-block;
	padding: 5px;
	border: 1px solid gainsboro;
	font-weight: bold;
	transition: 0.3s ease-in-out;
	cursor: pointer;
}

.section table tbody tr.page-action span:hover {
	background-color: azure;
}
</style>
		</div>
	</div>

	<jsp:include page="layout/footer.jsp"></jsp:include>
</body>
</html>