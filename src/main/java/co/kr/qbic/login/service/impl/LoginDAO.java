package com.qbic.web.login.service.impl;

import org.springframework.stereotype.Repository;

import com.qbic.web.login.vo.LoginVO;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("loginDAO")
public class LoginDAO extends EgovAbstractDAO{

	public LoginVO selectLoginUser(LoginVO loginVO){

		//TEST
		return loginVO;

		//운영시 주석해제
		//return (LoginVO)selectByPk("", loginVO);
	}
}
