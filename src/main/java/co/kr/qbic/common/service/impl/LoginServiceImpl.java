package co.kr.iitech.comm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kr.iitech.comm.service.LoginService;
import co.kr.iitech.comm.vo.LoginVO;

@Service("loginService")
public class LoginServiceImpl  implements LoginService{

	@Resource(name="loginDAO")
	private LoginDAO loginDAO;

	@Override
	public LoginVO selectLoginUser(LoginVO loginVO){
		//TEST
		return loginDAO.selectLoginUser(loginVO);
	}
}
