	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Videos</title>

<style type="text/css">
<%@include file='css/videos.css' %>
</style>
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

			<span class="add-new-film"><a
				href="/SOF3011_ASSIGNMENT/admin/video/new">Thêm Mới</a></span>
			<section>
				<div class="filter-action">
					<select id="action">
						<option value="">Action</option>
						<option value="">Delete</option>
					</select> 
					
					<select id="status">
						<option value="">Status</option>
						<option value="">Trash</option>
					</select> 
					
					<select id="category">
						<c:forEach var="cat" items="${catList }">
							<option value="${cat.id }">${cat.name }</option>
						</c:forEach>
						
						<script type="text/javascript">
						document.getElementById('category').addEventListener('change', (e)=>{
						    console.log(e.target.value)
						})
						</script>
					</select>
				</div>
				${requestScope['javax.servlet.forward.query_string'].replaceAll('/\bcatagory\b=[\w\s]+/', '') }
			</section>
			<!-- end filter -->
			<div class="section">
				<table>
					<thead>
						<tr>
							<th><input type="checkbox"></th>
							<th>Title</th>
							<th>Category</th>
							<th>Tags</th>
							<th>Author</th>
							<th>Date Update</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="video" items="${videoList }">
							<tr>
								<td style="padding: 5px;"><input type="checkbox"></td>
								<td style="max-width: 300px">
									<div class="column-title" style="word-break: break-all;">
										${video.title }</div>

									<div class="column-action">
										<span><a
											href="${pageContext.request.contextPath }/admin/video/edit?id=${video.id }">Sửa</a>
										</span> <span><a
											href="${pageContext.request.contextPath }/admin/video/delete?id=${video.id }">Xóa</a></span>
										<span><a href="${pageContext.request.contextPath }/film/${video.title}/${video.id}">Xem</a></span>
									</div>
								</td>
								<td>
									<c:forEach var="v_cat" items="${video.categores }">
										${v_cat.name }
									</c:forEach>
								</td>
								<td>
									<c:forEach var="v_tag" items="${video.tags }">
										${v_tag.name }, 
									</c:forEach>
								</td>
								<td>Shiki</td>
								<td><span>${video.dateCreate !=null ?video.dateCreate.toGMTString(): 'asa' }</span></td>
							</tr>
						</c:forEach>
						
						<tr class="page-action">
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<!-- divide page with 2 column-->
							<jsp:include page="layout/pageTable.jsp"></jsp:include>
						</tr>

					</tbody>
				</table>
			</div>
		</div>
		
	</div>
	<jsp:include page="layout/footer.jsp"></jsp:include>
</body>
</html>
