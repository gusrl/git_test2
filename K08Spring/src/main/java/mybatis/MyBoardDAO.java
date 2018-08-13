package mybatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.web.bind.annotation.RequestMapping;

public class MyBoardDAO {
	
	//기본생성자 
	public MyBoardDAO() {
		//DB 연결이 필요없음. 빈 만들었자나 이미끝나서 주입받아 쓰면되. servlet-context.xml 에 해놨거든
		//이미 생성한 template 빈을 주입받아 사용할 것이므로 별도의 DB 연결이 필요없음.
		
	}
	JdbcTemplate template;
	/*
	자동으로 끌어다 써주면 되기때문에 오토 와이어드만 생성해주면되지.
	이미 생성된 빈을 자동으로 주입받기 위한 멤버변수/setter 생성을 먼저하고 .
	@Autowired 을 위에 붙여주면 어노테이션은 빈의 타입을 기반으로 자동주입 받게 된다.
	*/
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	/*
	 Mybatis를 사용할때 Servlet-context.xml 에서 생성한 sqlSession 빈을 자동으로 주입받기 위해 생성한 
	 멤버변수
	 Autowired 어노테이션을 사용할대는 setter() 가 아니어도 타입만 일치하면 자동주입 받을수 있다.
	 */
	@Autowired
	private SqlSession sqlSession; // 빈생성을 잘해줘서 주입받을 준비가 잘 되어있다
	
	//방명록 리스트 
	public ArrayList<MyBoardDTO> list () { 
		
		String query = " select * from myboard order by idx desc ";
		ArrayList<MyBoardDTO> lists = 
				(ArrayList<MyBoardDTO>) 
				template.query(query, new BeanPropertyRowMapper<MyBoardDTO>(MyBoardDTO.class));
		return lists;
	}
	//로그인 처리 
	public MemberVO login(String id , String pass) {
		String sql = "select * from member where id = '"+id+"' and pass='"+pass+"' ";
		MemberVO vo =null;
		try {
			
			vo = template.queryForObject(sql, new BeanPropertyRowMapper<MemberVO>(MemberVO.class));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	//글쓰기 처리 
	public void write (final String name ,final String contents ,final String id) { 
 
		this.template.update(new PreparedStatementCreator() { 
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				
				String sql = " INSERT INTO myboard "
						+ " (idx,name,contents,id) "
						+ " VALUES "
						+ " (myboard_seq.nextval , ? , ? , ? )" ;
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setString(1, name);
				psmt.setString(2, contents);
				psmt.setString(3, id);
				return psmt;
			}
		});
	} 
	// 수정처리를 위한 view 메소드 
	public MyBoardDTO view(String idx, String id) {  
		MyBoardDTO dto = null;
		
		String sql = "select * from myboard where idx='"+idx+"'and id='"+id+"'";
		try {
		dto = template.queryForObject(sql,new BeanPropertyRowMapper<MyBoardDTO>(MyBoardDTO.class));
		}
		catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return  dto;
	}
	//수정 처리
	public int modify(final String idx , final String name, final String contents, final String id) {  
		
		String sql = "update myboard "
				+ " set name = ? , contents=? "
				+ " where idx= ? and id=? ";
		 this.template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				// TODO Auto-generated method stub
				psmt.setString(1, name);
				psmt.setString(2, contents);
				psmt.setInt(3, Integer.parseInt(idx));
				psmt.setString(4, id);
				
			}
		});
		 //메소드의 반환타입이 int 형 이므로 return 이 필요함.
		 return 0;
	}
	//삭제 처리
	public int delete(final String idx, final String id) {  
		String sql = "delete from myboard where idx = ? and id = ? ";
		this.template.update(sql,new PreparedStatementSetter() { 
			
			@Override
			public void setValues(PreparedStatement psmt) throws SQLException { 
				psmt.setInt(1, Integer.parseInt(idx));
				psmt.setString(2, id);
			}
		});
		return 0;
	}
	
	
}
