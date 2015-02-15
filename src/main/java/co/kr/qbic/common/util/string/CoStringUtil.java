package co.kr.qbic.common.util.string;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @Class Name  : DspCoStringUtil.java
 * @Description : 문자열 데이터 처리 관련 유틸리티
 * @Modification Information
 * @
 * @  수정일      수정자       수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2012.05.31   김정수       최초생성
 *
 * @author 기술지원팀 김정수
 * @since 2012. 05.31
 * @version 1.0
 *
 *  Copyright (C) 2012 by SAMSUNG SDS co.,Ltd. All right reserved.
 */

@Component
public class CoStringUtil {




    /**
     * 빈 문자열 <code>""</code>.
     */
    public static final String EMPTY = "";

    protected static final Logger LOGGER = Logger.getLogger(CoStringUtil.class);

    /**
     * <p>Padding을 할 수 있는 최대 수치</p>
     */
    // private static final int PAD_LIMIT = 8192;
    /**
     * <p>An array of <code>String</code>s used for padding.</p>
     * <p>Used for efficient space padding. The length of each String expands as needed.</p>
     */
    /*
	private static final String[] PADDING = new String[Character.MAX_VALUE];

	static {
		// space padding is most common, start with 64 chars
		PADDING[32] = "                                                                ";
	}
     */

    /**
     * 문자열이 지정한 길이를 초과했을때 지정한길이에다가 해당 문자열을 붙여주는 메서드.
     * @param source 원본 문자열 배열
     * @param output 더할문자열
     * @param slength 지정길이
     * @return 지정길이로 잘라서 더할분자열 합친 문자열
     */
    public static String cutString(String source, String output, int slength) {
        String returnVal = null;
        if (source != null) {
            if (source.length() > slength) {
                returnVal = source.substring(0, slength) + output;
            } else{
                returnVal = source;
            }
        }
        return returnVal;
    }

    /**
     * 문자열이 지정한 길이를 초과했을때 해당 문자열을 삭제하는 메서드
     * @param source 원본 문자열 배열
     * @param slength 지정길이
     * @return 지정길이로 잘라서 더할분자열 합친 문자열
     */
    public static String cutString(String source, int slength) {
        String result = null;
        if (source != null) {
            if (source.length() > slength) {
                result = source.substring(0, slength);
            } else{
                result = source;
            }
        }
        return result;
    }

    /**
     * <p>
     * String이 비었거나("") 혹은 null 인지 검증한다.
     * </p>
     *
     * <pre>
     *  EfrosStringUtil.isEmpty(null)      = true
     *  EfrosStringUtil.isEmpty("")        = true
     *  EfrosStringUtil.isEmpty(" ")       = false
     *  EfrosStringUtil.isEmpty("bob")     = false
     *  EfrosStringUtil.isEmpty("  bob  ") = false
     * </pre>
     *
     * @param str - 체크 대상 스트링오브젝트이며 null을 허용함
     * @return <code>true</code> - 입력받은 String 이 빈 문자열 또는 null인 경우
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * <p>
     * String이 비었거나("") 혹은 null 인지 검증한다.
     * </p>
     *
     * <pre>
     *  EfrosStringUtil.isNullBlank(null)      = ""
     *  EfrosStringUtil.isNullBlank("")        = ""
     * </pre>
     *
     * @param str - 체크 대상 스트링오브젝트이며 null을 허용함
     * @return <code>true</code> - 입력받은 String 이 빈 문자열 또는 null인 경우
     */
    public static String isNullBlank(String str) {

    	String rstr = "";
    	if(str == null || str.length() == 0 || str.equals("") || str.trim().equals("")){
    		rstr = "";
    	}else{
    		return str;
    	}
        return rstr;
    }


