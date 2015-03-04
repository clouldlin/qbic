package co.kr.qbic.common.vo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class FileVO implements Serializable {

	/**
	 * 첨부파일 아이디
	 */
	public String attachFileId = "";

	/**
	 * 파일확장자
	 */
	public String fileExtsn = "";

	/**
	 * 파일크기
	 */
	public String fileMg = "";

	/**
	 * 파일연번
	 */
	public String fileSn = "";

	/**
	 * 파일저장경로
	 */
	public String fileStreCours = "";
	
	/**
	 * 원파일명
	 */
	public String orignlFileNm = "";
	
	/**
	 * 저장파일명
	 */
	public String streFileNm = "";

	public String getAttachFileId() {
		return attachFileId;
	}

	public void setAttachFileId(String atchFileId) {
		this.attachFileId = atchFileId;
	}

	public String getFileExtsn() {
		return fileExtsn;
	}

	public void setFileExtsn(String fileExtsn) {
		this.fileExtsn = fileExtsn;
	}

	public String getFileMg() {
		return fileMg;
	}

	public void setFileMg(String fileMg) {
		this.fileMg = fileMg;
	}

	public String getFileSn() {
		return fileSn;
	}

	public void setFileSn(String fileSn) {
		this.fileSn = fileSn;
	}

	public String getFileStreCours() {
		return fileStreCours;
	}

	public void setFileStreCours(String fileStreCours) {
		this.fileStreCours = fileStreCours;
	}

	public String getOrignlFileNm() {
		return orignlFileNm;
	}

	public void setOrignlFileNm(String orignlFileNm) {
		this.orignlFileNm = orignlFileNm;
	}

	public String getStreFileNm() {
		return streFileNm;
	}

	public void setStreFileNm(String streFileNm) {
		this.streFileNm = streFileNm;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
