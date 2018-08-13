package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CommonAOP2 {

	//공통기능이 동작할 범위와 아이디를 ㅈ비정함 그러므로 해당 함수는 실행부가 없어도 상관없음 
	@Pointcut("within(aop.*)")
	private void pointcutMethod() {}
	
	//공통기능의ㅏ  아이디로 지정한 실제메소드로 핵심기능 전 후 예외발생시 실행하겠다는 선언 
	@Around("pointcutMethod()")
	public Object loggerAOP(ProceedingJoinPoint jointPoint) throws Throwable {
		//해당 메소드의 실행내용은 핵심기능이 수행되는 소요시간을 구해서 반환함 .
		String si = jointPoint.getSignature().toShortString();
		Object obj = null;
		
		System.out.println(si+"실행 시작되씸");
		
		long sTime = System.currentTimeMillis();
		
		try {
			obj= jointPoint.proceed();
		}
		catch (Exception e) {  
			e.printStackTrace();
		}
		finally {
			long  eTime = System.currentTimeMillis();
			System.out.println(si+"실행종료");
			System.out.println(si+"실행경과시간 :" + (eTime-sTime));
		}
		return obj;
	}
	 
}
