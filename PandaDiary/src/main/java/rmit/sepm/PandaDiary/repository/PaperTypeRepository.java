package rmit.sepm.PandaDiary.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rmit.sepm.PandaDiary.entity.PaperType;

/**
 * @author Chih-Hsuan Lee<s3714761>
 *
 */
public interface PaperTypeRepository extends JpaRepository<PaperType, String>{
	
	@Query(value = "select id from paper_types order by id desc limit 1", nativeQuery = true)
	Integer findLastId();

}
