package com.kosmo.K08Spring;

import java.lang.annotation.Annotation;

import javax.security.auth.login.AppConfigurationEntry;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import di.AppConfiguration;
import di.Avengers;
import di.AvengersInfo;
import di.BMIInfoView;
import di.CalculatorDTO;
import di.Car;

@Controller
public class DIController {
	@RequestMapping("/di/myCalculator")
	public String myCal(Model model) {
		
		/*
		applicationContext 파일의 위치를 문자열에 저장함 . 
		물리적 경로는 /src/main/resources 폴더 하위임 
		 */
		String configLocation = 
				"classpath:DIAppCtxCalculator.xml";
		/*
		 스프링 컨테이너 생성 : xml 파싱하여 파싱된 내용을 기반으로 ctx 참조변수에 할당한다.
		 */
		AbstractApplicationContext ctx = 
				new GenericXmlApplicationContext(configLocation);
		/*
		 xml 설정파일에서 생성한 빈 (Bean[객체])을 getBean() 메소드를 통해 주입받아 참조변수에 할당한다.
		 new 연산자를 통해 생성한것과 동일하지만 외부파일에서 이미 생성된것을 해당 클래스로 주입(injection)
		 받은것임에 유념할것.
		 */
		CalculatorDTO myCal = 
				ctx.getBean("myCal",CalculatorDTO.class);
		/*
		 주입받은 빈을 통해서 해당 클래스에 정의된 메소드를 호출할 수 있다.
		 */
		model.addAttribute("addResult",myCal.add());
		model.addAttribute("subResult",myCal.sub());
		model.addAttribute("mulResult",myCal.mul());
		model.addAttribute("divResult",myCal.div());
		
		return "04DI/myCalculator";
	}
	
