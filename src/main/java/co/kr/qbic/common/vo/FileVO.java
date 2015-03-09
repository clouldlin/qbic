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
	
	/**
	 * 파일확장자
	 */
	public String fileExt = "";
	
	/**
	 * 파일크기
	 */
	public long fileSize;
	
    /**
     * 생성일자
     */
    public String regDate = "";

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
	
	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	
	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
