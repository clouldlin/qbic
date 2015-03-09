package co.kr.qbic.common.code.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kr.qbic.common.code.CommonCodeService;
import co.kr.qbic.common.vo.CommonVO;
import co.kr.qbic.web.common.service.impl.CommonDAO;

@Service("commonCodeService")
public class CommonCodeServiceImpl implements CommonCodeService {

	@Resource(name = "commonCodeDAO")
	CommonCodeDAO commonCodeDAO;

	@Override
	public List selectListCode(CommonVO searchVO) throws Exception {
		return commonCodeDAO.selectListCode(searchVO);
	}

	@Override
	public List selectListSubCode(Map<String, String> map) throws Exception {
		return commonCodeDAO.selectListSubCode(map);
	}

}
