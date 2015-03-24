package co.kr.qbic.web.board.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import co.kr.qbic.common.controller.CommonAbstarctController;
import co.kr.qbic.common.file.CommonFileUtil;
import co.kr.qbic.common.file.service.CommonFileService;
import co.kr.qbic.common.util.string.CommonStringUtil;
import co.kr.qbic.common.vo.CommonVO;
import co.kr.qbic.common.vo.FileVO;
import co.kr.qbic.web.board.service.BoardService;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping("/board/")
public class BoardController extends CommonAbstarctController {
	
	public static Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	BoardService boardService;
	
    @Resource(name = "commonFileUtil")
    private CommonFileUtil commonFileUtil;

    @Resource(name = "commonFileService")
    private CommonFileService commonfileService;
	
	/** BOARD_ID Generation Service */
	@Resource(name = "egovBoardIdGnrService")
	private EgovIdGnrService egovBoardIdGnrService;
	
	@RequestMapping("list.do")
	public String boardList(Map<String,String> commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		
		/** 페이징 처리 */
		int pageIndex 	= CommonStringUtil.isEmpty(commandMap.get("pageIndex")) ? 1: Integer.parseInt(commandMap.get("pageIndex"));
		int pageUnit  	= CommonStringUtil.isEmpty(commandMap.get("pageUnit")) ? 10: Integer.parseInt(commandMap.get("pageUnit"));

		CommonVO commonVO = new CommonVO();
		commonVO.setPageIndex(pageIndex);
		commonVO.setPageUnit(pageUnit);
		PaginationInfo paginationInfo  = this.setPaginationInfo(commonVO, commandMap);

		int totCnt = 0;

		List<?> list = boardService.list(commandMap);
		
		totCnt= boardService.listTotalCount(commandMap);
		paginationInfo.setTotalRecordCount(totCnt);
		
		List<?> codeList = listCommCode("BOARD_SEARCH_CONDS");
		
		model.addAttribute("boardList"		, list);
		model.addAttribute("codeList"		, codeList);
		model.addAttribute("commonVO"		, commonVO);
		model.addAttribute("listTotal"		, totCnt);
		model.addAttribute("paginationInfo"	, paginationInfo);
		model.addAttribute("content"		,"board/boardList.jsp");
		return "main.tiles";
	}
	
	@RequestMapping("write.do")
	public String boardWrite(Map<String,String> commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		
		String fileId = "";
    	List<FileVO> fileList = null;
    	
    	MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
	    final Map<String, MultipartFile> files = multiRequest.getFileMap();
	    
	    if (!files.isEmpty()) {
	    	fileList = commonFileUtil.parseFileInfo(files, "BOARD", 0);
	    	fileId = commonfileService.insertFileInfos(fileList);
	    }
	    
	    commandMap.put("file_id", fileId);
    	commandMap.put("board_id", egovBoardIdGnrService.getNextStringId());
    	commandMap.put("txt_content", (String) request.getAttribute("txt_content"));
    	commandMap.put("title", (String) request.getAttribute("title"));
    	
    	boardService.insert(commandMap);
		
		return "redirect:/board/list.do";
	}
	
	@RequestMapping("detail.do")
	public String boardDetail(Map<String,String> commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		boardService.updateHitCount(commandMap);
		Map view = boardService.view(commandMap);
		
		List<FileVO> fileList = null;
		
		if(view.get("fileId") != null){
			FileVO fvo = new FileVO();
			fvo.setFileId(view.get("fileId").toString());
			fileList = commonfileService.selectFileInfos(fvo);
		}
		
		model.addAttribute("searchData"	,commandMap);
		model.addAttribute("view"		,view);
		model.addAttribute("fileList"		,fileList);
		model.addAttribute("content"	,"board/boardView.jsp");
		
		return "main.tiles";
	}
	
	@RequestMapping("update.do")
	public String boardUpdate(Map<String,String> commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		model.addAttribute("content","board/boardUpdate.jsp");
		return "main.tiles";
	}
	
	@RequestMapping("delete.do")
	public String delete(Map<String,String> commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		boardService.delete(commandMap);
		return "redirect:/board/list.do";
	}
}
