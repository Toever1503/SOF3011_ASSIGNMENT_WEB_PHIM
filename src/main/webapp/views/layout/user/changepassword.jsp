<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="change-password login">
	<form action="" onsubmit='return formValidate(this)'>
		<h1 style="text-align: center;">Đổi Mật Khẩu</h1>
		<div>
			<label for="password">Mật Khẩu Hiện Tại:</label> <input
				type="password" name="password" id="password"
				onkeyup='handleInputChange(this)'>
			<div class="hasError">
				<span></span>
			</div>
		</div>
		<div>
			<label for="newPassword">Mật Khẩu Mới:</label> <input type="password"
				name="newPassword" id="newPassword" placeholder=""
				onkeyup='handleInputChange(this)'>
			<div class="hasError">
				<span></span>
			</div>
		</div>
		<div>
			<label for="newPasswordConfirm">Xác Nhận Mật Khẩu Mới:</label> <input
				type="password" name="newPasswordConfirm" id="newPasswordConfirm"
				placeholder="" onkeyup='handleInputChange(this)'>
			<div class="hasError">
				<span></span>
			</div>
		</div>

		<p
			style="text-align: center; background: antiquewhite; padding: 5px; display: block;">
		</p>

		<input type="submit" value="Lưu">
	</form>
	<script>
		function handleInputChange(e) {
			e.nextElementSibling.getElementsByTagName('span')[0].innerHTML = null;
		}

		function formValidate(e) {
			let children = e.children;
			let password = document.getElementById('password');
			let newPassword = document.getElementById('newPassword');
			let newPasswordConfirm = document
					.getElementById('newPasswordConfirm');

			let check = 0;
			if (password.value.length <= 0)
				password.nextElementSibling.getElementsByTagName('span')[0].innerHTML = 'Mật khẩu cũ không được bỏ trống!';
			else if (newPassword.value.length <= 0)
				newPassword.nextElementSibling.getElementsByTagName('span')[0].innerHTML = 'Mật khẩu mới không được bỏ trống!';
			else if (newPasswordConfirm.value.length <= 0)
				newPasswordConfirm.nextElementSibling
						.getElementsByTagName('span')[0].innerHTML = 'Mật khẩu mới không được bỏ trống!';
			else {
				if (newPassword.value !== newPasswordConfirm.value)
					newPasswordConfirm.nextElementSibling
							.getElementsByTagName('span')[0].innerHTML = ' Mật khẩu xác nhận không trùng với mật khẩu mới!';
				else {
					newPasswordConfirm.nextElementSibling
							.getElementsByTagName('span')[0].innerHTML = '';
					children[4].setAttribute('disabled', false);
					return true;
				}
			}

			children[4].setAttribute('disabled', true);
			return false;
		}
	</script>
</div>