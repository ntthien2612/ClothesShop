package ClothesShop.Entity;

public class Users {
	private long id_kh;
	private String ten_kh;
	private String pass;
	private String email_kh;
	private String diachi;
	private String sdt;
	public long getId_kh() {
		return id_kh;
	}
	public void setId_kh(long id_kh) {
		this.id_kh = id_kh;
	}
	public String getTen_kh() {
		return ten_kh;
	}
	public void setTen_kh(String ten_kh) {
		this.ten_kh = ten_kh;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmail_kh() {
		return email_kh;
	}
	public void setEmail_kh(String email_kh) {
		this.email_kh = email_kh;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public Users() {
		super();
	}
	
	
}