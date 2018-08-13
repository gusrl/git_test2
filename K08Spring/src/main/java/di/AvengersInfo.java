package di;

public class AvengersInfo {

	// 멤버변수
	private Avengers avengers;

	// 인자생성자 (constructor) 
	public AvengersInfo(Avengers avengers) {
		this.avengers = avengers;
	}

	//getter/setter 

	public Avengers getAvengers() {
		return avengers;
	}

	public void setAvengers(Avengers avengers) {
		this.avengers = avengers;
	}
	
	/*
	 캡틴 아메리카 빈은 
	 XML 설정파일에서 생성자를 이용하여 빈을 생성하므로 setter 는 없어도 무방하다.
	 하지만 아이언맨은 컨트롤러에서 setter 를 이용해서 설정하는 부분이 있으므로 아래코드가 필요함.
	 */
	public String AvengersView() {
		
		String returnStr = "";
		if(avengers!=null) {
			returnStr += String.format("본명:%s<br/>",avengers.getName());
			returnStr += String.format("히어오명:%s<br/>",avengers.getHeroName());
			returnStr += String.format("능력:%s<br/>",avengers.getAbility());
			returnStr += String.format("나이:%s<br/>",avengers.getAge());	
		}
		return returnStr;
	}
	

}
