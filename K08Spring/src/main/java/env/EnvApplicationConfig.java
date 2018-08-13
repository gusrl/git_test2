package env;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/*
 @Configuration 언테이션으로 XML 설정파일의 역할을 대신하는 클래스로 정의함 
 */
@Configuration
public class EnvApplicationConfig {
	
	/*
	멤버변수 : 설정값을 @Value 어노테이션으로 정의한다
	 */
	@Value("${board1.user}")
	private String board_user; //계정아이디
	
	@Value("${board1.pass}")
	private String board_pass;//게정 패스워드
	
	@Value("${board2.driver}")
	private String board_driver;//접속 드라이버 
	
	@Value("${board2.url}")
	private String board_url;//접속 URL
	
	/*
	 외부파일 읽어오기 즉 , 읽기위해 만들었다.
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer
	properties() {
		//프로퍼티 파일을 읽기위한 객체생성
		PropertySourcesPlaceholderConfigurer config = new PropertySourcesPlaceholderConfigurer();
		//프로퍼티 파일의 위치를 설정하기 위한 Resource 타입의 객체 배열 생성 
		Resource [] loacations = new Resource[2];
							//↓ 얘를 통해     ㅡ>   접근할수 있는겨 classpath:XXXX.xml 이랑 똑같아.
		/*
		 classPath 로 지정된 resources 폴더를 지정하여 프로퍼티 파일의 위치를 설정함
		 */
		loacations[0] = new ClassPathResource("EnvBoard1.properties");
		loacations[1] = new ClassPathResource("EnvBoard2.properties");
		
		//설정된 위치를 참조변수로 전달하여 파일을 읽어곰
		config.setLocations(loacations);
		
		return config;
		
	}
	
	/* 프로퍼티 소스를 통해 읽어온값으로 빈을 생성함  */
	
	@Bean
	public BoardConnection boardConfig() {
		BoardConnection bconn = new BoardConnection();
		
		bconn.setUser(board_user);
		bconn.setPass(board_pass);
		bconn.setDriver(board_driver);
		bconn.setUrl(board_url);
		
		return bconn;
	}
}
