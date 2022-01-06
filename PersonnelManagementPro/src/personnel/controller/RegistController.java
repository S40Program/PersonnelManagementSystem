package personnel.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import personnel.pojo.CreateStaticId;
import personnel.pojo.RegistCode;
import personnel.pojo.User;
import personnel.service.PersonnelService;
import personnel.util.common.Constants;
import personnel.util.common.RegistRandomCode;
import personnel.util.common.ShiroMD5Privacy;

/**
 * 用户注册控制器
 */
@Controller
public class RegistController {
	/**
	 * 注册时候，先跳转到注册码页面，如果正确，则可以注册，否则不可以
	 * @return
	 */
	@Autowired
	@Qualifier("PersonnelService")
	private PersonnelService personnelservice;//接口实现类对象
	
	private static int NUM = 0;
	private static int NUM1 = 0;
	
	
	//管理员点击自动生成注册码
	@RequestMapping("/toCreateCode")
	@ResponseBody
	/**
	 * @ResponseBody是作用在方法上的，@ResponseBody 表示该方法的返回结果直接写入 HTTP response body 中，
	 * 一般在异步获取数据时使用【也就是AJAX】，
	 * 在使用 @RequestMapping后，返回值通常解析为跳转路径，但是加上 @ResponseBody 
	 * 后返回结果不会被解析为跳转路径，而是直接写入 HTTP response body 中。 
	 * @return
	 */
	public Map<String, String>  toCreateCode(HttpServletResponse response,HttpSession session) {
		Map<String, String> map = new HashMap<String, String>();
		//这里分为两个用户 admin超级管理员 和manager管理员
		User user = (User) session.getAttribute(Constants.USER_SESSION);
		if(user.getLoginname().equals("admin")) {
			if(NUM<10) {
				String regist_code = RegistRandomCode.getRegisCode();
				map.put("code", regist_code);
				RegistCode registCode = new RegistCode();
				registCode.setCode(regist_code);
				personnelservice.addCode(registCode);
				System.out.println("注册码"+regist_code);
				NUM++;
			}else {
				map.put("code", "您的次数已经用完了");
			}
			return map;
		}else {
			if(NUM1<10) {
				String regist_code = RegistRandomCode.getRegisCode();
				
				map.put("code", regist_code);
				RegistCode registCode = new RegistCode();
				registCode.setCode(regist_code);
				personnelservice.addCode(registCode);
				System.out.println("注册码"+regist_code);
				NUM1++;
			}else {
				map.put("code", "您的次数已经用完了");
			}
			return map;
		}
		
	}
	
	
	//上面页面提交的注册码交给拦截器去判断是否正确
	@RequestMapping("/registCode")
	public String registCode() {
		String blank = "registCode";
		return blank;
	}	
	@RequestMapping("/toregistCode")
	public ModelAndView toregistCode(String registCode,ModelAndView mv) {
		if(personnelservice.findCode(registCode)!=null) {
			mv.setViewName("/regist");
		}else {
			mv.addObject("message", "注册码错误，请输入正确的注册码！");
			mv.setViewName("/registCode");
		}
		return mv;
	}
	
	//注册提交
	
	//提交完的用户数据已经经过校验，不再进行校验，直接进行插入数据库即可
	
	//这个地方需要生成一个id，这个id是为了给che_id,sal_id,con_id 提供的一个值
    //那这个id该如何去生成，怎么就能唯一呢
    //可以去随机生成一4位数的数字，生成之后，这个数字必须保证唯一，所以，先生成数字，再去user_inf表中去查找看存不存在
    //如果不存在，就可以将这个数字设置为id，否则，继续去生成，完成之后，就可以进行插入操作了
    //这里随机生成的数字不能确保唯一性，所以，定义一个全局的id，这个id初始化的值每次需要从数据库获取到，确保唯一
	
	//插入成功之后，这里执行更新工作，就是将这个值更新掉
	
	//在注册成功之后，我需要通过获取邮箱信息给用户发送邮件，告诉用户管理员会在2小时之内进行身份审核
	//审核完之后，会发送邮件给用户，用户就可以登录系统了
    //sendMail.sendEmail(javaMailSender,user);
	@RequestMapping("/register")
	public ModelAndView regist(ModelAndView mv,@ModelAttribute User user) {	
	    	user.setPassword(ShiroMD5Privacy.privacy(user.getLoginname(), user.getPassword()));	
		      CreateStaticId createStaticId = new CreateStaticId(personnelservice.getStaticId().getStaticId());
		      Integer staticId=createStaticId.getStaticId();
			    	user.setEmp_id(staticId);
			    	user.setChe_id(staticId);
			    	user.setCon_id(staticId);
			    	user.setSal_id(staticId);
			    	personnelservice.insert_UserInfo(user);			  
			   personnelservice.updata_staticId(staticId);		
			   mv.setViewName("/loginForm");
			   return mv;
	}
	
}
