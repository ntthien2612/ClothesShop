<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/views/layouts/user/taglib.jsp"%>
<div class="container-fluid text-center" style="border: 1px solid black">
	<div class="row content">
		<div class="col-sm-2 sidenav" style="text-align: left">
			<p>
				<a class="nav-link active" href="./"> <i
					class="bi bi-house-fill"></i> Trang Chủ
				</a>
			</p>
			<p>
				<a class="nav-link" href="quanlydanhmucSP"> <i
					class="bi bi-folder"></i> Danh Mục Sản Phẩm
				</a>
			</p>
			<p>
				<a class="nav-link" href="quanlysanpham"> <i
					class="bi bi-file-earmark-text"></i> Sản Phẩm
				</a>
			</p>
			<p>
				<a class="nav-link" href="quanlydonhang"> <i
					class="bi bi-minecart"></i> Quản Lý Đơn Hàng
				</a>
			</p>
			<p>
				<a class="nav-link" href="quanlynguoidung"> <i
					class="bi bi-people-fill"></i> Quản Lý Người Dùng
				</a>
			</p>
			<p>
				<a class="nav-link" href="quanlynhanvien"> <i
					class="bi bi-question-circle-fill"></i> Quản Lý Nhân Viên
				</a>
			</p>
			<p>
				<a class="nav-link" href="baocao"> <i
					class="bi bi-bar-chart-line"></i> Báo Cáo
				</a>
			</p>
		</div>
		<div style="text-align: left;">
			<h1>
				<b>Chi Tiết Đơn Hàng</b>
			</h1>
			<p><b>Thông tin khách hàng:</b></p>
			<c:forEach var="donhang" items="${ donhang }">
			<p><b>Ngày mua: </b>${donhang.thoigian_mua }</p>	
			</c:forEach>
			 <table style="width: 60%;">
			 <tr>
			 <th style="width: 15%;">Họ & Tên</th>
			 <th style="text-align: center; width: 15%;">Email</th>
			 <th style="text-align: center;width: 15%;">Địa Chỉ</th>
			 <th style="text-align: center;width: 15%;">Phone</th>
			 </tr>
			 	<c:forEach var="donhang" items="${ donhang }">
						<tr style="text-align: center;">
							<td style="text-align: left">${donhang.ten_kh }</td>
							<td>${donhang.email_kh }</td>
							<td>${donhang.diachi }</td>
							<td>${donhang.sdt }</td>
						</tr>
					
			</c:forEach>
			</table><hr>
<p><b>Thông tin mua hàng:</b></p>
<div style="height: 200px;overflow-y: scroll;scroll-behavior: smooth;">
	<table style="width: 100%;" border="1">
              <thead>
                <tr>
                  <th style="width: 70px">Hình ảnh</th>
                  <th>Tên Sản Phẩm</th>
                  <th style="text-align: center">Size</th>
                  <th style="text-align: center">Giá bán</th>
                  <th style="text-align: center">Số lượng </th>
                  <th style="text-align: center">Tổng tiền</th>
				</tr>
              </thead>
              <tbody>
				<c:forEach var="chitietdonhang" items="${ chitietdonhang }">
					<tr>
					<td><img style="width: 70px; height: 70px;"
						src="<c:url value= "/image/${chitietdonhang[4] }"/>"></td>
	                  <td>${chitietdonhang[8] }</td>
	                  <td style="text-align: center">${chitietdonhang[6] }</td>
	                  <td style="text-align: center">${chitietdonhang[7] }</td>
	                  <td style="text-align: center">${chitietdonhang[5] }</td>
	                  <td style="text-align: center">${chitietdonhang[7] * chitietdonhang[5] } ₫</td>
	                </tr>
				</c:forEach>
				</tbody>
            </table><br/>
            </div>
		</div>
	</div>
</div>