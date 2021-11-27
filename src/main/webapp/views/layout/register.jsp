<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="login">
	<form action="" onsubmit='return formValidate(this)'>
		<h1 style="text-align: center;">Đăng Ký</h1>
		<div>
			<label for="username">Tài khoản:</label> <input type="text"
				name="username" id="username" placeholder="Tên đăng nhâp"
				onkeyup='handleInputChange(this)'>
			<div class="hasError">
				<span></span>
			</div>
		</div>
		<div>
			<label for="name">Tên bạn là:</label> <input type="text" name="name"
				id="name" placeholder="................"
				onkeyup='handleInputChange(this)'>
			<div class="hasError">
				<span></span>
			</div>
		</div>
		<div>
			<label for="password">Mật Khẩu:</label> <input type="password"
				name="password" id="password" placeholder="................."
				onkeyup='handleInputChange(this)'>
			<div class="hasError">
				<span></span>
			</div>
		</div>

		<div>
			<label for="email">Email:</label> <input type="email" name="email"
				id="email" placeholder="................"
				onkeyup='handleInputChange(this)'>
			<div class="hasError">
				<span></span>
			</div>
		</div>

		<p
			style="text-align: center; background: antiquewhite; padding: 5px; display: block;">
		</p>

		<input type="submit" value="Đăng Ký">
	</form>

	<script>
		function handleInputChange(e) {
			e.nextElementSibling.getElementsByTagName('span')[0].innerHTML = null;
		}

		function formValidate(e) {
			let children = e.children;
			let username = document.getElementById('username');
			let password = document.getElementById('password');
			let fullname = document.getElementById('name');
			let email = document.getElementById('email');

			let check = 0;
			if (username.value.length <= 0)
				username.nextElementSibling.getElementsByTagName('span')[0].innerHTML = 'Tài khoản không được bỏ trống!';
			else if (fullname.value.length <= 0)
				fullname.nextElementSibling.getElementsByTagName('span')[0].innerHTML = 'Họ Tên không được bỏ trống!';
			else if (password.value.length <= 0)
				password.nextElementSibling.getElementsByTagName('span')[0].innerHTML = 'Mật khẩu không được bỏ trống!';
			else if (email.value.length <= 0)
				email.nextElementSibling.getElementsByTagName('span')[0].innerHTML = 'Email không được bỏ trống!';
			else {
				children[4].setAttribute('disabled', false);
				return true;
			}

			children[4].setAttribute('disabled', true);
			return false;
		}
	</script>
</div>