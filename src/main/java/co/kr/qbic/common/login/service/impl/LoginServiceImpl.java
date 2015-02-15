package com.qbic.web.login.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qbic.web.login.service.LoginService;
import com.qbic.web.login.vo.LoginVO;

@Service("loginService")
public class LoginServiceImpl  implements LoginService{

	@Resource(name="loginDAO")
	private LoginDAO loginDAO;

	@Override
	public LoginVO selectLoginUser(LoginVO loginVO){
		return loginDAO.selectLoginUser(loginVO);
	}

}
