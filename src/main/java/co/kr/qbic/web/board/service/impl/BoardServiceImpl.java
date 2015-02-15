package co.kr.qbic.board.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kr.qbic.board.service.BoardService;


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

}
