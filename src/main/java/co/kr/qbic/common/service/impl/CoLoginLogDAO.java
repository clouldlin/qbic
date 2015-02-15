package co.kr.iitech.comm.service.impl;

import org.springframework.stereotype.Repository;

import co.kr.iitech.comm.service.CoLoginLogVo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("coLoginLogDAO")
public class CoLoginLogDAO extends EgovAbstractDAO {

	public String insertloginLog(CoLoginLogVo vo) {
		return (String)insert("CoLoginLogDAO.insertLoginLog", vo);
	}

}
