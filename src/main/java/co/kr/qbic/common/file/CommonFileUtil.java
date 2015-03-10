package co.kr.qbic.common.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import co.kr.qbic.common.util.web.CommonWebUtil;
import co.kr.qbic.common.vo.FileVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;

@Component("commonFileUtil")
public class CommonFileUtil {

	public static final int BUFF_SIZE = 2048;

	private static final Logger LOG = Logger.getLogger(CommonFileUtil.class);

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name = "egovFileIdGnrService")
	private EgovIdGnrService egovFileIdGnrService;

	/**
	 * 첨부파일에 대한 목록 정보를 취득한다.
	 *
	 * @param files
	 * @return
	 * @throws Exception
	 */
	public List<FileVO> parseFileInfo(Map<String, MultipartFile> files,	String keyStr, int fileKeyParam) throws Exception {

		int fileKey = fileKeyParam;

		String upload_path = propertiesService.getString("Globals.fileStorePath");
		String file_id = egovFileIdGnrService.getNextStringId();

		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;

		List<FileVO> fileList = new ArrayList<FileVO>();
		FileVO fvo;

		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();

			file = entry.getValue();
			String orginFileName = file.getOriginalFilename();

			// 원 파일명이 없는 경우 처리 (첨부가 되지 않은 input file type)
			if ("".equals(orginFileName)) {
				continue;
			}

			GregorianCalendar today = new GregorianCalendar();

			String CUR_YY = (today.get(today.YEAR)) + "";
			String CUR_MM = String.format("%02d", (today.get(today.MONTH) + 1))	+ "";
			String CUR_DD = (today.get(today.DAY_OF_MONTH)) + "";

			String filePath = upload_path + "/" + CUR_YY + "/" + CUR_MM + "/"	+ CUR_DD + "/";
			parseSavaFolder(filePath);
			
			String newName = "";

			int index = orginFileName.lastIndexOf(".");
			String fileExt = orginFileName.substring(index + 1);
			long fileSize = file.getSize();

			newName = keyStr + "_" + getTimeStamp() + "_" + fileKey + "." + fileExt;
			
			writeFile(file, newName, filePath);

			fvo = setFileVO(fileKey, filePath, file_id, orginFileName,	newName, fileExt, fileSize);
			
			fileList.add(fvo);

			fileKey++;
		}

		return fileList;
	}

	/**
	 * 파일을 실제 물리적인 경로에 생성한다.
	 *
	 * @param file
	 * @param newName
	 * @param stordFilePath
	 * @throws Exception
	 */
	protected static void writeFile(MultipartFile file, String newName, String stordFilePath) throws Exception {
		
		InputStream stream = null;
		OutputStream bos = null;

		try {
			stream = file.getInputStream();
			File cFile = new File(CommonWebUtil.filePathBlackList(stordFilePath));

			if (!cFile.isDirectory())
				cFile.mkdir();

			bos = new FileOutputStream(CommonWebUtil.filePathBlackList(stordFilePath + File.separator + newName));

			int bytesRead = 0;
			byte[] buffer = new byte[BUFF_SIZE];

			while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
		} catch (Exception e) {
			Logger.getLogger(CommonFileUtil.class).debug("IGNORED: " + e.getMessage());
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (Exception ignore) {
					Logger.getLogger(CommonFileUtil.class).debug("IGNORED: " + ignore.getMessage());
				}
			}
			if (stream != null) {
				try {
					stream.close();
				} catch (Exception ignore) {
					Logger.getLogger(CommonFileUtil.class).debug("IGNORED: " + ignore.getMessage());
				}
			}
		}
	}

	/**
	 * 응용어플리케이션에서 고유값을 사용하기 위해 시스템에서 17자리의 TIMESTAMP값을 구하는 기능
	 *
	 * @param
	 * @return Timestamp 값
	 * @exception MyException
	 * @see
	 */
	private static String getTimeStamp() {

		String rtnStr = null;

		// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
		String pattern = "yyyyMMddhhmmssSSS";

		try {
			SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern,	Locale.KOREA);
			Timestamp ts = new Timestamp(System.currentTimeMillis());

			rtnStr = sdfCurrent.format(ts.getTime());
		} catch (Exception e) {
			LOG.debug("IGNORED: " + e.getMessage());
		}

		return rtnStr;
	}
	
	// 저장폴더 생성
	private void parseSavaFolder(String file_path) {
		
		File saveFolder = new File(CommonWebUtil.filePathBlackList(file_path));

		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}
	}
	
	// FileVO 매핑
	private FileVO setFileVO(int fileKey, String file_path, String file_id,	String orginFileName, String newName, String fileExt, long fileSize) {
		FileVO fvo;
		fvo = new FileVO();
		fvo.setFileId(file_id);
		fvo.setFilePath(file_path); 				// 파일경로 	 ex) C:/Dropbox/files/qbic/upload/2015/03/4/
		fvo.setFileUploadNm(newName); 				// 업로드 파일명 ex) BOARD_20150304162205055_0.hwp
		fvo.setFileOriginNm(orginFileName); 		// 실제 파일명 	 ex) 파일철라벨.hwp
		fvo.setFileSn(String.valueOf(fileKey));		// 파일 SN 
		fvo.setFileExt(fileExt);					// 파일확장자
		fvo.setFileSize(fileSize);					// 파일크기
		return fvo;
	}
	
}
