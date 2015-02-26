package co.kr.qbic.web.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("commonDAO")
public class CommonDAO extends EgovAbstractDAO {

	public List list(Map<String, String> commandMap) throws Exception {
		return list("", commandMap);

	}

	public void insert(Map<String, String> commandMap) throws Exception {
		insert("", commandMap);
	}

	public void update(Map<String, String> commandMap) throws Exception {
		update("", commandMap);
	}

	public void delete(Map<String, String> commandMap) throws Exception {
		delete("", commandMap);
	}
}
