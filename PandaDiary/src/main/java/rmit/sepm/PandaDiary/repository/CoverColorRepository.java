package rmit.sepm.PandaDiary.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rmit.sepm.PandaDiary.entity.CoverColor;

/**
 * @author Chih-Hsuan Lee<s3714761>
 *
 */
public interface CoverColorRepository extends JpaRepository<CoverColor, String>{
	
	@Query(value = "select id from cover_colors order by id desc limit 1", nativeQuery = true)
	Integer findLastId();

}
