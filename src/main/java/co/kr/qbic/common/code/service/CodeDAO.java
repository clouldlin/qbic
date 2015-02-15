package co.kr.qbic.common.code.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import co.kr.qbic.common.vo.CommonVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("codeDAO")
public class CodeDAO extends EgovAbstractDAO {

	public List selectListCode(CommonVO searchVO) throws Exception {
		return list("CodeSQL001.list", searchVO);
	}

	public List selectListSubCode(Map<String, String> map) throws Exception {
		return list("CodeSQL001.subList", map);
	}

	public List<Map<String, String>> openInfoList(Map<String, String> map) throws Exception {
		return list("CodeSQL001.openInfoList", map);
	}
}
