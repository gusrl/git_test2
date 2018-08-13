<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<link  rel="stylesheet" href="./common/bootstrap3.3.7/css/bootstrap.min.css" />
<script src="./common/bootstrap3.3.7/jquery/jquery-3.2.1.min.js"></script>

<body>

	<div class="container">
		<h2>스프링 MVC 시작하기</h2>


		<h3>resources 폴더 사용하기</h3>
		<table class="table table-bordered" style="width: 300px;">
			<tr>
				<td><img src="./resources/1.png" width="150" alt="스펀지밥" /></td>
				<td><img src="./common/3.gif" width="150" alt="징징이" /></td>
			</tr>
			<tr>
				<td>난 resources폴더</td>
				<td>common폴더</td>
			</tr>
		</table>

		<h3>1st 컨트롤러 만들기</h3>

		<li><a href="./home/helloSpring" target="_blank"> First컨트롤러
				바로가기 </a></li>

		<h3>2st 폼값 처리하기</h3>

		<li><a href="./form/servletRequest?id=kosmo&pw=1234"
			target="_blnak"> /form/servletRequest [HttpServletRequest로 폼값받기]
				<!-- 함수형태 -->
		</a></li>

		<li><a
			href="./form/requestParam?name=홍길동&id=hong
			&email=hong@gildong.com&pw=1234"
			target="_blank"> /form/requestParam [@RequestParam 어노테이션으로 폼값받기]
				<!-- 컨트롤러에 저장한거에 매개변수 형태로 받는것 -->
		</a></li>

		<li><a
			href="./form/commandObjGet?name=홍길동&id=hong
			&email=hong@gildong.com&pw=1234"
			target="_blank"> /form/commendObjGet [커멘드객체로 한번에 폼값받기] <!-- getP 나 reqP 나 아무런 명령없이 한방에 받는법 -->
		</a></li>

		<li><a href="./form/gildong99/홍길동" target="_blank">
				/form/pathVariable [pathVariable 어노테이션으로 폼값받기] </a></li>

		<h3>@RequestMapping 어노테이션</h3>

		<li><a href="./requestMapping/index" target="_blank">
				/requestMapping/index 시작페이지 바로가기 </a></li>


		<!-- 

총 로직  ~ ! : 
DI 를 활용한 개발순서 

1. 요청명을 결정 
		-> "di/myBMICal"
2. 컨트롤러 생성후 해당 요청명을 매핑한 메소드 정의 
		-> 
		  @RequestMapping("di/myBMICal")
		  public String bmiCal(Model model){
		  	메소드 내용 ...
		  }
3. 해당 프로그램에 사용할 클래스 생성
		-> /src/main/java 아래 패키지 생성후 클래스 파일 
		추가.
		*실제경로: 프로젝트명/src/main/java/
4. xml 설정파일 생성 후 빈 생성 
		->/src/main/resources/하위에 
		Spring Bean Configuration File 생성 
		*빈생성은 해당 파일 참조 
5. 컨트롤러에서 생성된 빈 주입받기
		->getBean() 메소드를 통해 주입받음 
