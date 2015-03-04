package co.kr.qbic.common.file.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import co.kr.qbic.common.file.service.CommonFileService;
import co.kr.qbic.common.vo.FileVO;

@Service("commonFileService")
public class CommonFileServiceImpl implements CommonFileService {

	@Resource(name = "commonFileDAO")
	private CommonFileDAO commonFileDAO;

	public static Logger logger = LoggerFactory.getLogger(CommonFileServiceImpl.class);

	public void deleteFiles(List list) throws Exception {
		commonFileDAO.deleteFiles(list);
	}

	/**
	 * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
	 *
	 */
	public String insertFile(FileVO vo) throws Exception {
		String attachFileId = vo.getAttachFileId();

		commonFileDAO.insertFile(vo);

		return attachFileId;
	}

	/**
	 * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
	 *
	 */
	public String insertFiles(List list) throws Exception {
		String attachFileId = "";

		if (list.size() != 0) {
			attachFileId = commonFileDAO.insertFiles(list);
		}
		if (attachFileId == "") {
			attachFileId = null;
		}
		return attachFileId;
	}

	/**
	 * 파일에 대한 목록을 조회한다.
	 *
	 * @see egovframework.com.cmm.service.EgovFileMngService#selectFileInfs(egovframework.com.cmm.service.FileVO)
	 */
	public List<FileVO> selectFileInfs(FileVO fvo) throws Exception {
		return commonFileDAO.selectFileInfs(fvo);
	}

	public List<FileVO> selectFileInfsGov(FileVO fvo) throws Exception {
		return commonFileDAO.selectFileInfsGov(fvo);
	}

	/**
	 * 여러 개의 파일에 대한 정보(속성 및 상세)를 수정한다.
	 *
	 * @see egovframework.com.cmm.service.EgovFileMngService#updateFileInfs(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public void updateFiles(List fvoList) throws Exception {
		// Delete & Insert
		commonFileDAO.updateFiles(fvoList);
	}

	/**
	 * 하나의 파일을 삭제한다.
	 *
	 * @see egovframework.com.cmm.service.EgovFileMngService#deleteFileInf(egovframework.com.cmm.service.FileVO)
	 */
	public void deleteFile(FileVO fvo) throws Exception {
		commonFileDAO.deleteFile(fvo);
	}

	/**
	 * 파일에 대한 상세정보를 조회한다.
	 *
	 * @see egovframework.com.cmm.service.EgovFileMngService#selectFileInf(egovframework.com.cmm.service.FileVO)
	 */
	public FileVO selectFile(FileVO fvo) throws Exception {
		return commonFileDAO.selectFileInf(fvo);
	}

	/**
	 * 파일 구분자에 대한 최대값을 구한다.
	 *
	 * @see egovframework.com.cmm.service.EgovFileMngService#getMaxFileSN(egovframework.com.cmm.service.FileVO)
	 */
	public int getMaxFileSN(FileVO fvo) throws Exception {
		return commonFileDAO.getMaxFileSN(fvo);
	}

	/**
	 * 파일명 검색에 대한 목록을 조회한다.
	 *
	 * @see egovframework.com.cmm.service.EgovFileMngService#selectFileListByFileNm(egovframework.com.cmm.service.FileVO)
	 */
	public Map<String, Object> selectFileListByFileNm(FileVO fvo)
			throws Exception {
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
	 * @see egovframework.com.cmm.service.EgovFileMngService#selectImageFileList(egovframework.com.cmm.service.FileVO)
	 */
	public List<FileVO> selectImageFileList(FileVO vo) throws Exception {
		return commonFileDAO.selectImageFileList(vo);
	}

	@Override
	public void deleteAllFile(FileVO fvo) throws Exception {
		commonFileDAO.deleteAllFileInf(fvo);
	}
}
