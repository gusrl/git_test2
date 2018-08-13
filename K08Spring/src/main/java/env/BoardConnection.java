package env;

public class BoardConnection {
	//멤버변수 
	private String user;//계정 아이디
	private String pass;//비밀번호 
	private String driver;//DB연결 드라이버 
	private String url;//DB연결 URL
	
	//getter/setter 
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
