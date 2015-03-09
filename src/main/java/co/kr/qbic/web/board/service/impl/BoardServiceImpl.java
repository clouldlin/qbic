package co.kr.qbic.web.board.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kr.qbic.web.board.service.BoardService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


@Service("boardService")
public class BoardServiceImpl extends AbstractServiceImpl implements BoardService {

	@Resource(name="boardDAO")
	BoardDAO boardDAO;

	@Override
	public List<?> list(Map<String, String> commandMap) throws Exception {
		return boardDAO.list(commandMap);
	}

	@Override
	public Integer listTotalCount(Map<String, String> commandMap) throws Exception {
		return boardDAO.listTotalCount(commandMap);
	}

	@Override
	public Map view(Map<String, String> commandMap) throws Exception {
		return boardDAO.view(commandMap);
	}

	@Override
	public void update(Map<String,String> commandMap) throws Exception {
		boardDAO.update(commandMap);
	}
	
	@Override
	public void updateHitCount(Map<String,String> commandMap) throws Exception {
		boardDAO.updateHitCount(commandMap);
	}
	
	@Override
	public void delete(Map<String, String> commandMap) throws Exception {
		boardDAO.delete(commandMap);
	}

	@Override
	public void insert(Map<String, String> commandMap) throws Exception {
		boardDAO.insert(commandMap);
	}
}
