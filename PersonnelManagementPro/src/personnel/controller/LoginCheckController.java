package personnel.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import personnel.service.PersonnelService;


@Controller
public class LoginCheckController {
	@Autowired
	@Qualifier("PersonnelService")
    private PersonnelService personnelservice;
	String loginameMessage = "";

	@RequestMapping(value="checkLoginnameAndPassword",method = RequestMethod.POST)
    @ResponseBody
    public void checkLoginnameAndPassword(@RequestParam("loginname") String loginname, 
    		@RequestParam("password") String password,@RequestParam("user_input_verifyCode") String user_input_verifyCode,HttpServletResponse response) throws IOException{
    	response.setContentType("application/text;charset=utf-8");
		PrintWriter out = response.getWriter();
		String message = null;
    	if(loginname==""&&password=="") {
    		message = "登录名和密码不能为空,请重新输入";
        	out.write(message);
    	}else if(loginname!=""&&password=="") {
    		message = "密码不能为空,请输入您的密码";
        	out.write(message);
    	}else if(loginname!=""&&password!=""&&user_input_verifyCode=="") {
    		message = "校验码不能为空,请输入校验码";
    		out.write(message);
    	}else if(loginname==""&&password!=""){
    		message = "登录名不能为空,请重新输入";
        	out.write(message);
    	}else if(loginname!=""&& loginameMessage!="") {
    		message = "请更正你的登录名";
        	out.write(message);
    	}
    	else if(loginameMessage==""){
    		message = "";
    		out.write(message);
    	}
    }

    @RequestMapping(value="checkLoginname",method = RequestMethod.POST)
	@ResponseBody
	public void checkLogin(@RequestParam("loginname") String loginname,HttpServletResponse response) throws IOException {
		response.setContentType("application/text;charset=utf-8");
		PrintWriter out = response.getWriter();
        if(personnelservice.findUserByLogin(loginname)==null) {
        	loginameMessage = "非法用户,请您重新输入";
        	out.write(loginameMessage);
        }else {
        	loginameMessage ="";
        	out.write(loginameMessage);
        }
	}
}
