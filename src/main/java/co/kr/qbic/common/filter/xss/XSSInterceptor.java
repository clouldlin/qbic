package co.kr.qbic.common.filter.xss;

import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class XSSInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Enumeration<String> e = request.getParameterNames();
		while(e.hasMoreElements()) {
			String key = e.nextElement();
            String values[] = request.getParameterValues(key);
            if(values != null) {
            	if(values.length == 1) {
            		request.setAttribute(key, stripXSS(values[0]));
            	} else {
            		for(int i=0, s=values.length; i<s; i++) {
            			values[i] = stripXSS(values[i]);
            		}
            		request.setAttribute(key, values);
            	}
            }
		}
		return true;
	}
	
	
	private String stripXSS(String value) {
		if (value != null) {
			value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");         
			value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");         
			value = value.replaceAll("'", "&#39;");     
			/*
				value = value.replaceAll("", "");
	
				Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
				value = scriptPattern.matcher(value).replaceAll("");
	
				scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				value = scriptPattern.matcher(value).replaceAll("");
	
				scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				value = scriptPattern.matcher(value).replaceAll("");
	
				scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
				value = scriptPattern.matcher(value).replaceAll("");
	
				scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				value = scriptPattern.matcher(value).replaceAll("");
	
				scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				value = scriptPattern.matcher(value).replaceAll("");
	
				scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				value = scriptPattern.matcher(value).replaceAll("");
	
				scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
				value = scriptPattern.matcher(value).replaceAll("");
	
				scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
				value = scriptPattern.matcher(value).replaceAll("");
	
				scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				value = scriptPattern.matcher(value).replaceAll("");
			*/
		}
		return value;
	}

}
