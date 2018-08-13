package springBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;

import springBoard.model.JDBCTemplateDAO;
import springBoard.model.SpringBbsDAO;
import springBoard.model.SpringBbsDTO;

public class ModifyActionCommand implements BbsCommandImpl {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("req");
		SpringBbsDTO springBbsDTO = (SpringBbsDTO)map.get("springBbsDTO");
		//폼값확인 
		System.out.println("[command]springBbsDTO[내용]="+springBbsDTO.getContents());
		/*String idx = req.getParameter("idx");				
		String name = req.getParameter("name");
		String title = req.getParameter("title");
		String contents = req.getParameter("contents");
		String pass = req.getParameter("pass");*/
		
		//커넥션풀로 만든 DAO 
		//SpringBbsDAO dao = new SpringBbsDAO();		
		
		//JDBCTemplate 로 만든 DAO 
		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		//dao.modify(idx, name, title, contents, pass);
		dao.modify(springBbsDTO);
	}
}

