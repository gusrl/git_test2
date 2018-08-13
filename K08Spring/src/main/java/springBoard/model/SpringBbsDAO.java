package springBoard.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SpringBbsDAO {

	Connection con; //오라클 서버와 연결할때 사용
	PreparedStatement psmt;//오라클 서버와 쿼리전송 역활
	ResultSet rs;//쿼리의 결과를 받을때 사용
	
	public SpringBbsDAO() {		 
		try {
			Context ctx = new InitialContext();
			DataSource source = 
			  (DataSource)
			  ctx.lookup("java:comp/env/jdbc/myoracle");
			
			con = source.getConnection();
			System.out.println("Spring DBCP 연결성공");
		}
		catch(Exception e) {
			System.out.println("Spring DBCP 연결실패");
			e.printStackTrace();
		}		
	}
	
	public void close() { 
		try {
			if(rs!=null) rs.close();
			if(psmt!=null) psmt.close();
			if(con!=null) con.close();
		}
		catch(Exception e) {
			System.out.println("자원반납시 예외발생");
			e.printStackTrace();
		}
	}
	
	//전체목록 카운트 하기
	public int getTotalCount(Map<String, Object> map) 
	{
		int totalCount = 0;
		try{
			String sql = "SELECT COUNT(*) FROM springboard ";
										
			if(map.get("Word")!=null){
				sql +=" WHERE "+map.get("Column")+" "
					+ " 	LIKE '%"+map.get("Word")+"%' ";				
			}
			
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			totalCount = rs.getInt(1);
		}
		catch(Exception e){}
		
		return totalCount;		
	}
	
	//리스트 select 하기
	public ArrayList<SpringBbsDTO>list(Map<String, Object> map)     
	{
		ArrayList<SpringBbsDTO> bbs = 
				new ArrayList<SpringBbsDTO>();
		
		String sql = ""
			+"SELECT * FROM ("
			+"    SELECT Tb.*, rownum rNum FROM ("
			+"        SELECT * FROM springboard ";
			
		if(map.get("Word")!=null){
			sql +=" WHERE "+map.get("Column")+" "
				+ " LIKE '%"+map.get("Word")+"%' ";				
		}
		
		sql += " ORDER BY bgroup DESC, bstep ASC"
		//sql += " ORDER BY idx DESC"
		+"    ) Tb"
		+")"
		+" WHERE rNum BETWEEN ? and ?";
			
		System.out.println("쿼리문:"+sql);
						
		try{
			//3.prepare 객체생성 및 실행
			psmt = con.prepareStatement(sql);
						
			psmt.setInt(1, 
				Integer.parseInt(map.get("start").toString()));
			psmt.setInt(2, 
				Integer.parseInt(map.get("end").toString()));			

			rs = psmt.executeQuery();
			while(rs.next())
			{
				//4.결과셋을 DTO객체에 담는다.
				SpringBbsDTO dto = new SpringBbsDTO();
				
				//답변글처리를 위한 로직추가				 
				int indentNum = rs.getInt(9);
				String spacer = "";
				 
				if(indentNum>0){
					for(int i=1 ; i<=indentNum ; i++){
						spacer += "&nbsp;&nbsp;"; // 공백을 만들어서 안으로 한칸 밀어준다. 라는것 
					}
					spacer += spacer+"<img src='../common/re1.gif'>"; 
				} 
				 
				
				dto.setIdx(rs.getInt(1));
				dto.setName(rs.getString(2));				
				//답변글 처리 
				dto.setTitle(spacer + rs.getString(3));
				dto.setContents(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setHits(rs.getInt(6));
				dto.setBgroup(rs.getInt(7));
				dto.setBstep(rs.getInt(8));
				dto.setBindent(rs.getInt(9));
				dto.setPass(rs.getString(10));				
							
				
				//5.DTO객체를 컬렉션에 추가한다.
				bbs.add(dto);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		
		return bbs;		
	}
	
	//게시물 상세보기
	public SpringBbsDTO view(String idx) 
	{
		//조회수증가
		updateHit(idx);		
		
		SpringBbsDTO dto = null;
		
		String sql = "SELECT * FROM springboard "
				+ " WHERE idx=?";
		try{
			psmt = con.prepareStatement(sql);
			psmt.setString(1, idx);
			rs = psmt.executeQuery();
			if(rs.next()){
				dto = new SpringBbsDTO();
				
				dto.setIdx(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				/*
				내용부분은 줄바꿈 처리후 dto객체에 저장
				하는것이 좋으나, 해당 메소드를 "수정"
				에서 같이 사용하기 위해 줄바꿈처리는 
				command에서 하도록 한다. 
				*/
				dto.setContents(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setHits(rs.getInt(6));			
				
				//답변글달기 추가
				dto.setBgroup(rs.getInt(7));
				dto.setBstep(rs.getInt(8));
				dto.setBindent(rs.getInt(9));
				
				dto.setPass(rs.getString(10));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return dto;
	}
	
	//조회수증가
	public void updateHit(String idx) 
	{
		String sql = "UPDATE springboard SET "
			+ " hits=hits+1 "
			+ " WHERE idx=? ";
		try{
			psmt = con.prepareStatement(sql);
			psmt.setString(1, idx);
			psmt.executeUpdate();
		}
		catch(Exception e){}
	}	
	
	//글쓰기 처리1
	public void write(String name, String title, String contents, String pass)
	{
		try{			
			String sql = "INSERT INTO springboard ("
				+ " idx, name, title, contents, hits "
				+ " ,bgroup, bstep, bindent, pass) "
				+ " VALUES ("
				+ " springboard_seq.NEXTVAL,?,?,?,0,"
				+ "	springboard_seq.NEXTVAL,0,0,?)";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setString(2, title);
			psmt.setString(3, contents);
			psmt.setString(4, pass);
			
			psmt.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}
	//글쓰기 처리2 
	public void write(SpringBbsDTO dto)
	{
		try{			
			String sql = "INSERT INTO springboard ("
				+ " idx, name, title, contents, hits "
				+ " ,bgroup, bstep, bindent, pass) "
				+ " VALUES ("
				+ " springboard_seq.NEXTVAL,?,?,?,0,"
				+ "	springboard_seq.NEXTVAL,0,0,?)";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContents());
			psmt.setString(4, dto.getPass());
			
			psmt.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	//패스워드 검증
	public int password(String idx, String pass)   
	{
		int retNum = 0;
		
		try {			
			String sql = "SELECT * FROM springboard "
					+ " WHERE pass=? AND idx=?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, pass);
			psmt.setString(2, idx);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				/*
				조건에 맞는 게시물이 있다면 idx값은
				무조건 1이상의 값을 가지게 되므로 0인지
				그 이상인지만 확인하면 된다.
				*/
				retNum = rs.getInt(1);
			}
		}
		catch(Exception e) {
			//쿼리실행시 예외가 발생하더라도 false반환
			retNum = 0;
			e.printStackTrace();
		}
			
		return retNum;
	}
	
	//수정처리
	public void modify(String idx, String name, String title, String contents, String pass){  
		
		try{ 
			String sql = "UPDATE springboard "
					+ " SET name=?, title=?, contents=? , pass= ? "
					+ " WHERE idx=?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setString(2, title);
			psmt.setString(3, contents);
			psmt.setString(4, pass);
			psmt.setInt(5, Integer.parseInt(idx));    
			
			int rn = psmt.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		} 
	}
	//수정처리 (DTO)로 파라미터 받음  
	public void modify(SpringBbsDTO dto){ 
		
		try{ 
			String sql = "UPDATE springboard   SET name=?, title=?, contents=?  WHERE idx=? and pass= ? ";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContents());
			psmt.setInt(4, dto.getIdx()); 
			psmt.setString(5, dto.getPass());
			
			psmt.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//삭제처리
	public void delete(String idx){    
		
		try{
			String sql = "DELETE FROM springboard WHERE idx=?";
			psmt = con.prepareStatement(sql);			
			psmt.setInt(1, Integer.parseInt(idx));		
			
			int rn = psmt.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void delete(String idx,String pass) { 
		
		try{
			String sql = "DELETE FROM springboard WHERE idx=? and pass=? ";
			psmt = con.prepareStatement(sql);			
			psmt.setInt(1, Integer.parseInt(idx));	
			psmt.setString(2, pass);
			
			int rn = psmt.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//답글쓰기폼
	public SpringBbsDTO reply(String idx){   
				
		SpringBbsDTO dto = null;
		try{
			String sql = "SELECT * FROM springboard WHERE idx=?";
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, Integer.parseInt(idx));
			rs = psmt.executeQuery();
			if(rs.next()){				
				dto = new SpringBbsDTO();
								
				dto.setIdx(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setPostdate(rs.getDate(5));
				dto.setHits(rs.getInt(6));
				dto.setBgroup(rs.getInt(7));
				dto.setBstep(rs.getInt(8));
				dto.setBindent(rs.getInt(9));
				dto.setPass(rs.getString(10));				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return dto;
	}

	//답글처리
	public void reply(String name, String title, String contents,String pass, String bgroup, String bstep, String bindent){   
		
		//답변글쓰기전 레코드 업데이트
		//replyPrevUpdate(bgroup, bstep);
		
		//답변글 입력
		try{
			String sql = "INSERT INTO springboard "
					+ " (idx, name, title, contents, pass, "
					+ "	bgroup, bstep, bindent) "
					+ " VALUES "
					+ " (springboard_seq.nextval, ?, ?, ?, ?,"
					+ " ?, ?, ?)";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setString(2, title);
			psmt.setString(3, contents);
			psmt.setString(4, pass);
			
			//답글은 기존글에 bstep+1, bindent+1 해준다.
			//bgroup : 원본글의 idx값을 입력받게 되어 같은 그룹으로 처리됨
			//bstep : 같은 그룹내에서의 정렬순서
			//bindent : 답변글의 깊이(1이라면 첫번째 답변글임)
			psmt.setInt(5, Integer.parseInt(bgroup));
			psmt.setInt(6, Integer.parseInt(bstep) + 1);
			psmt.setInt(7, Integer.parseInt(bindent) + 1);
			
			int rn = psmt.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	//답글처리 오버로딩 처리부분
	public void reply(SpringBbsDTO dto){ 
		
		//답변글쓰기전 레코드 업데이트
		replyPrevUpdate(dto.getBgroup(), dto.getBstep());
		
		//답변글 입력
		try{
			String sql = "INSERT INTO springboard "
			+ " (idx, name, title, contents, pass, "
			+ "	bgroup, bstep, bindent) "
			+ " VALUES "
			+ " (springboard_seq.nextval, ?, ?, ?, ?,"
			+ " ?, ?, ?)";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContents());
			psmt.setString(4, dto.getPass());
			
			//답글은 기존글에 bstep+1, bindent+1 해준다.
			//bgroup : 원본글의 idx값을 입력받게 되어 같은 그룹으로 처리됨
			//bstep : 같은 그룹내에서의 정렬순서
			//bindent : 답변글의 깊이(1이라면 첫번째 답변글임)
			psmt.setInt(5, dto.getBgroup());
			psmt.setInt(6, dto.getBstep() + 1);
			psmt.setInt(7, dto.getBindent() + 1);
			
			int rn = psmt.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}
	//답변글 입력전 레코드 일괄 업데이트
	public void replyPrevUpdate(int strGroup, int strStep){ 
		/*
		 * 현재 답변글이 작성되는 위치(bstep)를 확인하여 
		 * 해당 위치보다 큰 레코드를 일괄적으로 +1 처리한다.
		 */
		try{ 
			//현재 그룹번호 내에서 내뒤로 들어가있는 아이들을 하나씩 업데이트 해주게써 라는 쿼리 .
			String query = "UPDATE springboard "
					+ " SET bstep=bstep+1 "
					+ " WHERE bgroup=? AND bstep>?";
			psmt = con.prepareStatement(query);
			psmt.setInt(1, strGroup);
			psmt.setInt(2, strStep);
			
			int rn = psmt.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}	
}








