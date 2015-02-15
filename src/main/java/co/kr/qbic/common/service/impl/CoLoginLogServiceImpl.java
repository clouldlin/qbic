package co.kr.iitech.comm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kr.iitech.comm.service.CoLoginLogService;
import co.kr.iitech.comm.service.CoLoginLogVo;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("goLoginLogService")
public class CoLoginLogServiceImpl extends AbstractServiceImpl  implements CoLoginLogService{

	@Resource(name = "coLoginLogDAO")
	private CoLoginLogDAO goLoginLogDAO;
	private String menuId;

	public String insertLuslprdat(CoLoginLogVo vo) throws Exception {

		String url = vo.getUrl();
		if(url.indexOf("/main/main.do") > 0){
			menuId = "MAIN00";
		}else if(url.indexOf("/phobon/loadList.do") > 0){
			menuId = "PNSC01";
		}else if(url.indexOf("/dnabank/loadList.do") > 0){
			menuId = "DNSC01";
		}
		vo.setMenuId(menuId);

		String ret = goLoginLogDAO.insertloginLog(vo);
		return ret;
	}

}
