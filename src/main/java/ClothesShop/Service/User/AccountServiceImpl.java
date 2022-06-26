package ClothesShop.Service.User;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ClothesShop.Dao.UsersDao;
import ClothesShop.Entity.Users;
@Service
public class AccountServiceImpl implements IAccountService {
	@Autowired
	UsersDao usersDao = new UsersDao();
	
	public int AddAccount(Users user) {
		
		user.setPass(BCrypt.hashpw(user.getPass(), BCrypt.gensalt(12)));
		
		return usersDao.AddAccount(user);
	}
//dang nhap
	public Users CheckAccount(Users user) {
		String pass = user.getPass();
		user = usersDao.GetUserByAcc(user);
		if (user != null) {
			if (BCrypt.checkpw(pass, user.getPass())) {
				return user;
			} else {
				return null;
			}
		}
		return null;
		

	}
}
