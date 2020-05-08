package rmit.sepm.PandaDiary.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
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
	
	public CoverColor addCoverColors(String description) {
		
		Integer lastId = coverColorRepository.findLastId();
		
		CoverColor newCoverColor = new CoverColor();
		newCoverColor.setId(lastId+1);
		newCoverColor.setDescription(description);
		
		return coverColorRepository.save(newCoverColor);
	}

	public PaperColor addPaperColors(String description) {
		

		Integer lastId = paperColorRepository.findLastId();
		
		PaperColor newPaperColor = new PaperColor();
		newPaperColor.setId(lastId+1);
		newPaperColor.setDescription(description);
		
		return paperColorRepository.save(newPaperColor);
	}
	
	public PaperType addPaperTypes(String description) {
		

		Integer lastId = paperTypeRepository.findLastId();
		
		PaperType newPaperType = new PaperType();
		newPaperType.setId(lastId+1);
		newPaperType.setDescription(description);
		
		return paperTypeRepository.save(newPaperType);
	}

	public Boolean deleteCoverColors(List<PaperColor> deleteOptions) {
		
		Boolean result = true;
		
		for (PaperColor temp: deleteOptions) {
			
			CoverColor item = new CoverColor();
			item.setId(temp.getId());
			item.setDescription(temp.getDescription());
			item.setActive(false);
			
			CoverColor save = coverColorRepository.save(item);
			if (save == null) {
				return false;
			}
		}
		
		return result;
	}

	public Boolean deletePaperTypes(List<PaperColor> deleteOptions) {
		
		Boolean result = true;
		
		for (PaperColor temp: deleteOptions) {
			
			PaperType item = new PaperType();
			item.setId(temp.getId());
			item.setDescription(temp.getDescription());
			item.setActive(false);
			
			PaperType save = paperTypeRepository.save(item);
			if (save == null) {
				return false;
			}
		}
		
		return result;
	}

	public Boolean deletePaperColors(List<PaperColor> deleteOptions) {
		
		Boolean result = true;
		
		for (PaperColor item: deleteOptions) {
			
			item.setActive(false);
			
			PaperColor save = paperColorRepository.save(item);
			if (save == null) {
				return false;
			}
		}
		
		return result;
	}

	public Boolean recoverCoverColors(List<PaperColor> recoverOptions) {
		
		Boolean result = true;
		
		for (PaperColor temp: recoverOptions) {
			
			CoverColor item = new CoverColor();
			item.setId(temp.getId());
			item.setDescription(temp.getDescription());
			item.setActive(true);
			
			CoverColor save = coverColorRepository.save(item);
			if (save == null) {
				return false;
			}
		}
		
		return result;
	}

	public Boolean recoverPaperTypes(List<PaperColor> recoverOptions) {
		
		Boolean result = true;
		
		for (PaperColor temp: recoverOptions) {
			
			PaperType item = new PaperType();
			item.setId(temp.getId());
			item.setDescription(temp.getDescription());
			item.setActive(true);
			
			PaperType save = paperTypeRepository.save(item);
			if (save == null) {
				return false;
			}
		}
		
		return result;
	}

	public Boolean recoverPaperColors(List<PaperColor> recoverOptions) {
		
		Boolean result = true;
		
		for (PaperColor item: recoverOptions) {
			
			item.setActive(true);
			
			PaperColor save = paperColorRepository.save(item);
			if (save == null) {
				return false;
			}
		}
		
		return result;	
	}
	
}
