package com.kosmo.K08Spring;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mybatis.MemberVO;
import mybatis.MyBoardDAO;
import mybatis.MyBoardDTO;
import mybatis.MybatisDAOImpl;
import mybatis.MybatisMemberImpl;
import springBoard.model.PagingUtil;

@Controller
public class MybatisController {

	@Autowired
	private SqlSession sqlSession;

	MyBoardDAO dao; // 얘는 template 라는 빈을 만들었는데 Template 를 참조해서 그 기능을 그대로 쓰고싶은거야
					// beans 를 참조해서 MyBoardDAO 라고 DAO 타입의 변수가 있고 변수중에도 AutoWired 라는걸 건놈이있으면
					// 주입해주겠다 라는거지

	// 여기서도 오토 와이어트 붙여주지 . 이유는 MyBoardDAO 는 JDBCTemplate 을 기반으로 만들었던 템블릿 빈을
	// myboardDAO 라고 한들었어 . 그래서 컨트롤러쪽에선 자동으로 이미 생성된거 받아올라고 autowired 를 끌어오는거야.만약에
	// DAO
	// 호출하고 싶다. doa.list 쓸수있는거야 . new 연산자가 없어도 돼. 객체 == 빈 == 자바빈 == Object 다 똑같아.
	// 스프링 쪽으로 넘어오니깐 DI 라는 개념이있어서 xml 쪽에서 미리 생성해놓고 기다리고 있는 형태 "주입할라고"
	// 기능을 수행하는 XML 을 우리가 정의할수도 있고. 이미 있는거 갖다 쓸수도 있어 .
	// 주입 받을려면 두가지 , getBean , AutoWired 라는 두방법이 있어 . Autowired 는 타입을 기반으로 뭔가를 받아 .
	// 찾아보고 그 Bean 이 있으면 자동으로 땡겨와서 사용을 한다.

	@Autowired
	public void setDao(MyBoardDAO dao) {
		this.dao = dao;
	}

	// 결과적으로 new 를 한줄 줄여줄라고 그런거야 대신 자동으로 주입받도록 BEAN 을 미리 만들어야 명시적으로 쓸수있는거지 한마디로 아다리가
	// 맞아야하는것.!

	// 방명록 리스트

	@RequestMapping("mybatis/list.do")
	public String list(Model model, HttpServletRequest req) {
		// JDBCTemplate 사용
		// ArrayList<MyBoardDTO> lists = dao.list();

		// Mybatis 사용
		// ArrayList<MyBoardDTO> lists =
		// sqlSession.getMapper(MybatisDAOImpl.class).list();

		// mybatis 사용 - 페이지 처리
		int totalRecordCount = sqlSession.getMapper(MybatisDAOImpl.class).getTotalCount();

		int pageSize = 4;
		int blockPage = 2;

		int totalPage = (int) Math.ceil( 
								(double) totalRecordCount / pageSize );

		// 시작 및 끝 rownum 구하기
		int nowPage = req.getParameter("nowPage") == null ? 1 : Integer.parseInt(req.getParameter("nowPage"));
		int start = (nowPage - 1) * pageSize + 1;
		int end = nowPage * pageSize;

		ArrayList<MyBoardDTO> lists = sqlSession.getMapper(MybatisDAOImpl.class).listPage(start, end);
		// 컨텍스트 루트
		String pagingImg = PagingUtil.pagingImgServlet(totalRecordCount, pageSize, blockPage, nowPage,
				req.getContextPath() + "/mybatis/list.do?");
		model.addAttribute("pagingImg", pagingImg);

		// 줄바꿈 처리
		for (MyBoardDTO dto : lists) {
			String temp = dto.getContents().replaceAll("\r\n", "<br/>");
			dto.setContents(temp);
		}

		model.addAttribute("lists", lists);
		return "07Mybatis/list";
	}
					
	// 방명록 쓰기
	@RequestMapping("/mybatis/write.do")
	public String write(Model model, HttpSession session) {

		// 회원 로그인후 접근하도록 설정
		if (session.getAttribute("siteUserInfo") == null) {
			// 로그인 전이라면 로그인 페이지로 이동한다 .
			model.addAttribute("backUrl", "07Mybatis/write");
			return "redirect:login.do";
		}
		// 로그인 되있다면 쓰기 페이지를 호출한다.
		return "07Mybatis/write";
	}

	// 로그인 페이지
	@RequestMapping("/mybatis/login.do")
	public String login(Model model) {
		return "07Mybatis/login";
	}

