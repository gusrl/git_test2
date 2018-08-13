package springBoard.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

/*
 JdbcTemplate 관련 주요메소드 
 -List query (String sql , RowMapper rowMapper) : 여러개의 레코드를 반환하는 select 계열의 쿼리문인 경우 사용함 
 -List query (String sql , Object [] args , RowMapper rowMapper ) :인파라미터를 가진 여러개의 레코드를 반환하는 select 계열의 쿼리문인경우 사용함 
 	차이점 - 인파라미터가 있는경우 없는경우 
 		 - 오버로딩 차이 
 	배열형태로 넘길때 보통 Map 을 사용한다. 
 	Map 에 떄려박아 전달하는 방법 반대쪽에선 Map 뚜껑따서 전달받는다. 

	※ List 나 Set 보단 List 를 많이쓰는이유는 key 값을 가지고 있기 때문이다.
	명시적 이기 때문에.
	
	
 -int queryForInt (String sql) / queryForInt(String sql , Object[] args) 
 : 쿼리문의 실행결과 숫자를 반환하는 select 계열의 쿼리문인 경우 사용함 
 -Object queryForObject(String sql , RowMapper rowMapper ) 
 -Object queryForObject(String sql ,Object[] args , RowMapper  rowMapper) 
 :하나의 레코드를 반환하는 select 계열의 쿼리문 
 -int update(String sql) : 인파라미터가 없는 update/delete/insert 쿼리문인 경우 사용 
 :int update(String sql,Object [] args) : 인파라미터가 있는 update/delete/insert 쿼리문인 경우 사용 
  
  ※ 인파라미터는 Object 계열로 배열초기화를 통해 전달한다.
  Object[] args 는 인파라미터를 뜻함 
  ※queryForObject 메소드는 실행결과 레코드가 0개 이거나 2 개이상인 겨웅에 예외가 발생되므로 try~catch 문으로 처리해야함.
 */
public class JDBCTemplateDAO {
	// 멤버변수
	JdbcTemplate template;

	// 생성자
	public JDBCTemplateDAO() {
		this.template = JDBCTemplateConst.template; // 자동으로 주입을 하기 때문에 여기서부터 사용할 준비가 된다.
		System.out.println("JDBCTemplate 이용한 DB연결 성공 ");
	}

	// 자원반납
	public void close() {
		// JDBCTemplate 에서는 사용하지 않음.
	}

	// 전체목록 카운트 하기
	public int getTotalCount(Map<String, Object> map) { 
		int totalCount = 0;

		String query = "SELECT COUNT(*) FROM SPRINGBOARD ";

		if (map.get("Word") != null) {
			query += " WHERE " + map.get("Column") + " " + " 	LIKE '%" + map.get("Word") + "%' "; // My batis 는 쿼리문이
																									// XML 문으로 빠져
		}

		return template.queryForObject(query, Integer.class);
	}

	// 리스트 보기
	public ArrayList<SpringBbsDTO> list(Map<String, Object> map) // List 계열 이기때문 
	{

		int start = Integer.parseInt(map.get("start").toString());

		int end = Integer.parseInt(map.get("end").toString());

		String query = "" + "SELECT * FROM (" + "    SELECT Tb.*, rownum rNum FROM ("
				+ "        SELECT * FROM springboard ";

		if (map.get("Word") != null) {
			query += " WHERE " + map.get("Column") + " " + " LIKE '%" + map.get("Word") + "%' ";
		}

		query += " ORDER BY bgroup DESC, bstep ASC"

				+ "    ) Tb" + ")" + " WHERE rNum BETWEEN " + start + " AND " + end; // Map 뚜껑따서 쿼리문에 심는경우임 이게 .

		// 장점 : 코드가 간단하다
		// 단점 : 사용자가 로직을 정의해서 넣을수 없다.

		return (ArrayList<SpringBbsDTO>) template.query(query,
				new BeanPropertyRowMapper<SpringBbsDTO>(SpringBbsDTO.class));
	}