6. 뷰로 전달할 정보 저장후 뷰 호출 
		->문자열로 반환하거나 ModelAndView() 를 사용함 

 -->

		<h3>DI(Dependency Injection) : 의존성 주입</h3>

		<li><a href="di/myCalculator" target="_blank"> 간단한 사칙연산 계산기 </a>
		</li>
		<li><a href="di/myBMICal" target="_blank"> BMI 지수 (비만지수) 계산하기
		</a></li>

		<li><a href="di/myAvengers" target="_blank"> 어벤져스 히어로 </a></li>
		<li><a href="di/"></a></li>
		<li><a href="./di/myCar"> 마이카~ </a></li>
		<li><a href="./di/myAnnotation" target="_blank"> 어노테이션 </a></li>
		<!-- 
	Environment 객체를 이용한 외부파일 참조 절차
	1.요청명을 결정한다 
		ex) /environment/main1 
		
	2.요청명에 따른 매핑작업과 뷰생성 
		ex) 매핑작업을 위해서 컨트롤러 역할을 할 클래스 
			생성후 해당 요청을 받을 메소드를 생성한다.
		ex) @Controller 
		public class 클래스명 (
			@RequestMapping("/environment/main1") 
			public String 메소드명() {
				return "뷰경로/파일명";
				
			}
		)
	3.참조할 외부파일을 작성한다.
		ex) 생성경로 : /src/main/resources 하위 
		파일명 : EnvAdmin properties
		
	4.컨트롤러에서 외부파일을 읽어서 사용한다.
		
		4-1 ) 스프링 컨택스트 생성
		4-2 ) Environment 객체 생성 
		4-3 ) PropertySources 가져옴
		4-4 ) 외부파일을 가져와서 addLast 로 추가후 내용읽음
		4-5 ) 읽은 내용을 Model 에 저장하거나 비즈니스로직 에 직접 저장함 
		
		※ EnvironmentController 의 main1 () 메소드 참조 
	 -->
		<h3>Environment 객체를 이용한 외부파일 참조하기</h3>
		<li><a href="environment/main1" target="_blank"> 바로가기1 </a></li>
		<li><a href="environment/main2" target="_blank"> 바로가기2 </a></li>
		<li><a href="environment/main3" target="_blank"> 바로가기3 </a></li>
		<!-- 
	Spring JDBC 사용하기 위한 절차 
	:웹 어플리케이션을 제작할때 DataBase 사용을 위해 매번 같은 동작을 반복하는  부분이 있다 .
	주로 DAO 객체를 이용해서 드라이버로드 ,커넥션 생성 및 DB 연결, SQL 실행 , 자원해제 
	및 레코드를 관리한다.
	JDBCTemplate 을 이용하면 이런 반복적인 작업을 아주 짧은 코드로 처리할수 있다.
	
	사용절차 
	1.pom.xml 에 의존설정 
	<dependencys> 엘리먼트 하위에 설정한다.
	 Spring JSBC 을 위한 의존 설정   
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
			<version>4.1.4.RELEASE</version>
		</dependency>
		의존 설정후 관련 라이브러리가 다운로드 되었는지 확인한다 .
		
	2.Spring 설정파일에서 JDbctemplate 를 사용하기 위한 빈을 생성한다. servlet-context.xml 에서 설정한다.
		<beans:bean name="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
			Driver and URl 
			<beans:property name="driverClassName" value="oracle.jdbc.OracleDriver"/>
			<beans:property name="url" value="jdbc:oracle:thin:@localhost:1521:orcl"/>
			
			아이디 패스워드 부분 					
			<beans:property name="username" value="사용자 아이디"/>
			<beans:property name="password" value="사용자 패스워드"/>
			
		</beans:bean>	
		
		<beans:bean name = "template" class="org.springframework.jdbc.core.JdbcTemplate">
			<beans:property name="dataSource" ref="dataSource"/>
		</beans:bean>
	dataSource 빈을 1차로 생성한다. 해당빈은  DB 연결을 해주게 된다. 1차로 생성된 빈을 기반으로 template 빈을 생성한다.
	DB연결후 JDBCTemplate 클래스를 사용할 준비를 마친다. 
	이렇게 생성된 빈을 컨트롤러에서 주입 (Injection) 받아서 사용하기만 하면 된다.
	
	3. 컨트롤러에서 스프링 설정파일에서 생성한 JdbcTemplate 빈을 주입받기 위해 멤버변수와 setter() 를 추가한다 .
	Ex)
	BbsController 에 template 멤버변수와 setter() 추가됨 
	
	4.컨트롤러에서 빈을 자동으로 주입 받기 위해 setter() 에 
	@Autowired 어노테이션을 설정한다. <- 얘가 어떤 역할을 하느냐 하면 . 의존관계를 자동으로 설정해주는 역할을 한다. 
	" (type) 기반으로 한다 " 
	 생성자 , 필드 , 메소드에 적용 가능하다 
	 -setXXX() 의 형식이 아니어도 적용이 가능하다 
	 타입을 이용해 자동적으로 프로퍼티 값을 설정하므로 해당 타입의 빈객체가 존재하지 않거나 또는 
	 두개이상 존재할 경우 예외가 발생되므로 주의해야 한다.
	
	5.JdbcTemplate 을 웹 어플리케이션 전체에서 사용하기 위해서 static(정적) 타입의 변수를 생성후 빈을 대입한다.
	
	Ex)_ model.JDBCTemplateConst 클래스 생성 및  static타입의 멤버변수 생성 
	
	6.static Type 변수에다 주입하는 부분 필요하니  
	    컨트롤러에서 주입받은 빈을 static 타입의 변수에 재할당
	    setter() 메소드에서 아래코드 추가 
	    Ex) JDBCtemplateConst.template = this.template;
	    
	    
	
	7.JDBCTemplateDAO 클래스 생성 
	
	Ex) 생성자에 다음 코드 삽입
		this.template = JDBCTemplateConst.template;
	
	8. template 멤버변수를 통해  JDBCTemplate 클래스의 메소드 호출 
	
	Ex) queryForObject () , query() 등 . . .   
	
	요청 ㅡ> 디스패처서블렉 ( 서블렛컨텍스트 ) 빈생성처럼 .... 
	그담에 어플리케이션 실행을 위한 준비를 하면 이미 만들어져 있어서 준비하고 있어 주사기처럼 ..
	오토 와이어드 라고 하는 얘를 만나게 되면 컨테이너 안에서 찾아 . JDBCTemplate 타입의 빈이 있는지 
	딱 찾아. 그래서 컨테이너 안에 얘를 보면 쪽 빨아서 주입을 받는거야. 
	주사기를 누구한테 꽂을지 얼타는게 아니라 자동으로 주입받는다고 ㅇㅇㅇ 
	우리가 getBean 하지 않아도 오토와이어드 하면 자동으로 쓸수 있는거야 
	즉, 코드 한줄을 절약한거지 .
	GetBean 해서 자동주입 받았던게 그냥 같은타입이 있기만 하면 쪽 빨아서 사용하겠단 의미임
	
	Ex ) queryForObject() , query() 등 
	※JDBCTemplateDAO.java 에 관련 메소드 
 -->
		<h2>Spring 답변형 비회원제 게시판 제작</h2>
		<li><a href="board/list.do" target="_blank"> 커넥션풀과 커맨드 패턴을
				이용한 게시판 (SpringBbsDAO 사용) -> Spring JDBC(JDBCTemplate) 을 이용한 게시판으로
				컨버팅 (JDBCTemplateDAO 사용) </a></li>

		<%-- 
	Mybatis 사용절차 
	
	1.pom.xml 파일에 의존설정 .
 		 Mybatis 를 위한 의존설정 
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis</artifactId>
			<version>3.2.8</version>
		</dependency>
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis-spring</artifactId>
			<version>1.2.2</version>
		</dependency>
		
		관련 라이브러리가 자동으로 다운로드 된다. Maven 항목에서 확인한다.
		2.스프링 설정파일 servlet_contenxt.xml 에서 mybatis 빈을 생성하기 위한 코드를 입력한다.
		
		Mybatis 이용한 방명록 제작 2 차버전 
	    : Mybatis 로 컨버팅 하기 
	  
	 <beans:bean id = "sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
	 	
	 	1번 항목은 : DB 연결을 위한 dataSource 빈을 참조 
	 	<beans:property name="dataSource" ref="dataSource"/>
	 	
	 	2번쨰 항목은 : mapper 파일이 있는 위치를 설정, 여기서는 mybatis 하위의 mapper 패키지를 설정하였음 .
	 	mapper 하위에 있는 모든 xml 파일을 매퍼파일로 설정함. 
	 	<beans:property name="mapperLocations" value="classpath:mybatis/mapper/*.xml"/>
		</beans:bean>
		<beans:bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
			<beans:constructor-arg index="0" ref="sqlSessionFactory"/>
		</beans:bean>
		
		3. 컨트롤러에서 빈을 자동주입 받음.
		
		@Autowired
		private SqlSession sqlSession;
	
		4. sqlSessionFactory 빈을 생성할때 mapperLocations 에 지정한 위치에 매퍼파일을 생성함. 
		
			4-1 : XML 파일 을 생성 
			4-2 : http://blog.bybatis.org 에서 DOCTYPE 검색후 복사해서 붙여넣기한다.
				
			<!DOCTYPE mapper
			    PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
			    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
			4-3 : <mapper 엘리먼트 추가 
			<mapper namespace = "연결한 인터페이스의 패키지를 포함한 클래스명 " > 
			select id = "호출할 함수명 " resultType="반환타입">
			insert...<delete... <update 사용 
			</mapper>
			반환타입을 명시할때 주의사항 
				기본자료형인 경우 : int , double 과같이 타입명만 쓴다 .
			클래스(객체)인 경우 : 패키지명을 포함한 클래스명을 써야 한다.
			
			Mapper에서 파라미터 사용방법 
			방법1: param1 ,param2 ... 
			1 부터 순차적으로 증가하는 형태 
			select * from 테이블 where 컬럼 =#{param1} and 컬럼=#{param2}
			
			방법2: #{0} , #{1}  ... 
			0부터 시작하는 인덱스를 사용하는 형태 
			select -* from 테이블 where 컬럼=#{0} and 컬럼=#{1}
			
			방법3: 파라미터명을 그대로 사용하기위해 @param 어노테이션을 사용하는 형태 
			
			호출을 받는 Interface 의 추상메소드 정의시 
			public void 함수명 (@param("매퍼에서 사용할 이름") String파라미터명 );
			
			Mapper 에서 
			select * from 테이블 where 컬럼 =#{매퍼에서사용할 이름}
	 --%>

		<h3>Mybatis 를 이용한 방명록 제장</h3>
		<li><a href="mybatis/list.do"> 1차 버전[JDBCTemplate 사용] -> 2차
				버전 [Mybatis로 컨버팅] </a> <input type="hidden" name="nowPage" value="1" />
		</li>

		<h3>네이버 아이디로 로그인은 방명록에 구현해놨음</h3>

		<h3></h3>
		<h2>Spring Security - Step1(디폴트 페이지 사용)</h2>


		<!-- 
	
	Spring security 구현절차 . 
	1.pom.xml 의존설정을 먼져 해주지 
	
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-web</artifactId>
			<version>4.2.1.RELEASE</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-config</artifactId>
			<version>4.2.1.RELEASE</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-taglibs</artifactId>
			<version>4.2.1.RELEASE</version>
		</dependency>
		
	2.web.xml 스프링 설정파일 위치 추가 해주고 ? 
	
	<!-- spring security for xml add  
	<context-param>
		<param-name>contextConfigLocation</param-name> 
		<param-value>
		/WEB-INF/spring/root-context.xml
		/WEB-INF/spring/security-context.xml ㅡ> 이부분 추가 
		</param-value>
	</context-param>
	
	3.2번에서 설정한 위치에 security-context.xml 파일 생성 (생성후에 하고 싶다면 파일 하단의 namespace 탭 사용하여 추가)
	-intercept-url , form-login 등 관련항목은 해당 xml 파일 참조 
	
	4.web.xml 에 시큐리티를 사용하기 위한 필터설정 
	
	<filter>
		<filter-name>springSecurityFilterChain</filter-name>
		<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
	</filter>
	
	<filter-mapping>
		<filter-name>springSecurityFilterChain</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
	
	-JDBC 연동을 하지 않는다면 4번 까지 의 과정으로 처리 가능함.
	
	5.JDBC 연동을 한다면 dataSource 연결을 위해 
	root-context.xml 에서 해당 빈을 생 성후 처리 	
	-DB 연결을 위한 dataSource 빈은 servlet-context.xml 에서 생성하면 안댐 . 반드시 root-context.xml 에서 생성 
	해야함. 
	-root-context.xml 에서 엘리먼트 작성시 beans: 접두어를 생략해야함 . 해당 파일은 beans xmlns(xml name space) <ㅡ name space 가 없기 때문에 제거하지
	않으면 오류나니깐 ㅇㅇㅇ 
	
	-테이블 생성 및 컬럼명 등은 쿼리문 참조
	
	-root-context.xml 에서 엘리먼트 작성시 
	
	 -->




		<!-- 
			step2 와 3 은 security:authentication-manager 부분의 내용만 다름  
			2 단계 : 인증 정보를 코드에 표현함
			3 단계 : JDBC 를 이용하여 인증정보를 DB 에 저장후 사용함 
	-->
		<li><a href="./security2/index.do" target="_blank"> step2
				INDEX 페이지 바로가기 </a></li>

		<li><a href="./security2/login.do" target="_blank"> step2 로그인
				페이지 바로가기 </a></li>

		<li><a href="./security2/admin/main.do" target="_blank">
				step2 관리자영역 페이지 바로가기 </a></li>

		<li><a href="./security2/accessDenied.do" target="_blank">
				step2 접근불가안내 페이지 바로가기 </a></li>
		<!-- 
	
	파일 업로드 & 다운로드 구현절차
