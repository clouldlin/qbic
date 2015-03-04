package co.kr.qbic.common.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import co.kr.qbic.common.Constants;
import co.kr.qbic.common.util.string.CoStringUtil;
import co.kr.qbic.common.util.web.CommonWebUtil;

public class CommonFileUtil {

    public static final int BUFF_SIZE = 2048;

    public static Logger logger = LoggerFactory.getLogger(CommonFileUtil.class);

    /**
     * 첨부로 등록된 파일을 서버에 업로드한다.
     *
     * @param file
     * @param upload file path
     * @return
     * @throws Exception
     */
    public static Map<String, String> uploadFile(MultipartFile file, String fileStorePath) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		
		GregorianCalendar today = new GregorianCalendar();
	
		String CUR_YY 	= (today.get ( today.YEAR )) 		 + "";
		String CUR_MM 	= String.format("%02d", (today.get ( today.MONTH ) + 1)) 	 + "";
		String CUR_DD 	= (today.get ( today.DAY_OF_MONTH )) + "";
	
		String newName  = "";
		String filePath = fileStorePath+"/"+CUR_YY+"/"+CUR_MM+"/"+CUR_DD+"/";
		String orginFileName = file.getOriginalFilename();
	
		int index = orginFileName.lastIndexOf(".");
		String fileExt = orginFileName.substring(index + 1);
		long size = file.getSize();
	
		newName = getTimeStamp() + "." + fileExt;
		writeFile(file, newName, filePath);
		
		map.put(Constants.ORIGIN_FILE_NM, orginFileName);
		map.put(Constants.UPLOAD_FILE_NM, newName);
		map.put(Constants.FILE_EXT, fileExt);
		map.put(Constants.FILE_PATH, filePath);
		map.put(Constants.FILE_SIZE, String.valueOf(size));
	
