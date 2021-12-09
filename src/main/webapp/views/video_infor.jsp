<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/views/layout/head.jsp"></jsp:include>
<style type="text/css">
<%@include file="/resources/css/video_infor.css" %>
</style>
</head>
<body>
	<jsp:include page="/views/layout/modal_input.jsp"></jsp:include>
	<header>
		<jsp:include page="/views/layout/header.jsp"></jsp:include>
	</header>

	<div class="body-container">
		<jsp:include page="/views/layout/breadcrumb.jsp"></jsp:include>

			<div class="video-detail">
				<div class="video-infor">
					<div class="video-infor-left"
						style="background-image: url('${video.imageBanner}')">
					</div>
					<div class="video-infor-right">
						<div class="title" style="text-align: center">
							<h1>${video.title }</h1>
						</div>
						<div class="alternate-title">
							Tên Khác: <span>${video.alternateTtitle }</span>
						</div>
						<div class="country">
							Quốc Gia: <span>${video.country }</span>
						</div>
						<div class="category">
<%-- 							Thể loại: <span>${video.categores.toString() }</span> --%>
						</div>
						<div class="season">
							Season: <span>${video.season} ${video.year }</span>
						</div>
						<div class="duration">
							Thời Lượng: <span>${video.duration }</span> phút/1 tập
						</div>
						<div class="episodes">
							Số tập: <span>${video.volume }</span> tập
						</div>
						<div class="studio">
							Studio: <span>Lantis, Heart Company</span>
						</div>
						<div class="status">
							Trạng thái: <span>${video.status }</span>
						</div>
						<div class="rating">
							Đánh giá: <span>5.6 (20 lượt đánh giá)</span>
						</div>
					</div>
				</div>
				<div class="video-description">
					<h3>Nội Dung Phim</h3>
					<p>${video.description==null?'Hiện đang cập nhật!' : video.description }</p>
				</div>
				<div class="video-trailer">
					<h3>Trailer</h3>
					<iframe width="560" height="315"
						src="${video.trailer }"
						title="YouTube video player" frameborder="0"
						allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
						allowfullscreen></iframe>
				</div>
			</div>
		<c:if test="${videoList != null }">	
			<div class="video-others">
				<h3>Phim cùng thể loại</h3>
				<jsp:include page="/views/layout/videoList.jsp"></jsp:include>
			</div>
		</c:if>
		
	</div>

	<footer>
		<jsp:include page="/views/layout/footer.jsp"></jsp:include>
	</footer>
</body>
</html>