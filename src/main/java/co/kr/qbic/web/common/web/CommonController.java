package co.kr.qbic.web.common.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import co.kr.qbic.web.common.service.CommonService;

@Controller
@RequestMapping("/common/")
public class CommonController {

	@Resource(name = "commonService")
	CommonService commonService;

	@RequestMapping("page.do")
	public String guideList(Map<String, String> commandMap, ModelMap model,	HttpServletRequest request) throws Exception {
		model.addAttribute("script", commandMap.get("pagejs"));
		model.addAttribute("content", commandMap.get("page"));
		return "main.tiles";
	}
}