package springBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import springBoard.model.JDBCTemplateDAO;
import springBoard.model.SpringBbsDAO;
import springBoard.model.SpringBbsDTO;

public class ReplyActionCommand implements BbsCommandImpl {

	@Override
	public void execute(Model model) {
		// request 한꺼번에 전달받기
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest) paramMap.get("req");
		
		
		//Model 에 저장된 DTO 객체 가져오기 
		SpringBbsDTO dto = (SpringBbsDTO)paramMap.get("springBbsDTO");
		 
		//커넥션 풀을 이용한 DAO 객체 생성
		//SpringBbsDAO dao = new SpringBbsDAO();
		
		//JDBCTemplate 로 가즈아 
		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		
		
		//폼값 받기 
		/*
		 * 커맨드 쓰자 String name = req.getParameter("name"); String title =
		 * req.getParameter("title"); String contents = req.getParameter("contents");
		 * String pass = req.getParameter("pass");
		 * 
		 * String bgroup = req.getParameter("bgroup"); String bstep =
		 * req.getParameter("bstep"); String bindent = req.getParameter("bindent");
		 */
		
//		dao.reply(name, title, contents, pass, bgroup, bstep, bindent);
		
		//커맨드 객체로 폼값받기  이렇게할라면 컨트롤러로 갔다온상태 여야해 
		
		dao.reply(dto);
		dao.close();
		
	}
}