	@RequestMapping("/di/myBMICal")
	public String bmiCal(Model model) {
		
		
		//스프링 컨테이너 생성  (/src/main/resources 하위)
		String configLoc = "classpath:DIAppCtxBMICal.xml";
		//저장해서 ctx 에 넘겨준다 ( 아 물론 context root 물고 )
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLoc);
		//XML 설정파일에서 생성한 빈을 주입받음
		/*
		 getBean() 메소드를 통해서 주입받음.
		 사용법 : getBean("빈의 참조변수", 빈 생성시 사용된 클래스명.class);
		 */
		BMIInfoView myInfo = ctx.getBean("myInfo",BMIInfoView.class); //주입받는다.
		//스프링 컨테이너 자원해제
		ctx.close();
		//주입받은 빈을 통해서 메소드를 호출하고 
		String myBMIResult = myInfo.getInfo();
		//Model 영역에 데이터 저장하고 
		model.addAttribute("myBMIResult",myBMIResult);
		//VIEW 호출 
		return "04DI/myBMICal";
	}
	
	@RequestMapping("/di/myAvengers")
	public ModelAndView myAvengers() {
		
		/* applicationContext 파일 생성 및 위치 설정 */ 
		String configLocation = "classpath:DIAppCtxAvengers.xml";
		/* 설정파일을 기반으로 스프링 컨테이너 생성 */
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		
		//xml 생성했으니 주입을 받아야 겠지 
									//캡틴 아메리카 빈을 주입받은후 정보를 출력하기 위한 문자열 저장.
		AvengersInfo avengersInfo = ctx.getBean("AvengersInfo1",AvengersInfo.class);
		/*
		 XML 설정파일에서 생성된 빈을 주입받을때는 getBean()메소드를 이용하여
		  어떤 클래스를 기반으로 생성된 빈인지만 명시해주면 된다
		 */
		
		String captainAmerica = avengersInfo.AvengersView();
		
		// 1]. 아이언맨 빈을 주입받음.
		Avengers avengers = ctx.getBean("hero2",Avengers.class);
		// 2].아이언맨 정보를 출력하기 위한 객체 생성
		avengersInfo.setAvengers(avengers);
		// 3].객체에 저장된 문자열 반환
		String ironMan = avengersInfo.AvengersView();
		//ModelAndView 이용하여 뷰로 전달할 정보저장및 뷰 설정
		ModelAndView mv = new ModelAndView();
		mv.setViewName("04DI/myAvengers");
		
		mv.addObject("captainAmerica",captainAmerica);
		mv.addObject("ironMan",ironMan);
		
		//자원해제 
		ctx.close();
		
		//반환
		return mv;
	}
	
	@RequestMapping("/di/Anonymouce")
	public ModelAndView myAnonymouce() {
		
		String configLocation = "classpath:DIAppCtxAnonymouce.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		
		//xml 생성했으니 주입을 받아야 겠지 
									//캡틴 아메리카 빈을 주입받은후 정보를 출력하기 위한 문자열 저장.
		AvengersInfo avengersInfo = ctx.getBean("AvengersInfo1",AvengersInfo.class);
		/*
		 XML 설정파일에서 생성된 빈을 주입받을때는 getBean()메소드를 이용하여
		  어떤 클래스를 기반으로 생성된 빈인지만 명시해주면 된다
		 */
		
		String captainAmerica = avengersInfo.AvengersView();
		
		// 1]. 아이언맨 빈을 주입받음.
		Avengers avengers = ctx.getBean("hero2",Avengers.class);
		// 2].아이언맨 정보를 출력하기 위한 객체 생성
		avengersInfo.setAvengers(avengers);
		// 3].객체에 저장된 문자열 반환
		String ironMan = avengersInfo.AvengersView();
		//ModelAndView 이용하여 뷰로 전달할 정보저장및 뷰 설정
		ModelAndView mv = new ModelAndView();
		mv.setViewName("04DI/myAvengers");
		
		mv.addObject("captainAmerica",captainAmerica);
		mv.addObject("ironMan",ironMan);
		
		//자원해제 
		ctx.close();
		
		//반환
		return mv;
	}
	@RequestMapping ("/di/myCar")
	public String myCar(Model model) {
		
		//XML 설정파일 및 객체생성 
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:"+
		"DIAppCtxMyCar.xml");
		
		//반 주입받기 
		
		Car car = ctx.getBean("car",Car.class);
		model.addAttribute("myDrive",car.myDrive());
		
		//자원해제
		ctx.close();
		return "04DI/myCar";
	}
	//모델엔뷰를 안쓰면 함수를 만들때 항상 Model 호출해줘야해 뷰랑 모델이랑 다가지고 있기떄문에 매개변수 하나를 줄일수 있어
	@RequestMapping ("/di/myAnnotation")
	public ModelAndView myAnnotation() {
		
		//여기선 XML 파일을 이용하는게 아니라 annotaion 을 사용하는걸로.								여기서 xml 이여야 하지만 자바쪽에서 어노테이션.
		//빈을 생성할 java 파일을 가져와서 스프링 컨테이너 생성함.
		AnnotationConfigApplicationContext aCtx =new AnnotationConfigApplicationContext(AppConfiguration.class); //이름 자체가 긴이유가 기능자체가 복잡해서.
		//JAVA 파일에서 생성한 빈을 메소드를 통해 주입받음
		BMIInfoView mem1 = 
		aCtx.getBean("member1",BMIInfoView.class);
		String str1 = "이름 :"+ mem1.getName() + "<br/>";
		str1 +="취미:" + mem1.getHobbys() + "<br/>";
		str1 +="신장:" + mem1.getHeight() +"<br/>";
		str1 +="몸무게: "+mem1.getWeight() + "<br/>";
		//str1 += "BMI결과:" + mem1.bmiCalculation();
		
		BMIInfoView mem2 = 
		aCtx.getBean("member1",BMIInfoView.class);
		String str2 = "이름 :"+ mem2.getName() + "<br/>";
		str2 +="취미:" + mem2.getHobbys() + "<br/>";
		str2 +="신장:" + mem2.getHeight() +"<br/>";
		str2 +="몸무게: "+mem2.getWeight() + "<br/>";
		//str2 += "BMI결과:" + mem2.bmiCalculation();
		 
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("memberInfo1",str1);
		mv.addObject("memberInfo2",str2);
		
		
		mv.setViewName("04DI/myAnnotation");
		return mv;
	}
}
