package co.kr.iitech.comm.service;

import co.kr.iitech.comm.vo.LoginVO;

public interface LoginService {

	/**
	 * 유저 로그인
	 * @param loginVO
	 * @return
	 */
	public LoginVO selectLoginUser(LoginVO loginVO);
}
