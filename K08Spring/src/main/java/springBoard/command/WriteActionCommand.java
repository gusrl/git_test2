package springBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;

import springBoard.model.JDBCTemplateDAO;
import springBoard.model.SpringBbsDAO;
import springBoard.model.SpringBbsDTO;

public class WriteActionCommand implements BbsCommandImpl {

	@Override
	public void execute(Model model) {
		
		//파라미터 한번에 전달받기
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = 
			(HttpServletRequest)paramMap.get("req");
		SpringBbsDTO springBbsDTO = 
				(SpringBbsDTO)paramMap.get("springBbsDTO");
		
		//폼값받기
		String name = req.getParameter("name");
		String title = req.getParameter("title");
		String contents = req.getParameter("contents");
		String pass = req.getParameter("pass");
		
		//커맨드객체 받아서 확인하기
		System.out.println("springBbsDTO.title="+springBbsDTO.getTitle());
		
		
		// 얘는 커넥션풀 사용한 DAO -> SpringBbsDAO dao = new SpringBbsDAO();
		
		//Spring JDBC (template) 사용한 DAO  
		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		
		dao.write(name, title, contents, pass);
		//dao.write(springBbsDTO);
		dao.close();		
	}
}
