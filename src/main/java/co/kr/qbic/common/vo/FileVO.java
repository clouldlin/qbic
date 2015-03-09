package co.kr.qbic.common.vo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class FileVO implements Serializable {

	/**
	 * 첨부파일 아이디
	 */
	public String fileId = "";

	/**
	 * 파일연번
	 */
	public String fileSn = "";

	/**
	 * 파일저장경로
	 */
	public String filePath = "";
	
	/**
	 * 원파일명
	 */
	public String fileOriginNm = "";
	
	/**
	 * 저장파일명
	 */
	public String fileUploadNm = "";

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileSn() {
		return fileSn;
	}

	public void setFileSn(String fileSn) {
		this.fileSn = fileSn;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileOriginNm() {
		return fileOriginNm;
	}

	public void setFileOriginNm(String fileOriginNm) {
		this.fileOriginNm = fileOriginNm;
	}

	public String getFileUploadNm() {
		return fileUploadNm;
	}

	public void setFileUploadNm(String fileUploadNm) {
		this.fileUploadNm = fileUploadNm;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
