package co.kr.qbic.web.board.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kr.qbic.web.board.service.BoardService;


@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Resource(name="boardDAO")
	BoardDAO boardDAO;

	@Override
	public List<?> boardList(Map<String, String> commandMap) throws Exception {
		return boardDAO.boardList(commandMap);
	}

	@Override
	public Integer boardListTotalCount(Map<String, String> commandMap) throws Exception {
		return boardDAO.boardListTotalCount(commandMap);
	}

	@Override
	public Map detailView(Map<String, String> commandMap) throws Exception {
		return boardDAO.detailView(commandMap);
	}

	@Override
	public void update(Map<String,String> commandMap) throws Exception {
		boardDAO.update(commandMap);
	}

	@Override
	public void delete(Map<String, String> commandMap) throws Exception {
		boardDAO.delete(commandMap);
	}
}
