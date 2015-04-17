package co.kr.qbic.web.map.service;

import java.util.List;
import java.util.Map;


public interface MapService {

	List<?> sidoCodelist() throws Exception;

	List<?> sggCodelist(Map<String, String> commandMap) throws Exception;
	
	List<?> umdCodeList(Map<String, String> commandMap) throws Exception;

	List<?> lawdCodeList(Map<String, String> commandMap) throws Exception;

	List<?> indexCodeList(Map<String, String> commandMap) throws Exception;

}
