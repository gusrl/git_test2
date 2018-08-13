package com.kosmo.K08Spring;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import common.MemberDTO;
/*
 사용자의 요청을 제일먼저 받는 dispatcherServlet 은 기본패키지인  
 com.kosmo.k08Spring 을 스켄히야 컨트롤러 클래스를 찾는다.
 그리고 해당 요청명에 매핑되는 메드를 찾아 실행하게된다.
 요청명의 매핑은 @RequestMapping 어노테이션이 담당한다.
 
 */
/*
 @controller 어노테이션 : 해당 클래스를 컨트롤러로 사용하고 싶을때 클래스명위에 선언하게된다.
 */
@Controller
public class FormController {
	/*
	패키지를 스캔하여 컨트롤러 클래스를 찾았다면 , 요청명이 매핑된 
	메소드를 찾게된다. YO靑명은 서블릿과 마찬가지로 " CONTEXT-ROOT " 를 제외한 나머지 경로명이 된다.
	YO靑 명 매핑정보를 통해 메소드를 찾게되므로 컨트롤러 에서 
	메소드명은 큰 의미가 없다. 단지 개발자가 꼴리기(구분하기) 좋은 정도의 이름을 붙여주면 된다.
	 */
	@RequestMapping("/form/servletRequest")
										//컨트롤러              UI
	public String loginMember(HttpServletRequest req, Model model) {
		/*
		 폼값 받기 [1] 파라미터로 전달된 값을 
		 HttpServletRequest 클래스를 통해서 받는다. 이처럼 하게되면 
		 JSP 와 동일하게 getParameter() 메소드를 통해 폼값을 받을수 있다 
		 */
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		/*
		 View 영역으로 전송할 데이터를 Model 영역에 저장한다.
		 JSP 에서의 영역과 동일하다고 생각하면 된다. 
		 */
		
		model.addAttribute("id",id);
		model.addAttribute("pw",pw);
		model.addAttribute("message","로그인 정보가 전달되었습니다.");
		
		/*
		 View 페이지명을 반환한다.
		아래처럼 뷰 경로를 문자열로 반환하면 ViewResolver 가 경로를 조립하여 해당 View 를 웹브라우저에 로딩하게된다 
		(설정파일 : servlet-context.xml) 
		 */
		return "01Form/servletRequest"; // 이걸보면 01Form/servletRequest.jsp 아 이거구나 하면 되는것.
 
	}
	/*
	 폼값 받기2] @RequestParam 어노테이션으로 폼값받기 
	 	파라미터 형식으로 
	 	@RequestParam("파라미터명") 변수타입 변수명 
	 	을 사용한다. 이와같이 하면 해당 메소드내에서 변수명을 
	 	그대로 사용할수 있다.
	 */
	@RequestMapping("/form/requestParam")
	public String joinMember(
			@RequestParam("name") String name, //폼값을 받아서 그옆에있는 변수에 할당하는 방식 
			@RequestParam("id") String id,
			@RequestParam("pw") String pw,
			@RequestParam("email") String email,
			Model model
			) {
		MemberDTO member = new MemberDTO();
		
		member.setId(id); //지역변수처럼 바로쓸수 있어
		member.setName(name);
		member.setPw(pw);
		member.setEmail(email);
		
		model.addAttribute("memberInfo",member);
		
		return "01Form/requestParam";
	}
	/*
	 폼값 한번에 받는녀석 DTO 멤버변수의 갯수가 동일할때 사용가능함 . ※ 코드양이 적어서 실무에서 사용한다.
	  
	 그런데 한가지 원칙이 있어 : 일단 . 스프링에서  커맨드 객체를 사용할때는 매개변수가 소문자만 바꾼 형태로 시작해야해
	 MemberDTO -> memberDTO 
	 스프링은 폼값을 커맨드 객체의 setter 를 통해 자동저장하고 , 동시에 model 영역에 저장해.
	 ※ 커맨드 객체를 만들때는 파라미터 의 갯수는 상관없으나 파라미터의 이름과 멤버변수의 이름은 동일해야 해. 
	 getter/setter 가 없으면 받을수 없고 당연히 출력도 할수없지.
	 */
	@RequestMapping ("/form/commandObjGet")
	public String commandObjectSimpleGet(MemberDTO memberDTO /*, 얘는 여기선 없어도 되지만 . 기본적으로 있는게 좋다. Model model*/) {
		// 모델영역에 저장하는 부분 주석처리 model.addAttribute("m",memberDTO);
		return "01Form/commandObjGet";
	}
	/*
	 @pathVariable 어노테이션으로 폼값 받기 
	 요청명 자체는 파라미터로 사용하는 형태로 "./form/" 뒤에 붙는 값이 메소드에서 사용가능한 파라미터값이 된다.
	 즉 아래 매핑정보로는 2 개의 파라미터를 받을수 있다 .
	 파라미터의 개수가 틀릴경우 404 오류가 발생한다.
	 이거는 요청명을 타고들어와야 하기때문에 post 방법은 좀 어려워.
	 */
	@RequestMapping("/form/{memberId}/{memberName}") //중괄호 안에있는 놈들을 내가 폼값으로 받겠다 . 세개 다 그럼 요청 하나 더 늘어나 /
	public String pathVariable(@PathVariable String memberId, @PathVariable String memberName, Model model) {
		model.addAttribute("memberId",memberId);
		model.addAttribute("memberName",memberName);
		return "01Form/PathVariable";
	}
	
	
}
