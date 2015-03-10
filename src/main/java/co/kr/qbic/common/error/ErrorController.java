package co.kr.qbic.common.error;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error/")
public class ErrorController {
	private Logger logger = Logger.getLogger(ErrorController.class);

	@RequestMapping(value = "404.do")
	public String error404(Locale locale, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return "error/404";
	}
	
	@RequestMapping(value = "500.do")
	public String error500(Locale locale, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return "error/500";
	}
	
}