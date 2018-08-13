package mybatis;

import java.sql.Date;

/*
 * 용어 정리 
 * VO(value Object) : DTO (Data TranseFor Object) 와 동일한 개념으로 데이터 저장을 
 * 목적으로 생성하는 객체를 말한다.
 */
public class MemberVO {
	//멤버변수 
	private String id ;
	private String pass;
	private String name;
	private java.sql.Date regidate;
	
	public MemberVO() {
		// TODO Auto-generated constructor stub
	}
	public MemberVO(String id, String pass, String name, Date regidate) {
		 
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.regidate = regidate;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public java.sql.Date getRegidate() {
		return regidate;
	}
	public void setRegidate(java.sql.Date regidate) {
		this.regidate = regidate;
	}
	
	
}
