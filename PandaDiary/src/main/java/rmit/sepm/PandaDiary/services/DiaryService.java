package rmit.sepm.PandaDiary.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rmit.sepm.PandaDiary.constants.Constants;
import rmit.sepm.PandaDiary.entity.CoverColor;
import rmit.sepm.PandaDiary.entity.PaperColor;
import rmit.sepm.PandaDiary.entity.PaperType;
import rmit.sepm.PandaDiary.repository.CoverColorRepository;
import rmit.sepm.PandaDiary.repository.PaperColorRepository;
import rmit.sepm.PandaDiary.repository.PaperTypeRepository;

/**
 * @author Chih-Hsuan Lee <s3714761>
 *
 */
@Service
public class DiaryService {
	
	@Autowired
	CoverColorRepository coverColorRepository;
	
	@Autowired
	PaperColorRepository paperColorRepository;
	
	@Autowired
	PaperTypeRepository paperTypeRepository;
	
	public Map<String, Object> getDiaryParameters(String[] options){
		
		if (ArrayUtils.isEmpty(options))
			options = Constants.ALL_DIARY_PARAMETERS;
		
		Map<String, Object> params = new HashMap<>();
		
		for (String option : options) {
			
			switch (option) {
				case Constants.DIARY_PARAMETERS_PC:
					List<PaperColor> paperColors = paperColorRepository.findAll();
					params.put(Constants.DIARY_PARAMETERS_PC, paperColors);
					break;
				case Constants.DIARY_PARAMETERS_PT:
					List<PaperType> paperTypes = paperTypeRepository.findAll();
					params.put(Constants.DIARY_PARAMETERS_PT, paperTypes);
					break;
				case Constants.DIARY_PARAMETERS_CC:
					List<CoverColor> coverColors = coverColorRepository.findAll();
					params.put(Constants.DIARY_PARAMETERS_CC, coverColors);
					break;
			}
		}
		
		return params;
	}

}
