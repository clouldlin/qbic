package co.kr.qbic.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("adminDAO")
public class AdminDAO extends EgovAbstractDAO {

	public List<?> adminList(Map<String, String> commandMap) throws Exception {
		return list("BoardSQL001.boardList", commandMap);
	}

	public int adminListTotalCount(Map<String, String> commandMap) throws Exception {
		return (Integer) getSqlMapClientTemplate().queryForObject("BoardSQL001.boardListTotalCount",commandMap);
	}

}
