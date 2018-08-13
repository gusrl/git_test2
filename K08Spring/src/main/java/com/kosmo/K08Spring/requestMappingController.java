package com.kosmo.K08Spring;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import common.StudentDTO;

@Controller
public class requestMappingController {
	/*
	 @RequestMapping 어노테이션
	 -사용법은 : 
			@RequestMapping(method=RequestMethod.GET[POST] , value="/요청명1/요청명2")
	 -method : 요청시 정송방식을 명시함 
	 -value  : 요청명을 명시함 
	 즉, 요청명과 전송방식을 만족해야 메소드가 호출되는 방식으로 동작됨.
	 -method 는 생략가능하며 명시하지 않으면 디폴트로 GET 방식이 된다. 
	 */
		@RequestMapping("/requestMapping/index")
		public String rmIndex() {
			return "02RequestMapping/index";
		}
		
		@RequestMapping(value="/reqeustMapping/getSearch",method=RequestMethod.GET)
		public String getSearch(HttpServletRequest req,Model model) {
			
			System.out.println("RequestMethod.GET 방식으로 "+"폼값전송");
			
			String sColumn= req.getParameter("searchColumn");
			String sWord =req.getParameter("searchWord");
			
			model.addAttribute("sColumn",sColumn);
			model.addAttribute("sWord",sWord);
			
			return "02RequestMapping/getSearch"; //뷰에 요청명을 저장.
		}
		//말그대로 모델 and 뷰 로 만들어져 있어 
		/*
			ModelAndView () : 뷰로 전송할 데이터 저장과 뷰를 호출하는 2 가지 로직을 동시에 처리할 수 있는 클래스임
			사용법 참조변수.setViewName("뷰의경로 및 파일명");
			->뷰 설정 
			참조변수.addObject("속성명","속성값")
			->데이터 저장 
			뷰를 호출할때는 ModelAndView 참조변수를 return 하면 된다.
		 */
		@RequestMapping (value="/reqeusetMapping/postLogin", method=RequestMethod.POST)
		public ModelAndView postLogin( @RequestParam("user_id") String id , @RequestParam("user_pw") String pw) {
			ModelAndView mv = new ModelAndView();
			
			mv.setViewName("02RequestMapping/postLogin"); //뷰의 경로를 셋팅하는 부분이고  위에서 return "02RequestMapping/getSearch"; 이거랑똑같고 
			mv.addObject("id",id); //model.addAttribute("sColumn",sColumn); 위의 이거랑 똑같아
			mv.addObject("pw",pw);
			
			return mv;
		}
		
		@RequestMapping ("/requestMapping/modelAttribute")
		/*
		 영역에 저장되어있는 놈 너무 길어 하 이걸 줄일수 없을까 ? modelDTO.getName 언제 쓰고 앉았어 ... 
		 그래서 
		 @ModelAttribute 어노테이션으로 : 뷰로 전달되는 커멘드 객체의 이름을 임의로 변경하고 싶을떄 사용하는거야 . 
		 */
		public String studentInfo( @ModelAttribute("si") StudentDTO studentDTO ) {
			return "02RequestMapping/modelAttribute";
		}
		
}
