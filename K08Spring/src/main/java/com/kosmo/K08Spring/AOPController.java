package com.kosmo.K08Spring;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aop.StudentVO;

@Controller
public class AOPController {

	@RequestMapping("/aop/main1.do")
	public String main1() {

		// xml 설정파일의 위치를 저장
		String xmlLocation = "classpath:AOPAppCtx1.xml";

		// xml 설정파일을 기반으로 스프링 컨테이너 생성
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(xmlLocation);

		// 미리 생성된 빈을 주입받음(DI:의존성 주입)
		StudentVO student = ctx.getBean("student", StudentVO.class);

		// 빈의 정보 출력
		student.showStudent(); // 핵심기능이 수행되는 시간을 보여주는게 공통점.

		// 컨테이너 자원해제
		ctx.close();

		// 뷰호출
		return "10Aop/main1";
	}

	@RequestMapping("/aop/main2.do")
	public String main2() {
		
		// xml 설정파일을 기반으로 스프링 컨테이너 생성
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:AOPAppCtx2.xml");

		// 미리 생성된 빈을 주입받음(DI:의존성 주입)
		StudentVO student = ctx.getBean("student", StudentVO.class);

		// 빈의 정보 출력
		student.showStudent(); // 핵심기능이 수행되는 시간을 보여주는게 공통점.

		// 컨테이너 자원해제
		ctx.close();
		
		//뷰호출이죠  ? 
		return "10Aop/main2";
	}
	

}
