package personnel.controller;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import personnel.pojo.User;
import personnel.service.PersonnelService;
import personnel.util.common.ShiroMD5Privacy;

@Controller
public class FindPasswordController {
	
	@Autowired
	@Qualifier("PersonnelService")
	private PersonnelService personnelservice;
	
	@RequestMapping(value="/repassword")
	public String  FindPassword() {
		return "/repasswordPage";
	}
	@RequestMapping(value="/toFindPassword")
	public String toFindPassword(Model model,HttpSession session,User user, @RequestParam("user_input_verifyCode") String user_input_verifyCode) {
		  User user1 = personnelservice.findUserByLoginAndName(user.getLoginname(), user.getUsername());
		
			  if(!user_input_verifyCode.toLowerCase().equals(session.getAttribute("code"))){
				    model.addAttribute("message", "验证码错误!请重新输入！！！");
					
				    return "/repasswordPage";
			   }else {
				  model.addAttribute("user", user1);
				  return "/findPassword";
			  }
	}
	@RequestMapping(value="/rePassword")
	public String rePassword(User user) {
		  personnelservice.toUpdatePassword(user.getLoginname(),ShiroMD5Privacy.privacy(user.getLoginname(), user.getPassword()));
		  return "redirect:/loginForm";
	}
	// 服务器内部跳转到登录页面
}
