package co.kr.iitech.comm.service;

import java.io.Serializable;

public class CoLoginLogVo implements Serializable {

	private static final long serialVersionUID = 5637966272431491336L;

	private String requestId;
	private String occrrncDe;
	private String url;
	private String rqesterIp;
	private String userId;
	private String menuId;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getOccrrncDe() {
		return occrrncDe;
	}

	public void setOccrrncDe(String occrrncDe) {
		this.occrrncDe = occrrncDe;
	}

	public String getUrl() {
		return url;
	}

	public String getUserId() {
		return userId;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRqesterIp() {
		return rqesterIp;
	}

	public void setRqesterIp(String rqesterIp) {
		this.rqesterIp = rqesterIp;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}
}
