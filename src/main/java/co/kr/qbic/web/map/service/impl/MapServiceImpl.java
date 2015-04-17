package co.kr.qbic.web.map.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kr.qbic.web.map.service.MapService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


@Service("mapService")
public class MapServiceImpl extends AbstractServiceImpl implements MapService {

	@Resource(name="mapDAO")
	MapDAO mapdDAO;
	
	@Override
	public List<?> sidoCodelist() throws Exception {
		return mapdDAO.sidoCodelist();
	}

	@Override
	public List<?> sggCodelist(Map<String, String> commandMap) throws Exception {
		return mapdDAO.sggCodelist(commandMap);
	}

	@Override
	public List<?> umdCodeList(Map<String, String> commandMap) throws Exception {
		return mapdDAO.umdCodeList(commandMap);
	}

	@Override
	public List<?> lawdCodeList(Map<String, String> commandMap) throws Exception {
		return mapdDAO.lawdCodeList(commandMap);
	}

	@Override
	public List<?> indexCodeList(Map<String, String> commandMap) throws Exception {
		return mapdDAO.indexCodeList(commandMap);
	}

}