	// 글쓰기 처리1
	public void write(final String name, final String title, final String contents, final String pass) {
		try {
			// 한번쓰고 버리는 놈
			this.template.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					String sql = "INSERT INTO springboard (" + " idx, name, title, contents, hits "
							+ " ,bgroup, bstep, bindent, pass) " + " VALUES (" + " springboard_seq.NEXTVAL,?,?,?,0,"
							+ "	springboard_seq.NEXTVAL,0,0,?)";
					/*
					 * 얘는 원자성을 보장받는 놈이라 상수화 를 시켜줘야해 psmt.setString(1, 매변 ) <ㅡ 얘네들이 애러나니 미리 함수 머리부분의
					 * 매개변수에 final 붙여주고 가야해.
					 */

					PreparedStatement psmt = con.prepareStatement(sql);
					psmt.setString(1, name);
					psmt.setString(2, title);
					psmt.setString(3, contents);
					psmt.setString(4, pass);

					return psmt;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 게시물 상세보기
	public SpringBbsDTO view(String idx) {
		// 조회수증가
		updateHit(idx);

		SpringBbsDTO dto = null; // 당연히 하나있어야하고

		String sql = "SELECT * FROM springboard  WHERE idx=" + idx; // 아무래도 인파라메타로 쎗팅하면 글쓰기 떄 복잡해져 . 매변으로 넘어오는 idx 를 바로
																	// 꽂아줄라고
		/*
		 * 웹브라우져 주소창의 idx 값을 임의로 변경하는경우 예외가 발생될수 있으므로 아래와 같이 예외처리 해주는것이 좋다.
		 */
		try {
			dto = template.queryForObject(sql, new BeanPropertyRowMapper<SpringBbsDTO>(SpringBbsDTO.class)); // 얘를 해석하자면
			/*
			 * bean 객체 와 property 속성 두개는 . command 형태로 쓰고 있는건데 쿼리로 레코드를 가져온거지 그역할이
			 * queryForObject 이지 가져온걸 BeanPropertyRowMapper가 DTO 에 때려박아주는거야 get_title
			 * getContents 해서 게터 호출해서 값을 꽂아준걸 Row 에 매핑시켜주는거지.
			 */

			// 커맨드 객체에 대해서 다시한번 이해 . DTO 의 멤버변수랑 DB 상의 컬럼 이랑 멤버변수가
			// 똑같아야되. 일단 발동조건 자체가 그렇다고. 단순히 둘다 해줘~ . 도대체 뭘 ㅅㅂ .
			// 암튼 그래서 getter/setter 가 있는거야 Spring 이 자동으로 setter 를 통해서 자동으로
			// request.getParameter 가 해주는거지 .
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	// 게시물 조회수 증가
	public void updateHit(final String idx) {
		// 매개변수를 익명클래스에서 사용해야 하므로 값의 보장을 위해 final로 선언해야 한다.
		System.out.println("조회수 증가");
		String sql = " UPDATE springboard SET " + " hits=hits+1 " + " WHERE idx=? ";
		this.template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				psmt.setInt(1, Integer.parseInt(idx));

			}
		});
	}

	// 패스워드 검증
	public int password(String idx, String pass) {
		/*
		 * 패스워드 검증을 위해 select 한후 조건에 맞는 레코드가 있다면 게시물의 idx 값을 반환하고 없다면 0을 반환한다.
		 */
		int boardIdx = 0;
		String sql = "SELECT * FROM SPRINGBOARD WHERE pass=" + pass + " AND idx=" + idx;
		/*
		 * queryForObject 메소드는 실행결과가 1 개가 아니면 무조건 예외가 발생된다. 반드시 try~catch 로 처리해주는것이 좋다.
		 */
		try {
			SpringBbsDTO dto = template.queryForObject(sql,
					new BeanPropertyRowMapper<SpringBbsDTO>(SpringBbsDTO.class));
			boardIdx = dto.getIdx();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardIdx;

	}

	// 수정처리 (DTO)로 파라미터 받음
	public void modify(final SpringBbsDTO dto) {

		String sql = "UPDATE springboard   SET name=?, title=?, contents=?  WHERE idx=? and pass= ? ";
		this.template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {

				psmt.setString(1, dto.getName());
				psmt.setString(2, dto.getTitle());
				psmt.setString(3, dto.getContents());
				psmt.setInt(4, dto.getIdx());
				psmt.setString(5, dto.getPass());
			}
		});
	}

	// 삭제처리
	public void delete(final String idx, final String pass) {

		String sql = "DELETE FROM springboard WHERE idx=? AND pass=? ";
		this.template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				//내부 클래스 사용하고 끝 
				psmt.setString(1, idx);
				psmt.setString(2, pass);
			}
		 }
	  );
	}
	
	//답글처리 오버로딩 처리부분
	public void reply(final SpringBbsDTO dto){ 
		
		//답변글쓰기전 레코드 업데이트
		replyPrevUpdate(dto.getBgroup(), dto.getBstep());
		
		 
			String sql = "INSERT INTO springboard "
			+ " (idx, name, title, contents, pass, "
			+ "	bgroup, bstep, bindent) "
			+ " VALUES "
			+ " (springboard_seq.nextval, ?, ?, ?, ?,"
			+ " ?, ?, ?)";
			 
			this.template.update(sql,new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement psmt) throws SQLException {
					psmt.setString(1, dto.getName());
					psmt.setString(2, dto.getTitle());
					psmt.setString(3, dto.getContents());
					psmt.setString(4, dto.getPass());
					psmt.setInt(5, dto.getBgroup());
					psmt.setInt(6, dto.getBstep()+1);
					psmt.setInt(7, dto.getBindent()+1);
					
				}
			});
		  
	}
	//답변글 입력전 레코드 일괄 업데이트
	public void replyPrevUpdate(final int strGroup,final int strStep){ 
		 
			String query = "UPDATE springboard "
					+ " SET bstep=bstep+1 "
					+ " WHERE bgroup=? AND bstep>?";
			this.template.update(query,new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement psmt) throws SQLException {
					 
					psmt.setInt(1, strGroup);
					psmt.setInt(2, strStep);
				}
			});
	}	
	
}