    /**
     * <p>
     * 유통 전송 encoding type 셋팅
     * </p>
     *
     * <pre>
     *  CoStringUtil.isStrURLEncoder("aaa","utf-8")
     * </pre>
     *
     * @param str - 체크 대상 스트링오브젝트
     * @return <code>true</code> - 입력받은 String 이 빈 문자열 또는 null인 경우
     */
    public static String isStrURLEncoder(String str) {

    	String rstr = "";
    	if(str == null || str.length() == 0 || str.equals("") || str.trim().equals("")){
    		rstr = "";
    	}else{

    		try {
				rstr = URLEncoder.encode(str  ,  "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				LOGGER.info(e.getMessage());
			}
    		return str;
    	}
        return rstr;
    }

    /**
     * <p>
     * 유통 전송 encoding type 셋팅
     * </p>
     *
     * <pre>
     *  CoStringUtil.isStrURLEncoder("aaa","utf-8")
     * </pre>
     *
     * @param str - 체크 대상 스트링오브젝트
     * @return <code>true</code> - 입력받은 String 이 빈 문자열 또는 null인 경우
     */
    public static String isStrURLEncoder(String str , String URLEncoderType) {

    	//인코딩 타입이 설정되지 않앗다면...
    	String rstr = "";
    	if(URLEncoderType == null || URLEncoderType.length() == 0 || URLEncoderType.equals("") || URLEncoderType.trim().equals("")){
    		return str;
    	}

    	if(str == null || str.length() == 0 || str.equals("") || str.trim().equals("")){
    		rstr = "";
    	}else{

    		try {
				rstr = URLEncoder.encode(str  ,  "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				LOGGER.info(e.getMessage());
			}
    		return str;
    	}
        return rstr;
    }


    /**
     * <p>기준 문자열에 포함된 모든 대상 문자(char)를 제거한다.</p>
     *
     * <pre>
     * EfrosStringUtil.remove(null, *)       = null
     * EfrosStringUtil.remove("", *)         = ""
     * EfrosStringUtil.remove("queued", 'u') = "qeed"
     * EfrosStringUtil.remove("queued", 'z') = "queued"
     * </pre>
     *
     * @param str  입력받는 기준 문자열
     * @param remove  입력받는 문자열에서 제거할 대상 문자열
     * @return 제거대상 문자열이 제거된 입력문자열. 입력문자열이 null인 경우 출력문자열은 null
     */
    public static String remove(String str, char remove) {
        String rtrStr = "";
        if (isEmpty(str) || str.indexOf(remove) == -1) {
            rtrStr = str;
        }else
        {
            char[] chars = str.toCharArray();
            int pos = 0;
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] != remove) {
                    chars[pos++] = chars[i];
                }
            }
            rtrStr = new String(chars, 0, pos);
        }
        return rtrStr;
    }

    /**
     * <p>문자열 내부의 콤마 character(,)를 모두 제거한다.</p>
     *
     * <pre>
     * EfrosStringUtil.removeCommaChar(null)       = null
     * EfrosStringUtil.removeCommaChar("")         = ""
     * EfrosStringUtil.removeCommaChar("asdfg,qweqe") = "asdfgqweqe"
     * </pre>
     *
     * @param str 입력받는 기준 문자열
     * @return " , "가 제거된 입력문자열
     *  입력문자열이 null인 경우 출력문자열은 null
     */
    public static String removeCommaChar(String str) {
        return remove(str, ',');
    }

    /**
     * <p>문자열 내부의 마이너스 character(-)를 모두 제거한다.</p>
     *
     * <pre>
     * EfrosStringUtil.removeMinusChar(null)       = null
     * EfrosStringUtil.removeMinusChar("")         = ""
     * EfrosStringUtil.removeMinusChar("a-sdfg-qweqe") = "asdfgqweqe"
     * </pre>
     *
     * @param str  입력받는 기준 문자열
     * @return " - "가 제거된 입력문자열
     *  입력문자열이 null인 경우 출력문자열은 null
     */
    public static String removeMinusChar(String str) {
        return remove(str, '-');
    }


    /**
     * 원본 문자열의 포함된 특정 문자열을 새로운 문자열로 변환하는 메서드
     * @param source 원본 문자열
     * @param subject 원본 문자열에 포함된 특정 문자열
     * @param object 변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열
     */
    public static String replace(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        String srcStr  = source;

        while (srcStr.indexOf(subject) >= 0) {
            preStr = srcStr.substring(0, srcStr.indexOf(subject));
            nextStr = srcStr.substring(srcStr.indexOf(subject) + subject.length(), srcStr.length());
            srcStr = nextStr;
            rtnStr.append(preStr).append(object);
        }
        rtnStr.append(nextStr);
        return rtnStr.toString();
    }

    /**
     * 원본 문자열의 포함된 특정 문자열 첫번째 한개만 새로운 문자열로 변환하는 메서드
     * @param source 원본 문자열
     * @param subject 원본 문자열에 포함된 특정 문자열
     * @param object 변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열 / source 특정문자열이 없는 경우 원본 문자열
     */
    public static String replaceOnce(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        String rtrStr = "";
        if (source.indexOf(subject) >= 0) {
            preStr = source.substring(0, source.indexOf(subject));
            nextStr = source.substring(source.indexOf(subject) + subject.length(), source.length());
            rtnStr.append(preStr).append(object).append(nextStr);
            rtrStr = rtnStr.toString();
        } else {
            rtrStr = source;
        }
        return rtrStr;
    }

    /**
     * <code>subject</code>에 포함된 각각의 문자를 object로 변환한다.
     *
     * @param source 원본 문자열
     * @param subject 원본 문자열에 포함된 특정 문자열
     * @param object 변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열
     */
    public static String replaceChar(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        String srcStr  = source;

        char chA;

        for (int i = 0; i < subject.length(); i++) {
            chA = subject.charAt(i);

            if (srcStr.indexOf(chA) >= 0) {
                preStr = srcStr.substring(0, srcStr.indexOf(chA));
                nextStr = srcStr.substring(srcStr.indexOf(chA) + 1, srcStr.length());
                srcStr = rtnStr.append(preStr).append(object).append(nextStr).toString();
            }
        }

        return srcStr;
    }

    /**
     * <p><code>str</code> 중 <code>searchStr</code>의 시작(index) 위치를 반환.</p>
     *
     * <p>입력값 중 <code>null</code>이 있을 경우 <code>-1</code>을 반환.</p>
     *
     * <pre>
     * EfrosStringUtil.indexOf(null, *)          = -1
     * EfrosStringUtil.indexOf(*, null)          = -1
     * EfrosStringUtil.indexOf("", "")           = 0
     * EfrosStringUtil.indexOf("aabaabaa", "a")  = 0
     * EfrosStringUtil.indexOf("aabaabaa", "b")  = 2
     * EfrosStringUtil.indexOf("aabaabaa", "ab") = 1
     * EfrosStringUtil.indexOf("aabaabaa", "")   = 0
     * </pre>
     *
     * @param str  검색 문자열
     * @param searchStr  검색 대상문자열
     * @return 검색 문자열 중 검색 대상문자열이 있는 시작 위치 검색대상 문자열이 없거나 null인 경우 -1
     */
    public static int indexOf(String str, String searchStr) {
        int rtrVal;
        if (str == null || searchStr == null) {
            rtrVal = -1;
        }else{
            rtrVal = str.indexOf(searchStr);
        }

        return rtrVal;
    }


    /**
     * <p>오라클의 decode 함수와 동일한 기능을 가진 메서드이다.
     * <code>sourStr</code>과 <code>compareStr</code>의 값이 같으면
     * <code>returStr</code>을 반환하며, 다르면  <code>defaultStr</code>을 반환한다.
     * </p>
     *
     * <pre>
     * EfrosStringUtil.decode(null, null, "foo", "bar")= "foo"
     * EfrosStringUtil.decode("", null, "foo", "bar") = "bar"
     * EfrosStringUtil.decode(null, "", "foo", "bar") = "bar"
     * EfrosStringUtil.decode("하이", "하이", null, "bar") = null
     * EfrosStringUtil.decode("하이", "하이  ", "foo", null) = null
     * EfrosStringUtil.decode("하이", "하이", "foo", "bar") = "foo"
     * EfrosStringUtil.decode("하이", "하이  ", "foo", "bar") = "bar"
     * </pre>
     *
     * @param sourceStr 비교할 문자열
     * @param compareStr 비교 대상 문자열
     * @param returnStr sourceStr와 compareStr의 값이 같을 때 반환할 문자열
     * @param defaultStr sourceStr와 compareStr의 값이 다를 때 반환할 문자열
     * @return sourceStr과 compareStr의 값이 동일(equal)할 때 returnStr을 반환하며,
     *         <br/>다르면 defaultStr을 반환한다.
     */
    public static String decode(String sourceStr, String compareStr, String returnStr, String defaultStr) {
        String rtrStr = "";
        if (sourceStr == null && compareStr == null) {
            rtrStr = returnStr;
        }else if (sourceStr == null && compareStr != null) {
            rtrStr = defaultStr;
        }else if (sourceStr.trim().equals(compareStr)) {
            rtrStr = returnStr;
        }else{
            rtrStr = defaultStr;
        }
        return rtrStr;
    }

    /**
     * <p>오라클의 decode 함수와 동일한 기능을 가진 메서드이다.
     * <code>sourStr</code>과 <code>compareStr</code>의 값이 같으면
     * <code>returStr</code>을 반환하며, 다르면  <code>sourceStr</code>을 반환한다.
     * </p>
     *
     * <pre>
     * EfrosStringUtil.decode(null, null, "foo") = "foo"
     * EfrosStringUtil.decode("", null, "foo") = ""
     * EfrosStringUtil.decode(null, "", "foo") = null
     * EfrosStringUtil.decode("하이", "하이", "foo") = "foo"
     * EfrosStringUtil.decode("하이", "하이 ", "foo") = "하이"
     * EfrosStringUtil.decode("하이", "바이", "foo") = "하이"
     * </pre>
     *
     * @param sourceStr 비교할 문자열
     * @param compareStr 비교 대상 문자열
     * @param returnStr sourceStr와 compareStr의 값이 같을 때 반환할 문자열
     * @return sourceStr과 compareStr의 값이 동일(equal)할 때 returnStr을 반환하며,
     *         <br/>다르면 sourceStr을 반환한다.
     */
    public static String decode(String sourceStr, String compareStr, String returnStr) {
        return decode(sourceStr, compareStr, returnStr, sourceStr);
    }

    /**
     * 객체가 null인지 확인하고 null인 경우 "" 로 바꾸는 메서드
     * @param object 원본 객체
     * @return resultVal 문자열
     */
    public static String isNullToString(Object object) {
        String string = "";

        if (object != null) {
            string = object.toString().trim();
        }

        return string;
    }

    /**
     *<pre>
     * 인자로 받은 String이 null일 경우 &quot;&quot;로 리턴한다.
     * &#064;param src null값일 가능성이 있는 String 값.
     * &#064;return 만약 String이 null 값일 경우 &quot;&quot;로 바꾼 String 값.
     *</pre>
     */
    public static String nullConvert(Object src) {
	//if (src != null && src.getClass().getName().equals("java.math.BigDecimal")) {
	//if (src != null && src instanceof java.math.BigDecimal) {
        String rtrStr = "";
        if (src instanceof java.math.BigDecimal) {
            rtrStr = ((BigDecimal)src).toString();
    	}else if (src == null || src.toString().equals("null")) {
    	    rtrStr = "";
    	} else {
    	    rtrStr = ((String)src).trim();
    	}
        return rtrStr;
    }

    /**
     *<pre>
     * 인자로 받은 String이 null일 경우 &quot;&quot;로 리턴한다.
     * &#064;param src null값일 가능성이 있는 String 값.
     * &#064;return 만약 String이 null 값일 경우 &quot;&quot;로 바꾼 String 값.
     *</pre>
     */
    public static String nullConvert(String src) {
        String rtrStr;
    	if (src == null || src.equals("null") || "".equals(src) || " ".equals(src)) {
    	    rtrStr = "";
    	} else {
    	    rtrStr = src.trim();
    	}
    	return rtrStr;
    }

    /**
     *<pre>
     * 인자로 받은 String이 null일 경우 &quot;0&quot;로 리턴한다.
     * &#064;param src null값일 가능성이 있는 String 값.
     * &#064;return 만약 String이 null 값일 경우 &quot;0&quot;로 바꾼 String 값.
     *</pre>
     */
    public static int zeroConvert(Object src) {
        int rtrVal;
    	if (src == null || src.toString().equals("null")) {
    	    rtrVal = 0;
    	} else {
    	    rtrVal = Integer.parseInt(((String)src).trim());
    	}
    	return rtrVal;
    }

    /**
     *<pre>
     * 인자로 받은 String이 null일 경우 &quot;&quot;로 리턴한다.
     * &#064;param src null값일 가능성이 있는 String 값.
     * &#064;return 만약 String이 null 값일 경우 &quot;&quot;로 바꾼 String 값.
     *</pre>
     */
    public static int zeroConvert(String src) {
        int rtrVal;
    	if (src == null || src.equals("null") || "".equals(src) || " ".equals(src)) {
    	    rtrVal = 0;
    	} else {
    	    rtrVal = Integer.parseInt(src.trim());
    	}
    	return rtrVal;
    }

    /**
     * <p>문자열에서 {@link Character#isWhitespace(char)}에 정의된
     * 모든 공백문자를 제거한다.</p>
     *
     * <pre>
     * EfrosStringUtil.removeWhitespace(null)         = null
     * EfrosStringUtil.removeWhitespace("")           = ""
     * EfrosStringUtil.removeWhitespace("abc")        = "abc"
     * EfrosStringUtil.removeWhitespace("   ab  c  ") = "abc"
     * </pre>
     *
     * @param str  공백문자가 제거도어야 할 문자열
     * @return the 공백문자가 제거된 문자열, null이 입력되면 <code>null</code>이 리턴
     */
    public static String removeWhitespace(String str) {
        String rtrStr;
        if (isEmpty(str)) {
            rtrStr = str;
        }else{
            int sz = str.length();
            char[] chs = new char[sz];
            int count = 0;
            for (int i = 0; i < sz; i++) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    chs[count++] = str.charAt(i);
                }
            }
            if (count == sz) {
                rtrStr = str;
            }else
            {
                rtrStr = new String(chs, 0, count);
            }
        }
        return rtrStr;
    }

    /**
     * Html 코드가 들어간 문서를 표시할때 태그에 손상없이 보이기 위한 메서드
     *
     * @param strString
     * @return HTML 태그를 치환한 문자열
     */
    public static String checkHtmlView(String strString) {
    	String strNew = "";

    	try {
    	    StringBuffer strTxt = new StringBuffer("");

    	    char chrBuff;
    	    int len = strString.length();

    	    for (int i = 0; i < len; i++) {
    		chrBuff = strString.charAt(i);

    		switch (chrBuff) {
    		case '<':
    		    strTxt.append("&lt;");
    		    break;
    		case '>':
    		    strTxt.append("&gt;");
    		    break;
    		case '"':
    		    strTxt.append("&quot;");
    		    break;
    		case 10:
    		    strTxt.append("<br>");
    		    break;
    		case ' ':
    		    strTxt.append("&nbsp;");
    		    break;
    		//case '&' :
    		    //strTxt.append("&amp;");
    		    //break;
    		default:
    		    strTxt.append(chrBuff);
    		}
    	    }

    	    strNew = strTxt.toString();

    	} catch (Exception ex) {
    	    LOGGER.error(ex);
    	}

    	return strNew;
    }


    /**
     * 문자열을 지정한 분리자에 의해 배열로 리턴하는 메서드.
     * @param source 원본 문자열
     * @param separator 분리자
     * @return result 분리자로 나뉘어진 문자열 배열
     */
    public static String[] split(String source, String separator) throws NullPointerException {
        String[] returnVal = null;
        int cnt = 1;

        int index = source.indexOf(separator);
        int index0 = 0;
        while (index >= 0) {
            cnt++;
            index = source.indexOf(separator, index + 1);
        }
        returnVal = new String[cnt];
        cnt = 0;
        index = source.indexOf(separator);
        while (index >= 0) {
            returnVal[cnt] = source.substring(index0, index);
            index0 = index + 1;
            index = source.indexOf(separator, index + 1);
            cnt++;
        }
        returnVal[cnt] = source.substring(index0);

        return returnVal;
    }

    /**
     * <p>{@link String#toLowerCase()}를 이용하여 소문자로 변환한다.</p>
     *
     * <pre>
     * EfrosStringUtil.lowerCase(null)  = null
     * EfrosStringUtil.lowerCase("")    = ""
     * EfrosStringUtil.lowerCase("aBc") = "abc"
     * </pre>
     *
     * @param str 소문자로 변환되어야 할 문자열
     * @return 소문자로 변환된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String lowerCase(String str) {
        String rtrStr;
        if (str != null) {
            rtrStr =  str.toLowerCase();
        }else
        {
            rtrStr  = str;
        }

        return rtrStr;
    }

    /**
     * <p>{@link String#toUpperCase()}를 이용하여 대문자로 변환한다.</p>
     *
     * <pre>
     * EfrosStringUtil.upperCase(null)  = null
     * EfrosStringUtil.upperCase("")    = ""
     * EfrosStringUtil.upperCase("aBc") = "ABC"
     * </pre>
     *
     * @param str 대문자로 변환되어야 할 문자열
     * @return 대문자로 변환된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String upperCase(String str) {
        String rtrStr;
        if (str != null) {
            rtrStr =  str.toUpperCase();
        }else
        {
            rtrStr  = str;
        }

        return rtrStr;

    }

    /**
     * <p>입력된 String의 앞쪽에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.</p>
     *
     * <pre>
     * EfrosStringUtil.stripStart(null, *)          = null
     * EfrosStringUtil.stripStart("", *)            = ""
     * EfrosStringUtil.stripStart("abc", "")        = "abc"
     * EfrosStringUtil.stripStart("abc", null)      = "abc"
     * EfrosStringUtil.stripStart("  abc", null)    = "abc"
     * EfrosStringUtil.stripStart("abc  ", null)    = "abc  "
     * EfrosStringUtil.stripStart(" abc ", null)    = "abc "
     * EfrosStringUtil.stripStart("yxabc  ", "xyz") = "abc  "
     * </pre>
     *
     * @param str 지정된 문자가 제거되어야 할 문자열
     * @param stripChars 제거대상 문자열
     * @return 지정된 문자가 제거된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String stripStart(String str, String stripChars) {

    	int strLen;
        String rtrStr;
        strLen = str.length();

        if (str == null || strLen == 0) {
            rtrStr = str;
        }
        else{
            int start = 0;
            if (stripChars == null) {
                while ((start != strLen) && Character.isWhitespace(str.charAt(start))) {
                    start++;
                }
                rtrStr = str.substring(start);
            } else if (stripChars.length() == 0) {
                rtrStr = str;
            } else {
                while ((start != strLen) && (stripChars.indexOf(str.charAt(start)) != -1)) {
                    start++;
                }
                rtrStr = str.substring(start);
            }
        }
        return rtrStr;
    }


    /**
     * <p>입력된 String의 뒤쪽에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.</p>
     *
     * <pre>
     * EfrosStringUtil.stripEnd(null, *)          = null
     * EfrosStringUtil.stripEnd("", *)            = ""
     * EfrosStringUtil.stripEnd("abc", "")        = "abc"
     * EfrosStringUtil.stripEnd("abc", null)      = "abc"
     * EfrosStringUtil.stripEnd("  abc", null)    = "  abc"
     * EfrosStringUtil.stripEnd("abc  ", null)    = "abc"
     * EfrosStringUtil.stripEnd(" abc ", null)    = " abc"
     * EfrosStringUtil.stripEnd("  abcyx", "xyz") = "  abc"
     * </pre>
     *
     * @param str 지정된 문자가 제거되어야 할 문자열
     * @param stripChars 제거대상 문자열
     * @return 지정된 문자가 제거된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String stripEnd(String str, String stripChars) {
        int end;
        end = str.length();
        String rtrStr;
        if (str == null || end == 0) {
            rtrStr = str;
        }else{
            if (stripChars == null) {
                while ((end != 0) && Character.isWhitespace(str.charAt(end - 1))) {
                    end--;
                }
                rtrStr = str.substring(0, end);
            } else if (stripChars.length() == 0) {
                rtrStr = str;
            } else {
                while ((end != 0) && (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
                    end--;
                }
                rtrStr = str.substring(0, end);
            }
        }
        return rtrStr;
    }

    /**
     * <p>입력된 String의 앞, 뒤에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.</p>
     *
     * <pre>
     * EfrosStringUtil.strip(null, *)          = null
     * EfrosStringUtil.strip("", *)            = ""
     * EfrosStringUtil.strip("abc", null)      = "abc"
     * EfrosStringUtil.strip("  abc", null)    = "abc"
     * EfrosStringUtil.strip("abc  ", null)    = "abc"
     * EfrosStringUtil.strip(" abc ", null)    = "abc"
     * EfrosStringUtil.strip("  abcyx", "xyz") = "  abc"
     * </pre>
     *
     * @param str 지정된 문자가 제거되어야 할 문자열
     * @param stripChars 제거대상 문자열
     * @return 지정된 문자가 제거된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String strip(String str, String stripChars) {
        String rtrStr;
        if (isEmpty(str)) {
            rtrStr = str;
    	}else{
   	       String srcStr = str;
	       srcStr = stripStart(srcStr, stripChars);
   	       rtrStr = stripEnd(srcStr, stripChars);
    	}
        return rtrStr;
    }

    /**
     * 문자열을 지정한 분리자에 의해 지정된 길이의 배열로 리턴하는 메서드.
     * @param source 원본 문자열
     * @param separator 분리자
     * @param arraylength 배열 길이
     * @return 분리자로 나뉘어진 문자열 배열
     */
    public static String[] split(String source, String separator, int arraylength) throws NullPointerException {
        String[] returnVal = new String[arraylength];
        int cnt = 0;
        int index0 = 0;
        int index = source.indexOf(separator);
        while (index >= 0 && cnt < (arraylength - 1)) {
            returnVal[cnt] = source.substring(index0, index);
            index0 = index + 1;
            index = source.indexOf(separator, index + 1);
            cnt++;
        }
        returnVal[cnt] = source.substring(index0);
        if (cnt < (arraylength - 1)) {
            for (int i = cnt + 1; i < arraylength; i++) {
                returnVal[i] = "";
            }
        }

        return returnVal;
    }

    /**
     * 문자열 A에서 Z사이의 랜덤 문자열을 구하는 기능을 제공 시작문자열과 종료문자열 사이의 랜덤 문자열을 구하는 기능
     *
     * @param startChr
     *            - 첫 문자
     * @param endChr
     *            - 마지막문자
     * @return 랜덤문자
     * @exception MyException
     * @see
     */
    public static String getRandomStr(char startChr, char endChr) {

	int randomInt;
	String randomStr = null;

	// 시작문자 및 종료문자를 아스키숫자로 변환한다.
	int startInt = Integer.valueOf(startChr);
	int endInt = Integer.valueOf(endChr);

	// 시작문자열이 종료문자열보가 클경우
	if (startInt > endInt) {
	    throw new IllegalArgumentException("Start String: " + startChr + " End String: " + endChr);
	}

	try {
	    // 랜덤 객체 생성
	    SecureRandom rnd = new SecureRandom();

	    do {
		// 시작문자 및 종료문자 중에서 랜덤 숫자를 발생시킨다.
		randomInt = rnd.nextInt(endInt + 1);
	    } while (randomInt < startInt); // 입력받은 문자 'A'(65)보다 작으면 다시 랜덤 숫자 발생.

	    // 랜덤 숫자를 문자로 변환 후 스트링으로 다시 변환
	    randomStr = Character.toString((char)randomInt);

	} catch (Exception e) {
		Logger.getLogger(CoStringUtil.class).debug(e);//e.printStackTrace();
	}

	// 랜덤문자열를 리턴
	return randomStr;
    }

    /**
     * 문자열을 다양한 문자셋(EUC-KR[KSC5601],UTF-8..)을 사용하여 인코딩하는 기능 역으로 디코딩하여 원래의 문자열을
     * 복원하는 기능을 제공함 String temp = new String(문자열.getBytes("바꾸기전 인코딩"),"바꿀 인코딩");
     * String temp = new String(문자열.getBytes("8859_1"),"KSC5601"); => UTF-8 에서
     * EUC-KR
     *
     * @param srcString
     *            - 문자열
     * @param srcCharsetNm
     *            - 원래 CharsetNm
     * @param charsetNm
     *            - CharsetNm
     * @return 인(디)코딩 문자열
     * @exception MyException
     * @see
     */
    public static String getEncdDcd(String srcString, String srcCharsetNm, String cnvrCharsetNm) {

	String rtnStr = null;

	if (srcString != null)
	{
    	try {
    	    rtnStr = new String(srcString.getBytes(srcCharsetNm), cnvrCharsetNm);
    	} catch (UnsupportedEncodingException e) {
    	    LOGGER.error(e);
    	}
	}
	return rtnStr;
    }

