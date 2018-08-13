package aop;

import org.aspectj.lang.ProceedingJoinPoint;

//공통기능을 수행할 클래스 
public class CommonAOP1 {

	public Object logViewAOP (ProceedingJoinPoint jointPoint)  throws Throwable {
		
		//현재 호출되는 메소드명을 문자열 형태로 변환후 반환해줌 즉 실행되는 메소드명을 알수있고 
		String joinSignStr = jointPoint.getSignature().toShortString(); //해석을 하면 현재 실행되고 있는 함수를 문자형태로 읽어지는거야 . 
		
		//advice 를 around 로 지정시 공통기능 수행부분 
		System.out.println("핵심기능"+joinSignStr+"수행전");
		
		//현재의 시스템의 시간을 가져온다.
		long startTime = System.currentTimeMillis();
		Object obj = null;
		
		try {
			//핵심 기능을 수행한다 . 핵심기능을 수행하는것을 
			//proxy 라고 표현한다.
			obj = jointPoint.proceed();
		} catch (Exception e) {
			//around 로 지정시 공통기능 수행부분 
			//수행중--[예외발생시]
			e.printStackTrace();
		}
		finally {
			//around 로 지정시 공ㄹ통기능 수행부분 
			//[핵심기능 수정전] 
			long endTime = System.currentTimeMillis();
			//핵심기능이 수행된 시간을 로그에 출력한다.
			System.out.println("핵심기능"+joinSignStr+"실행후");
			System.out.println(joinSignStr+" 가 실행된 경과시간:"+(endTime-startTime));
			System.out.println();
		}
		return obj;
	}
}
