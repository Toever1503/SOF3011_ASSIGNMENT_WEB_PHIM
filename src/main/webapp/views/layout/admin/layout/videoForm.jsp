<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="route-action">
	<span class="route-item"><a href="">Quay lại</a></span>
</div>
<section class="video login">
	<form action="" onsubmit='return formValidate(this)'>
		<div>
			<label for="title">Tiêu Đề</label> <input type="text" name="title"
				id="title" placeholder="Tiêu đề video"
				onkeyup='handleInputChange(this)'>
			<div class="hasError">
				<span></span>
			</div>
		</div>
		<div>
			<label for="description">Mô Tả</label>
			<textarea rows="5" name="description" id="description"
				placeholder="................" onkeyup='handleInputChange(this)'></textarea>
			<div class="hasError">
				<span></span>
			</div>
		</div>

		<div>
			<label for="category">Thể Loại</label>
			<div class="category container-box">
				<span class="container-item item_checked">Action</span> <span
					class="container-item">Drama</span> <span class="container-item">Romance</span>
				<span class="container-item">School</span> <span
					class="container-item">Sci-Fic</span> <span class="container-item">Adventure</span>
			</div>
			<div class="hasError">
				<span></span>
			</div>
			<script>
				addEvenContainerBoxClick(document
						.getElementsByClassName('container-box')[0])
				function addEvenContainerBoxClick(box) {
					let cats = box.children;
					for (let i = 0; i < cats.length; i++) {
						cats[i]
								.addEventListener(
										'click',
										function() {
											this.className = this.className
													.indexOf('item_checked') == -1 ? (this.className + ' item_checked')
													.trim()
													: this.className.replace(
															'item_checked', '')
															.trim()
										})
					}
				}
			</script>
		</div>

		<div>
			<label for="director">Đạo Diễn</label> <input type="text"
				name="director" id="director">
			<div class="director-container container-box">
				<span class="container-item">Hikari</span> <span
					class="container-item">Shokaha Inori</span> <span
					class="container-item">Shinkai Makoto</span>
			</div>
			<div class="suggest-container">
				<div class="suggest-item">Amanda</div>
				<div class="suggest-item">Amanda</div>
				<div class="suggest-item">Amanda</div>
				<div class="suggest-item">Amanda</div>
				<script>
					function addSuggestClick() {
						let suggests = document
								.getElementsByClassName('suggest-container')[0].children;
						for (let i = 0; i < suggests.length; i++) {
							suggests[i].addEventListener('click', function() {
								let item = document.createElement('span');
								item.className = 'container-item';
								item.innerHTML = this.innerHTML;
								this.parentElement.previousElementSibling
										.appendChild(item);

								this.parentElement.parentElement
										.removeChild(this.parentElement);
							})
						}
					}
					document
							.getElementById('director')
							.addEventListener(
									'keydown',
									function() {
										let suggestContainer = document
												.getElementsByClassName('suggest-container');

										if (suggestContainer.length == 0) {
											suggestContainer = document
													.createElement('div');
											let suggestItem = suggestContainer
													.cloneNode(true);
											suggestContainer.className = 'suggest-container';
											suggestItem.className = 'suggest-item';

										} else {
											let suggestItem = document
													.createElement('div');
											suggestContainer.className = 'suggest-container';
										}

									})
					addSuggestClick();
				</script>
			</div>
			<style>
.suggest-container {
	position: absolute;
	top: 60px;
	left: 0;
	background: aliceblue;
	border: 1px solid gainsboro;
	z-index: 10;
}

.suggest-container .suggest-item {
	padding: 5px;
	min-width: 250px;
	max-width: 500px;
	cursor: pointer;
}

.suggest-container .suggest-item:hover {
	background-color: bisque;
}
</style>
			<div class="hasError">
				<span></span>
			</div>
		</div>

		<div>
			<label for="view">Lượt xem:</label> <input type="view" name="view"
				id="view" value="0" onkeyup='handleInputChange(this)'>
			<div class="hasError">
				<span></span>
			</div>
		</div>

		<div>
			<label for="trailer">Trailer</label> <input type="text"
				name="trailer" id="trailer" placeholder="Link youtube trailer"
				onkeyup='handleInputChange(this)'>
			<div class="hasError">
				<span></span>
			</div>
		</div>

		<p
			style="text-align: center; background: antiquewhite; padding: 5px; display: block;">
		</p>

		<input type="submit" value="Thêm">
	</form>
</section>


<style>
.item_checked {
	background: bisque !important;
}

.section {
	border: 1px solid gray;
	background-color: white;
	margin: 10px 0;
}

.route-action {
	
}

.route-action .route-item {
	border: 1px solid gray;
	padding: 5px;
	border-radius: 5px;
	background-color: white;
}

.login form {
	width: unset;
}

.login form>div>label {
	display: block;
	font-size: 20px;
}

.login form input[type='text'], input[type='password'], input[type='email'],
	textarea {
	width: 95%;
}

.login .hasError {
	margin-left: 0;
}

.container-box {
	position: relative;
	margin-top: 10px;
}

.container-box .container-item {
	display: inline-block;
	background: gainsboro;
	padding: 5px;
	border-radius: 4px;
	margin: 2px 0;
	cursor: pointer;
}
</style>

<style>
body {
	background-color: #f1ededba;
}

.body-container {
	width: 1000px;
	margin: 0 auto;
}

.login {
	margin-top: 10px;
}

.login form {
	padding: 15px;
	background-color: white;
	width: 500px;
	margin: 0 auto;
	border-radius: 5px;
}

.login form>div {
	position: relative;
	margin: 15px 0;
}

.login form>div>label {
	width: 30%;
	display: inline-block;
	font-size: 15px;
}

.login form input[type='checkbox'] {
	margin-left: 30%;
	cursor: pointer;
}

.login form>div>span {
	margin-left: 30%;
	width: 50%;
}

.login form input[type='text'], input[type='password'], input[type='email'],
	textarea {
	padding: 8px;
	width: 63%;
	border: 1px solid gainsboro;
}

.login form input[type='submit'] {
	display: block;
	margin: 5px auto;
	padding: 6px;
	transition: 0.3s ease-in-out;
	border: 1px solid gainsboro;
	cursor: pointer;
	border-radius: 3px;
}

.login form input[type='submit']:hover {
	background-color: seashell;
}

.login .hasError {
	margin-left: 30%;
	padding: 5px;
	color: cornflowerblue;
	font-size: 14px;
}
</style>
