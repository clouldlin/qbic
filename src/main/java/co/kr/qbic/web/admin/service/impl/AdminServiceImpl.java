package co.kr.qbic.admin.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kr.qbic.admin.service.AdminService;


@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Resource(name="adminDAO")
	AdminDAO adminDAO;

	@Override
	public List<?> adminList(Map<String, String> commandMap) throws Exception {
		return adminDAO.adminList(commandMap);
	}

	@Override
	public Integer adminListTotalCount(Map<String, String> commandMap) throws Exception {
		return adminDAO.adminListTotalCount(commandMap);
	}

}
