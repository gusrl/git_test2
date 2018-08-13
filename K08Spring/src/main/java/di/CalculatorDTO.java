package di;

public class CalculatorDTO {
	//멤버변수 
	Calculator cal;
	private int firstNum;
	private int secondNum;
	
	//멤버변수 
	public double add() {
		return cal.adder(firstNum,secondNum);
	}
	public double sub() {
		return cal.sub(firstNum,secondNum);
	}
	public double mul() {
		return cal.multi(firstNum,secondNum);
	}
	public double div() {
		return cal.divide(firstNum,secondNum);
	}
	
	
	public Calculator getCal() {
		return cal;
	}
	public void setCal(Calculator cal) {
		this.cal = cal;
	}
	public int getFirstNum() {
		return firstNum;
	}
	public void setFirstNum(int firstNum) {
		this.firstNum = firstNum;
	}
	public int getSecondNum() {
		return secondNum;
	}
	public void setSecondNum(int secondNum) {
		this.secondNum = secondNum;
	}
	
}
