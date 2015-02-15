package co.kr.qbic.common.code.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kr.qbic.common.code.CodeService;
import co.kr.qbic.common.service.impl.DnaBankCodeDAO;
import co.kr.qbic.common.vo.CommonVO;

@Service("codeService")
public class CodeServiceImpl implements CodeService {

	@Resource(name = "codeDAO")
	CodeDAO codeDAO;

	@Override
	public List selectListCode(CommonVO searchVO) throws Exception {
		return codeDAO.selectListCode(searchVO);
	}

	@Override
	public List selectListSubCode(Map<String, String> map) throws Exception {
		return codeDAO.selectListSubCode(map);
	}

	@Override
	public List<Map<String, String>> openInfoList(Map<String, String> map) throws Exception {
		return codeDAO.openInfoList(map);
	}
}
