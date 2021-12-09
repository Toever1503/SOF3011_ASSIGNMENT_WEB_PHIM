<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<style>
.body-container {
	width: 1000px;
	margin: auto;
	margin: 10px auto;
	min-height: 700px;
}

footer {
	background: wheat;
}

.footer-content {
	width: 1000px;
	margin: auto;
	padding: 10px 0;
}
</style>
<div class="header-content">
	<div class="main-menu">
		<div class="menu-item">
			<a href="${pageContext.request.contextPath}">ONLINE ENTERTAINMENT</a>
		</div>
		<div class="menu-item">
			<a href="${pageContext.request.contextPath}/myFavourites">My
				Favourites</a>
		</div>
		<div class="menu-item">
			<script type="text/javascript">
				function myAccountCick(e){
					console.log(e)
					let check = e.parentElement.lastElementChild.getAttribute('data-active')==='true';
		            e.parentElement.lastElementChild.setAttribute('data-active', !check)
		            e.parentElement.lastElementChild.style.display = !check ?'block': 'none';
				}
				
				document.addEventListener("DOMContentLoaded", function(){
					document.getElementById('user-action-logout').addEventListener('click', function(){
					console.log("1231")
					fetch('http://localhost:8080/SOF3011_ASSIGNMENT/signout').then(res=>res.text()).then(data=>{
							if(data=='success'){
								alert('logout success');
							}
							else
								alert('You has logged out')
					}).catch(error=>console.log(error));
				})
				});
				
			</script>
			<a onclick="myAccountCick(this)">My Account</a>
			<div class="sub-menu-item" data-active="false">
				<div class="sub-item-menu-item">
					<a href="${pageContext.request.contextPath}/login">Login</a>
				</div>
				<div class="sub-item-menu-item">
					<a href="">Forgot Password</a>
				</div>
				<div class="sub-item-menu-item">
					<a href="${pageContext.request.contextPath}/register">Registration</a>
				</div>
				<div class="sub-item-menu-item">
					<a id="user-action-logout">Logoff</a>
				</div>
				<div class="sub-item-menu-item">
					<a href="${pageContext.request.contextPath}/user/changepassword">Change
						Password</a>
				</div>
				<div class="sub-item-menu-item">
					<a href="${pageContext.request.contextPath}/user/edit_profile">Edit
						Profile</a>
				</div>
			</div>
		</div>
	</div>
</div>

<style>
* {
	margin: 0;
	padding: 0;
	font-family: cursive;
}

a {
	text-decoration: none;
	color: black;
	cursor: pointer;
}

img {
	width: 100%;
	height: 100%;
}

header {
	width: 100%;
	height: 70px;
	background: salmon;
}

header .header-content {
	width: 1000px;
	margin: auto;
}

header .header-content .main-menu {
	display: flex;
}

header .header-content .main-menu>.menu-item {
	margin: 10px 30px;
	line-height: 50px;
	font-size: 20px;
	position: relative;
}

header .header-content .main-menu .menu-item>a {
	color: white;
	font-weight: bold;
}

header .header-content .main-menu .menu-item a:hover {
	color: cyan;
}

header .header-content .main-menu .menu-item .sub-menu-item {
	position: absolute;
	width: max-content;
	line-height: normal;
	background: seashell;
	display: none;
	animation: sub-menu-item-show 0.4s forwards;
	animation-timing-function: ease;
	z-index: 1;
}

@
keyframes sub-menu-item-show {from { display:block;
	opacity: 0.3;
	top: 150px;
}

to {
	opacity: 1;
	top: 50px;
}

}
header .header-content .main-menu .menu-item .sub-item-menu-item {
	border: 1px solid gainsboro;
	padding: 5px;
	border-radius: 3px;
	transition-timing-function: linear;
}

header .header-content .main-menu .menu-item .sub-item-menu-item:hover>a
	{
	color: burlywood;
}
</style>
<!-- end header -->

