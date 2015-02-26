package co.kr.qbic.web.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kr.qbic.web.common.service.CommonService;

@Service("commonService")
public class CommonServiceImpl implements CommonService {

	@Resource(name = "commonDAO")
	CommonDAO commonDAO;

	@Override
	public List list(Map<String, String> commandMap) throws Exception {
		return commonDAO.list(commandMap);

	}

	@Override
	public void insert(Map<String, String> commandMap) throws Exception {
		commonDAO.insert(commandMap);
	}

	@Override
	public void update(Map<String, String> commandMap) throws Exception {
		commonDAO.update(commandMap);
	}

	@Override
	public void delete(Map<String, String> commandMap) throws Exception {
		commonDAO.delete(commandMap);
	}

}
