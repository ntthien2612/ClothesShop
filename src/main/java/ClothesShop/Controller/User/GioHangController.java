package ClothesShop.Controller.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ClothesShop.Dao.GioHangDao;
import ClothesShop.Dao.UsersDao;
import ClothesShop.Entity.DanhMuc;
import ClothesShop.Entity.GioHang;
import ClothesShop.Service.User.AccountServiceImpl;
import ClothesShop.Service.User.GioHangImpl;

@Controller
public class GioHangController {
	@Autowired
	GioHangImpl giohangImpl;
	@Autowired
	GioHangDao giohangDao;
	public ModelAndView _mvShare = new ModelAndView();

	@Autowired
	AccountServiceImpl accountService = new AccountServiceImpl();

	// chuc nang them gio hang
	@RequestMapping(value = "themgiohang", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public ModelAndView CreateGioHang(HttpSession session, @ModelAttribute("giohang") GioHang giohang) {
		int count = giohangImpl.KiemTraGioHang(giohang);
		if (count > 0) {
			giohangImpl.UpdateGioHang(giohang);
		} else {
			giohangImpl.ThemGioHang(giohang);
			session.setAttribute("kh", giohang.getId_kh());
			session.setAttribute("count", giohangDao.Count(giohang.getId_kh()));
		}
		_mvShare.setViewName("redirect:/giohang?id_kh=" + giohang.getId_kh());
		return _mvShare;
	}

	// trang gio hang
	@RequestMapping(value = "/giohang", method = RequestMethod.GET, params = "id_kh")
	public ModelAndView GioHang(int id_kh, HttpSession session, HttpServletRequest request) {
			ModelAndView giohang = new ModelAndView("user/giohang");
			if (session.getAttribute("LoginInfo") != null) {
			giohang.addObject("giohang", giohangImpl.HienGioHang(id_kh));
			return giohang;
		}
			return new ModelAndView("user/dangnhap");
	}
	// cong so luong
	@RequestMapping(value = "/cong", method = RequestMethod.GET, params = "idsp")
	public ModelAndView CongGioHang(int idsp, int idkh) {
		_mvShare.addObject("giohang", giohangImpl.CongGioHang(idsp, idkh));
		_mvShare.setViewName("redirect:/giohang?id_kh=" + idkh);
		return _mvShare;
	}

	// tru so luong
	@RequestMapping(value = "/tru", method = RequestMethod.GET, params = "idsp")
	public ModelAndView TruGioHang(int idsp, int idkh) {
		_mvShare.addObject("giohang", giohangImpl.TruGioHang(idsp, idkh));
		_mvShare.setViewName("redirect:/giohang?id_kh=" + idkh);
		return _mvShare;
	}

	// xoa san pham
	@RequestMapping(value = "/xoagiohang", method = RequestMethod.GET, params = "id")
	public ModelAndView XoaGioHang(HttpSession session, int id, int idkh) {
		_mvShare.addObject(giohangImpl.XoaGioHang(id, idkh));
		session.setAttribute("kh", idkh);
		session.setAttribute("count", giohangDao.Count(idkh));
		_mvShare.setViewName("redirect:/giohang?id_kh=" + idkh);
		return _mvShare;
	}
}
