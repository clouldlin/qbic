package co.kr.qbic.common.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import co.kr.qbic.common.util.web.CommonWebUtil;
import co.kr.qbic.common.vo.FileVO;
import egovframework.rte.fdl.cmmn.exception.FdlException;
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
	public List<FileVO> parseFileInfo(Map<String, MultipartFile> files,	String keyStr, int fileKeyParam, String fileId, String fileStorePath) throws Exception {

		int fileKey = fileKeyParam;

		String file_path = "";
		String file_id = "";

		file_path = parseFileStorePath(fileStorePath);
		file_id = parseFileId(fileId);
		parseSavaFolder(file_path);

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

			String filePath = file_path + "/" + CUR_YY + "/" + CUR_MM + "/"	+ CUR_DD + "/";
			String newName = "";

			int index = orginFileName.lastIndexOf(".");
			String fileExt = orginFileName.substring(index + 1);
			long fileSize = file.getSize();

			newName = keyStr + "_" + getTimeStamp() + "_" + fileKey + "." + fileExt;
			writeFile(file, newName, filePath);

			fvo = new FileVO();
			fvo.setFileId(file_id);
			fvo.setFilePath(file_path); 				// 파일경로 	 ex) C:/Dropbox/files/qbic/upload/2015/03/4/
			fvo.setFileUploadNm(newName); 				// 업로드 파일명 ex) BOARD_20150304162205055_0.hwp
			fvo.setFileOriginNm(orginFileName); 		// 실제 파일명 	 ex) 파일철라벨.hwp
			fvo.setFileSn(String.valueOf(fileKey));		// 파일 SN 
			fvo.setFileExt(fileExt);					// 파일확장자
			fvo.setFileSize(fileSize);					// 파일크기
			
			// LOG.info(fvo.toString());
			fileList.add(fvo);

			fileKey++;
		}

		return fileList;
	}
	
	/**
	 * 첨부파일을 서버에 저장한다.
	 *
	 * @param file
	 * @param newName
	 * @param stordFilePath
	 * @throws Exception
	 */
	protected void writeUploadedFile(MultipartFile file, String newName, String stordFilePath) throws Exception {
		InputStream stream = null;
		OutputStream bos = null;

		try {
			stream = file.getInputStream();
			File cFile = new File(stordFilePath);

			if (!cFile.isDirectory()) {
				boolean _flag = cFile.mkdir();
				if (!_flag) {
					throw new IOException("Directory creation Failed ");
				}
			}

			bos = new FileOutputStream(stordFilePath + File.separator + newName);

			int bytesRead = 0;
			byte[] buffer = new byte[BUFF_SIZE];

			while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
		} catch (Exception e) {
			LOG.error("IGNORE:", e);
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (Exception ignore) {
					LOG.debug("IGNORED: " + ignore.getMessage());
				}
			}
			if (stream != null) {
				try {
					stream.close();
				} catch (Exception ignore) {
					LOG.debug("IGNORED: " + ignore.getMessage());
				}
			}
		}
	}

	/**
	 * 서버의 파일을 다운로드한다.
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public static void downFile(HttpServletRequest request,	HttpServletResponse response) throws Exception {

		String downFileName = "";
		String orgFileName = "";

		if ((String) request.getAttribute("downFile") == null) {
			downFileName = "";
		} else {
			downFileName = (String) request.getAttribute("downFile");
		}

		if ((String) request.getAttribute("orgFileName") == null) {
			orgFileName = "";
		} else {
			orgFileName = (String) request.getAttribute("orginFile");
		}

		orgFileName = orgFileName.replaceAll("\r", "").replaceAll("\n", "");

		File file = new File(CommonWebUtil.filePathBlackList(downFileName));

		if (!file.exists()) {
			throw new FileNotFoundException(downFileName);
		}

		if (!file.isFile()) {
			throw new FileNotFoundException(downFileName);
		}

		byte[] b = new byte[BUFF_SIZE]; // buffer size 2K.

		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition:", "attachment; filename="
				+ new String(orgFileName.getBytes(), "UTF-8"));
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");

		BufferedInputStream fin = null;
		BufferedOutputStream outs = null;

		try {
			fin = new BufferedInputStream(new FileInputStream(file));
			outs = new BufferedOutputStream(response.getOutputStream());
			int read = 0;

			while ((read = fin.read(b)) != -1) {
				outs.write(b, 0, read);
			}
		} finally {
			if (outs != null) {
				try {
					outs.close();
				} catch (Exception ignore) {
					LOG.debug("IGNORED: " + ignore.getMessage());
				}
			}
			if (fin != null) {
				try {
					fin.close();
				} catch (Exception ignore) {
					LOG.debug("IGNORED: " + ignore.getMessage());
				}
			}
		}
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
	 * 서버 파일에 대하여 다운로드를 처리한다.
	 *
	 * @param response
	 * @param streFileNm : 파일저장 경로가 포함된 형태
	 * @param orignFileNm
	 * @throws Exception
	 */
	public void downFile(HttpServletResponse response, String streFileNm, String orignFileNm) throws Exception {
		
		String downFileName = streFileNm;
		String orgFileName = orignFileNm;

		File file = new File(downFileName);

		if (!file.exists()) {
			throw new FileNotFoundException(downFileName);
		}

		if (!file.isFile()) {
			throw new FileNotFoundException(downFileName);
		}

		// byte[] b = new byte[BUFF_SIZE]; //buffer size 2K.
		int fSize = (int) file.length();
		if (fSize > 0) {
			BufferedInputStream in = null;

			try {
				in = new BufferedInputStream(new FileInputStream(file));

				String mimetype = "text/html"; // "application/x-msdownload"

				response.setBufferSize(fSize);
				response.setContentType(mimetype);
				response.setHeader("Content-Disposition:","attachment; filename=" + orgFileName);
				response.setContentLength(fSize);
				// response.setHeader("Content-Transfer-Encoding","binary");
				// response.setHeader("Pragma","no-cache");
				// response.setHeader("Expires","0");
				FileCopyUtils.copy(in, response.getOutputStream());
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (Exception ignore) {

						Logger.getLogger(CommonFileUtil.class).debug("IGNORED: " + ignore.getMessage());
					}
				}
			}
			response.getOutputStream().flush();
			response.getOutputStream().close();
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
	
	// 첨부파일ID 설정
	private String parseFileId(String fileId) throws FdlException {
		String file_id;
		
		if ("".equals(fileId) || fileId == null) {
			file_id = egovFileIdGnrService.getNextStringId();
		} else {
			file_id = fileId;
		}
		return file_id;
	}

	// 파일저장위치 설정
	private String parseFileStorePath(String fileStorePath) {
		String file_path;
		
		if ("".equals(fileStorePath) || fileStorePath == null) {
			file_path = propertiesService.getString("Globals.fileStorePath");
		} else {
			file_path = fileStorePath;
		}
		return file_path;
	}
	
}
