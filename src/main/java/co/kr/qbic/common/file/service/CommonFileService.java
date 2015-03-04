package co.kr.qbic.common.file.service;

import java.util.List;
import java.util.Map;

import co.kr.qbic.common.vo.FileVO;

public interface CommonFileService {

    public List<FileVO> selectFileInfs(FileVO fvo) throws Exception;

    public List<FileVO> selectFileInfsGov(FileVO fvo) throws Exception;

    public String insertFile(FileVO fvo) throws Exception;

    public String insertFiles(List fvoList) throws Exception;

    public void updateFiles(List fvoList) throws Exception;

    public void deleteFiles(List fvoList) throws Exception;

    public void deleteFile(FileVO fvo) throws Exception;

    public FileVO selectFile(FileVO fvo) throws Exception;

    public int getMaxFileSN(FileVO fvo) throws Exception;

    public void deleteAllFile(FileVO fvo) throws Exception;

    public Map<String, Object> selectFileListByFileNm(FileVO fvo) throws Exception;

    public List<FileVO> selectImageFileList(FileVO vo) throws Exception;
}
