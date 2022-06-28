<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/layouts/user/taglib.jsp"%>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="topNav">
			<div class="container">
				<div class="alignR">
					<div class="pull-left socialNw">
						<a href="#"><span class="icon-twitter"></span></a> <a href="#"><span
							class="icon-facebook"></span></a> <a href="#"><span
							class="icon-youtube"></span></a> <a href="#"><span
							class="icon-tumblr"></span></a>
					</div>
					<a class="" href="./"> <span class="icon-home"></span>
						Trang chủ
					</a> 
					<c:if test="${ empty LoginInfo }">
					<a href="<c:url value="/dang-ky" />"><span class="icon-edit"></span>
						Đăng ký </a>
					</c:if>
					<c:if test="${ empty LoginInfo }">
					<a href="<c:url value="/dang-nhap" />"><span class="icon-edit"></span>
						Đăng nhập </a>
					</c:if>
					<c:if test="${ not empty LoginInfo }">
					<a href="./chi-tiet-khach-hang?id_kh=${LoginInfo.id_kh}"><span class="icon-user"></span> ${ LoginInfo.ten_kh }</a>
					<a href="<c:url value="/dang-xuat/" />"> <span
						class="icon-edit"></span> Đăng xuất
					</a>
					<script>
						function abc() {
							document.getElementById("item").submit();
						}
					</script>
					<span class="btn btn-mini" onclick="return abc()"><span
						class="icon-shopping-cart" onclick="return abc()"
						style="color: red"> ${count} sản phẩm</span> </span>
					<form action="giohang" method="get" id="item"
						modelAttribute="giohang">
						<input name="id_kh" value="${LoginInfo.id_kh}" hidden>
					</form>
				</c:if>

			</div>
		</div>
	</div>
</div>
<header id="header">
	<div class="row">
		<div class="span4">
			<h1
				style="color: #ee4d2d; text-shadow: yellow 2px -1px 3px, white -2px 1px 2px; position: relative; font-size: 50px; padding-top: 3%;">
				ClothesShop</h1>
		</div>
		<div class="span4">
			<div>
				<marquee
					style="padding: 10px;height: 50px; font-size: 30px;margin-top: 5%; color: Blue; text-shadow: yellow 2px -1px 3px, white -2px 1px 2px; position: relative;">ClothesShop
					mang sự tự tin, thoải mái cho phái mạnh</marquee>
			</div>
		</div>
		<div class="span4 alignR">
			<p>

				<br> <strong> Support (24/7) : 0383835251 </strong><br>

				<br> <strong>Nhóm 05 </strong><br> <br>
			</p>
			
		</div>
	</div>
</header>

<!--
Navigation Bar Section 
-->
<div class="navbar">
	<div class="navbar-inner">
		<div class="container">
			<a data-target=".nav-collapse" data-toggle="collapse"
				class="btn btn-navbar"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a>
			<div class="nav-collapse">
				<ul class="nav">
					<li class=""><a href="./">Trang chủ </a></li>
					<li class=""><a href="./sanpham">Sản phẩm</a></li>
					<c:if test="${ not empty LoginInfo }">
					<li class=""><a href="./donhang">Đơn hàng</a></li>
					</c:if>
				</ul>
				
				<form action="sanpham" method="GET" modelAttribute="sanpham" class="navbar-search pull-left" style="float: right">
					<input type="text" placeholder="Search" class="search-query span2" name="noidung" style="width: 300px"/>
					<button type="submit">Tìm</button>
				</form>
				
				
			</div>
		</div>
	</div>
</div>
