package rmit.sepm.PandaDiary.pojo;

import java.util.List;

import lombok.Data;
import rmit.sepm.PandaDiary.entity.PaperColor;

/**
 * @author Chih-Hsuan Lee <s3714761>
 *
 */
@Data
public class DiaryParametersBean {
	
	public DiaryParametersBean(String userId, String target, List<PaperColor> diaryOptions) {
		this.userId = userId;
		this.target = target;
		this.diaryOptions = diaryOptions;
	}
	
	private String userId;
	private String target;
	private List<PaperColor> diaryOptions;

}
