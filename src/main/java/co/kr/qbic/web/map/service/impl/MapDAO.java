package co.kr.qbic.web.map.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("mapDAO")
public class MapDAO extends EgovAbstractDAO {

	public List<?> sidoCodelist() throws Exception {
		return list("MapSQL001.sidoCodeList", null);
	}
	
	public List<?> sggCodelist(Map<String, String> commandMap) throws Exception {
		return list("MapSQL001.sggCodeList", commandMap);
	}
	
	public List<?> umdCodeList(Map<String, String> commandMap) throws Exception {
		return list("MapSQL001.umdCodeList", commandMap);
	}

	public List<?> lawdCodeList(Map<String, String> commandMap) throws Exception {
		return list("MapSQL001.lawdCodeList", commandMap);
	}

	public List<?> indexCodeList(Map<String, String> commandMap) throws Exception {
		return list("MapSQL001.indexCodeList", commandMap);
	}
}
