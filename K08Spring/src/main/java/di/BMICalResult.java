package di;

public class BMICalResult {
	//멤버변수 
	private double lowWeight; // 저체중 
	private double normal; // 정상 
	private double overWeight;//과체중
	private double obesity;//돼지 
	
	
	public void setLowWeight(double lowWeight) {
		this.lowWeight = lowWeight;
	}
	public void setNormal(double normal) {
		this.normal = normal;
	}
	public void setOverWeight(double overWeight) {
		this.overWeight = overWeight;
	}
	public void setObesity(double obesity) {
		this.obesity = obesity;
	}
	//체질량 지수를 계산을 위한 메소드 
	public String bmiCalculation(double weight,double height) {
		double h = height*0.01;
		double result = weight / (h*h);
		
		String resultStr = "당신의 BMI 지수는 ? " + (int)result;
		
		if(result > obesity) {
			resultStr += "<br/>씹돼지 입니다 ㅠㅠ";
		}
		else if(result > overWeight){
			resultStr += "<br/> 돼지새끼 입니다 ㅡㅜ";
		}
		else if(result > normal){
			resultStr += "<br/>정상입니다 ^^";
		}
		else {
			resultStr += "<br/>존나 배안고프냐 말입니다 . -ㅁ-;";
		}
		return resultStr;
	}	
}
