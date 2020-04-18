package rmit.sepm.PandaDiary.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rmit.sepm.PandaDiary.entity.Order;

/**
 * @author Chih-Hsuan Lee<s3714761>
 *
 */
public interface OrderRepository extends JpaRepository<Order, String>{
	
	@Query(value = "select *\n" + 
					"from orders o\n" + 
					"where o.buyer = :userId \n" + 
					"order by o.id desc limit 5;", nativeQuery=true)
	List<Order> findTop5ByBuyer(@Param(value="userId")String userId);

}
