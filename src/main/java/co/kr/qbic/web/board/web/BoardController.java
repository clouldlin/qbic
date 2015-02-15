package co.kr.qbic.web.board.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import co.kr.qbic.common.controller.CommonAbstarctController;
import co.kr.qbic.common.util.string.CommonStringUtil;
import co.kr.qbic.common.vo.CommonVO;
import co.kr.qbic.web.board.service.BoardService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping("/board/")
public class BoardController extends CommonAbstarctController {

	@Autowired
	BoardService boardService;

	@RequestMapping("list.do")
	public String boardList(Map<String,String> commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		
		/** 페이징 처리 */
		int pageIndex 	= CommonStringUtil.isEmpty(commandMap.get("pageIndex")) ? 1: Integer.parseInt(commandMap.get("pageIndex"));
		int pageUnit  	= CommonStringUtil.isEmpty(commandMap.get("pageUnit")) ? 10: Integer.parseInt(commandMap.get("pageUnit"));

		CommonVO commonVO = new CommonVO();
		commonVO.setPageIndex(pageIndex);
		commonVO.setPageUnit(pageUnit);
		PaginationInfo paginationInfo  = this.setPaginationInfo(commonVO, commandMap);

		int totCnt=0;

		List<?> list = boardService.boardList(commandMap);
		
		totCnt= boardService.boardListTotalCount(commandMap);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("boardList", list);
		model.addAttribute("commonVO"		, commonVO);
		model.addAttribute("listTotal"		, totCnt);
		model.addAttribute("paginationInfo"	, paginationInfo);
		model.addAttribute("content","board/boardList.jsp");
		return "main.tiles";
	}
	
	@RequestMapping("write.do")
	public String boardWrite(Map<String,String> commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		model.addAttribute("content","board/boardUpdate.jsp");
		return "main.tiles";
	}
	
	@RequestMapping("detail.do")
	public String boardDetail(Map<String,String> commandMap, ModelMap model, HttpServletRequest request) throws Exception
	{
		model.addAttribute("content","board/boardDetail.jsp");
		return "main.tiles";
	}
	
	@RequestMapping("update.do")
	public String boardUpdate(Map<String,String> commandMap, ModelMap model, HttpServletRequest request) throws Exception
	{
		model.addAttribute("content","board/boardUpdate.jsp");
		return "main.tiles";
	}
	
}
