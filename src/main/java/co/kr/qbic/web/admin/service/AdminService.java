package co.kr.qbic.admin.service;

import java.util.List;
import java.util.Map;

public interface AdminService {

	public List<?> adminList(Map<String, String> commandMap) throws Exception;

	public Integer adminListTotalCount(Map<String, String> commandMap) throws Exception;
}