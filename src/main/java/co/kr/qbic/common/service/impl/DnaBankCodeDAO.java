package co.kr.iitech.comm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.kr.iitech.comm.vo.CoDefaultVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;



/**
*
* @Class Name   :CodeDAO.java
* @Description  :CodeDAO.java
* @Modification :개정이력(Modification Information)
* @
* @  수정일            수정자              수정내용
* @ ---------   ---------   -------------------------------
* @ 2013.11.14	정용진		최초생성
* @author IIT
* @since 2013.11.14
* @version 1.0
* @see
*
* Copyright (C) by IIT All right reserved.
 */
@Repository("dnaBankCodeDAO")
public class DnaBankCodeDAO extends EgovAbstractDAO
{
	/*@Override
	@Resource(name = "sqlMapClient2")
    public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSuperSqlMapClient(sqlMapClient);
    }*/

	public List selectListCodeDna(CoDefaultVO searchVO) throws Exception
	{
		return list("CodeDnaBankSQL001.list", searchVO);
	}
}
