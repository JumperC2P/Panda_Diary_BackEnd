package rmit.sepm.PandaDiary.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import rmit.sepm.PandaDiary.entity.Order;

/**
 * @author Chih-Hsuan Lee<s3714761>
 *
 */
public interface OrderRepository extends JpaRepository<Order, String>{

}