		return map;
    }

    /**
     * 파일을 실제 물리적인 경로에 생성한다.
     *
     * @param file
     * @param newName
     * @param stordFilePath
     * @throws Exception
     */
    public static void writeFile(MultipartFile file, String newName, String stordFilePath) throws Exception {
	InputStream stream = null;
	OutputStream bos   = null;

	try {
	    stream 		= file.getInputStream();

	    File cFile 	= new File(CommonWebUtil.filePathBlackList(stordFilePath));

	    /* 디렉토리 없는경우 생성.*/
    	if(!cFile.getParentFile().exists()){
    		cFile.getParentFile().mkdirs();

	        // 해당 첨부파일  밑에 모든 권한을 셋팅해 준다.. 755
	    	/*
	    	    String cmd = "chmod -R 755 "+cFile;
		    	boolean cmdFlag = CoCommonUtil.processRuntime(cmd);
		    	if(!cmdFlag){
		    		CoCommonUtil.processUnixRuntime(cmd);
		    	}
	    	*/
    	}

	    if (!cFile.isDirectory()){
	    	cFile.mkdir();

	        //해당 첨부파일  밑에 모든 권한을 셋팅해 준다.. 755
	    	/*
	    		String cmd = "chmod -R 755 "+cFile;
	    		boolean cmdFlag = CoCommonUtil.processRuntime(cmd);
	    		if(!cmdFlag){
	    			CoCommonUtil.processUnixRuntime(cmd);
	    		}
	    	*/
	    }

	    bos = new FileOutputStream(CommonWebUtil.filePathBlackList(stordFilePath + File.separator + newName));

	    int bytesRead = 0;
	    byte[] buffer = new byte[BUFF_SIZE];

	    bytesRead = stream.read(buffer, 0, BUFF_SIZE);
	    while (bytesRead != -1) {
	    	bos.write(buffer, 0, bytesRead);
	    	bytesRead = stream.read(buffer, 0, BUFF_SIZE);
	    }
	} catch (Exception e) {
	} finally {
	    if (bos != null) {
		try {
		    bos.close();
		} catch (Exception ignore) {
		}
	    }
	    if (stream != null) {
		try {
		    stream.close();
		} catch (Exception ignore) {
		}
	    }
	}

	}

    /**
     * 서버 파일에 대하여 다운로드를 처리한다.
     *
     * @param response
     * @param streFileNm
     *            : 파일저장 경로가 포함된 형태
     * @param orignFileNm
     * @return
     * @throws Exception
     */
    public static Map<String, String> downFile(HttpServletRequest request, HttpServletResponse response, String streFileNm, String orignFileNm) throws Exception {
	String downFileName = streFileNm;
	String orgFileName = orignFileNm;

	Map<String, String> downHist = new HashMap<String, String>();
	File file = new File(downFileName);
	//log.debug(this.getClass().getName()+" downFile downFileName "+downFileName);
	//log.debug(this.getClass().getName()+" downFile orgFileName "+orgFileName);

	if (!file.exists()) {
	    throw new FileNotFoundException(downFileName);
	}

	if (!file.isFile()) {
	    throw new FileNotFoundException(downFileName);
	}

	//byte[] b = new byte[BUFF_SIZE]; //buffer size 2K.
	int fSize = (int)file.length();

		if (fSize > 0) {
		    BufferedInputStream  in = null;
		    BufferedOutputStream out = null;

		    //다운로드 시작시간
			String stDtm = getTimeStamp();
			downHist.put("stDtm", stDtm);
		    try {
			in  = new BufferedInputStream(new FileInputStream(file));
			out = new BufferedOutputStream(response.getOutputStream());

	    	//String mimetype = "text/html"; //"application/x-msdownload"
	    	String mimetype = "application/x-msdownload";

	    	response.setBufferSize(fSize);
			response.setContentType(mimetype);
			//response.setHeader("Content-Disposition:", "attachment; filename=" + new String(orgFileName.getBytes(),"UTF-8" ));
			setDisposition(orgFileName, request, response);
			response.setContentLength(fSize);

			//response.setHeader("Content-Transfer-Encoding","binary");
			//response.setHeader("Pragma","no-cache");
			//response.setHeader("Expires","0");

			FileCopyUtils.copy(in, out);
			in.close();
			out.flush();
			out.close();

		    }catch (Exception e) {
				// 다운로드 에러
		    	downHist.put("jobgbId", "09");
		    } finally {
				if (in != null) {
				    try {
				    	in.close();

				    	//다운로드 종료 시간
				    	String endDtm = getTimeStamp();
				    	downHist.put("endDtm", endDtm);
				    	downHist.put("jobgbId", "03");

				    } catch (Exception ignore) {
				    }
				}
		    }
		}else{
				response.setContentType("application/x-msdownload");

				PrintWriter printwriter = response.getWriter();
				printwriter.println("<html>");
				printwriter.println("<br><br><br><h2>Could not get file name:<br>" + orgFileName + "</h2>");
				printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
				printwriter.println("<br><br><br>&copy; webAccess");
				printwriter.println("</html>");
				printwriter.flush();
				printwriter.close();
		}
		return downHist;
    }

    /**
     * 2011.08.09
     * 공통 컴포넌트 utl.fcc 패키지와 Dependency제거를 위해 내부 메서드로 추가 정의함
     * 응용어플리케이션에서 고유값을 사용하기 위해 시스템에서17자리의TIMESTAMP값을 구하는 기능
     *
     * @param
     * @return Timestamp 값
     * @exception MyException
     * @see
     */
    private static String getTimeStamp() {

		String rtnStr = null;
	
		// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
		String pattern = "yyyyMMddHHmmssSSS";
	
		try {
		    SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
		    Timestamp ts = new Timestamp(System.currentTimeMillis());
	
		    rtnStr = sdfCurrent.format(ts.getTime());
		} catch (Exception e) {
		}
	
		return rtnStr;
    }


    /**
     * 폴더 생성하기
     * @param filePath 파일 경로.
     * @return boolean 생성 여부
     * @throws Exception
     */
    public static boolean createFileDir(String filePath)
    {
    	String targetPath = filePath;

		File saveFolder   = new File(targetPath);
		// 디렉토리 없는경우 생성.
    	if(!saveFolder.getParentFile().exists()){
    		saveFolder.getParentFile().mkdirs();
    	}

    	// 디렉토리 생성
    	if (!saveFolder.isDirectory()) {
			saveFolder.mkdirs();
		}

    	return true;
    }


    /**
     * 파일 삭제하기
     * @param filePath 파일 경로.
     * @return boolean 삭제여부
     * @throws Exception
     */
    public static boolean removeFile(String filePath , String fileName)
    {
    	String targetPath=filePath+fileName;
    	File sFile = new File(targetPath);
    	if(sFile.exists())
    	{
    		sFile.delete();
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }

    /**
     * 파일 삭제하기
     * @param filePath 파일 경로.
     * @return boolean 삭제여부
     * @throws Exception
     */
    public static boolean removeFile(String targetPath)
    {
    	File sFile = new File(targetPath);
    	if(sFile.exists())
    	{
    		sFile.delete();
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }

    /**
     * 디렉토리 및 파일 삭제를 위한 재귀함수.
     * 디렉토리 내에 존재하는 모든 디렉토리와 파일을 포함하여 전부 삭제 한다.
     * @param path 파일 경로.
     * @return boolean 삭제여부
     * @throws Exception
     */
    public static boolean deleteDirectory(String delTarget) {
    	  File delDir = new File(delTarget);

    	  if(delDir.isDirectory()) {
    		  File[] allFiles = delDir.listFiles();

	    	   for(File delAllDir : allFiles) {
	    		   deleteDirectory(delAllDir.getAbsolutePath());
	    	   }
    	  }

    	  return delDir.delete();
   }

    /**
     * 파일 존재 여부
     * @param path 파일 경로.
     * @return boolean 삭제여부
     * @throws Exception
     */
    public static boolean isFile(String path) {

    	  File file = new File(path);

    	  // 파일 존재 여부 판단
    	  if (file.isFile()) {
    		  return true;
    	  }else{
    		  return false;
    	  }
   }


    /**
     * 파일 용량, 확장자 체크
     *
     * 파일 업로드 시 용량과 확장자를 체크하여 결과를 반환
     *
     *  // 사용법1
     *  int test = FileMngUtil.checkUploadFileSizeExt(files, 100, "jsp:php:jpg");
     *
     *  // 사용법2 : 파일 확장자 체크
     *  if(FileMngUtil.checkUploadFileSizeExt(files, 100,"JPG:GIF:TXT") > 0){
     *      ModelAndView mavCk = new ModelAndView("forward:/svc/rci/eip/ipr/EntrprsFnnrInfoRegistForm.do");
     *      mavCk.addObject("alertMsg", "entrprsInfo.noCrtfctLogin.mgs");
     *      return mavCk;
     *  }
     *
     * @param files 업로드 파일
     * @param size 제한 용량(단위 : MB)
     * @param ext 제한 확장자 (ex>jpg:exe:bat)
     * @return 체크결과(0:정상 1:확장자문제 2:사이즈문제)
    */
    public static int checkUploadFileSizeExt(Map<String, MultipartFile> files, int size, String ext) throws Exception {

    	String FileExt = ext;
        int sizeLimit = size * 1024 * 1024;  // 파일업로드 용량 제한. 단위 : MB
        int result = 0;
        FileExt = FileExt.toUpperCase();
        String[] extArray = FileExt.split(":");
        Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();

        long sizeHap = 0;

        while (itr.hasNext()) {
            Entry<String, MultipartFile> entry = itr.next();
            MultipartFile file = entry.getValue();
            String upOriginFileName = file.getOriginalFilename();
            String  testExt = "";
            long upSize = file.getSize();

            testExt = upOriginFileName.substring(upOriginFileName.lastIndexOf(".")+1,upOriginFileName.length());



            for(int i = 0 ; i < extArray.length ; i ++) {
                if(!extArray[i].equals("") && extArray[i].equals(testExt.toUpperCase())) {
                    result = 1;
                    break;
                }
            }

            /*if(!ext.equals("") && ext.indexOf(testExt.toUpperCase())>0){
                result = 1;
                break;
            }else*/

            sizeHap = sizeHap+upSize;
            if((ext!= null && !ext.equals("")) && upSize > sizeLimit){
                result = 2;
                break;
            }
        }

        if(sizeHap > sizeLimit){
            result = 2;
        }


        return result;
    }

    /** ******************************************************* 파일 체크 *************************************************************/
    public static Integer checkFileExtention(MultipartHttpServletRequest request, String extentions){
    	 // 파일확장자 및 제한용량체크
        int fileCkResult = checkFileExtention(request, "N", -1, extentions);

       return fileCkResult;
    }


    /**
     * 파일 확장자 체크(복수건)
     *
     * @param request 요청정보
     * @param checkSe (Y:허용체크 N:미허용체크 체크안함:X)
     * @param extentions 파일 확장자 쉼표로구분
     * @return 체크결과(0:정상 1:확장자문제 2:사이즈문제)
     */
    public static Integer checkFileExtention(HttpServletRequest request, String checkSe, String extentions){
        return checkFileExtention(request, checkSe, -1, extentions);
    }

    /**
     * 파일 확장자 체크(미허용 복수건)
     *
     * 사용법
     *
        // 파일확장자 및 제한용량체크
        int FileCkResult = FileMngUtil.checkFileExtention(request, Integer.parseInt(propertiesService.getString("global.fileupload.size")), propertiesService.getString("global.fileupload.excludeFileExt"));

        if(FileCkResult != 0){
            ModelAndView mavCk = new ModelAndView("forward:/svc/rci/eip/ipr/EntrprsFnnrInfoRegistForm.do");
            mavCk.addObject("alertMsg", (FileCkResult == 1 ? "common.imposbl.fileuploadext" : "common.imposbl.fileuploadsize"));
            return mavCk;
        }
     *
     * @param request 요청정보
     * @param extentions 파일 확장자 쉼표로구분
     * @param size 파일제한사이즈(-1 이면 사이즈 체크안함)
     * @return 체크결과(0:정상 1:확장자문제 2:사이즈문제)
     */
    public static Integer checkFileExtention(HttpServletRequest request, long size, String extentions){
        return checkFileExtention(request, "N", size, extentions);
    }

    /**
     * 파일 확장자 체크(단건)
     *
     * @param originalFilename 파일명
     * @param checkSe (Y:허용체크 N:미허용체크 체크안함:X)
     * @param extentions 파일 확장자 쉼표로구분( ex > jpg,exe,bat )
     * @return 체크결과(0:정상 1:확장자문제 2:사이즈문제)
     */
    public static Integer checkFileExtention(String originalFilename, String checkSe, String extentions){
        return checkFileExtention(originalFilename, checkSe, -1, -1, extentions);
    }

    /**
     * 파일 확장자 체크(복수건 사이즈체크포함)
     *
     * @param request 요청정보
     * @param checkSe (Y:허용체크 N:미허용체크 체크안함:X)
     * @param extentions 파일 확장자 쉼표로구분( ex > jpg,exe,bat )
     * @param size 파일제한사이즈(-1 이면 사이즈 체크안함)
     * @return 체크결과(0:정상 1:확장자문제 2:사이즈문제)
     */
    @SuppressWarnings("unchecked")
    public static Integer checkFileExtention(HttpServletRequest request, String checkSe, long size, String extentions){

        int result = 0;

        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

        Iterator<Entry<String, MultipartFile>> itr =  multiRequest.getFileMap().entrySet().iterator();


        // 파일정보 추출
        while (itr.hasNext()) {
            MultipartFile file = itr.next().getValue();
            Integer rv = checkFileExtention(file.getOriginalFilename(), checkSe, file.getSize(), size, extentions);

            if(rv>0){
                result = rv;
                break;
            }
        }

        return result;
    }

    /**
     * 파일 확장자 체크(단건 사이즈체크포함)
     *
     * @param originalFilename 파일명
     * @param checkSe (Y:허용체크 N:미허용체크 체크안함:X)
     * @param extentions 파일 확장자 쉼표로구분( ex > jpg,exe,bat )
     * @param fileSize 파일실제사이즈
     * @param size 파일제한사이즈(-1 이면 사이즈 체크안함)
     * @return 체크결과(0:정상 1:확장자문제 2:사이즈문제)
     */
    public static Integer checkFileExtention(String originalFilename, String checkSe, long fileSize, long size, String extentions){

    	String FileExtentions = extentions;
    	long   FileSize = size;

        Integer result = 0;

        FileSize = FileSize * 1024 * 1024;

        FileExtentions = FileExtentions.toUpperCase();

        // 파일확장자 추출
        int extStart 	= originalFilename.lastIndexOf(".",originalFilename.length()-1);
        // 확장자를 대문자로 변환
        String ext 		= originalFilename.substring(extStart+1).toUpperCase();

        // 허용된 파일 체크(속하지 않으면 1리턴)
        // PMD 오류로 인한 처리 명진 20130620
        if(ext!=null && !ext.equals("")){
	        if(checkSe.equalsIgnoreCase("Y") && FileExtentions.indexOf(ext)<0){
	            return 1;
	        // 금지된 파일 체크(속하면 1리턴)
	        }else if(checkSe.equalsIgnoreCase("N") && FileExtentions.indexOf(ext)>-1){
	            return 1;
	        // 파일 사이즈 체크
	        }else if(FileSize > -1 && fileSize > FileSize){
	            return 2;
	        }
        }

        /*
        if((ext!=null && !ext.equals("")) && checkSe.equalsIgnoreCase("Y") && FileExtentions.indexOf(ext)<0){
            return 1;
        // 금지된 파일 체크(속하면 1리턴)
        }else if((ext!=null && !ext.equals("")) && checkSe.equalsIgnoreCase("N") && FileExtentions.indexOf(ext)>-1){
            return 1;
        // 파일 사이즈 체크
        }else if((ext!=null && !ext.equals("")) && FileSize > -1 && fileSize > FileSize){
            return 2;
        }
        */

        return result;
    }


    /**
     * 브라우저 구분 얻기.
     *
     * @param request
     * @return
     */
    private static String getBrowser(HttpServletRequest request) {
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
    private static void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String browser = getBrowser(request);

		String dispositionPrefix = "attachment; filename=";
		String encodedFilename   = null;

		if (CoStringUtil.equals(browser,"Trident")) {
		    encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		}else if (CoStringUtil.equals(browser,"MSIE")) {
		    encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (CoStringUtil.equals(browser,"Firefox")) {
		    encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (CoStringUtil.equals(browser,"Opera")) {
		    encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (CoStringUtil.equals(browser,"Chrome")) {
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
		    //throw new RuntimeException("Not supported browser");
		    throw new IOException("Not supported browser");
		}

		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

		if ("Opera".equals(browser)){
		    response.setContentType("application/octet-stream;charset=UTF-8");
		}
    }
}
