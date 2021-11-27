<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<section>
	<div class="video-list">
		<h3>Video</h3>
		<select name="" id="">
			<option value="">Hankioo</option>
			<option value="">Hankioo</option>
			<option value="">Hankioo</option>
			<option value="">Hankioo</option>
			<option value="">Hankioo</option>
			<option value="">Hankioo</option>
		</select>
	</div>
	<style>
.video-list {
	display: flex;
}

.video-list>h3 {
	width: 20%;
}

.video-list>select {
	width: 80%;
	border: 1px solid gainsboro;
	background: reseashell;
}

option {
	text-shadow: 0 1px 0 rgba(0, 0, 0, 0.4);
	transition: 0.3s ease-in-out;
}

.video-list>select select::selection {
	background: salmon;
}
</style>
</section>