package co.kr.qbic.common.file.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import co.kr.qbic.common.file.service.CommonFileService;
import co.kr.qbic.common.vo.FileVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("commonFileService")
public class CommonFileServiceImpl extends AbstractServiceImpl implements CommonFileService {

	public static final Logger LOGGER = Logger.getLogger(CommonFileServiceImpl.class);

	@Resource(name = "commonFileDAO")
	private CommonFileDAO commonFileDAO;

	/**
	 * 여러 개의 파일을 삭제한다.
	 *
	 */
	public void deleteFileInfs(List fvoList) throws Exception {
		commonFileDAO.deleteFileInfs(fvoList);
	}

	/**
	 * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
	 *
	 */
	public String insertFileInf(FileVO fvo) throws Exception {
		String atchFileId = fvo.getFileId();

		commonFileDAO.insertFileInf(fvo);

		return atchFileId;
	}

	/**
	 * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
	 *
	 */
	public String insertFileInfos(List fvoList) throws Exception {
		String fileId = "";

		if (fvoList.size() != 0) {
			fileId = commonFileDAO.insertFileInfos(fvoList);
		}
		if (fileId == "") {
			fileId = null;
		}
		return fileId;
	}

	/**
	 * 파일에 대한 목록을 조회한다.
	 *
	 */
	public List<FileVO> selectFileInfos(FileVO fvo) throws Exception {
		return commonFileDAO.selectFileInfos(fvo);
	}

	public List<FileVO> selectFileInfsGov(FileVO fvo) throws Exception {
		return commonFileDAO.selectFileInfsGov(fvo);
	}

	/**
	 * 여러 개의 파일에 대한 정보(속성 및 상세)를 수정한다.
	 *
	 */
	public void updateFileInfs(List fvoList) throws Exception {
		// Delete & Insert
		commonFileDAO.updateFileInfs(fvoList);
	}

	/**
	 * 하나의 파일을 삭제한다.
	 *
	 */
	public void deleteFileInf(FileVO fvo) throws Exception {
		commonFileDAO.deleteFileInf(fvo);
	}

	/**
	 * 파일에 대한 상세정보를 조회한다.
	 *
	 */
	public FileVO selectFileInf(FileVO fvo) throws Exception {
		return commonFileDAO.selectFileInf(fvo);
	}

	/**
	 * 파일 구분자에 대한 최대값을 구한다.
	 *
	 */
	public int getMaxFileSN(FileVO fvo) throws Exception {
		return commonFileDAO.getMaxFileSN(fvo);
	}

	/**
	 * 전체 파일을 삭제한다.
	 *
	 */
	public void deleteAllFileInf(FileVO fvo) throws Exception {
		commonFileDAO.deleteAllFileInf(fvo);
	}

	/**
	 * 파일명 검색에 대한 목록을 조회한다.
	 *
	 */
	public Map<String, Object> selectFileListByFileNm(FileVO fvo) throws Exception {
		List<FileVO> result = commonFileDAO.selectFileListByFileNm(fvo);
		int cnt = commonFileDAO.selectFileListCntByFileNm(fvo);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
	 * 이미지 파일에 대한 목록을 조회한다.
	 *
	 */
	public List<FileVO> selectImageFileList(FileVO vo) throws Exception {
		return commonFileDAO.selectImageFileList(vo);
	}

}
