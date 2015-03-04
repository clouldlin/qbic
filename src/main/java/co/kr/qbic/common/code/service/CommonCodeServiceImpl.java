package co.kr.qbic.common.code.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kr.qbic.common.code.CommonCodeService;

@Service("commonCodeService")
public class CommonCodeServiceImpl implements CommonCodeService {

	@Resource(name = "commonCodeDAO")
	CommonCodeDAO commonCodeDAO;

	@Override
	public List selectListCode(Map<String, String> commandMap) throws Exception {
		return commonCodeDAO.selectListCode(commandMap);
	}

}
