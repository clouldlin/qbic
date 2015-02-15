package co.kr.qbic.board.service.impl;

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

}
