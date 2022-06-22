package ClothesShop.Controller.Admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ClothesShop.Entity.ChiTietSanPham;
import ClothesShop.Entity.DanhMuc;
import ClothesShop.Entity.SanPham;
import ClothesShop.Service.Admin.AdminHomeImpl;
import ClothesShop.Service.Admin.SanPhamImpl;

@Controller
public class SanPhamController {
	@Autowired
	AdminHomeImpl HomeService;
	public ModelAndView _mvShare = new ModelAndView();
	@Autowired
	SanPhamImpl sanphamHomeImpl = new SanPhamImpl();
	// trang them san pham
		@RequestMapping(value = "/admin/quanlysanpham", method = RequestMethod.GET, produces="application/x-www-form-urlencoded;charset=UTF-8")
		public ModelAndView SanPham() {
			_mvShare.setViewName("admin/admin_sanpham");
			_mvShare.addObject("danhmuc", HomeService.GetDataDanhMuc());
			_mvShare.addObject("tensanpham", sanphamHomeImpl.GetDataSanPham());
			_mvShare.addObject("sanpham", new SanPham());
			return _mvShare;
		}
		//xu ly them san pham
		@RequestMapping(value = "admin/quanlysanpham", method = RequestMethod.POST, produces="application/x-www-form-urlencoded;charset=UTF-8")
		public ModelAndView CreateSanPham(@ModelAttribute("sanpham") SanPham sanpham, @RequestParam(value = "image", required = false) MultipartFile file, HttpServletRequest request ) {
			
			String pathdir = request.getSession().getServletContext().getRealPath("/")+"assets\\user\\img\\";
			System.out.println(pathdir);
			String filename =  String.valueOf(new Date().getTime())+ file.getOriginalFilename();
			
			
				try {
					byte[] bytes = file.getBytes();
					//File dir = new File(pathdir);
					//if( !dir.exists()) {
					//	dir.mkdir();
					//}
					
					File serverFile = new File(pathdir+File.separator+filename);
					//System.out.println(serverFile.getAbsolutePath());
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					
					sanpham.setHinhanh(filename);
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			
			
			
			int count = sanphamHomeImpl.AddSanPham(sanpham);
			if (count > 0) {
				_mvShare.addObject("status", "Thêm sản phẩm thành công !");

			} else {
				_mvShare.addObject("status", "Thêm sản phẩm thất bại !");
			}
		_mvShare.setViewName("/admin/admin_sanpham");
			return _mvShare;
		}
		//xu ly them chi tiet san pham
		@RequestMapping(value = "admin/themchitietsanpham", method = RequestMethod.POST, produces="application/x-www-form-urlencoded;charset=UTF-8")
		public ModelAndView SanPhamChiTiet(@ModelAttribute("chitietsanpham") ChiTietSanPham chitietsp) {
			 int count = sanphamHomeImpl.KiemTraChiTiet(chitietsp);
			 if (count > 0) {
				 sanphamHomeImpl.UpdateChiTiet(chitietsp);

				} else {
					sanphamHomeImpl.ThemSanPhamChiTiet(chitietsp);
				}
			 _mvShare.setViewName("redirect:/admin/quanlysanpham");
			return _mvShare;
		}
//		@RequestMapping(value = "admin/themchitietsanpham", method = RequestMethod.GET, produces="application/x-www-form-urlencoded;charset=UTF-8", params = "id_sp, soluong, size")
//		public ModelAndView ThemChiTiet(int id_sp, int soluong, String size) {
//			ModelAndView chitiet = new ModelAndView("admin/admin_sanpham");
//			chitiet.addObject("ctsp", sanphamHomeImpl.KiemTraChiTiet(id_sp, soluong, size));
//			System.out.print(id_sp);
////			if (count >0) {
////				sanphamHomeImpl.UpdateSanPhamChiTiet(id_sp, soluong, size);
////
////			} else {
////				sanphamHomeImpl.ThemMoiSanPhamChiTiet(id_sp, soluong, size);
////			}
//			_mvShare.setViewName("redirect:/admin/quanlysanpham");
//			return _mvShare;
//			}
}