package co.kr.qbic.common.login.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kr.qbic.common.login.service.LoginService;
import co.kr.qbic.common.vo.LoginVO;

@Service("loginService")
public class LoginServiceImpl  implements LoginService{

	@Resource(name="loginDAO")
	private LoginDAO loginDAO;

	@Override
	public LoginVO selectLoginUser(LoginVO loginVO){
		return loginDAO.selectLoginUser(loginVO);
	}

}
