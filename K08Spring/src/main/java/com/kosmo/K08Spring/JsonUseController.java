package com.kosmo.K08Spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mybatis.MemberVO;
import mybatis.MyBoardDTO;
import mybatis.MybatisDAOImpl;
import springBoard.model.PagingUtil;

@Controller
public class JsonUseController {
	/*
	 * 언제 필요하나 , 업무를 제휴하는 경우 타회사쪽에서 데이타가 필요한데 . DB에 있는 내용을 JSON 으로 파싱해서 데이타를 내릴때.
	 */
	@Autowired
	private SqlSession sqlSession;

	// ResponseBody 어노테이션을 이용한 JSON 만들기
	@RequestMapping("/jsonUse/jsonView.do")
	@ResponseBody
	public Map<String, Object> responseBodyView() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("String", "나는 문자열이다");
		map.put("Number", 1004);
		map.put("Number", "@ResponseBody 슈퍼그뤠잇~!");

		ArrayList<String> list = new ArrayList<String>();
		list.add("Collection");
		list.add("모르면");
		list.add("스튜핏~!");

		map.put("Collection", list);

		return map;

	}

	// Ajax 와 JSON 을 활용한 게시판
	@RequestMapping("/jsonUse/board.do")
	public String board() {
		return "12JsonUse/board";
	}

	@RequestMapping("/jsonUse/aList.do")
	public String aList(Model model, HttpServletRequest req) {

		// mybatis 사용 - 페이지 처리
		int totalRecordCount = sqlSession.getMapper(MybatisDAOImpl.class).getTotalCount();

		int pageSize = 4;
		int blockPage = 2;

		int totalPage = (int) Math.ceil((double) totalRecordCount / pageSize);

		// 시작 및 끝 rownum 구하기
		int nowPage = req.getParameter("nowPage") == null ? 1 : Integer.parseInt(req.getParameter("nowPage"));
		int start = (nowPage - 1) * pageSize + 1;
		int end = nowPage * pageSize;

		ArrayList<MyBoardDTO> lists = sqlSession.getMapper(MybatisDAOImpl.class).listPage(start, end);
		// 페이지 처리를 위한 처리부분 
		String pagingImg = PagingUtil.pagingAjax(totalRecordCount, pageSize, blockPage, nowPage,"");
		model.addAttribute("pagingImg", pagingImg);

		// 줄바꿈 처리
		for (MyBoardDTO dto : lists) {
			String temp = dto.getContents().replaceAll("\r\n", "<br/>");
			dto.setContents(temp);
		}

		model.addAttribute("lists", lists);

		return "12JsonUse/aList";
	}
	//글 삭제처리
	@RequestMapping("/jsonUse/deleteAction.do")
	@ResponseBody //결과는 json 형태로 내려줄수있고 key 랑 value 로 json 을 내려줄수 있다.
	public Map<String, Object> deleteAction(HttpServletRequest req,  HttpSession session){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 로그인확인
		if (session.getAttribute("siteUserInfo") == null) {
			//로그인 실패 . 로그인 후 삭제처리 
			map.put("statusCode", 1);
			return map;
		}
		
		
		// mybatis사용시 delete 쿼리를 사용하게 되면 쿼리를 처리한 후 삭제된 행의 갯수를 반환하게 된다 . 
		//이를통해 성공/실패 여부를 판단할수 있다 ※mybatis 매퍼 파일 참조할것
		int result = sqlSession.getMapper(MybatisDAOImpl.class).delete(req.getParameter("idx"),
				((MemberVO) session.getAttribute("siteUserInfo")).getId());
		
		if(result<=0) {
			//삭제실패라는 코드는 0 
			map.put("statusCode", 0);
		}
		else {
			//삭제 성공이라면 코드는 2 
			map.put("statusCode", 2);
		}
		
		return map;
	}
}
