package aop;

public class StudentVO {
	//학생을 표현하는 VO 클래스 
	private String name; //이름 
	private int age;	 //나이
	private String major;//전공과목
	private int gradeNum;//학년 
	
	//view 메소드 
	public void showStudent() {
		System.out.println("이름:"+getName());
		System.out.println("나이:"+getAge());
		System.out.println("전공과목:"+getMajor());
		System.out.println("학년:"+getGradeNum());
	}
	
	//getter/setter 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public int getGradeNum() {
		return gradeNum;
	}

	public void setGradeNum(int gradeNum) {
		this.gradeNum = gradeNum;
	}
	
	
	
}
