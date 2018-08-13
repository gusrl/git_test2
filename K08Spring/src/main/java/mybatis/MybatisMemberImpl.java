package mybatis;

import org.springframework.ui.Model;

public interface MybatisMemberImpl {
	public MemberVO login(String id , String pass);
	
	void execute(Model model);
}