1.pom.xml 의존설정

		<dependency>
			<groupId>commons-io</groupId>
			<artifactId>commons-io</artifactId>  
			<version>2.0.1</version>
		</dependency>
		 <dependency>
			 <groupId>commons-fileupload</groupId>
			 <artifactId>commons-fileupload</artifactId>
			 <version>1.2.2</version>
		 </dependency>


2.servlet_context.xml에서 업로드 관련 빈 생성

<beans:bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		  업로드는  1 메가 까지 허용    
		<beans:property name="maxUploadSize" value="1000000"></beans:property>
	</beans:bean>

3.컨트롤러 제작(업로드폼, 업로드처리, UUID생성)

	FileuploadController.java 참조 
	UUID 클래스를 통해 생성되는 유니크한 32 글자의 문자 를 통해 중복되지 않는 파일명을 생성함 

4.각 폼과 처리결과 확인 페이지 작성

5.업로드된 폴더를 기반으로 파일리스트 작성하기

6.파일 다운로드를 위한 빈 생성

<beans:bean id = "fileDownloadView" class="common.FileDownloadView"/>
	<beans:bean id="fileViewResolver" class="org.springframework.web.servlet.view.BeanNameViewResolver" >
		<beans:property name="order" value="0" />
 
 	</beans:bean>
