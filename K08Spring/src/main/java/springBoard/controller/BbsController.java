package springBoard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import springBoard.command.BbsCommandImpl;
import springBoard.command.DeleteActionCommand;
import springBoard.command.ListCommand;
import springBoard.command.ModifyActionCommand;
import springBoard.command.ModifyCommand;
import springBoard.command.ReplyActionCommand;
import springBoard.command.ReplyCommand;
import springBoard.command.ViewCommand;
import springBoard.command.WriteActionCommand;
import springBoard.model.JDBCTemplateConst;
import springBoard.model.JDBCTemplateDAO;
import springBoard.model.SpringBbsDAO;
import springBoard.model.SpringBbsDTO;

@Controller
public class BbsController {

	/*
	 Spring JDBC 를 사용하기위한 설정 
	 멤버변수 template 과 setter() 메소드 정의
	 */
	private JdbcTemplate template;
	//setter생성
	
	@Autowired 
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		
		System.out.println("@Autowired-> JDBCTemplate 연결성공 ");	
		//JDBCTemplate 을 전체에서 사용하기 위한 설정 
		
		/*   싱글톤 패턴 - 클래스 인스턴스가 하나만 만들어지도록 하고, 그 인스턴스에 대한 전역 접근을 제공한다.  */
		JDBCTemplateConst.template = this.template; //주입받은 static 타입을 다시 쓴다 ? 
		
		
	}
	/*
	 JDBC Template 연결확인을 위한 임시코드 [생성자]
	 ->연결 확인후 주석처리 
	 */
	//JDBCTemplateDAO dao = new JDBCTemplateDAO(); 
		
	/*
	 * BbsCommand 참조변수선언(클래스내에서 전역적으로 사용하기 위함) 게시판에서 사용할 모든 클래스는 아래 BbsCommandImpl
	 * 인터페이스를 '구현'한 후 정의할것임.
	 */
	BbsCommandImpl command = null;


	// 게시판 리스트보기 주소창에 직접 "/board/list.do" 경로를 입력하면 이 메소드가 실행
	@RequestMapping("/board/list.do")
	public String list(Model model, HttpServletRequest req) { 
		System.out.println("list() 메소드 호출됨");

		// 커넥션풀 연결 테스트(확인후 주석처리)
		// SpringBbsDAO dao = new SpringBbsDAO();

		model.addAttribute("req", req);
		command = new ListCommand(); // listCommand 객체 생성
		command.execute(model);

		return "06Board/list";
	}

	// 게시판 상세보기
	@RequestMapping("/board/view.do")
	public String view(Model model, HttpServletRequest req) {
		System.out.println("view() 메소드 호출됨");

		// 컨트롤러가 받은 파라미터 전체를 ListCommand로 넘기는 역활
		model.addAttribute("req", req);
		command = new ViewCommand();
		command.execute(model);

		return "06Board/view";
	}

	// 게시판 글쓰기
	@RequestMapping("/board/write.do")
	public String write(Model model) {
		// System.out.println("write() 메소드 호출됨");

		return "06Board/write";
	}

	// 게시판 글쓰기 처리
	@RequestMapping("/board/writeAction.do") // xml 역할 .
	public String writeAction(Model model, HttpServletRequest req, SpringBbsDTO springBbsDTO) {
		System.out.println("write() 메소드 호출됨");
		// System.out.println("springBbsDTO.title="+springBbsDTO.getTitle());

		// 모든 request 넘기기
		model.addAttribute("req", req);
		// 폼값을 커맨드객체로 받아서 넘기기
		model.addAttribute("springBbsDTO", springBbsDTO);
		command = new WriteActionCommand();
		command.execute(model);

		/*
		 * 컨트롤러에서 view에 대한 요청이 아닌 페이지이동을 할때에는 redirect를 사용한다. 새로운 글을 작성한 후의 이동이므로 무조건
		 * 1페이지로 간다.
		 */
		return "redirect:list.do?nowPage=1";
	}

	// 수정,삭제전 패스워드 확인페이지
	@RequestMapping("/board/password.do")
	public String password(Model model, HttpServletRequest req) { 
		// idx값만 Model에 저장한다.
		model.addAttribute("idx", req.getParameter("idx"));
		return "06Board/password";
	}

	// 패스워드를 전송후 검증
	@RequestMapping("/board/passwordAction.do") //요청명 
	public String passwordAction(Model model, HttpServletRequest req) {   
		/*
		 * 수정 or 삭제 여부에 따라 이동할 페이지가 다르므로 조건문으로 처리할 것임
		 */
		String modePage = null;

		// 파라미터받기
		String mode = req.getParameter("mode");
		String idx = req.getParameter("idx");
		String nowPage = req.getParameter("nowPage");
		String pass = req.getParameter("pass");

		//커넥션 풀을 이용한 DAO 
		//SpringBbsDAO dao = new SpringBbsDAO();
		
		//JDBCTemplate 을 이용한 DAO 
		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		
		
		int rowExist = dao.password(idx, pass);
		dao.close(); // 자원 반납

		if (rowExist <= 0) {
			// 패스워드가 일치하지 않는 경우
			// 다시 패스워드 입력페이지로 이동한다.

			model.addAttribute("isCorrMsg", "패스워드가 일치하지 않습니다");
			// model.addAttribute("mode", mode);
			model.addAttribute("idx", idx);

			modePage = "06Board/password";
		} else {
			// 패스워드가 일치하는 경우
			if (mode.equals("modify")) {
				// 수정이면 수정폼으로 이동한다. 
				model.addAttribute("req", req);
				command = new ModifyCommand();
				command.execute(model);

				modePage = "06Board/modify";
			} else if (mode.equals("delete")) {
				// 삭제면 삭제처리후 리스트로 이동한다.
				model.addAttribute("req", req);
				command = new DeleteActionCommand();
				command.execute(model);
				
				/*
				  컨트롤러에서 redirect 를 거는경우 페이지명 뒤에 쿼리스트링을 붙여야 한다면 model 에 해당 파라미터를 저장후 
				  아래처럼 redirect 하면된다. 그러면 자동으로 파일경로.do?쿼리스트링=값" 
				  형태로 이동된다.
				 */
				model.addAttribute("nowPage", req.getParameter("nowPage"));

				modePage = "redirect:list.do";
			}
		}

		return modePage;
	}

	// 수정처리
	@RequestMapping("/board/modifyAction.do")
	public String modifyAction(HttpServletRequest req, Model model ,SpringBbsDTO springBbsDTO) {   
		//커맨드 객체로 받은 폼값 확인하기 
		System.out.println("SpringBbsDTO[제목]="+springBbsDTO.getTitle());
		
		model.addAttribute("req", req);
		//커맨드 객체로 받은값을 command 로 넘겨주기 위해 Model 에 저장 
		command = new ModifyActionCommand();
		command.execute(model);

		// 수정처리후 상세보기 페이지로 이동함
		model.addAttribute("idx", req.getParameter("idx"));
		model.addAttribute("nowPage", req.getParameter("nowPage"));
		return "redirect:view.do";
	}

	// 답변글쓰기
	@RequestMapping("/board/reply.do")                              // 커맨드의 사용예.
	public String reply(HttpServletRequest req, Model model , SpringBbsDTO springBbsDTO) {   

		System.out.println("reply()메소드호출");
		/*답변글쓰기 폼에서 폼값을 커맨드 객체를 이용하여 한번에 받아 전달하기*/
		model.addAttribute("springBbsDTO",springBbsDTO);
		model.addAttribute("req", req);
		command = new ReplyCommand();
		command.execute(model);

		//답변글 작성후에는 리스트로 이동
		model.addAttribute("idx", req.getParameter("idx"));
		return "06Board/reply";
	}

	// 답변글처리
	@RequestMapping("/board/replyAction.do")
	public String replyAction(HttpServletRequest req, Model model,SpringBbsDTO springBbsDTO) {   
		
		System.out.println("replyAction() 메소드 호출");
		model.addAttribute("springBbsDTO",springBbsDTO);
	
		model.addAttribute("req", req);
		command = new ReplyActionCommand();
		command.execute(model);

		model.addAttribute("nowPage", req.getParameter("nowPage"));
		return "redirect:list.do";
	}

}
