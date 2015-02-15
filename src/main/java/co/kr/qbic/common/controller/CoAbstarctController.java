package co.kr.qbic.common.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import com.qbic.web.common.util.CoStringUtil;
import com.qbic.web.common.vo.CoDefaultVO;
import com.qbic.web.login.LoginSessionManager;
import com.qbic.web.login.vo.LoginVO;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationManager;


/**
*
* @Class Name :CoAbstarctController.java
* @Description :CoAbstarctController.java
* @Modification Information
* @
* @  수정일            수정자              수정내용
* @ ---------   ---------   -------------------------------
* @ 2013.11.14	정용진		최초생성
* @author CMS
* @since 2013. 11. 14
* @version 1.0
* @see
*
* Copyright (C) by IIT All right reserved.
 */
public class CoAbstarctController {

	 /** EgovPropertyService */
    @Resource(name = "egovPropertiesService")
    protected EgovPropertyService propertiesService;

	@Resource(name = "loginSessionManager")
	protected LoginSessionManager loginSessionManager;

	@Resource(name = "codeService")
	protected CodeService codeService;

	@Resource(name="paginationManager")
	protected PaginationManager paginationManager;

	final static Logger logger = LoggerFactory.getLogger(CoAbstarctController.class);

	/**
	 * NIBR_CM 공통코드를 조회해서 List로 넘겨준다.
	 * @see
	 */
	protected List listCommCode(String grpCd){

		grpCd = CoStringUtil.isNullBlank(grpCd);
		CoDefaultVO searchVO = new CoDefaultVO();
		searchVO.setGrpCd(grpCd);
		List codeList = null;

		/*분류 선택에 의한 하위 코드 리스트 불러오기 */
		try
		{
			codeList = codeService.selectListCode(searchVO);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return codeList;
	}


	/**
	 * DNABANK 공통코드를 조회해서 List로 넘겨준다.
	 * @see
	 */
	protected List listCommCodeDna(String grpCd){

		grpCd = CoStringUtil.isNullBlank(grpCd);
		CoDefaultVO searchVO = new CoDefaultVO();
		searchVO.setGrpCd(grpCd);
		List codeList = null;

		/*분류 선택에 의한 하위 코드 리스트 불러오기 */
		try
		{
			codeList = codeService.selectListCodeDna(searchVO);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return codeList;
	}

	/**
	 * NIBR_CM 공통코드를 sub List
	 * @see
	 */
	protected List selectListSubCode(Map<String, String> commandMap){

		List codeList = null;
		/*분류 선택에 의한 하위 코드 리스트 불러오기 */
		try
		{
			codeList = codeService.selectListSubCode(commandMap);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return codeList;
	}


	/**
	 * 로그인 vo getter 메서드
	 * null일 수도 있다 .
	 * @return 로그인 vo
	 */
	protected LoginVO getLoginVO() {
		LoginVO getLoginVO = null;
		if(loginSessionManager.getLoginVO() != null){
			getLoginVO = loginSessionManager.getLoginVO();
		}else{
			getLoginVO = new LoginVO();
		}
		return getLoginVO;
	}

	/**
	 * 로그인 vo remove 메서드
	 */
	protected void removeLoginVO() {
		loginSessionManager.removeLoginVO();
	}

	/**
	 * 세션을 생성해준다.
	 *
	 * @param DspCoLoginVO
	 */
	protected void setSession(LoginVO loginVO) {
		loginSessionManager.setSession(loginVO);
	}

	/**
	 * 세션을 삭제한다.
	 *
	 * @param request
	 */
	protected void removeSession(HttpServletRequest req) {
		loginSessionManager.removeSession(req);
	}



	/**
	 * PaginationInfo 에 페이지에 대한 정보를 셋팅해 준다.
	 * @see
	 */
	protected PaginationInfo setPaginationInfo(CoDefaultVO searchVO) {

		/** pageing setting */
			PaginationInfo paInfo = new PaginationInfo();
			paInfo.setCurrentPageNo(searchVO.getPageIndex());		//현재 페이지 번호
			paInfo.setRecordCountPerPage(searchVO.getPageUnit());	//한 페이지에 게시되는 게시물 건수
			paInfo.setPageSize(searchVO.getPageSize());				//페이징 리스트의 사이즈
			return paInfo;
	}

	/**
	 * PaginationInfo 에 페이지에 대한 정보를 셋팅해 준다.
	 * pageRecourdCount 에 대한 셋팅
	 * @see
	 */
	protected PaginationInfo setPaginationInfo(CoDefaultVO searchVO, Map<String,String> commandMap) {

		/** pageing setting */
			PaginationInfo paInfo = new PaginationInfo();
			paInfo.setCurrentPageNo(searchVO.getPageIndex());		//현재 페이지 번호
			paInfo.setRecordCountPerPage(searchVO.getPageUnit());	//한 페이지에 게시되는 게시물 건수
			paInfo.setPageSize(searchVO.getPageSize());				//페이징 리스트의 사이즈

			/** pageing setting */
			int firstRecordIndex	 = paInfo.getFirstRecordIndex();
			int recordCountPerPage 	 = paInfo.getRecordCountPerPage();
			commandMap.put("firstIndex", firstRecordIndex+"" );
			commandMap.put("lastIndex", (recordCountPerPage+firstRecordIndex)+"" );

			return paInfo;
	}

	/**
	 * page 정보를 session에 저장해 준다
	 * @see
	 */
	protected void setSearchPageInfo(HttpServletRequest request,Map<String,String> commandMap) {
	    /*검색 조건 넘겨줌*/
	    WebUtils.setSessionAttribute(request ,"getData" , commandMap);
	}

	/**
	 * commandMap의 key value 값을 출력
	 * @param commandMap
	 */
	protected void parameStringLog(Map<String,String> commandMap){

		Iterator iterator =  commandMap.keySet().iterator();
		String key = "";
		while(iterator.hasNext()){
			key = (String)iterator.next();
			logger.info("KEY :::::::::::::::::::: " + key);
			logger.info("VALUE :::::::::::::::::: " + commandMap.get(key));
		}
	}

	/**
	 * commandMap의 key value 값을 출력
	 * @param commandMap
	 */
	protected void parameObjLog(Map<String,Object> commandMap){

		Iterator iterator =  commandMap.keySet().iterator();
		String key = "";
		while(iterator.hasNext()){
			key = (String)iterator.next();
			logger.debug("KEY :::::::::::::::::: " + key);
			logger.debug("VALUE :::::::::::::::::: " + commandMap.get(key));
		}
	}

	/**
	 * HTML 형태를 엑셀로 저장 시킨다
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("excelDown.do")
	protected String excelDown(Map<String,String> commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		model.addAttribute("html",commandMap.get("excelDownData"));
		return "excel.download";
	}

	/**
	 * 통합 로그인 json 데이터를 받아 Map 형태로 파싱하여 리턴
	 * @param pUrl
	 * @param param
	 * @return
	 * @throws Exception
	 */
	protected Map<String, Object> executeUrl(String pUrl, Map<String, String> param) throws Exception {
        URL url = new URL(pUrl);
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);

        OutputStreamWriter osw = new OutputStreamWriter( connection.getOutputStream());

        //프로토콜 셋팅
        Map<String, String> pibox = param;
        //pibox.put("access_key"	, "uOxiqIrynpjeFvCKQeh9pw ");
        //pibox.put("req_svc_gbn"	, "3");
        //pibox.put("action"		, "login");
        //pibox.put("site_sno"		, "10");
        //pibox.put("access_ip"		, "192.168.0.1");

        //파라미터 설정
        Iterator en = pibox.keySet().iterator();
        int pCnt = 0;
        while(en.hasNext())
        {
            String key = (String)en.next();
            if (pCnt > 0)
            {
            	osw.write("&");
            }
            osw.write(key+"=" + URLEncoder.encode(pibox.get(key), "UTF-8"));
            pCnt++;
        }
        osw.close();

        BufferedReader in 	= new BufferedReader( new InputStreamReader(connection.getInputStream(), "UTF-8"));
        ObjectMapper mapper = new ObjectMapper();

        // JSON 문자열을 xml 다루는것과 비슷하게 트리로 만들어서 트래버싱하기(Tree Model)
        JsonNode root  = mapper.readTree(in);
        String rtn_res = root.path("sfw_response").path("box").path("rtn_res").getTextValue();
        String rtn_message = root.path("sfw_response").path("box").path("rtn_message").getTextValue();

        //로그인 실패 성공 분기
        String value 			= "";
        Map<String, Object> map = null;
        if("OK".equalsIgnoreCase(rtn_res))
        {
        	 value 	= root.path("sfw_response").path("rsWp").path("rbox").toString();
        	 map 	= mapper.readValue(value , new TypeReference<Map<String,Object>>(){});

        	 map.put("rtn_res", "OK");
        	 map.put("rtn_message", rtn_message);
        }
        else
        {
        	 map = new HashMap<String,Object>();
	       	 map.put("rtn_res", "ERR");
	       	 map.put("rtn_message", rtn_message);
        }
        parameObjLog(map);
        in.close();
        return map;
    }

	/**
     * 특정 클래스의 메소드 invoke
     * @param obj           Method Invoke(호출)할 오브젝트
     * @param methodName    Method Name
     * @param objList       Parameter Object List
     * @return
     */
	protected Object invoke(Object obj, String methodName, Object[] objList) {
    	//모두 소문자로 변환하여 비교 한다.
    	String toLowMethodNm = methodName.toLowerCase();
    	Method[] methods 	 = obj.getClass().getMethods();

    	//메소드를 검색하고 data 셋팅
        for(int i=0; i<methods.length; i++)
        {
        	String objMethodName = methods[i].getName().toLowerCase();
        	if(toLowMethodNm.equals(objMethodName))
        	{
                try
                {
                    if (methods[i].getReturnType().getName().equals("void"))
                    {
                            methods[i].invoke(obj, objList);
                    }
                    else
                    {
                            return methods[i].invoke(obj, objList);
                    }
                }
                catch(IllegalAccessException lae)
                {
                    System.out.println("LAE : " + lae.getMessage());
                }
                catch(InvocationTargetException ite)
                {
                    System.out.println("ITE : " + ite.getMessage());
                }
             }
        }
        return obj;
    }

	/**
	 * 특정 클래스의 getter 메소드 출력
	 * @param className
	 * @param refClassName
	 * @throws Exception
	 */
	protected void allModelPropPrint(String className,Object refClassName)throws Exception{
		try{
			Class obj 		= Class.forName(className);
			Method[] method = obj.getMethods(); //메소드 이름을 얻어와서 동적으로 호출
			for(int i=0; i<method.length;i++)
			{
				if(method[i].toString().indexOf("get")>1)
				{
					String mFullName 	= method[i].toString();
					String methodName 	= mFullName.substring(mFullName.lastIndexOf(".")+1,mFullName.length()-2);
					Method m 			= obj.getMethod(methodName, null);
					Object result 		= m.invoke(refClassName, null);
					if(result == null)
					{
							logger.debug(methodName+": [null]");
					}
					else
					{
						    logger.debug(methodName+": ["+result.toString()+"]");
					}
				}
			}
		}catch(Exception e){
			throw new Exception("ReflectionUtil.allModelPropPrint\r\n"+e.toString());
		}
	}

	/**
	 * 로그인 유저 데이터 셋팅
	 * @param commandMap
	 */
	protected void userSetting(Map<String,String> commandMap){

		String user_id 		= getLoginVO().getUserId();
		String user_sno		= getLoginVO().getUserSno();
		String user_nm 		= getLoginVO().getUserKname();
		String org_nm 		= getLoginVO().getOrgKname();
		String dept_nm 		= getLoginVO().getDepartNm();
		String tel    		= getLoginVO().getTel();
		String mobile 		= getLoginVO().getMobile();
		String email 		= getLoginVO().getEmail();
		String office_zip_no= getLoginVO().getZipNo();
		String office_addr1 = getLoginVO().getAddr1();
		String office_addr2 = getLoginVO().getAddr2();

		commandMap.put("user_id"		, user_id);
		commandMap.put("user_sno"		, user_sno);
		commandMap.put("user_nm"		, user_nm);
		commandMap.put("org_nm"			, org_nm);
		commandMap.put("dept_nm"		, dept_nm);
		commandMap.put("tel"			, tel);
		commandMap.put("mobile"			, mobile);
		commandMap.put("email"			, email);
		commandMap.put("office_zip_no"	, office_zip_no);
		commandMap.put("office_addr1"	, office_addr1);
		commandMap.put("office_addr2"	, office_addr2);
	}

	/**
	 * email 주소 추출
	 * @param manageList
	 * @param emailKey
	 * @param userNameKey
	 * @throws Exception
	 */
	protected void manageEmail(List manageList,String emailKey,String userNameKey, String userSubject) throws Exception
	{

		Map mapVal = new HashMap();

		if(!manageList.isEmpty())
		{
			for(int i=0;i<manageList.size();i++)
			{
				mapVal = (Map)manageList.get(i);
				String subject 	= CoStringUtil.isNullBlank(userSubject);
				String toMail  	= CoStringUtil.isNullBlank(((String)mapVal.get(userNameKey))).trim();
				String toNm  	= CoStringUtil.isNullBlank(((String)mapVal.get(userNameKey))).trim();
				String cont  	= "표본대여 신청";
				String fromMail = "";
				String fromNm	= "";

				Map<String,String> param = new HashMap<String,String>();
				param.put("method"		, "sendByJson");		//고정값
				param.put("byemail"		, "Y");					//고정값
				param.put("subject"		, subject);				//각 메시지를 구분하는 데이터로 사용
				param.put("to_email"	, toMail);				//수신메일 주소
				param.put("to_nm"		, toNm);				//수신메일
				param.put("mail_cont"	, cont);				//수신메일
				param.put("from_email"	, fromMail);			//발송메일
				param.put("from_nm"		, fromNm);				//발송메일
				manageEmailsSend(param);
			}
		}
	}

	/**
	 * 이메일 전송
	 * @param emails
	 * @param userNm
	 * @param msg
	 * @param subject
	 * @throws Exception
	 */
	protected Boolean manageEmailsSend(Map<String,String> param)throws Exception
	{
		String root_url = propertiesService.getString("svr_url.datacenter.msg");
		URL url = new URL(root_url);
		HttpURLConnection httpConn 	= (HttpURLConnection)url.openConnection();
		httpConn.setDoOutput(true);
		httpConn.setRequestMethod("POST");

		OutputStreamWriter osw 	= new OutputStreamWriter(httpConn.getOutputStream());
		Iterator iterator 		= param .keySet().iterator();
		String key 	= "";
		int pCnt 	= 0;

		while(iterator.hasNext())
		{
			key = (String)iterator.next();
            if (pCnt > 0)
            {
            	osw.write("&");
            }
            osw.write(key+"=" + URLEncoder.encode(param.get(key), "UTF-8"));
            pCnt++;
		}
		osw.close();

		BufferedReader in 	= new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
		ObjectMapper mapper = new ObjectMapper();

        // JSON 문자열을 xml 다루는것과 비슷하게 트리로 만들어서 트래버싱하기(Tree Model)
        JsonNode root  = mapper.readTree(in);
        String rtn_res = root.path("sfw_response").path("box").path("rtn_res").getTextValue();

		//닫기
		in.close();

		 if("OK".equals(rtn_res))
	        {
	        	return true;
	        }
	        else
	        {
	        	return false;
	        }
	}

	/**
	 * 사용자 이름 , 핸드폰 번호 추출
	 * @param manageList
	 * @param mobileKey
	 * @param userNameKey
	 * @throws Exception
	 */
	protected void manageSmsNumber(List manageList,String mobileKey,String userNameKey) throws Exception
	{

		Map mapVal = new HashMap();
		String specimenGb = "";	//표본 유전자원 구분 Y 표본 , A 유전자원
		StringBuffer smsMessage = new StringBuffer();;
		if(!manageList.isEmpty())
		{
			logger.info("=============================================");
			logger.info("manageSmsNumber LIST" + manageList.size());
			logger.info("=============================================");

			StringBuffer numbers = new StringBuffer();
			for(int i=0;i<manageList.size();i++)
			{
				mapVal = (Map)manageList.get(i);
				String name   = ((String)mapVal.get(userNameKey)).trim();
				String number = ((String)mapVal.get(mobileKey)).replaceAll("-", "").trim();
				/*
				smsMessage.append("==== 신청 항목 ====");
				smsMessage.append("\n");
				smsMessage.append("자원번호" + mapVal.get("colId"));
				smsMessage.append("\n");
				smsMessage.append("학명" + mapVal.get("clsFullNm"));
				smsMessage.append("\n");
				smsMessage.append("국명" + mapVal.get("clsKname"));
				smsMessage.append("\n");
				smsMessage.append("보유점수 : " + mapVal.get("indivCnt"));
				smsMessage.append("\n");
				smsMessage.append("신청점수 : " + mapVal.get("lentCnt"));
				*/
				if(i>0){
					numbers.append("|");
				}else{
					specimenGb = ((String)mapVal.get("charge")).trim();
				}
				numbers.append(name);
				numbers.append("^");
				//numbers.append("01089901043");
				numbers.append("01073451339");
				logger.info("=============================================");
				logger.info("numbers " + numbers.toString());
				logger.info("=============================================");

			}

			if("Y".equals(specimenGb)){
				smsMessage.append("표본대여 접수신청\n");
			}
			else if("A".equals(specimenGb)){
				smsMessage.append("유전자원 분양신청\n");
			}

			manageSmsSend(numbers.toString(), smsMessage.toString(), smsMessage.toString());
		}
	}

	/**
	 * sms전송
	 * @param numbers 핸드폰 번호
	 * @param msg	  본문내용
	 * @param subject 제목
	 * @throws Exception
	 */
	protected Boolean manageSmsSend(String numbers, String msg, String subject) throws Exception
	{
		//통합 Login DB 조회
    	String root_url = propertiesService.getString("svr_url.datacenter.msg");
		URL url = new URL(root_url);
		HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
		httpConn.setDoOutput(true);
		httpConn.setRequestMethod("POST");

		OutputStreamWriter osw = new OutputStreamWriter(httpConn.getOutputStream());

		Map<String,String> param = new HashMap<String,String>();
		param.put("method"		, "sendByJson");	//고정값
		param.put("bysms"		, "Y");				//고정값
		param.put("msg"			, msg);				//80 byte
		param.put("receivers"	, numbers);			//이름^전화번호|이름^전화번호
		param.put("subject"		, subject);			//각 메시지를 구분하는 데이터로 사용
		param.put("sender"		, "032-590-7000");	//발송자번호

		Iterator iterator = param.keySet().iterator();
		String key = "";
		int pCnt = 0;
		while(iterator.hasNext())
		{
			key = (String)iterator.next();
            if (pCnt > 0)
            {
            	osw.write("&");
            }

            osw.write(key+"=" + URLEncoder.encode(param.get(key), "UTF-8"));
            pCnt++;
		}
		osw.close();

		BufferedReader in 	= new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
		ObjectMapper mapper = new ObjectMapper();

        // JSON 문자열을 xml 다루는것과 비슷하게 트리로 만들어서 트래버싱하기(Tree Model)
        JsonNode root  = mapper.readTree(in);
        //logger.debug("=========root========================================"+root.toString());
        String rtn_res = root.path("sfw_response").path("box").path("rtn_res").getTextValue();
        //logger.debug("=========rtn_res====================================="+rtn_res);

        //닫기
		in.close();

        if("OK".equals(rtn_res))
        {
        	return true;
        }
        else
        {
        	return false;
        }
	}
}
