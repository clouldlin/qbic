package co.kr.qbic.common.code;

import java.util.List;
import java.util.Map;

import co.kr.qbic.common.vo.CommonVO;

public interface CommonCodeService {

	public List selectListCode(CommonVO searchVO) throws Exception;

}
