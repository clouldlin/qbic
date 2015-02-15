package co.kr.qbic.login.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import com.qbic.web.common.Constants;
import com.qbic.web.common.controller.CoAbstarctController;
import com.qbic.web.common.util.CoStringUtil;
import com.qbic.web.login.service.LoginService;
import com.qbic.web.login.vo.LoginVO;

@Controller
@RequestMapping("/member/")
public class LoginController extends CoAbstarctController {


    @Resource(name = "loginService")
    private LoginService loginService;

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

	/**
	 * 로그인 화면 호출
	 * @param model
	 * @return "selectLoginMain.tiles"
	 * @throws Exception
	 */
    @RequestMapping("login.do")
    public String selectLoginMain(Map<String,String> commandMap,ModelMap model,HttpServletRequest request) throws Exception {

    	Map loginVO = (Map<String,String>) WebUtils.getSessionAttribute(request, Constants.userSession);
    	String returnUrl = CoStringUtil.nullConvert(commandMap.get("returnUrl"));

    	//로그인 체크 하기.. 로그인이 되어 있다면 메인으로
    	if(loginVO != null && loginVO.get("user_id") != null && !"".equals(loginVO.get("user_id"))){

    		 return "redirect:/main/main.do";
    	}

    	if(returnUrl != null && !"".equals(returnUrl))
		{
    		model.addAttribute("returnUrl",returnUrl);
		}

    	model.addAttribute("script","js/user/login.js");
		model.addAttribute("contents","user/login.jsp");
		return "main.tiles";
    }

	/**
	 * 로그인 처리
	 * @param request
	 * @param response
	 * @param model
	 * @return "jsonView"
	 * @throws Exception
	 */
	@RequestMapping("/AcessLogin.do")
    public String selectLoginAction(Map<String,String> commandMap,HttpServletRequest request, ModelMap model) throws Exception {

    	String retURL 			= "";
    	String ContextPath   = request.getContextPath();
    	String flag 			= "0";

    	// 회원 정보를 조회한다.
    	Map<String, Object> returnValue = null;
    	//commandMap.put("user_id", "iitech2005");
		//commandMap.put("pswd", "q!234567");
    	commandMap.put("access_key"	, "accessKey ");
    	commandMap.put("action"		, "login");
    	commandMap.put("site_sno"	, "10");
    	commandMap.put("access_ip"	, "192.168.0.1");

    	//통합 Login DB 조회
    	String root_url = propertiesService.getString("svr_url.root");
    	returnValue = executeUrl(root_url+"datacenter/com/datasvc/cmUser.jsp", commandMap);

    	//로그인 체크 하기.. 로그인이 되어 있다면 메인으로
    	if(returnValue != null && returnValue.get("user_id") != null && !"".equals(returnValue.get("user_id"))){

    		LoginVO loginVO = new LoginVO();
    		Iterator iterator = returnValue.keySet().iterator();
    		while(iterator.hasNext())
    		{
    			String key 			= (String)iterator.next();
    			String searchMehod 	= "set"+key.replaceAll("_", "");
    			String value		= (String)returnValue.get(key);
    			invoke(loginVO, searchMehod, new Object[]{value});
    		}

    		allModelPropPrint("co.kr.iitech.comm.vo.LoginVO", loginVO);
    		// 세션 생성해 주기
    		WebUtils.setSessionAttribute(request,Constants.userSession, loginVO);
    		// 현재 로그인 정보를 세션에 저장
    		loginSessionManager.setSession(loginVO);
    		flag = "1";
        	model.addAttribute("flag"    	, flag);

        	String returnUrl = CoStringUtil.nullConvert(commandMap.get("returnUrl"));
        	if(returnUrl != null && !"".equals(returnUrl))
    		{
        		if(returnUrl.indexOf("collreq.do") > 0 )
    		   {
        			Object object = WebUtils.getSessionAttribute(request,"dnabankChk");
        			WebUtils.setSessionAttribute(request,"dnabankChk",null);
        			model.addAttribute("dnabankChk",object);
    		   }
    		   else if(returnUrl.indexOf("lending.do") > 0 )
    		   {
    			   Object object = WebUtils.getSessionAttribute(request,"phobonChk");
    			   WebUtils.setSessionAttribute(request,"phobonChk",null);
    			   model.addAttribute("phobonChk",object);
    		   }
    			retURL	=	"redirect:"+returnUrl;
    		}
    		else
    		{
    			  retURL	=	"forward:/main/main.do";
    		}
    	}else{

    		flag = "0";
    		model.addAttribute("script"		,"js/user/login.js");
    		model.addAttribute("contents"	,"user/login.jsp");
    		model.addAttribute("flag"    	, flag);
        	model.addAttribute("message"    ,returnValue.get("rtn_message"));
        	retURL	=	"main.tiles";
    	}

    	WebUtils.setSessionAttribute(request, "FV_LOGIN_FLAG", "MAIN");
    	return retURL;
    }

	/**
	 * 회원기본정보 수정 페이지로 이동
	 * @param request
	 * @param response
	 * @param model
	 * @return "jsonView"
	 * @throws Exception
	 */
	@RequestMapping("/checkMember.do")
	public String checkMemberAction(Map<String,String> commandMap,HttpServletRequest request, ModelMap model) throws Exception {

		String root_url = propertiesService.getString("svr_url.root");

		// sso 인증값을 위한 파라미터
		Map<String, Object> returnValue = null;
		commandMap.put("action"		, "sso");
		commandMap.put("site_sno"	, "10");
		commandMap.put("sso_go_url"	, root_url + "datacenter/home/member/mb01002j.jsp");

		URL url = new URL(root_url+"datacenter/com/datasvc/cmUser.jsp");

        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);

        OutputStreamWriter osw = new OutputStreamWriter( connection.getOutputStream());

        Iterator en = commandMap.keySet().iterator();
        int pCnt = 0;
        while(en.hasNext())
        {
            String key = (String)en.next();
            if (pCnt > 0)
            {
            	osw.write("&");
            }
            osw.write(key+"=" + URLEncoder.encode(commandMap.get(key), "UTF-8"));
            pCnt++;
        }
        osw.close();

        BufferedReader in 	= new BufferedReader( new InputStreamReader(connection.getInputStream(), "UTF-8"));
        ObjectMapper mapper = new ObjectMapper();

        JsonNode root  = mapper.readTree(in);
        String sso_sno = root.path("sfw_response").path("box").path("sso_sno").getTextValue();

        return "redirect:" + root_url + "datacenter/home/sso_login.jsp?sso_sno=" + sso_sno + "&site_sno=" + commandMap.get("site_sno");
	}


	/**
	 * 로그아웃
	 * @param request
	 * @param model
	 * @return "redirect:/main/login.do"
	 * @throws Exception
	 *
	 * 현재 도메인만 SSO 리턴 URL을 체크 하기 때문에 다른 내부 시스템은 SSO LOGOUT를 사용하지 않는다.
	 */
    @RequestMapping("logout.do")
	public String logOut(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{

		//로그아웃 처리
    	WebUtils.setSessionAttribute(request,Constants.userSession, "");
    	removeSession(request);
    	removeLoginVO();

    	//return "redirect:" + "http://www.nibr.go.kr/specimen/member/login.do";
		return "redirect:/member/login.do";
	}
}
