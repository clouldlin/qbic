package co.kr.qbic.common.code.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import co.kr.qbic.common.vo.CommonVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("commonCodeDAO")
public class CommonCodeDAO extends EgovAbstractDAO {
	
	public List selectListCode(CommonVO searchVO) throws Exception {
		return list("CommonCodeSQL001.list", searchVO);
	}

	public List selectListSubCode(Map<String, String> map) throws Exception {
		return list("CodeSQL001.subList", map);
	}
}
