package co.kr.qbic.common.code;

import java.util.List;
import java.util.Map;

public interface CommonCodeService {

	public List selectListCode(Map<String, String> commandMap) throws Exception;
}
