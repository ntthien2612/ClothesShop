package ClothesShop.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import ClothesShop.Entity.Admin;
import ClothesShop.Entity.DanhMuc;
import ClothesShop.Entity.MapperAdmin;
import ClothesShop.Entity.MapperDanhMuc;
import ClothesShop.Entity.MapperNguoiDung;
import ClothesShop.Entity.MapperUsers;
import ClothesShop.Entity.NguoiDung;
import ClothesShop.Entity.Users;

@Repository
public class AdminDao extends BaseDao {
	
	//them khach hang
		public int AddAccountAdmin(Admin admin)
		{
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT ");
			sql.append("INTO admin ");
			sql.append("( ");
			sql.append("	id_ad, ");
			sql.append("	taikhoan, ");
			sql.append("	pass, ");
			sql.append("	hoten ");
			sql.append(") ");
			sql.append("VALUES ");
			sql.append("( ");
			sql.append("	'"+admin.getId_ad()+"', ");
			sql.append("	'"+admin.getTaikhoan()+"', ");
			sql.append("	'"+admin.getPass()+"', ");
			sql.append("	'"+admin.getHoten()+"' ");
			sql.append(")");
			
			int insert = _jdbcTemplate.update(sql.toString());
			return insert;
		}
	
	// kiem tra email trung
	public int CountTaiKhoan(String email) {
		String sql = "SELECT COUNT(*) FROM admin where taikhoan='" + email + "'";
		int count = _jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}
	
	
//	kiểm tra tài khoản admin
	public Admin GetAdminByAcc(Admin admin) {
		try {
			String sql = "SELECT * FROM admin WHERE taikhoan = '" + admin.getTaikhoan() + "'";
			Admin result = _jdbcTemplate.queryForObject(sql, new MapperAdmin());
			return result;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		 
		
	}
	//Kiem tra tai khoan trung
	public int Count(String email) {
		int count = 0;
		String sql = "SELECT COUNT(*) FROM admin where taikhoan='" + email + "'";
		count = _jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}

	//danh sach admin
	public List<Admin> GetDataDanhSachAdmin() {
		List<Admin> list = new ArrayList<Admin>();
		String sql = "select * from admin";
		list = _jdbcTemplate.query(sql, new MapperAdmin());
		return list;
	}

	public List<Admin> GetDataTimKiemAdmin(String tenad) {
		 List<Admin> list = new ArrayList<Admin>();
		String sql = "select * from admin where hoten LIKE '%" +tenad+"%'";
		list = _jdbcTemplate.query(sql, new MapperAdmin());
		return list;
	}

}
