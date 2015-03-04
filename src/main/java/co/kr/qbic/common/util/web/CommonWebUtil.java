package co.kr.qbic.common.util.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.util.WebUtils;

import co.kr.qbic.common.util.string.CoStringUtil;

public class CommonWebUtil {

	protected static final Logger LOGGER = Logger.getLogger(CommonWebUtil.class);

	public static String clearXSSMinimum(String value) {
		if (value == null || value.trim().equals("")) {
			return "";
		}

		String returnValue = value;

		returnValue = returnValue.replaceAll("&", "&amp;");
		returnValue = returnValue.replaceAll("<", "&lt;");
		returnValue = returnValue.replaceAll(">", "&gt;");
		returnValue = returnValue.replaceAll("\"", "&#34;");
		returnValue = returnValue.replaceAll("\'", "&#39;");
		return returnValue;
	}

	public static String clearXSSMaximum(String value) {
		String returnValue = value;
		returnValue = clearXSSMinimum(returnValue);

		returnValue = returnValue.replaceAll("%00", null);

		returnValue = returnValue.replaceAll("%", "&#37;");

		// \\. => .

		returnValue = returnValue.replaceAll("\\.\\./", ""); // ../
		returnValue = returnValue.replaceAll("\\.\\.\\\\", ""); // ..\
		returnValue = returnValue.replaceAll("\\./", ""); // ./
		returnValue = returnValue.replaceAll("%2F", "");

		return returnValue;
	}

	public static String filePathBlackList(String value) {
		String returnValue = value;
		if (returnValue == null || returnValue.trim().equals("")) {
			return "";
		}

		returnValue = returnValue.replaceAll("\\.\\./", ""); // ../
		returnValue = returnValue.replaceAll("\\.\\.\\\\", ""); // ..\

		return returnValue;
	}

	/**
	 * 행안부 보안취약점 점검 조치 방안.
	 *
	 * @param value
	 * @return
	 */
	public static String filePathReplaceAll(String value) {
		String returnValue = value;
		if (returnValue == null || returnValue.trim().equals("")) {
			return "";
		}

		returnValue = returnValue.replaceAll("/", "");
		returnValue = returnValue.replaceAll("\\", "");
		returnValue = returnValue.replaceAll("\\.\\.", ""); // ..
		returnValue = returnValue.replaceAll("&", "");

		return returnValue;
	}

	public static String filePathWhiteList(String value) {
		return value; // TODO
	}

	 public static boolean isIPAddress(String str) {
		Pattern ipPattern = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");

		return ipPattern.matcher(str).matches();
    }

    /*
    public static void main(String[] args) {
	String test = null;

	test = "<script language='javascript' encoding=\"utf-8\">q&a</script>";
	System.out.println("clearXSSMinimum() Test");
	System.out.println(test);
	System.out.println("=>");
	System.out.println(clearXSSMinimum(test));
	System.out.println();

	test = "/a/b/c../..\\";
	System.out.println("clearXSSMaximum() Test");
	System.out.println(test);
	System.out.println(" =>");
	System.out.println(clearXSSMaximum(test));
	System.out.println();

	test = "/a/b/c/../../../..\\..\\";
	System.out.println("filePathBlackList() Test");
	System.out.println(test);
	System.out.println("=>");
	System.out.println(filePathBlackList(test));
	System.out.println();

	test = "192.168.0.1";
	System.out.println("isIPAddress() test");
	System.out.println("IP : " + test + " => " + isIPAddress(test));
    }
    //*/

	/**
     * 파일명 얻기
     *
     * @param request
     * @return
     */
	 public static String createFileName(String title) {

			String rtnStr 	= null;
			String fileName = title;

			// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
			String pattern = "yyyyMMdd_HHmmss";

			try {
			    SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
			    Timestamp ts = new Timestamp(System.currentTimeMillis());

			    rtnStr = sdfCurrent.format(ts.getTime());
			} catch (Exception e) {

				LOGGER.info(e.getMessage());
			    //e.printStackTrace();
			    //throw new RuntimeException(e);	// 보안점검 후속조치
			}

	        return new StringBuilder(fileName).append("-").append(rtnStr).append(".xls").toString();
	}


