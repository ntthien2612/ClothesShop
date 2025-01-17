package ClothesShop.Controller.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ClothesShop.Service.Admin.DonHangImpl;

@Controller
public class DonHangController {
	@Autowired
	DonHangImpl donhangImpl;
	public ModelAndView _mvShare = new ModelAndView();

	// trang quan ly don hang
	@RequestMapping(value = "/admin/quanlydonhang", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public ModelAndView DanhSachDonHang(HttpSession session, HttpServletRequest request) {
		if (session.getAttribute("AdminLoginInfo") != null) {
			_mvShare.setViewName("admin/admin_donhang");
			_mvShare.addObject("donhang", donhangImpl.DanhSachDonHang());
			return _mvShare;
		} else {
			_mvShare.setViewName("redirect: ../login/");
			return _mvShare;
		}
	}

	@RequestMapping(value = "/admin/xacnhan", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8", params = "don")
	public ModelAndView XacNhanDonHang(int don) {
		_mvShare.addObject(donhangImpl.XacNhanDonHang(don));
		_mvShare.addObject(donhangImpl.ThemNhatKy(don));
		_mvShare.setViewName("redirect:/admin/quanlydonhang");
		return _mvShare;
	}

	@RequestMapping(value = "/admin/chitietdonhang", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8", params = "don")
	public ModelAndView ChiTietDonHang(int don) {
		_mvShare.setViewName("admin/admin_chitietdonhang");
		_mvShare.addObject("chitietdonhang", donhangImpl.ChiTietDonHang(don));
		_mvShare.addObject("donhang", donhangImpl.DonHang(don));
		return _mvShare;
	}
}
