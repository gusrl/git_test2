package springBoard.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;

import springBoard.model.JDBCTemplateDAO;
import springBoard.model.PagingUtil;
import springBoard.model.SpringBbsDAO;
import springBoard.model.SpringBbsDTO;

public class ListCommand implements BbsCommandImpl {

	/*
	 * BbsCommandImpl 인터페이스를 구현하므로 execute() 메소드는 무조건 오버라이딩 처리해야 한다.
	 */
	@Override
	public void execute(Model model) { 
		// System.out.println("ListCommand > execute()호출됨");

		// 컨트롤러에서 넘겨준 파라미터를 한번에 받기
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest) paramMap.get("req");

		// DAO객체 생성 [커넥션 풀 사용]
		//SpringBbsDAO dao = new SpringBbsDAO();

		//JDBCTemplateDAO 을 통한 DB 연결이후 사용하기 
		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		
		
		// 검색기능구현
		String addQueryString = "";
		String keyField = req.getParameter("keyField");
		String keyString = req.getParameter("keyString");
		if (keyString != null) {
			// 페이지번호에 링크를 걸기위한 추가 파라미터
			addQueryString = String.format("keyField=%s" + "&keyString=%s&", keyField, keyString);

			paramMap.put("Column", keyField);
			paramMap.put("Word", keyString);
		}

		// 전체 레코드 수 카운트하기
		int totalRecordCount = dao.getTotalCount(paramMap);

		// 페이지 처리를 위한 설정값 가져오기(우선은 변수로 처리)
		/*
		 * int pageSize = 4; int blockPage = 2;
		 */

		/*
		 * 페이지 처리를 위한 설정값을 외부파일(properties) 에서 가져오기 위한 변수 선언
		 */
		int pageSize = 0;
		int blockPage = 0;
		/*
		 * Environment객체를 활용한 외부파일에서 페이지 설정값 읽어오기
		 */
		/*
		 * 1.스프링 컨텍스트 파일을 생성함
		 */
		ConfigurableApplicationContext ctx = new GenericXmlApplicationContext();
		/*
		 * 2.Environment 객체를 생성함
		 */
		ConfigurableEnvironment env = ctx.getEnvironment();
		/*
		 * 3.PropertySources 를 가져온다.
		 */
		MutablePropertySources pSources = env.getPropertySources();
		try {
			/*
			 * 4.외부파일인 properties 파일을 가져와서 addLast로 추가한다.
			 */
			pSources.addLast(new ResourcePropertySource("classpath:SpringBoardInit.properties"));
			/*
			 * 5.해당 데이터를 getProperty로 읽어서 변수에 저장한다. 단 산술연산을 위해 int타입으로 캐스팅 후 사용해야 한다.
			 */
			pageSize = Integer.parseInt(env.getProperty("springBoard.pageSize"));
			blockPage = Integer.parseInt(env.getProperty("springBoard.blockPage"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 전체페이지수계산하기
		int totalPage = (int) Math.ceil((double) totalRecordCount / pageSize);

		// 시작 및 끝 rownum 구하기
		int nowPage = req.getParameter("nowPage") == null ? 1 : Integer.parseInt(req.getParameter("nowPage"));
		int start = (nowPage - 1) * pageSize + 1;
		int end = nowPage * pageSize;

		// 리스트 가져오기 위한 파라미터 저장
		paramMap.put("start", start);
		paramMap.put("end", end);

		// 출력할 리스트 가져오기
		ArrayList<SpringBbsDTO> listRows = dao.list(paramMap);
		
		//가상번호 계산하여 부여하기		
		int virtualNum = 0;
		int countNum = 0 ; 
		for(SpringBbsDTO row : listRows) {
			/*
			 게시물 일련번호 (idx) 는 DB 처리를 위해서 필요한 값이므로 , 게시판 리스트 번호는 전체레코드수를 기준으로 
			 하여 역순으로 출력해주는것이 좋다. 
			 model 이 반환해준 결과값을 다시 열어서 가상번호를 계산한후 재입력해준다
			 */
			virtualNum = totalRecordCount - (((nowPage-1)*pageSize) + countNum++) ;
			row.setVirtualNum(virtualNum);
			
			/*
			 답변글 들여쓰기 처리
			 : SpringBbsDAO 에서는 ResultSet 을 받아서 
			 	컬렉션에 저장하기전에 들여쓰기를 처리했으나 
			 	스프링  JDBC 에서는 모든과정이 자동으로 처리되기 때문에 
			 	결과를 받은후 재가공해야한다.
			*/
			String reSpace = "";
			if(row.getBindent() > 0 ) {
				//답변글 indent 만큼 들여쓰기 
				for(int i=0;i<row.getBindent();i++) {
					reSpace += "&nbsp;&nbsp;";
				}
				//제목앞에 스페이스와 아이콘 붙여주기
				row.setTitle(reSpace + "<img src='../common/re1.gif'>" + row.getTitle());
			}
		}

		// 페이지 처리를 위한 처리부분
		String pagingImg = PagingUtil.pagingImgServlet(totalRecordCount, pageSize, blockPage, nowPage,
				req.getContextPath() + "/board/list.do?" + addQueryString);
		model.addAttribute("pagingImg", pagingImg);
		model.addAttribute("totalPage", totalPage);// 전체페이지수
		model.addAttribute("nowPage", nowPage);// 현재페이지번호
		model.addAttribute("listRows", listRows);

		dao.close();

	}
}
