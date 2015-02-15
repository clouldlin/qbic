package co.kr.qbic.web.board.service;

import java.util.List;
import java.util.Map;

public interface BoardService {

	public List<?> boardList(Map<String, String> commandMap) throws Exception;

	public Integer boardListTotalCount(Map<String, String> commandMap) throws Exception;
}
