package com.kosmo.K08Spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import env.AdminConnection;
import env.BoardConnection;
import env.EnvApplicationConfig;
import env.UserConnection;

@Controller
public class EnvironmentController {
	
	/* 
	 /*
	외부파일을 이용한 설정방법 : 프로젝트를 진행하면서 필요한 설정값이 있는 경우에 외부파일을 입력 후
	   불러와서 사용할 수 있도록 해주는 방법이다.
	   예) DB접속정보, 관리자정보, 페이지설정값 등

	 
	 외부파일을 이용한 설정방법 
	 	: 프로젝트를 진행하면서 필요한 설정값이 있는경우에
	 	외부파일을 입력후 불러와서 사용할수 있도록 해주는 방법이다.
	 	
	 	예) DB접속정보, 관리자 정보 , 페이지설정값 등  	
	*/
	
	@RequestMapping("environment/main1")
	public String main1(Model model) {
		/*
			1.스프링 컨택스트 생성 (Ctrl + t 하면 클래스의 상속 관계를 볼수있음)
		 */
		ConfigurableApplicationContext ctx = new GenericXmlApplicationContext();
		/*
		    2.Environment 객체를 생성
		 */
		ConfigurableEnvironment env = ctx.getEnvironment();
		/*
		  	3.PropertySources 를 가져와서 , 외부파일을 읽을준비를 함.
		 */
		MutablePropertySources propertySources = env.getPropertySources();
		
		String adminIdStr="";
		String adminPwStr="";
		
		try {
			/*
			 4. 외부파일의 경로를 지정후 가져와서 addLast() 메소드를 통해 프로퍼티쏘스를 추가한다.
			 */
			String envPath = "classpath:EnvAdmin.properties";
			propertySources.addLast(new ResourcePropertySource(envPath));
			/*
			 5. getProperty 로 해당 데이터를 읽어서 변수에 저장한다.
			 */
			adminIdStr = env.getProperty("admin.id");
			adminPwStr = env.getProperty("admin.pw");
		} 
		catch (Exception e) {
			e.printStackTrace();	
		}
		//모델영역에 저장한다.
		model.addAttribute("adminID",adminIdStr);
		model.addAttribute("adminPW",adminPwStr);
		
		
		/* 
		 위에서 이미 생성했던 CtX 컨택스트 를 통해 새로운 스프링 컨텍스트를 생성함 .
		 */
		GenericXmlApplicationContext gCtx = (GenericXmlApplicationContext) ctx;
		//컨테이너 설정파일로드 
		gCtx.load("EnvAppCtx01.xml");
		//빈생성
		gCtx.refresh();
		//위 xml 설정파일에서 생성된 빈을 주입받음
		AdminConnection adminConnection = gCtx.getBean("adminConnection",AdminConnection.class);
		
		//getter 를 통해 가져옴
		adminIdStr = adminConnection.getAdminId();
		adminPwStr = adminConnection.getAdminPw();
		
		//뷰로 전달할 데이터 model 에 저장
		model.addAttribute("adminID2",adminIdStr);
		model.addAttribute("adminPW2",adminPwStr);
		
		//뷰호출
		return "05Environment/main1";
		}
		/*
		 외부파일 참조 2 
		 	: Environment 객체를 사용하지 않고 XML 파일에 프로퍼티 파일을 명시한후 직접 생성하여 빈을 설정하는 방법
		 */
		@RequestMapping("/environment/main2")
		public String main2 (Model model) {
			
			//XML설정파일을 기반으로 스프링 컨테이너 생성
			AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:EnvAppCtx02.xml");
			
			//외부에서 생성한 빈을 주입받아서 사용하기 
			UserConnection userCon = ctx.getBean("userConnection",UserConnection.class);
			
			//Model에 저장하기 
			
			model.addAttribute("mainUserId",userCon.getMainUserId());
			model.addAttribute("mainUserPw",userCon.getMainUserPw());
			model.addAttribute("subUserId",userCon.getSubUserId());
			model.addAttribute("subUserPw",userCon.getSubUserPw());
			
			//뷰호출
			return "05Environment/main2";
		}
		/*
		 외부파일참조 3 : 어노테이션을 이용한 외부파일 참조 xml 설정파일 대신 EnvApplicationConfig
		 클래스파일을 이용하여 외부파일참조 및 빈생성을 한다. 
		 */
		@RequestMapping("/environment/main3")
		public  String main3(Model model) {
			//어노테이션을 기반으로 스프링 컨테이너 생성
			AnnotationConfigApplicationContext ctx = 
					new AnnotationConfigApplicationContext(EnvApplicationConfig.class);
			//EnvApplicationConfig 클래스를 기반으로 ctx 를 참조할수 있게 되는거지 
			
			//설정파일에서 생성한 빈을 주입받음
			BoardConnection bConn = ctx.getBean("boardConfig",BoardConnection.class);
			
			model.addAttribute("id",bConn.getUser());
			model.addAttribute("pass",bConn.getPass());
			model.addAttribute("driver",bConn.getDriver());
			model.addAttribute("url",bConn.getUrl());
			
			return "05Environment/main3";
		}
}
