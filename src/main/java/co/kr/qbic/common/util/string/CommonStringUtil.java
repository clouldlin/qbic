package co.kr.qbic.common.util.string;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class CommonStringUtil {

    protected static final Logger LOGGER = Logger.getLogger(CommonStringUtil.class);

    /**
     * <p>
     * String이 비었거나("") 혹은 null 인지 검증한다.
     * </p>
     *
     * <pre>
     *  CommonStringUtil.isEmpty(null)      = true
     *  CommonStringUtil.isEmpty("")        = true
     *  CommonStringUtil.isEmpty(" ")       = false
     *  CommonStringUtil.isEmpty("bob")     = false
     *  CommonStringUtil.isEmpty("  bob  ") = false
     * </pre>
     *
     * @param str - 체크 대상 스트링오브젝트이며 null을 허용함
     * @return <code>true</code> - 입력받은 String 이 빈 문자열 또는 null인 경우
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

}