	// 로그인처리
	@RequestMapping("/mybatis/loginAction.do")
	public ModelAndView loginAcation(HttpServletRequest req, HttpSession session) {

		// JDBCTemplate 사용
		/* MemberVO vo = dao.login(req.getParameter("id"), req.getParameter("pass")); */

		// mybatis 사용
		MemberVO vo = sqlSession.getMapper(MybatisMemberImpl.class).login(req.getParameter("id"), req.getParameter("pass"));

		ModelAndView mv = new ModelAndView();

		if (vo == null) {
			// 로그인 실패시
			mv.addObject("LoginNG", "아이디/패스워드가 틀렸습니다.");
			mv.setViewName("07Mybatis/login");
			return mv;
		} else {
			// 로그인 실패시
			session.setAttribute("siteUserInfo", vo);
		}
		// 로그인 후 페이지 이동
		String backUrl = req.getParameter("backUrl");

		if (backUrl == null || backUrl.equals("")) {
			// 로그인 실패시
			mv.setViewName("07Mybatis/login");
		} else {
			mv.setViewName(backUrl);
		}
		return mv;
	}

	// 글쓰기 처리
	@RequestMapping("/mybatis/writeAction.do")
	public String writeAction(Model model, HttpServletRequest req, HttpSession session) {
		// 로그인이 해제되었는지 확인후 작성 완료
		if (session.getAttribute("siteUserInfo") == null) {
			return "redirect:login.do";
		}
		// JDBCTemplate 사용
		// dao.write(req.getParameter("name"),req.getParameter("contents"),((MemberVO)session.getAttribute("siteUserInfo")).getId());
		// mybatis 사용
		sqlSession.getMapper(MybatisDAOImpl.class).write( req.getParameter("name"), req.getParameter("contents"), 
													((MemberVO) session.getAttribute("siteUserInfo")).getId());

		return "redirect:list.do";
	}

	// 로그아웃
	@RequestMapping("/mybatis/logout.do")
	public String logout(HttpSession session) {

		// 세션영역에 저장된 데이터를 지워준다.
		session.setAttribute("siteUserInfo", null);
		return "redirect:login.do";
	}

	// 수정하기
	@RequestMapping("/mybatis/modify.do")
	public String modify(Model model, HttpServletRequest req, HttpSession session) {
		// 로그인 확인
		if (session.getAttribute("siteUserInfo") == null) {  
			// model.addAttribute("backUrl","07Mybatis/modify");
			return "redirect:login.do";
		}
		// id 가 필요한 이유는 회원제 게시판이기떄문에 .
		/*
		 * MyBoardDTO dto =
		 * dao.view(req.getParameter("idx"),((MemberVO)session.getAttribute(
		 * "siteUserInfo")).getId()
		 * 
		 * );
		 */
		// Mybatis 사용
		MyBoardDTO dto = sqlSession.getMapper(MybatisDAOImpl.class).view(req.getParameter("idx"),
				((MemberVO) session.getAttribute("siteUserInfo")).getId());

		model.addAttribute("dto", dto);
		return "07Mybatis/modify";
	}

	// 수우정
	@RequestMapping("/mybatis/modifyAction.do")
	public String modifyAction(Model model, HttpServletRequest req, HttpSession session) {
		// JDBCTemplate 사용
		/*
		 * dao.modify(req.getParameter("idx"), req.getParameter("name"),
		 * req.getParameter("contents"),
		 * ((MemberVO)session.getAttribute("siteUserInfo")).getId());
		 */

		// Mybatis 사용
		sqlSession.getMapper(MybatisDAOImpl.class).modify(req.getParameter("idx"), req.getParameter("name"),
				req.getParameter("contents"), ((MemberVO) session.getAttribute("siteUserInfo")).getId());
		return "redirect:list.do";
	}

	// 삭제
	@RequestMapping("/mybatis/delete.do")
	public String delete(HttpServletRequest req, Model model, HttpSession session) { 

		// 로그인확인
		if (session.getAttribute("siteUserInfo") == null) {
			return "redirect:login.do";
		}
		// JDBCTemplate 사용
		/*
		 * dao.delete( req.getParameter("idx"),( (MemberVO)
		 * session.getAttribute("siteUserInfo") ).getId() );
		 */
		// mybatis사용
		sqlSession.getMapper(MybatisDAOImpl.class).delete(req.getParameter("idx"),
				((MemberVO) session.getAttribute("siteUserInfo")).getId());
		return "redirect:list.do";
	}
// ------------------------------------------------------------------------------------------------- 네이버 시벌 로그인
	// 네이버 아이디 로그인
	@RequestMapping("/mybatis/NaverLogin.do")
	public String isComplete(HttpSession session) {
		return "/07Mybatis/callback";
	}

	@RequestMapping("/mybatis/callback.do")
	public String navLogin(HttpServletRequest request) throws Exception {
		return "naver/callback";
	}

	@RequestMapping("/mybatis/personalInfo.do")
	public void personalInfo(HttpServletRequest request) throws Exception {
		String token = "YOUR_ACCESS_TOKEN";// 네이버 로그인 접근 토큰; 여기에 복사한 토큰값을 넣어줍니다.
		String header = "Bearer " + token; // Bearer 다음에 공백 추가
		try {
			String apiURL = "https://openapi.naver.com/v1/nid/me";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", header);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			System.out.println(response.toString());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
