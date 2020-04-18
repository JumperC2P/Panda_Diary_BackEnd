package rmit.sepm.PandaDiary.services;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rmit.sepm.PandaDiary.constants.Constants;
import rmit.sepm.PandaDiary.entity.User;
import rmit.sepm.PandaDiary.pojo.RegisterBean;
import rmit.sepm.PandaDiary.repository.UserRepository;
import rmit.sepm.PandaDiary.utils.CommonUtils;

/**
 * @author Chih-Hsuan Lee <s3714761>
 *
 */
@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User login(String email, String password) {
		
		List<User> users = userRepository.findByEmailAndPassword(email, password);
		
		if (CollectionUtils.isEmpty(users)) {
			return null;
		}
		
		User user = users.get(0);
		user.setPassword("");
		return user;
	}

	public User register(RegisterBean registerBean) {
		
		String id = "", lastId = "";
		Integer idNumber = null;
		
		switch (registerBean.getRole()) {
			case 1:
				lastId = userRepository.findLastIdByRole(Constants.ROLE_ADMIN);
				if (StringUtils.isBlank(lastId))
					return null;
				
				idNumber = Integer.valueOf(lastId.substring(1))+1;
				id = new StringBuffer(Constants.ROLE_ADMIN).append(CommonUtils.addZero(idNumber.toString(), 4)).toString();
				break;
			case 2:
				lastId = userRepository.findLastIdByRole(Constants.ROLE_CUSTOMER);
				if (StringUtils.isBlank(lastId))
					return null;
				
				idNumber = Integer.valueOf(lastId.substring(1))+1;
				id = new StringBuffer(Constants.ROLE_CUSTOMER).append(CommonUtils.addZero(idNumber.toString(), 4)).toString();
				break;
		}
		
		User user = new User();
		user.setId(id);
		user.setName(registerBean.getName());
		user.setEmail(registerBean.getEmail());
		user.setPassword(registerBean.getPassword());
		
		return userRepository.save(user);
	}

	public User findByUserId(String userId) {
		Optional<User> users = userRepository.findById(userId);
		
		if (users != null) {
			return users.get();
		}
		return null;
	}

}
