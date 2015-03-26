package co.kr.qbic.common.login.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import co.kr.qbic.common.Constants;
import co.kr.qbic.common.controller.CommonAbstarctController;
import co.kr.qbic.common.login.service.LoginService;
import co.kr.qbic.common.vo.LoginVO;

@Controller
@RequestMapping("/member/")
public class LoginController extends CommonAbstarctController{

	private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource(name = "loginService")
    private LoginService loginService;

	@RequestMapping("login.do")
    public String login(Map<String,String> commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		
		LoginVO loginVO = loginService.selectUserById(commandMap);
	
    	if(loginVO != null && loginVO.getUserId()!= null && !"".equals(loginVO.getUserId())){
    		// 세션 생성해 주기
    		WebUtils.setSessionAttribute(request,Constants.userSession, loginVO);
    		// 현재 로그인 정보를 세션에 저장
    		loginSessionManager.setSession(loginVO);
        	return "redirect:/board/list.do";
    	}else{
    		model.addAttribute("flag" ,"0");
	    	model.addAttribute("message"   ,"아이디 또는 비밀번호가 일치하지 않습니다.");
	    	
	    	return "forward:/board/list.do";
    	}
    }
	
	@RequestMapping("logout.do")
    public String logout(Map<String,String> commandMap, ModelMap model, HttpServletRequest request) throws Exception {

    	WebUtils.setSessionAttribute(request,Constants.userSession, "");
    	removeSession(request);
    	removeLoginVO();

		return "redirect:/board/list.do";
    }

}
