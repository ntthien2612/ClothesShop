package ClothesShop.Controller.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import ClothesShop.Service.Admin.BaoCaoImpl;

@Controller
public class BaoCaoController {
	@Autowired
	BaoCaoImpl baocaoImpl;
	public ModelAndView _mvShare = new ModelAndView();
	//thong ke theo thang hien hanh
	@RequestMapping(value = "/admin/baocao")
	public ModelAndView BaoCao(HttpSession session, HttpServletRequest request) {
		if(session.getAttribute("AdminLoginInfo") != null) {
			_mvShare.addObject("thongkesp", baocaoImpl.ThongKeSanPham());
			_mvShare.addObject("count_sp", baocaoImpl.DemTongSP());
			_mvShare.addObject("thongkedh", baocaoImpl.ThongKeDonHang());
			_mvShare.addObject("count_dh", baocaoImpl.DemTongDH());
			_mvShare.addObject("tongtien", baocaoImpl.TongDH());
			_mvShare.setViewName("admin/admin_baocao");
			return _mvShare;
		} else {
			_mvShare.setViewName("redirect: ../login/");
			return _mvShare;
		}
	}
	//thong ke don hang theo thang chon
	@RequestMapping(value = "/admin/thongke", method = RequestMethod.GET, params = {"thang_dh","thang_sp"})
	public ModelAndView TKDH(int thang_dh, int thang_sp) {
		ModelAndView tk = new ModelAndView("admin/admin_baocao");
		tk.addObject("dh_thang",baocaoImpl.TKDH_thang(thang_dh));
		tk.addObject("count_dh", baocaoImpl.DemTongDH(thang_dh));
		tk.addObject("tongtien", baocaoImpl.TongDH(thang_dh));
		tk.addObject("sp_thang",baocaoImpl.TKSP_thang(thang_sp));
		tk.addObject("count_sp", baocaoImpl.DemTongSP(thang_sp));
		tk.addObject("thang_dh",thang_dh);
		tk.addObject("thang_sp",thang_sp);
		return tk;
	}
	
//	//thong ke san pham theo thang chon
//	@RequestMapping(value = "/admin/thongke", method = RequestMethod.GET, params = "thang_sp")
//	public ModelAndView TKSP(int thang_sp) {
//		ModelAndView tk = new ModelAndView("admin/admin_baocao");
//		tk.addObject("sp_thang",baocaoImpl.TKSP_thang(thang_sp));
//		tk.addObject("count_sp", baocaoImpl.DemTongSP(thang_sp));
//		return tk;
//	}
}