	/**
     * 브라우저 구분 얻기.
     *
     * @param request
     * @return
     */
	 public static String getBrowser(HttpServletRequest request) {
	        String header = request.getHeader("User-Agent");
	        if (header.indexOf("MSIE") > -1) {
	            return "MSIE";
	        } else if (header.indexOf("Chrome") > -1) {
	            return "Chrome";
	        } else if (header.indexOf("Opera") > -1) {
	            return "Opera";
	        }
	        return "Firefox";
	    }


	    /**
	     * Disposition 지정하기.
	     *
	     * @param filename
	     * @param request
	     * @param response
	     * @return
	     * @throws Exception
	     */
		 public static void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
				String browser = getBrowser(request);

				String dispositionPrefix = "attachment; filename=";
				String encodedFilename = null;

				if (CoStringUtil.equals(browser,"MSIE")) {
				    encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
				} else if (CoStringUtil.equals(browser,"Firefox")) {
				    encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
				} else if (CoStringUtil.equals(browser,"Opera")) {
				    encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
				} else if (CoStringUtil.equals(browser,"Chrome")) {
				    StringBuffer sb = new StringBuffer();
				    for (int i = 0; i < filename.length(); i++) {
					char c = filename.charAt(i);
					if (c > '~') {
					    sb.append(URLEncoder.encode("" + c, "UTF-8"));
					} else {
					    sb.append(c);
					}
				    }
				    encodedFilename = sb.toString();
				} else {
				    //throw new RuntimeException("Not supported browser");
				    throw new IOException("Not supported browser");
				}

				response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

				if ("Opera".equals(browser)){
				    response.setContentType("application/octet-stream;charset=UTF-8");
				}
		 }

	/**
	 * request 후 request에 해당하는 액션 전에 할 행동
	 * @param HttpServletResponse response
	 * @param HttpServletRequest request
	 * @param Object handler
	 * @return boolean
	 * @throws Exception
	 */
	public static void setXssRequest(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {

		boolean rtrVal     = false;
		String[] filterStr = null;

        //filterStr = new String[] {"&", "--", "'", "\"", "/", "@", "<", ">", "\\", "$", "%", ";", "|", "+", "..", "SCRIPT", "<IFRAME", "<OBJECT", "<EMBEDED", "<FORM", "JAVASCRIPT"};
		//20121109 개발팀 요청으로 변경  ";", "\"", "\\"
		filterStr = new String[] {"--", "'",  "$", "%", "|", "+", "..",  "SCRIPT", "<IFRAME", "<OBJECT", "<EMBEDED", "<FORM", "JAVASCRIPT"};

        Enumeration keys =  request.getParameterNames();
        while(keys.hasMoreElements())
        {
            String key = (String)keys.nextElement();
            String chkPw = "";

            if(key.length()>8){
                chkPw = key.substring(0, 8);
            }

            String[] value = request.getParameterValues(key);
            if(!key.equals("INIpluginData")&&
               !key.equals("_ETEExt_SEED_")&&
               !key.equals("ozrPath")&&
               !chkPw.equals("_E2E123_")&&
               !key.equals("SAMLResponse")&&
               !key.equals("duplJoinChkInfo")&&
               //!key.equals("forward")&&
                value!=null &&
               !value.equals("")
               )
            {

                for(int i=0; i<filterStr.length; i++)
                {
                    if(value[0].toUpperCase().indexOf(filterStr[i])!=-1)
                    {
                        WebUtils.setSessionAttribute(request ,"cmsXssMessage" , " key = [" + key + "]  value = ["+ value[0] +"]");
                        response.sendRedirect("/co/returnXssAlert.do");
                        rtrVal = false;
                    }
                }
            }
	    }

	}

}