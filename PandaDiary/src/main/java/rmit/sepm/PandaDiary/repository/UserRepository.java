package rmit.sepm.PandaDiary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rmit.sepm.PandaDiary.entity.User;

/**
 * @author Chih-Hsuan Lee<s3714761>
 *
 */
public interface UserRepository extends JpaRepository<User, String>{

	@Query(value = "select id, name, email, password, phone, address_street, "
				 + "address_surburb, address_postcode, address_state, active "
				 + "from Users "
				 + "where email = :email "
				 + "and password = :password", nativeQuery=true)
	List<User> findByEmailAndPassword(@Param(value = "email")String email, @Param(value = "password")String password);
	
	@Query(value= "select id from Users " + 
			"where lower(substring(id,1,1)) = :role " + 
			"order by id desc " + 
			"limit 1", nativeQuery = true)
	String findLastIdByRole(@Param(value="role")String role);

	User findByEmail(String email);
	
}
