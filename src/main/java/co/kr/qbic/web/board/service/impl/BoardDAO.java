package co.kr.qbic.web.board.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("boardDAO")
public class BoardDAO extends EgovAbstractDAO {

	public List<?> boardList(Map<String, String> commandMap) throws Exception {
		return list("BoardSQL001.boardList", commandMap);
	}

	public int boardListTotalCount(Map<String, String> commandMap) throws Exception {
		return (Integer) getSqlMapClientTemplate().queryForObject("BoardSQL001.boardListTotalCount",commandMap);
	}

	public Map detailView(Map<String,String> commandMap) throws Exception {
		return (Map) selectByPk("BoardSQL001.board_view",commandMap);
	}

	public void update(Map<String, String> commandMap) throws Exception {
		update("BoardSQL001.update",commandMap);
	}

	public void delete(Map<String, String> commandMap) throws Exception{
		delete("BoardSQL001.board_delete",commandMap);
	}
	
	public void insert(Map<String,String> commandMap) throws Exception {
		insert("BoardSQL001.insert",commandMap);
	}

}
