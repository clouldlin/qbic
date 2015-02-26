package co.kr.qbic.web.common.service;

import java.util.List;
import java.util.Map;

public interface CommonService {

	public List list(Map<String,String> commandMap) throws Exception;

	public void insert(Map<String,String> commandMap) throws Exception;

	public void update(Map<String,String> commandMap) throws Exception;

	public void delete(Map<String,String> commandMap) throws Exception;
}
