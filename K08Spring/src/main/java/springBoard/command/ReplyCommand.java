package springBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;

import springBoard.model.JDBCTemplateDAO;
import springBoard.model.SpringBbsDAO;
import springBoard.model.SpringBbsDTO;

public class ReplyCommand implements BbsCommandImpl {

	@Override
	public void execute(Model model) {
	
		Map<String, Object> map = model.asMap(); // asMap 은파라미터 한번에 받을때 사용하는것 ? 
		HttpServletRequest req = (HttpServletRequest)map.get("req");
	
		String idx = req.getParameter("idx");
		//커넥션 풀로 생성했지 
		//SpringBbsDAO dao = new SpringBbsDAO();
		//JDBCTemplate 로 가자 
		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		SpringBbsDTO dto = dao.view(idx);
		
				
		//제목처리
		dto.setTitle("[re]"+ dto.getTitle());

		//내용처리
		dto.setContents("\n\r\n\r---[원본글]---\n\r"+ dto.getContents());
			
		model.addAttribute("replyRow", dto);
		dao.close();
	}
}
