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
import ClothesShop.Entity.Users;
import ClothesShop.Service.User.AccountServiceImpl;
import ClothesShop.Service.User.UserHomeImpl;

@Controller
public class TaiKhoanController {
	@Autowired
	UserHomeImpl HomeService;
	@Autowired
	AccountServiceImpl acc;
	@Autowired
	GioHangDao giohangDao;
	@Autowired
	UsersDao usersDao;
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
		public ModelAndView CreateAcc(HttpSession session, @ModelAttribute("user") Users user) {
			int email = usersDao.Count(user.getEmail_kh());
			String h = user.getSdt();
			int check=usersDao.checkLogin(user);
			if(check==0){
				session.setAttribute("notification","Vui lòng nhập đầy đủ các thông tin bên dưới!");
				return _mvShare;
			}else if(h.length()>10){
				session.setAttribute("notification","Số điện thoại bạn nhập không được vượt quá 10 chữ số!");
				return _mvShare;
			}else if(email!=0){
				session.setAttribute("notification", "Đã tồn tại địa chỉ email này!");
				return _mvShare;
			}else {
				session.setAttribute("notification","Đăng ký tài khoản thành công!");
				accountService.AddAccount(user);
				return _mvShare;
			}
		}
		
		//ĐĂNG NHẬP TÀI KHOẢN 
		@RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
		public ModelAndView Login(HttpSession session) {
			_mvShare.setViewName("user/account/login");
			_mvShare.addObject("danhmuc", HomeService.GetDataDanhMuc());
			_mvShare.addObject("user", new Users());
			
			return _mvShare;
		}
		
		@RequestMapping(value = "/dang-nhap", method = RequestMethod.POST )
		public ModelAndView Login(HttpSession session,@ModelAttribute("user") Users user) {
			user = accountService.CheckAccount(user);
			if(user!=null){
				_mvShare.setViewName("redirect:/");
				session.setAttribute("LoginInfo", user);
				//lưu id khách hàng vào session
				session.setAttribute("kh",user.getId_kh());
				session.setAttribute("count", giohangDao.Count(user.getId_kh()));
			}else {
				session.setAttribute("notification","Email hoặc mật khẩu bạn nhập chưa đúng. Vui lòng thử lại!");
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
		
		//Xem chi tiết khách hàng (users)
		@RequestMapping(value="/chi-tiet-khach-hang", method=RequestMethod.GET, params="id_kh")
		public ModelAndView ChiTiet(int id_kh) {
			ModelAndView chitiet = new ModelAndView("user/account/chitietkhachhang");
			chitiet.addObject("khachhang", HomeService.GetDataChiTietKhachHang(id_kh));
			return chitiet;
		}
		
		//trả về trang chỉnh sửa chi tiết
		@RequestMapping(value="/chinh-sua-chi-tiet", method=RequestMethod.GET, params="id_kh")
		public ModelAndView ChinhSua(int id_kh) {
			ModelAndView chitiet = new ModelAndView("user/account/chinhsuachitiet");
			chitiet.addObject("khachhang", HomeService.GetDataChiTietKhachHang(id_kh));
			return chitiet;
		}

		//lưu chỉnh sửa chi tiết khách hàng
		@RequestMapping(value = "/luuchinhsuachitietkhachhang", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=UTF-8")
		public ModelAndView SuaKhachHang(HttpSession session,@ModelAttribute("khachhang") Users khachhang) {
			String h = khachhang.getSdt();
			int email = usersDao.Count(khachhang.getEmail_kh());
			if(h.length()>10) {		
				_mvShare.setViewName("redirect:/chinh-sua-chi-tiet?id_kh= "+khachhang.getId_kh());	
				session.setAttribute("notification", "Số điện thoại bạn nhập không được vượt quá 10 chữ số");
				return _mvShare;
			}else if(email!=0) {		
				_mvShare.setViewName("redirect:/chinh-sua-chi-tiet?id_kh= "+khachhang.getId_kh());	
				session.setAttribute("notification", "Đã tồn tại địa chỉ email này");
				return _mvShare;
			}{
				HomeService.ChinhSuaKhachHang(khachhang);
				_mvShare.setViewName("redirect:/chi-tiet-khach-hang?id_kh= "+khachhang.getId_kh());	
				session.setAttribute("notification", "Chỉnh sửa thông tin thành công");
				return _mvShare;
			}	
		}
				
}
