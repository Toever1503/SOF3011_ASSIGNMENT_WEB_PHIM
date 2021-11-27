<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="login">
	<form action="" onsubmit='return formValidate(this)'>
		<h1 style="text-align: center;">Đăng Nhập</h1>
		<div>
			<label for="username">Tài khoản:</label> <input type="text"
				name="username" id="username" placeholder="Tên đăng nhâp hoặc email"
				onkeyup='handleInputChange(this)'>
			<div class="hasError">
				<span></span>
			</div>
		</div>
		<div>
			<label for="password">Mật Khẩu:</label> <input type="password"
				name="password" id="password" onkeyup='handleInputChange(this)'>
			<div class="hasError">
				<span></span>
			</div>
		</div>
		<div>
			<input type="checkbox" value="true" name="remember" id="remember">
			<label for="remember">Nhớ tài khoản?</label>
		</div>

		<p
			style="text-align: center; background: antiquewhite; padding: 5px; display: block;">
		</p>

		<input type="submit" value="Đăng Nhập">
	</form>

	<script>
		function handleInputChange(e) {
			e.nextElementSibling.getElementsByTagName('span')[0].innerHTML = null;
		}

		function formValidate(e) {
			let children = e.children;
			let username = document.getElementById('username');
			let password = document.getElementById('password');

			let check = 0;
			if (username.value.length <= 0)
				username.nextElementSibling.getElementsByTagName('span')[0].innerHTML = 'Tài khoản không được bỏ trống!';
			else if (password.value.length <= 0)
				password.nextElementSibling.getElementsByTagName('span')[0].innerHTML = 'Mật khẩu không được bỏ trống!';
			else {
				children[4].setAttribute('disabled', false);
				return true;
			}

			children[4].setAttribute('disabled', true);
			return false;
		}
	</script>
</div>