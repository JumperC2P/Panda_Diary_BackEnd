package rmit.sepm.PandaDiary.services;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rmit.sepm.PandaDiary.entity.User;
import rmit.sepm.PandaDiary.repository.UserRepository;

/**
 * @author Chih-Hsuan Lee <s3714761>
 *
 */
@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public User login(String email, String password) {
		
		List<User> users = userRepository.findByEmailAndPassword(email, password);
		
		if (CollectionUtils.isEmpty(users)) {
			return null;
		}
		
		User user = users.get(0);
		user.setPassword("");
		return user;
		
	}

}
