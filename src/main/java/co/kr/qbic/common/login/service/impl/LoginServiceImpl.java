package co.kr.qbic.common.login.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kr.qbic.common.login.service.LoginService;
import co.kr.qbic.common.vo.LoginVO;

@Service("loginService")
public class LoginServiceImpl  implements LoginService{

	@Resource(name="loginDAO")
	private LoginDAO loginDAO;

	@Override
	public Integer loginUserCheck(Map<String, String> commandMap) {
		return loginDAO.loginUserCheck(commandMap);
	}

	@Override
	public LoginVO selectUserById(Map<String, String> commandMap) {
		return loginDAO.selectUserById(commandMap);
	}
}
