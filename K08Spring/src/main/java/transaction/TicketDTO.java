package transaction;

public class TicketDTO {
	private String customerId; //구매자 아이디 
	private int amount; // 구매금액 
	
	//getter/setter 
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	
}
