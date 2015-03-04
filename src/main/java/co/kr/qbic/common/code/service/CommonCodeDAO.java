package co.kr.qbic.common.code.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("commonCodeDAO")
public class CommonCodeDAO extends EgovAbstractDAO {

	public List selectListCode(Map<String, String> commandMap) throws Exception {
		return list("CommonCodeSQL001.list", commandMap);
	}

}
