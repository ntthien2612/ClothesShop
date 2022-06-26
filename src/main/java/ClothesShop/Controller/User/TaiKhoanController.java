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
import ClothesShop.Entity.Users;
import ClothesShop.Service.User.AccountServiceImpl;
import ClothesShop.Service.User.UserHomeImpl;

@Controller
public class TaiKhoanController {
	@Autowired
	UserHomeImpl HomeService;
	@Autowired
	GioHangDao giohangDao;
	// ĐĂNG KÝ TÀI KHOẢN
		@RequestMapping(value = "/dang-ky", method = RequestMethod.GET)
		public ModelAndView Register() {
			_mvShare.setViewName("user/account/register");
			_mvShare.addObject("danhmuc", HomeService.GetDataDanhMuc());
			_mvShare.addObject("user", new Users());
			return _mvShare;
		}

		@Autowired
		AccountServiceImpl accountService = new AccountServiceImpl();
		public ModelAndView _mvShare = new ModelAndView();

		@RequestMapping(value = "/dang-ky", method = RequestMethod.POST)
		public ModelAndView CreateAcc(@ModelAttribute("user") Users user) {
			int count = accountService.AddAccount(user);
			if (count > 0) {
				_mvShare.addObject("status", "Đăng ký tài khoản thành công !");

			} else {
				_mvShare.addObject("status", "Đăng ký tài khoản thất bại !");
			}
			_mvShare.setViewName("user/account/register");
			return _mvShare;
		}
		
		//ĐĂNG NHẬP TÀI KHOẢN 
				@RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
				public ModelAndView Login() {
					_mvShare.setViewName("user/account/login");
					_mvShare.addObject("danhmuc", HomeService.GetDataDanhMuc());
					_mvShare.addObject("user", new Users());
					return _mvShare;
				}
				
				@RequestMapping(value = "/dang-nhap", method = RequestMethod.POST )
				public ModelAndView Login(HttpSession session,@ModelAttribute("user") Users user) {
					 user = accountService.CheckAccount(user);
					if(user !=null ){
						_mvShare.setViewName("redirect:/");
						session.setAttribute("LoginInfo", user);
						//lưu id khách hàng vào session
						session.setAttribute("kh",user.getId_kh());
//						session.setAttribute("count", giohangDao.Count(user.getId_kh()));
					}else {
						_mvShare.addObject("statusLogin","Đăng nhập thất bại !");
					}		
					return _mvShare;	
				}
				//ĐĂNG XUẤT TÀI KHOẢN 
				@RequestMapping(value = "/dang-xuat", method = RequestMethod.GET )	
				public String Logout(HttpSession session, HttpServletRequest request) {
				session.removeAttribute("LoginInfo");
				session.removeAttribute("count");
				return "redirect:/";	
				}
				
}
