package com.kosmo.K08Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import transaction.TicketDAO;
import transaction.TicketDTO;
import transaction.TicketTplDAO;

@Controller
public class TransactionController {
	
	/*
	 servlet-context.xml 에서 미리 생성된 빈을 자동으로 주입받음 @Autowired 
	 어노테이션은 Type 을 기반으로 자동으로 주입 받는다. 
	 */
	
	//주석친 이유는 2 단계에서 만들어논 빈을 주석을 쳤으니 에러나서 친거임 싸그리 처야함
	/*private TicketDAO dao;
	@Autowired
	public void setDao(TicketDAO dao) {
		this.dao = dao;
	}
 
	
	//티켓 구매하기 페이지 
	@RequestMapping("/transaction/buyTicketMain.do")
	public String buyTicketMain() {	
		
		return "11Transaction/buyTicketMain";
	}
	
	 

	//티켓 구매 처리 페이지 
	@RequestMapping("/transaction/buyTicketAction.do")
	public String buyTicketAction(TicketDTO ticketDTO , Model model) {
		//티켓 구매처리를 위한 DAO 호출 
		dao.buyTicket(ticketDTO);
		//뷰로 전달할 데이터 저장
		model.addAttribute("ticketInfo",ticketDTO);
		
		return "11Transaction/buyTicketAction";
	}
	*/   
	//트랜잭션 3단계에서 사용할  DAO 시작
	private TicketTplDAO daoTpl;
	@Autowired
	public void setDaoTpl(TicketTplDAO daoTpl) {
		this.daoTpl = daoTpl;
	}

	
	//트랜잭션 템플릿 이용한 티켓 구매 
	@RequestMapping("/transaction/buyTicketTpl.do")
	public String buyTicketTpl() {
		return "11Transaction/buyTicketTpl";
	}
	//티켓 구매 처리 페이지 
	@RequestMapping("/transaction/buyTicketTplAction.do")
	public String buyTicketTplAction(TicketDTO ticketDTO , Model model) {
		//티켓 구매처리를 위한 DAO 호출 
		boolean isBool=	daoTpl.buyTicket(ticketDTO);
		if(isBool == true) {
			model.addAttribute("successOrFail","티켓구매가 정상처리 되었습니다.");
		}else {
			model.addAttribute("successOrFail","티켓구매가 취소되었습니다 . 다시시도해주세요");
		}
		
		//뷰로 전달할 데이터 저장
		 model.addAttribute("ticketInfo",ticketDTO);
		
		return "11Transaction/buyTicketTplAction";
	}
	//트랜잭션 처리 3단계 E 
	
}
