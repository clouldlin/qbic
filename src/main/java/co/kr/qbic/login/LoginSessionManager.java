package co.kr.qbic.login;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import com.qbic.web.common.Constants;
import com.qbic.web.login.vo.LoginVO;

@Service(value = "loginSessionManager")
public class LoginSessionManager implements Serializable {

	private static final long serialVersionUID = -911949841277967844L;
	private LoginVO loginVO;

	/* 세션 생성해 주기 */
	public void setSession(HttpServletRequest request , LoginVO userVo) throws Exception {
		WebUtils.setSessionAttribute(request,Constants.userSession, userVo);
	}

	/* 세션 받아 오기 */
	public LoginVO getSession(HttpServletRequest request) throws Exception {
		return (LoginVO)WebUtils.getSessionAttribute(request, Constants.userSession);
	}

	/* 세션 VO 받아 오기 */
	public LoginVO getLoginVO(HttpServletRequest request) throws Exception{

		loginVO = getSession(request);

		if(loginVO == null){
			loginVO = new LoginVO();
		}
		return loginVO;
	}

	/* 세션 VO 받아 오기 */
	public String getLoginUserID(HttpServletRequest request) throws Exception{

		loginVO = getSession(request);
		String UserId = "";
		if(loginVO != null){
			UserId = loginVO.getUserId();
		}
		return UserId;
	}

	/* 세션 ID 받아 오기 */
	public String getLoginUserID() throws Exception{

		String UserId = "";
		if(loginVO != null){
			UserId = loginVO.getUserId();
		}
		return UserId;
	}

	/**
	 * 로그인VO 얻음
	 *
	 * @param
	 * @return LoginVO
	 * @throws Exception
	 */
	public LoginVO getLoginVO() {
		if (loginVO == null) {
			this.loginVO = new LoginVO();
		}
		return loginVO;
	}
	
	/**
	 * 로그인VO 제거
	 *
	 * @param
	 * @throws Exception
	 */
	public void removeLoginVO() {
		this.loginVO = null;
	}

	/**
	 * 세션을 생성해준다.
	 *
	 * @param DspCoLoginVO
	 */
	public void setSession(LoginVO loginVO) {
		this.loginVO = loginVO;
	}

	/**
	 * 세션을 삭제한다.
	 *
	 * @param request
	 */
	public void removeSession(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		session.invalidate();
	}
}
