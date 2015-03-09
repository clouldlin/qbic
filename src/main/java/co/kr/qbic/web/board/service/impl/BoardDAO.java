package co.kr.qbic.web.board.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("boardDAO")
public class BoardDAO extends EgovAbstractDAO {

	public List<?> list(Map<String, String> commandMap) throws Exception {
		return list("BoardSQL001.list", commandMap);
	}

	public int listTotalCount(Map<String, String> commandMap) throws Exception {
		return (Integer) getSqlMapClientTemplate().queryForObject("BoardSQL001.listTotalCount",commandMap);
	}

	public Map view(Map<String,String> commandMap) throws Exception {
		return (Map) selectByPk("BoardSQL001.view",commandMap);
	}

	public void update(Map<String, String> commandMap) throws Exception {
		update("BoardSQL001.update",commandMap);
	}

	public void delete(Map<String, String> commandMap) throws Exception{
		delete("BoardSQL001.delete",commandMap);
	}
	
	public void insert(Map<String,String> commandMap) throws Exception {
		insert("BoardSQL001.insert",commandMap);
	}

}
