package springBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import springBoard.model.JDBCTemplateDAO;
import springBoard.model.SpringBbsDAO;

public class DeleteActionCommand implements BbsCommandImpl {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("req");
		
		//폼값받고 가자
		String idx = req.getParameter("idx");	
		String pass = req.getParameter("pass");	
		
		//DAO 만들어야지 
		//SpringBbsDAO dao = new SpringBbsDAO();
		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		//보내버려 
		dao.delete(idx,pass);		
	}
}

