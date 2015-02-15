package co.kr.qbic.main.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("mainDAO")
public class MainDAO extends EgovAbstractDAO {

	public List selectPosAll() throws Exception {
		return list("MainSQL001.selectList.001", null);
	}

	public List selectPosPhobon() throws Exception {
		return list("MainSQL001.selectList.002", null);
	}
}
