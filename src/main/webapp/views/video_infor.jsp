<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<jsp:include page="/views/layout/header.jsp"></jsp:include>
	</header>

	<div class="body-content">
		<jsp:include page="/views/layout/breadcrumb.jsp"></jsp:include>

		<div class="video-detail">
			<div class="video-infor">
				<div class="video-infor-left"
					style="background-image: url('https://animenews.life//uploads/chars/chara06shin_a.png')">
				</div>
				<div class="video-infor-right">
					<div class="title" style="text-align: center">
						<h1>5 Centimeters per Second</h1>
					</div>
					<div class="alternate-title">
						Tên Khác: <span>小林さんちのメイドラゴンＳ</span>
					</div>
					<div class="country">
						Quốc Gia: <span>Jappan</span>
					</div>
					<div class="category">
						Thể loại: <span>Comedy, Fantasy, Slice of Life</span>
					</div>
					<div class="season">
						Season: <span>Summer 2021</span>
					</div>
					<div class="duration">
						Thời Lượng: <span>20</span> phút/1 tập
					</div>
					<div class="episodes">
						Số tập: <span>20</span> tập
					</div>
					<div class="studio">
						Studio: <span>Lantis, Heart Company</span>
					</div>
					<div class="status">
						Trạng thái: <span>Đang chiếu</span>
					</div>
					<div class="rating">
						Đánh giá: <span>5.6 (20 lượt đánh giá)</span>
					</div>
				</div>
			</div>
			<div class="video-description">
				<h3>Nội Dung Phim</h3>
				<p>Mùa thứ hai của Kobayashi-san Chi no Maidragon.</p>
				<p>Một bước ngoặt kỳ lạ của các sự kiện khiến Rồng, Tohru, làm
					giúp việc của cô Kobayashi. Thỉnh thoảng cô ấy (đó là một lời nói
					dối, cô ấy thường) gây rắc rối cho cô Kobayashi yêu quý của mình
					trong khi hòa nhập vào xã hội loài người và lộng lẫy (đó là một lời
					nói dối, chỉ tầm thường) thực hiện nhiệm vụ giúp việc của cô ấy.
					Những con rồng đồng loại của cô, Kanna, Lucoa, Fafnir và Elma đều
					tìm thấy những nơi riêng của họ để phù hợp và tận hưởng sự tương
					tác giữa các loài với con người. Tuy nhiên, trong khi tất cả họ
					đang tận hưởng sự thoải mái và đôi khi hỗn loạn bên trái, mối đe
					dọa của một con rồng mới lao xuống cô Kobayashi.</p>
			</div>
			<div class="video-trailer">
				<h3>Trailer</h3>
				<iframe width="560" height="315"
					src="https://www.youtube.com/embed/35KhnfqWoA4"
					title="YouTube video player" frameborder="0"
					allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
					allowfullscreen></iframe>
			</div>
		</div>


		<div class="video-others">
			<h3>Phim cùng thể loại</h3>
			<jsp:include page="/views/layout/videoList.jsp"></jsp:include>
		</div>
	</div>

	<footer>
		<jsp:include page="/views/layout/footer.jsp"></jsp:include>
	</footer>
</body>
</html>