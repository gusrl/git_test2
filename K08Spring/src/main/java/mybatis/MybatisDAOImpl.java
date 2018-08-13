package mybatis;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

public interface MybatisDAOImpl {
	//방명록 리스트
	public ArrayList<MyBoardDTO> list();
	
	//방명록쓰기 
//	public void write (String name , String contents , String id) { 
	
	//ibatis 에선 파라미터 처리를 이렇게 어노테이션으로 처리해
	public void write( 
			@Param("_name") String name , 
			@Param("_contents") String contents ,
			@Param("_id") String id 
			);	
	
	//방명록 수정에서 내용가져오기
	public MyBoardDTO view(String idx, String id);
	
	//방명록 수정처리 
	public int modify(  String idx ,   String name,   String contents,   String id);
	
	//방명록 삭제처리 
	public int delete(  String idx,   String id);
	
	//방명록 게시판 페이지 처리
	//1.게시물 수 카운트하기 
	public int getTotalCount();
	
	//2.start , end 값을 받아서 select 하기 
	public ArrayList<MyBoardDTO> listPage(int s , int e);

	
}