7.관련 클래스 생성과 다운로드 처리
 위 빈을 생성할때 common 패키지에 FileDownloadView 클래스가 미리 정의되어 있어야함 . 실제 파일 다운로드를 처리하는 클래스임.

	
	 -->


		<li><a href="./fileUpload/uploadPath.do" target="_blank"> 물리적
				경로 보기 </a></li>

		<h2>파일 업로드</h2>
		<li><a href="./fileUpload/uploadForm.do" target="_blank">
				파일업로드 폼 </a></li>

		<h2>파일 목록보기</h2>
		<li><a href="./fileUpload/uploadList.do" target="_blank"> 파일
				목록보기 </a></li>
		
		
		<!-- AOP 구현절차  
			1.pom.xml 의존설정 
			
			<dependency>
				<groupId>org.aspectj</groupId>
				<artifactId>aspectjweaver</artifactId>
				<version>1.7.4</version>
			</dependency>
			
			2.요청명 결정 하고  컨트롤러 매핑 해주고 
			
			3.AOP 설정파일 생성
			 리소스 폴더 하위에 xml 설정파일 생성 .
			 생성시 네임스페이스 aop 항목 추가할것 
			 <aop:config>
			 	<aop:aspect ref="참조할 빈 ">
			 		<aop:pointcut expression="적용범위">
			 		<aop:around(혹은 before 등) : advice실행방식 >
			 		
			 		--설정파일에 따라 공통기능 수행 
			
			리소스 폴더 하위에 xml 설정파일 생성.
			생성시 네임스페이스 aop 항목 추가할것. 
			
			4.공통기능을 수행할 클래스 생성 
			
			5.핵심 기능에서 사용할 클래스 생성 및 핵심 기능 구현 
			
			
		
		-->
		<h2>AOP 관점지향 프로그래밍</h2>
		
		<li>
			<a href="aop/main1.do" target="_blank">
				관점 지향 프로그래밍1 
			</a>
		</li>
		
		<li>
			<a href="aop/main2.do" target="_blank">
				관점 지향 프로그래밍2
			</a>
		</li>
		
		<!-- 
			트랙젝션의 개념 
			-인터넷 뱅킹의 경우 A 가 B 에게 송금을 진행하는 겨우우 A 에는 출금이되었으나 B 에겐 입금이 되지 않는 상황이 발생된다면 
			-해당 거래는 취소되어야 한다 . 
			이와같이 양쪽 모두 만족되어야 하나의 프로세스를 완료처리 할수 있도록 해주는 기법을 트랜잭션 이라고 한다.
			-즉 2개 이상의 쿼리를 하나의  " 커넥션 <- 에 묶어 DB 에 전송하고 " 이과정 "  에서 <에러> 가 발생하는 경우 -- 양쪽 모두 - 를 '원래'의 상태로 되돌려,
			 놓는것을 말한다.
		 -->
		
		<h2>트랙젝션 (Transaction)</h2>
		<li>
			<a href="transaction/buyTicketMain.do" target="_blank">
				티켓구매하기1
			</a>
		</li>
		
				<li>
			<a href="transaction/buyTicketTpl.do" target="_blank">
				티켓구매하기2
			</a>
		</li>		
		<!-- 
		카탈리나(Catalina) : 서블릿 컨테이너로서 자바 서블릿을 호스팅하는 환경이다.
		재스퍼(Jasper) : 톰캣의 JAP 컴포넌트이다. 실제로는 JSP 페이지의 요청을 처리하는 서블릿이다.
		톰캣(Tomcat) : 카탈리나, 재스퍼와 서버를 시작하고 멈추는 배치 파일들, 예제 어플리케이션 등으로 구성되어 있다.		
		 -->
		 
		 <h2>Spring Framework 에서 JSON 활용</h2>
		 <li>
		 	<a href="./jsonUse/jsonView.do" target="_blank">
		 		@ResponseBody 어노테이션 이용한 JSON 보기 
		 	</a>
		 </li>
		
		 <li>
		 	<a href="./jsonUse/board.do" target="_blank">
		 		 Ajax와 JSON 을 활용한 게시판 
		 	</a>
		 </li> 
		 
	</div>
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
</body>
</html>
