package com.kosmo.K08Spring;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileuploadContoroller {

	@RequestMapping("/fileUpload/uploadForm.do")
	public String uploadForm() {
		
		return "09FileUpload/uploadForm";
	}
	//파일 업로드 처리 
	@RequestMapping("/fileUpload/uploadAction.do")
	public String uploadAction(HttpServletRequest req, Model model) {
		//물리적 경로 
		String path = req.getSession().getServletContext().getRealPath("/resources/upload");
		//뷰로 전달한 정보를 젇장하기 위한Map 컬랙션 생성
		Map returnObj = new HashMap();
		
		try {
			/*
			 파일 업로드를 위한 Multipart 객체 를 생성함 . 객체 생성과 동시에 파일업로드는 완료되고 
			 , 나머지 폼값은 Multipart 가 통째로 받아서 처리함
			 */
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest)req;
			
			//업로드 폼의 file 속성 필드의 이름을 모두 읽음
			Iterator itr = mhsr.getFileNames();
			//파일처리를 위한 변수 생성 
			MultipartFile mfile = null;
			String fileName = "";
			
			//파일 하나의 정보를 저장하귀 위한 List 계열의 컬렉션 생성  
			List resultList = new ArrayList();
			//나머지 폼값 받기 (제목) 
			String title = mhsr.getParameter("title");
			
			//물리적 경로를 기반으로 File 객체 생성 
			File directory = new File("path");
			
			//업로드할 디렉토리가 있는지 확인후 
			if(!directory.isDirectory()) {
				//만약 없다면 생성한다.
				directory.mkdirs();
			}
			
			//업로드 폼의 file 속성의 필드갯수만큼 반복함.
			while(itr.hasNext())
			{	
				//input 의 속성값 읽기 (userfile1 , userfile2)
				fileName = (String) itr.next();
				//서버로 업로ㅡㄷ된 임시파일명 가져옴 
				mfile = mhsr.getFile(fileName);
				System.out.println("mfile="+mfile); 
				// xxxx@12354 이런 형태로 출력됨.
				
				//한글깨짐 방지 처리후 업로드된 파일명을 가져옴
				String originalName = new String (mfile.getOriginalFilename().getBytes(),"UTF-8");
				
				//만약 파일명이 공백이라면 while 문의 처음으로 돌아간다. 즉, 파일명을 정확히 읽어온다.
				if("".equals(originalName)) {
					continue;
				}
				//파일명에서 확장 자 가져옴 , 만약 파일명이 "aaa.bbb.ccc.jpg" 형태일수 있으므로 반드시 
				//lastIndexOf() 를 이용하여 마지막 .() 을 가져와야 한다.
			String ext = originalName.substring(originalName.lastIndexOf('.'));
			
			//랜덤하게 생성된 UUID 를 확장자와 조립하여 파일명 생성 
			String saveFileName = getUuid() + ext;
			
			// 설정한 경로에 파일저장 
			//file.separator 는 경로의 구분자를 반환합니다 . window 일땐 \ 역슬러시 linux 일댄 / 슬러시 입니다.
			File serverFullName = new File(path + File.separator+ saveFileName);
			
			//업로드한 파일을 지정 경로에 저장한다.
			mfile.transferTo(serverFullName);
			
			Map file = new HashMap();
			
			file.put("originalName", originalName); // 원본파일명 
			file.put("saveFileName", saveFileName); // 저장된 파일명 
			file.put("serverFullName", serverFullName);// 서버에 저장 된 전체경로 및 파일명 
			file.put("title", title); // 타이틀 
			
			resultList.add(file);
			}
				//위파일의 정보를 담은 Map 을 List 에 저장
				returnObj.put("files", resultList);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		//뷰로 전달할 데이터를 모델에 저장 
		model.addAttribute("returnObj",returnObj);
		//뷰 호출
		return "09FileUpload/uploadAction";
	}
	
	//물리적 경로 확인하기 
	@RequestMapping("/fileUpload/uploadPath.do")
	public void uploadPath(HttpServletRequest req , HttpServletResponse resp)throws IOException {
		
		//컨트롤러에서 서버의 물리적 경로 가져오기 
		
		String path = req.getSession().getServletContext().getRealPath("/resources/upload");
		
		//컨트롤러에서 직접 문자여을 출력하는경우 
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter print = resp.getWriter();
		
		
		print.println("/upload 폴러더의 물리적 경로 ");
		print.print(path);
	}
	//uuid 생성할 메소드 선언 
	/*
	 UUID(Universally unique identifier), 범용 고유 식별자 UUID randomUUID().toString() 으로 생성하면 
	 b7646ddc-exe-40cc-b08b-dfe1w65f41ew 처럼 4개의 하이푼과 32 개의 문자로 이루어진 문자열을 반환한다.
	 */
	public static String getUuid() {
		String uuid = UUID.randomUUID().toString();
		//System.out.println(uuid);
		uuid = uuid.replaceAll("-", "");
		//System.out.println("생성된 UUID"+uuid);
		return uuid;
	}
	
	//파일 목록 보기 
	@RequestMapping("/fileUpload/uploadList.do")
	public String uploadList (HttpServletRequest req , Model model) {
		
		//uplaod 폴더 물리적 경로 얻어오기
		String path = req.getSession().getServletContext().getRealPath("/resources/upload");
		
		//File 객체생성 
		File file = new File(path);
		
		//파일의 목록 얻어오기
		File[] fileArray = file.listFiles();
		
		//파일의 정보를 저장하기 위한 Map 생성
		//Map Key 파일명 , value (파일크기) 
		Map<String, Integer> fileMap = new HashMap<String, Integer>();
		
		for(File f : fileArray) {
			//파일명을 key 로 저장 , 파일용량을 value 로 저장 
			fileMap.put(f.getName(), (int) Math.ceil(f.length()/1024.0));
		}
		//파일 정보를 모델에 저장 
		model.addAttribute("fileMap",fileMap);
		
		
		return "09FileUpload/uploadList";
	}
	
	//파일 다운로드 
	@RequestMapping("/fileUpload/download.do")
	public ModelAndView download(HttpServletRequest req , HttpServletResponse resp) throws Exception {
		
		String  fileName = req.getParameter("fileName");
		String  oriFileName = req.getParameter("oriFileName");
		
		String saveDirectory = req.getSession().getServletContext().getRealPath("/resources/upload");
		
		File downloadFile = new File(saveDirectory + "/"+fileName);
		
		if(!downloadFile.canRead()) {
			throw new Exception("파일을 찾을수 없습니다");
		}
		
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("fileDownloadView"); // 업로드 빈도 다운로드 빈도 필요해 . 
		mv.addObject("downloadFile",downloadFile);
		mv.addObject("oriFileName",oriFileName);
		
		
		return mv;
	}
	
}
