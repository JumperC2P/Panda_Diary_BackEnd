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
					"order by o.id desc limit :number", nativeQuery=true)
	List<Order> findByBuyerWithLimit(@Param(value="userId")String userId, @Param(value="number") Integer number);

	
	@Query(value = "select id from orders order by id desc limit 1", nativeQuery = true)
	String findLastOrderId();

}
