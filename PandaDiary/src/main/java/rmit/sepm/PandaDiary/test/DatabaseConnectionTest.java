package rmit.sepm.PandaDiary.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import rmit.sepm.PandaDiary.entity.User;
import rmit.sepm.PandaDiary.repository.UserRepository;

//@Component
//@Order(value=1)
public class DatabaseConnectionTest implements ApplicationRunner{
	
//	@Autowired
//	UserRepository usersRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
//		System.out.println("DO");
//		
//		Users user = new Users();
//		user.setId("c0001");
//		user.setAccount("admin");
//		user.setPassword("admin");
//		
//		usersRepository.save(user);
		
	}

}
