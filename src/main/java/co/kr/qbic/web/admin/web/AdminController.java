package co.kr.qbic.web.admin.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import co.kr.qbic.common.controller.CommonAbstarctController;
import co.kr.qbic.common.util.CommonStringUtil;
import co.kr.qbic.common.vo.CommonVO;
import co.kr.qbic.web.admin.service.AdminService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping("/admin/")
public class AdminController extends CommonAbstarctController {

	@Resource(name="adminService")
	AdminService adminService;

	@RequestMapping("list.do")
	public String adminList(Map<String,String> commandMap, ModelMap model, HttpServletRequest request) throws Exception
	{
		/** 페이징 처리 */
		int pageIndex  			= CommonStringUtil.isEmpty(commandMap.get("pageIndex")) ? 1: Integer.parseInt(commandMap.get("pageIndex"));
		int pageUnit  			= CommonStringUtil.isEmpty(commandMap.get("pageUnit")) ? 10: Integer.parseInt(commandMap.get("pageUnit"));

		CommonVO commonVO = new CommonVO();
		commonVO.setPageIndex(pageIndex);
		commonVO.setPageUnit(pageUnit);
		PaginationInfo paginationInfo  = this.setPaginationInfo(commonVO, commandMap);

		int totCnt = 0;

		List<?> list = adminService.adminList(commandMap);

		totCnt= adminService.adminListTotalCount(commandMap);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("adminList", list);
		model.addAttribute("commonVO"		, commonVO);
		model.addAttribute("listTotal"		, totCnt);
		model.addAttribute("paginationInfo"	, paginationInfo);
		model.addAttribute("content","admin/adminList.jsp");
		return "main.tiles";
	}
	
	@RequestMapping("detail.do")
	public String adminDetail(Map<String,String> commandMap, ModelMap model, HttpServletRequest request) throws Exception
	{
		model.addAttribute("content","admin/adminDetail.jsp");
		return "main.tiles";
	}
	
	@RequestMapping("update.do")
	public String adminUpdate(Map<String,String> commandMap, ModelMap model, HttpServletRequest request) throws Exception
	{
		model.addAttribute("content","admin/adminUpdate.jsp");
		return "main.tiles";
	}
	
}
