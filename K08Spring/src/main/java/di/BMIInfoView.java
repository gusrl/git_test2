package di;

import java.util.ArrayList;

public class BMIInfoView {
	//멤버변수 
	private String name;//이름
	private double height;//신장(키)
	private double weight;//몸무게
	private ArrayList<String> hobbys; //취미
	private BMICalResult bmiCalResult; //체질량 지수 계산기 
	
	public String bmiCalculation() {
		
		String bmiResult = bmiCalResult.bmiCalculation(weight, height);
		return bmiResult;
	}
	public String getInfo() {
		return String.format("이름:%s<br/>"
				+ "키:%s<br/>"
				+ "몸무게:%s<br/>"
				+ "취미:%s<br/> %s",
				 name,height,weight,hobbys,bmiCalculation());
		
	}
	//setter 정의 
	public void setName(String name) {
		this.name = name;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public void setHobbys(ArrayList<String> hobbys) {
		this.hobbys = hobbys;
	}
	public void setBmiCalResult(BMICalResult bmiCalResult) {
		this.bmiCalResult = bmiCalResult;
	}
	//getter 정의 
	public String getName() {
		return name;
	}
	public double getHeight() {
		return height;
	}
	public double getWeight() {
		return weight;
	}
	public ArrayList<String> getHobbys() {
		return hobbys;
	}
	public BMICalResult getBmiCalResult() {
		return bmiCalResult;
	}
	
}
