package springBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import springBoard.model.JDBCTemplateDAO;
import springBoard.model.SpringBbsDAO;
import springBoard.model.SpringBbsDTO;

public class ModifyCommand implements BbsCommandImpl {

	@Override
	public void execute(Model model) {
		
		//파라미터 한번에 받기...
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest)paramMap.get("req");
		
		String idx = req.getParameter("idx");
		//커넥션풀을 사용한 DAO
		//SpringBbsDAO dao = new SpringBbsDAO();
				
		//JDBCTemplate 을 사용한 DAO 
		 JDBCTemplateDAO dao = new JDBCTemplateDAO();
		SpringBbsDTO dto = dao.view(idx);
		model.addAttribute("viewRow", dto);
		dao.close();
	}
}
