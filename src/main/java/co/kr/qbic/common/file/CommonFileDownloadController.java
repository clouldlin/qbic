package co.kr.qbic.common.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import co.kr.qbic.common.file.service.CommonFileService;
import co.kr.qbic.common.vo.FileVO;

@Controller
public class CommonFileDownloadController {

	@Resource(name = "commonFileService")
	private CommonFileService commonFileService;

	private static final Logger LOG = Logger.getLogger(CommonFileDownloadController.class);

	/**
	 * 첨부파일로 등록된 파일에 대하여 다운로드를 제공한다.
	 *
	 * @param commandMap
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/common/file/fileDown.do")
	public void fileDownload(Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String fileId = (String) commandMap.get("fileId");
		String fileSn = (String) commandMap.get("fileSn");
		
		// Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		Boolean isAuthenticated = true;

		if (isAuthenticated) {

			FileVO fileVO = new FileVO();
			fileVO.setFileId(fileId);
			fileVO.setFileSn(fileSn);
			FileVO fvo = commonFileService.selectFileInfo(fileVO);

			File uFile = new File(fvo.getFilePath(), fvo.getFileUploadNm());
			int fSize = (int) uFile.length();

			if (fSize > 0) {
				String mimetype = "application/x-msdownload";

				response.setContentType(mimetype);
				setDisposition(fvo.getFileOriginNm(), request, response);
				response.setContentLength(fSize);

				BufferedInputStream in = null;
				BufferedOutputStream out = null;

				try {
					in = new BufferedInputStream(new FileInputStream(uFile));
					out = new BufferedOutputStream(response.getOutputStream());

					FileCopyUtils.copy(in, out);
					out.flush();
				} catch (Exception ex) {
					LOG.debug("IGNORED: " + ex.getMessage());
				} finally {
					if (in != null) {
						try {
							in.close();
						} catch (Exception ignore) {
							LOG.debug("IGNORED: " + ignore.getMessage());
						}
					}
					if (out != null) {
						try {
							out.close();
						} catch (Exception ignore) {
							LOG.debug("IGNORED: " + ignore.getMessage());
						}
					}
				}

			} else {
				response.setContentType("application/x-msdownload");

				PrintWriter printwriter = response.getWriter();
				printwriter.println("<html>");
				printwriter.println("<br><br><br><h2>Could not get file name:<br>" + fvo.getFileOriginNm() + "</h2>");
				printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
				printwriter.println("<br><br><br>&copy; webAccess");
				printwriter.println("</html>");
				printwriter.flush();
				printwriter.close();
			}
		}
	}

	// a href링크 파일들이 한글명이 안먹히니 영문파일명으로 교체한후 원본 한글파일명으로 교체해서 다운
	// @param - dir : 저장폴더
	// @param - fileName : 영문이름으로 교체한 파일
	// @param - orgFileName : 원본 한글이름
	@RequestMapping(value = "/common/file/fileDown_href.do")
	public void commonFileDown(Map<String, Object> commandMap, String url, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String dir = url;
		String fileName = (String) commandMap.get("fileName");
		String orgFileName = (String) commandMap.get("orgFileName");

		File uFile = new File(dir, fileName);
		int fSize = (int) uFile.length();

		if (fSize > 0) {
			String mimetype = "application/x-msdownload";

			response.setContentType(mimetype);
			setDisposition(orgFileName, request, response);
			response.setContentLength(fSize);

			BufferedInputStream in = null;
			BufferedOutputStream out = null;

			try {
				in = new BufferedInputStream(new FileInputStream(uFile));
				out = new BufferedOutputStream(response.getOutputStream());

				FileCopyUtils.copy(in, out);
				out.flush();
			} catch (Exception ex) {
				LOG.debug("IGNORED: " + ex.getMessage());
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (Exception ignore) {
						LOG.debug("IGNORED: " + ignore.getMessage());
					}
				}
				if (out != null) {
					try {
						out.close();
					} catch (Exception ignore) {
						LOG.debug("IGNORED: " + ignore.getMessage());
					}
				}
			}

		} else {
			response.setContentType("application/x-msdownload");

			PrintWriter printwriter = response.getWriter();
			printwriter.println("<html>");
			printwriter.println("<br><br><br><h2>Could not get file name:<br>"	+ orgFileName + "</h2>");
			printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
			printwriter.println("<br><br><br>&copy; webAccess");
			printwriter.println("</html>");
			printwriter.flush();
			printwriter.close();
		}
	}
	
	/**
	 * 브라우저 구분 얻기.
	 *
	 * @param request
	 * @return
	 */
	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		}
		return "Firefox";
	}
	
	/**
	 * Disposition 지정하기.
	 *
	 * @param filename
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String browser = getBrowser(request);

		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;

		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\""	+ new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\""	+ new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			throw new IOException("Not supported browser");
		}

		response.setHeader("Content-Disposition", dispositionPrefix	+ encodedFilename);

		if ("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}

}
