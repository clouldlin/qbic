package co.kr.qbic.common.login.service;

import java.util.Map;

import co.kr.qbic.common.vo.LoginVO;

public interface LoginService {
	public Integer loginUserCheck(Map<String, String> commandMap);

	public LoginVO selectUserById(Map<String, String> commandMap);
}
