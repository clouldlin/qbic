package co.kr.qbic.main.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import co.kr.qbic.common.controller.CoAbstarctController;
import co.kr.qbic.main.service.MainService;

@Controller
@RequestMapping("/main/")
public class MainController extends CoAbstarctController {
	
	public final static Logger logger = LoggerFactory.getLogger(MainController.class);

	@Resource(name = "mainService")
	MainService mainService;

	@RequestMapping("main.do")
	public String searchList(Map<String, String> commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		List codeList = listCommCode("");
		model.addAttribute("codeList", codeList);
		model.addAttribute("script", "js/main/main.js");
		model.addAttribute("contents", "main/main.jsp");
		return "main.tiles";
	}

	@RequestMapping("mainAjaxPossession.do")
	public String mainAjaxPossession(Map<String, String> commandMap,
			ModelMap model, HttpServletRequest request) throws Exception {
		// 보유현황 데이터 조회
		List posSessionList = new ArrayList();
		if ("all".equals(commandMap.get("searchFlag"))) {
			posSessionList = mainService.selectPosAll();
		} else if ("phobon".equals(commandMap.get("searchFlag"))) {
			posSessionList = mainService.selectPosPhobon();
		} else {
		}

		model.addAttribute("posSessionList", posSessionList);
		model.addAttribute("script", "js/main/main.js");
		model.addAttribute("contents", "main/main.jsp");
		return "jsonView";
	}

	@RequestMapping("integration.do")
	public String integrationList(Map<String, String> commandMap, ModelMap model, HttpServletRequest request) {
		return "main.tiles";
	}

	@RequestMapping("popup.do")
	public String popup(Map<String, String> commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		model.addAttribute("contents", "");
		return "co/pkg01001l";
	}

}
