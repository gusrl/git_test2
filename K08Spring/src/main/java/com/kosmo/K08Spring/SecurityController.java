package com.kosmo.K08Spring;

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	// step 1 : 디폴트 페이지 사용
	@RequestMapping("/security1/index.do")
	public String securityIndex1() {
		return "08Security/Step1/index";

	}

	// step 2 : 커스텀 페이지 사용
	@RequestMapping("/security2/index.do")
	public String securityIndex2() {
		//커스텀 인덳스 페이지
		return "08Security/Step2/index";

	}

	// step 2 : 로그인 페이지
	@RequestMapping("/security2/login.do")
	public String securityindex2Login() {
		return "08Security/Step2/login";

	}

	// step 3 : 접근 거부 페이지
	@RequestMapping("/security2/accessDenied.do")
	public String securityIndex2AccessDenied() {
		return "08Security/Step2/accessDenied";

	}

	// step 3 : 관리자 모드 메인 페이지
	@RequestMapping("/security2/admin/main.do")
	public String securityIndex2AdminMain() {
		return "08Security/Step2/adminMain";

	}
 
}
