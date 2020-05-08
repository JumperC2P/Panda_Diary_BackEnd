package rmit.sepm.PandaDiary.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rmit.sepm.PandaDiary.entity.PaperColor;

/**
 * @author Chih-Hsuan Lee<s3714761>
 *
 */
public interface PaperColorRepository extends JpaRepository<PaperColor, String>{
	
	@Query(value = "select id from paper_colors order by id desc limit 1", nativeQuery = true)
	Integer findLastId();

}