/**
     * 특수문자를 웹 브라우저에서 정상적으로 보이기 위해 특수문자를 처리('<' -> & lT)하는 기능이다
     * @param 	srcString 		- '<'
     * @return 	변환문자열('<' -> "&lt"
     * @exception MyException
     * @see
     */
    public static String getSpclStrCnvr(String srcString) {

	String rtnStr = null;

	try {
	    StringBuffer strTxt = new StringBuffer("");

	    char chrBuff;
	    int len = srcString.length();

	    for (int i = 0; i < len; i++) {
		chrBuff = srcString.charAt(i);

		switch (chrBuff) {
		case '<':
		    strTxt.append("&lt;");
		    break;
		case '>':
		    strTxt.append("&gt;");
		    break;
		case '&':
		    strTxt.append("&amp;");
		    break;
		default:
		    strTxt.append(chrBuff);
		}
	    }

	    rtnStr = strTxt.toString();

	} catch (Exception e) {
		Logger.getLogger(CoStringUtil.class).debug(e);//e.printStackTrace();
	}

	return rtnStr;
    }

    /**
     * 응용어플리케이션에서 고유값을 사용하기 위해 시스템에서17자리의TIMESTAMP값을 구하는 기능
     *
     * @param
     * @return Timestamp 값
     * @exception MyException
     * @see
     */
    public static String getTimeStamp() {

	String rtnStr = null;

	// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
	String pattern = "yyyyMMddhhmmssSSS";

	try {
	    SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
	    Timestamp ts = new Timestamp(System.currentTimeMillis());

	    rtnStr = sdfCurrent.format(ts.getTime());
	} catch (Exception e) {
		Logger.getLogger(CoStringUtil.class).debug(e);//e.printStackTrace();
	}

	return rtnStr;
    }

    /**
     * html의 특수문자를 표현하기 위해
     *
     * @param srcString
     * @return String
     * @exception Exception
     * @see
     */
    public static String getHtmlStrCnvr(String srcString) {

    	String tmpString = srcString;

		try
		{
			/*
		    tmpString = tmpString.replaceAll("&lt;", "<");
			tmpString = tmpString.replaceAll("&gt;", ">");
			tmpString = tmpString.replaceAll("&amp;", "&");
			tmpString = tmpString.replaceAll("&nbsp;", " ");
			tmpString = tmpString.replaceAll("&apos;", "\'");
			tmpString = tmpString.replaceAll("&quot;", "\"");
			*/
		    //20121008 개발팀 요청으로 변경
		    tmpString = tmpString.replaceAll("&#60;","<");
		    tmpString = tmpString.replaceAll("&#62;",">");
		    tmpString = tmpString.replaceAll("&#64;","@");
		    tmpString = tmpString.replaceAll("&#47;","/");
		    tmpString = tmpString.replaceAll("&#38;","&");
		}
		catch (Exception ex)
		{
			Logger.getLogger(CoStringUtil.class).debug(ex);//ex.printStackTrace();
		}

		return  tmpString;

	}

    /**
     * <p>날짜 형식의 문자열 내부에 마이너스 character(-)를 추가한다.</p>
     *
     * <pre>
     *   EfrosStringUtil.addMinusChar("20100901") = "2010-09-01"
     * </pre>
     *
     * @param date  입력받는 문자열
     * @return " - "가 추가된 입력문자열
     */
	public static String addMinusChar(String date) {
		String rtrVal;
	    if(date.length() == 8){
	        rtrVal = date.substring(0,4).concat("-").concat(date.substring(4,6)).concat("-").concat(date.substring(6,8));
	    }else{
		    rtrVal= date;
	    }

	    return rtrVal;
	}

	 /**
     * <p>String 의 byte 길이를 반환한다.</p>
     *
     * <pre>
     *   EfrosStringUtil.getBytesLength("20100901")
     * </pre>
     *
     * @param str  입력받는 문자열
     * @return str 의 바이트 수 길이
     */
	public static int getBytesLength(String str){
		int strLength = 0;
		char[] tempChar = new char[str.length()];
		int i;

		for(i=0;i<tempChar.length;i++){
			tempChar[i] = str.charAt(i);

			if(tempChar[i] < 128){
				strLength++;
			}else{
				strLength += 2;
			}
		}

		return strLength;
	}

	/**
     * <p>SMS 의 contents가 80 bytes 가 넘는지 체크</p>
     *
     * <pre>
     *   EfrosStringUtil.checkSmsContentLength("발송되었습니다.")
     * </pre>
     *
     * @param str  입력받는 문자열
     * @return str 의 바이트 수 길이
     */
	public static boolean checkSmsContentLength(String contents){
		int byteLength = getBytesLength(contents);
		boolean rtrVal = false;
		if(byteLength < 80){
		    rtrVal = true;
		}
		return rtrVal;
	}


	   /**
     * <p>우측으로 Padding (글자수가 아닌 Byte 수로 계산한다.</p>
     *
     * <pre>
     *   EfrosStringUtil.rightPadding("tiger", 10, "*")
     *   returns tiger*****
     * </pre>
     *
     * @param str  입력받는 문자열
     * @param size size
     * @param fstr 붙일 String
	 * @throws UnsupportedEncodingException
     * @returns 수정된 문자열
     */
    public static String rightPadding(String str, int size, String fStr, String charset) throws UnsupportedEncodingException{

        byte[] b = str.getBytes(charset);
        int len = b.length;
        StringBuilder sb = new StringBuilder(str);

        int tmp = size - len;

        for (int i=0; i < tmp ; i++) {
            sb.append(fStr);
        }
        return sb.toString();
    }


    /**
     * Xss방지 변환
     *
     * @param srcString
     * @return String
     * @exception Exception
     * @see
     */
    public static String chgXssStr(String srcString) {

        String rslt = srcString;

        try
        {
            rslt = rslt.replaceAll("&","&#38;");
            rslt = rslt.replaceAll("/","&#47;");
            rslt = rslt.replaceAll("<","&#60;");
            rslt = rslt.replaceAll(">","&#62;");
            rslt = rslt.replaceAll("@","&#64;");
        }
        catch (Exception ex)
        {
            Logger.getLogger(CoStringUtil.class).debug(ex);//ex.printStackTrace();
        }
        return  rslt;

    }

    /**
     * Xss방지 변환
     *
     * @param srcString
     * @return String
     * @exception Exception
     * @see
     */
    public static String chgXssFilterHtmlStr(String srcString) {

    	if(srcString == null || srcString.equals("")){
    		return "";
    	}

        String rslt = srcString;

        try
        {
        	rslt = rslt.replaceAll("&amp;"   , "&");
        	rslt = rslt.replaceAll("&lt;"    , "<");
        	rslt = rslt.replaceAll("&gt;"    , ">");
        	rslt = rslt.replaceAll("&nbsp;"  ," ");
        	rslt = rslt.replaceAll("&apos;"  , "\'");
        	rslt = rslt.replaceAll("&quot;"  , "\"");
        }
        catch (Exception ex)
        {
            Logger.getLogger(CoStringUtil.class).debug(ex);//ex.printStackTrace();
        }
        return  rslt;

    }

    /**
     * Xss방지 변환
     *
     * @param srcString
     * @return String
     * @exception Exception
     * @see
     */
    public static String chgXssFilterStr(String srcString) {

    	if(srcString == null || srcString.equals("")){
    		return "";
    	}

        String rslt = srcString;

        try
        {
        	rslt = rslt.replaceAll("&"  , "&amp;");
        	rslt = rslt.replaceAll("<"  , "&lt;");
        	rslt = rslt.replaceAll(">"  , "&gt;");
        	//rslt = rslt.replaceAll(" "  , "&nbsp;");
        	rslt = rslt.replaceAll("\'" , "&apos;");
        	rslt = rslt.replaceAll("\"" , "&quot;");
        }
        catch (Exception ex)
        {
            Logger.getLogger(CoStringUtil.class).debug(ex);//ex.printStackTrace();
        }
        return  rslt;

    }

    /**
     * 문자열 비교
     *
     * @param str
     * @param str2
     * @return String
     * @exception Exception
     * @see
     */
    public static boolean equals(String str , String str2) {

    	//같으면 true
    	if(str != null && str.equals(str2)){
    		return true;

    	//아니면 false
    	}else{
    		return false;
    	}
    }

    /**
     * nl2br html br 처리
     *
     * @param str
     * @exception Exception
     * @see
     */
    public static String nl2br(String str) {

    	String rslt = str;

    	rslt = rslt.replaceAll("\r\n", "<br />");
    	rslt = rslt.replaceAll("\r", "<br />");
    	rslt = rslt.replaceAll("\n", "<br />");

 	   return rslt;
 	  }

    /**
     * 경로를 포함한 파일경로에서 파일명추출하기
     *
     * @param filepath	:	ex) "/home/path1/path2/path3/text.txt"
     * @return String   :   ex) "text.txt"
     * @exception Exception
     * @see
     */
	public static String getFileName(String fileFullpath) {

		if(isEmpty(fileFullpath)){
			return "";
		}
		//String filepath = "/home/path1/path2/path3/text.txt";
		String filepath 			= fileFullpath.replace("\\", "/");
			   filepath 			= filepath.replace("\'", "/");


	    int file 			= filepath.lastIndexOf("/");
	    int ext	 			= filepath.lastIndexOf(".");
	    int length 			= filepath.length();

	    if(ext <= -1 || length <= -1){
	    	return fileFullpath;
	    }

	    String filename 	= filepath.substring(file+1,ext);
	    String extname  	= filepath.substring(ext+1 ,length);
	    String fullfilename = filename+"."+extname;


	    return fullfilename;
	}


    /**
     * 확장자를 체크해서 이미지 명 뽑아 오기.
     *
     * @param filepath	:	ex) "/home/path1/path2/path3/text.txt"
     * @return String   :   ex) "txt"
     * @exception Exception
     * @see
     */
	public static String getFileNameExt(String fileFullpath) {

		if(isEmpty(fileFullpath)){
			return "";
		}
		//String filepath = "/home/path1/path2/path3/text.txt";
		String filepath 			= fileFullpath.replace("\\", "/");
			   filepath 			= filepath.replace("\'", "/");


	    int ext	 			= filepath.lastIndexOf(".");
	    int length 			= filepath.length();

	    if(ext <= -1 || length <= -1){
	    	return fileFullpath;
	    }

	    String extname = filepath.substring(ext+1 ,length);

	    //확장자 체크
	    extname 	   = extname.toLowerCase();

	    return extname;
	}

    /**
     * 드라이버&파일명을 제외한 파일경로 추출하기
     *
     * @param filepath	:	ex) "V:/KCA/KC1/2013/04/18/01/VDSKCAKC1201304180000.mxf"
     * @return String	:	ex) "/KCA/KC1/2013/04/11/01/"
     * @exception Exception
     * @see
     */
	public static String getFilePath(String fileFullpath) {

		if(isEmpty(fileFullpath)){
			return "";
		}

		String filepath 		= fileFullpath.replace("\\", "/");
			   filepath 		= filepath.replace("\'", "/");

	    int filept				= filepath.lastIndexOf(":");
	    int filept2				= filepath.lastIndexOf("/");

	    if(filept <= -1 || filept2 <= -1){
	    	return fileFullpath;
	    }

	    return filepath.substring(filept+1 , filept2+1);
	    //return path;
	}


    /**
     * 드라이버&파일명을 제외한 파일경로 추출하기
     *
     * @param fileFullpath			:	ex) "V:/KCA/KC1/2013/04/18/01/VDSKCAKC1201304180000.mxf"
     * @param clipId				:	ex) "VDSKCAKC1201304180000"
     * @param SocFileNm				:	ex) "BolivianFolkloricDance.mpg"
     * @param serverTargetBasicPath	:	ex) "W:/TRIngest"
     *
     *
     * @return String		:	ex) "W:/TRIngest/KCA/KCA/2013/07/12/01/PGMKCAKCA201307120001_BolivianFolkloricDance.mpg"
     * @exception Exception
     * @see
     */
	public static String getFilePath(String fileFullpath , String clipId , String SocFileNm, String serverTargetBasicPath) {
		//server
		String strFilePath = getFilePath(fileFullpath);	//	"/KCA/KC1/2013/04/18/01/"
		String strFullPath = serverTargetBasicPath + "" + strFilePath + clipId + "_" + SocFileNm;

		return strFullPath;
	}

    /**
     * 드라이버&파일명을 제외한 파일경로 추출하기
     *
     * @param filepath	:	ex) "V:/KCA/KC1/2013/04/18/01/VDSKCAKC1201304180000.mxf"
      * @return String	:	ex) "KCA/KC1/2013/04/11/01/"
     * @exception Exception
     * @see
     */
	public static String getFileFullPath(String fileFullpath) {

		if(isEmpty(fileFullpath)){
			return "";
		}

		String filepath 		= fileFullpath.replace("\\", "/");
			   filepath 		= filepath.replace("\'", "/");

	    int filept				= filepath.lastIndexOf(":");
	    int filept2				= filepath.lastIndexOf("/");

	    if(filept <= -1 || filept2 <= -1){
	    	return fileFullpath;
	    }

	    return filepath.substring(filept+2 , filept2+1);
	}

    /**
     * 파일명을 제외한 파일경로 추출하기
     *
     * @param filepath	:	ex) "V:/KCA/KC1/2013/04/18/01/VDSKCAKC1201304180000.mxf"
     * @return String	:	ex) "V:/KCA/KC1/2013/04/11/01/"
     * @exception Exception
     * @see
     */
	public static String getFileDrPath(String fileFullpath) {

		if(isEmpty(fileFullpath)){
			return "";
		}

		String filepath 		= fileFullpath.replace("\\", "/");
			   filepath 		= filepath.replace("\'", "/");

	    int filept				= filepath.lastIndexOf("/");

	    if(filept <= -1){
	    	return fileFullpath;
	    }

	    return filepath.substring(0 , filept+1);
	}

    /**
     * FTP 용 SOC PATH 파일 뽑기
     *
     * @param filepath	:	ex) "/kca/PublishQtubeMCTest.mxf"
     * @return String	:	ex) "/kca/"
     * @exception Exception
     * @see
     */
	public static String getFtpFilePath(String fileFullpath) {

		if(isEmpty(fileFullpath)){
			return "";
		}

		String filepath 		= fileFullpath.replace("\\", "/");
			   filepath 		= filepath.replace("\'", "/");

	    int filept				= filepath.lastIndexOf("/");

	    if(filept <= -1){
	    	return fileFullpath;
	    }

	    return filepath.substring(0 , filept+1);
	}





    /**
     * 문자 특수문자 제거
     *
     * @param str		:	파일 이름
     * @return String	:	파일 이름
     * @exception Exception
     * @see
     */
	 public static String stringReplace(String string){

		 	String str 				= string;
		    String str_imsi   		= "";
		    String[] filter_word = {"","\\.","\\?","\\/","\\~","\\!","\\@","\\#","\\$","\\%","\\^","\\&","\\*","\\(","\\)","\\_","\\+","\\=","\\|","\\\\","\\}","\\]","\\{","\\[","\\\"","\\'","\\:","\\;","\\<","\\,","\\>","\\.","\\?","\\/"};

		    for(int i=0;i<filter_word.length;i++){
		        str_imsi = str.replaceAll(filter_word[i],"");
		        str = str_imsi;
		    }

		    //str = str.replaceAll("[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]", "");    // 특수문자 제거
		    //str = str.replaceAll("\\p{Space}", "");    					  // 공백제거
		    return str;
	}


    /**
     * 특정숫자 집합에서 랜덤 숫자를 구하는 기능 시작숫자와 종료숫자 사이에서 구한 랜덤 숫자를 반환한다
     *
     * @param startNum - 시작숫자
     * @param endNum   - 종료숫자
     * @return 랜덤숫자
     * @exception MyException
     * @see
     */
    public static int getRandomNum(int startNum, int endNum) {
			int randomNum = 0;

				try {
				    // 랜덤 객체 생성
				    SecureRandom rnd = new SecureRandom();

				    do {
					// 종료숫자내에서 랜덤 숫자를 발생시킨다.
					randomNum = rnd.nextInt(endNum + 1);
				    } while (randomNum < startNum); // 랜덤 숫자가 시작숫자보다 작을경우 다시 랜덤숫자를 발생시킨다.
				} catch (Exception e) {
				    //e.printStackTrace();
				    //throw new RuntimeException(e);	// 2011.10.10 보안점검 후속조치
					LOGGER.info(e.getMessage());
				}

			return randomNum;
    }

    /**
     * 문자열을 8859_1에서 utf-8로 인코딩 한다.
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodeUtf( String str )
    {
        return encodeText( str, "8859_1", "UTF-8" );
    }

    /**
     * 문자열 해당코드로 변환한다..
     *
     * @param str - String
     * @param encode - String
     * @param charsetName - String
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String encodeText( String str, String encode, String charsetName )  {
        String result = null;

        try {
            result = CoStringUtil.isEmpty( str ) ? null : new String( str.getBytes( encode ), charsetName );
        } catch ( UnsupportedEncodingException e ) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * lpad
     * @param str
     * @param f_char
     * @param size
     * @return
     */
    public static String lpad(String str, String f_char, int size) {
        if(str.length() >= size)
            return str;
        else
            return getFillChar("",f_char,size - str.length()) + str;
      }

    /**
     * lpad
     * @param str
     * @param f_char
     * @param size
     * @return
     */
    public static String getFillChar(String str, String f_char, int size) {
	   String fillChar = "";

	    if (str.length() >= size)
	        return str;

	    for(int index=0; index<(size-str.length()); ++index) {
	        fillChar += f_char;
	    }

	    return str+fillChar;
	}

    /**
	 * 넘어온 param decoding
	 * @param commandMap
     * @throws UnsupportedEncodingException
	 */
    public static Map<String,String> parameDecoding(Map<String,String> commandMap) throws UnsupportedEncodingException{

		Iterator iterator =  commandMap.keySet().iterator();
		String key   = "";
		String value = "";
		Map<String,String> map = new HashMap<String,String>();
		while(iterator.hasNext()){
			key 	= (String)iterator.next();
			value 	= URLDecoder.decode(commandMap.get(key), "UTF-8");
			System.out.println("SQL 파라미터 :" + value);
			map.put(key,value);
		}

		return map;
	}
}
