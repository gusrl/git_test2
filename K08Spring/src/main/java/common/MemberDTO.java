package common;

public class MemberDTO {
	private String name ;
	private String id ;
	private String pw ;
	private String email ;
	//멤버변수 추가하기
	private String mobile ;
	private String addr ; // 게터세터 없으니 당연히 받을수없고 출력도 할수가 없어.
	
	//getter setter 

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
}
