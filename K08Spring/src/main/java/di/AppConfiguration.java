package di;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
  @Configuration : 해당 클래스를 스프링 설정파일로 사용하겠다는 선언으로 XML 설정 파일을 사용하는 대신 
  해당 java 파일을 설정 파일로 사용한다.
 */

@Configuration
public class AppConfiguration {
	
/*
   @Bean : 설정파일에서 빈을 생성할때 선언함.
      해당 빈은 메인클래스(컨트롤러)로 getBean() 메소드를 통해 주입된다.
*/
	@Bean
	public BMIInfoView member1 () {
		
		
		ArrayList<String> hobbys = new ArrayList<String>();
		
		hobbys.add("공부하기");
		hobbys.add("근성죠지기");
		
		BMIInfoView mem1 = new BMIInfoView();
		mem1.setName("신현기");
		mem1.setHobbys(hobbys);
		mem1.setHeight(175.5);
		mem1.setWeight(64);
		
		return mem1;
	}
	
	@Bean
	public BMIInfoView member2() {
		ArrayList<String> hobbys = new ArrayList<String>();
		hobbys.add("웨이크보드");
		hobbys.add("스노우보드");
		
		BMIInfoView mem2 = new BMIInfoView();
		mem2.setName("성유겸");
		mem2.setHobbys(hobbys);
		mem2.setWeight(20);
		mem2.setHeight(115);
		
		return mem2;
	}
}
