package springBoard.model;

import org.springframework.jdbc.core.JdbcTemplate;

//JDBCTemplate 을 웹어플리케이션 어디서나 사용할수 있도록 하기위해 
//static(정적) 타입의 변수를 생성한다.
public class JDBCTemplateConst {

	public static JdbcTemplate template;

}
