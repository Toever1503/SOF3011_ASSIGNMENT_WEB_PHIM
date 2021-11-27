<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="header-content">
	<div class="main-menu">
		<div class="menu-item">
			<a href="">ONLINE ENTERTAINMENT</a>
		</div>
		<div class="menu-item">
			<a href="">My Favourites</a>
		</div>
		<div class="menu-item">
			<a
				onclick='(()=>{let check = this.parentElement.lastElementChild.getAttribute("data-active")==="true";
                                        this.parentElement.lastElementChild.setAttribute("data-active", !check)
                                        this.parentElement.lastElementChild.style.display = !check ?"block": "none"
                                    })()'>My
				Account</a>
			<div class="sub-menu-item" data-active="false">
				<div class="sub-item-menu-item">
					<a href="">Login</a>
				</div>
				<div class="sub-item-menu-item">
					<a href="">Forgot Password</a>
				</div>
				<div class="sub-item-menu-item">
					<a href="">Registration</a>
				</div>
				<div class="sub-item-menu-item">
					<a href="">Logoff</a>
				</div>
				<div class="sub-item-menu-item">
					<a href="">Change Password</a>
				</div>
				<div class="sub-item-menu-item">
					<a href="">Edit Profile</a>
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

