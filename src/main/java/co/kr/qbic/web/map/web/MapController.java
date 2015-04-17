package co.kr.qbic.web.map.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.kr.qbic.common.controller.CommonAbstarctController;
import co.kr.qbic.web.map.service.MapService;

@Controller
@RequestMapping("/map/")
public class MapController extends CommonAbstarctController {
	
	public static Logger logger = LoggerFactory.getLogger(MapController.class);

	@Autowired
	MapService mapService;
	
	/*
 		congestionIndex : 혼잡지표
 		congestionCost : 혼잡비용
		trafficVolume : 교통량
		demarcationSpeed : 속도
	 */
	
	@RequestMapping("map.do")
	public String map(Map<String,String> commandMap, ModelMap model, HttpServletRequest request, @RequestParam(value="menu", required = false, defaultValue="congestionIndex") String menu) throws Exception {
		
		logger.info(menu);
		
		String menu_name = null;
		if(menu.equals("congestionIndex")){
			menu_name = "혼 잡 지 표";
			commandMap.put("CLASS_CODE", menu);
		}else if(menu.equals("congestionCost")){
			menu_name = "혼 잡 비 용";
			commandMap.put("CLASS_CODE", menu);
		}else if(menu.equals("trafficVolume")){
			menu_name = "교 통 량";
			commandMap.put("CLASS_CODE", menu);
		}else if(menu.equals("demarcationSpeed")){
			menu_name = "속 도";
			commandMap.put("CLASS_CODE", menu);
		}
		
		List<?> sidoCodeList = mapService.sidoCodelist();
		
		commandMap.put("CODE_GROUP", "map_index");
		List<?> indexCodeList = mapService.indexCodeList(commandMap);
		
		model.addAttribute("sidoCodeList", sidoCodeList);
		model.addAttribute("indexCodeList", indexCodeList);
		
		model.addAttribute("menu"		 , menu);
		model.addAttribute("menu_name"	 , menu_name);
		return "map.default";
	}
	
	@RequestMapping("landcode.do")
	public String ajaxForLandCode(Map<String,String> commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		List<?> returnList = null;
		
		if ("sgg_cd".equals(commandMap.get("code"))){
			returnList = mapService.sggCodelist(commandMap);
		}
		else if ("emd_cd".equals(commandMap.get("code"))){
			returnList = mapService.umdCodeList(commandMap);
		}
		else if ("lawd_cd".equals(commandMap.get("code"))){
			returnList = mapService.lawdCodeList(commandMap);
		}

		model.addAttribute("returnList",returnList);
		return "jsonView";
	}
	
	@RequestMapping("page.do")
	public String page(Map<String, String> commandMap, ModelMap model,	HttpServletRequest request) throws Exception {
		String page = commandMap.get("page");
		model.addAttribute("menu"	 , page);
		return "map/" + page;
	}
	
}
