package transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TicketDAO {
	
	//스프링 JDBC 사용 
	JdbcTemplate template;
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	/*
	  트랜잭션 2에서 추가되는 부분 
	  : 트랜잭션 처리르 위한 참조변수와 세터설정 
	 */
	PlatformTransactionManager transactionManager;
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	//기본생성자 
	public TicketDAO() {
		System.out.println(template);
	}
	//티켓구매를 위한 메소드 
	public void buyTicket(final TicketDTO dto) {
		
		System.out.println("buyTicket()메소드 호출");
		System.out.println(dto.getCustomerId()+"님이 " +"티켓" + dto.getAmount() +"장을 " + "구매합니다. " );
		
		/*
		 트랜잭션 2 에서 추가하는 내용 
		 :트랜잭션을 사용하기 위한 객체생성 
		 */
		
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			//결제금액 처리
			template.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					 
					 String query = "INSERT INTO transaction_pay (customerId , amount) "
					 		+ " VALUES (? , ?) ";
					 PreparedStatement psmt = con.prepareStatement(query);
					 psmt.setString(1, dto.getCustomerId());
					 psmt.setInt(2, dto.getAmount()*10000);
					 return psmt;
				}
			});
			
			//티켓 구매 처리
			template.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				 
					String query = "insert into transaction_ticket (customerId, countNum) values(?,?)";
					PreparedStatement psmt = con.prepareStatement(query);
					psmt.setString(1, dto.getCustomerId());
					psmt.setInt(2, dto.getAmount());
					return psmt;
				}
			});
			System.out.println("카드결제와 티켓구매 모두 정상처리 되었습니다.");
			transactionManager.commit(status);
			}
			catch (Exception e) {
				//e.printStackTrace();
				System.out.println("제약조건을 위배하여 카드결제와 티켓구매 모두가 취소되었습니다.");
				transactionManager.rollback(status);
			}
	}
	
	
}
