package personnel.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import personnel.pojo.User;
import personnel.pojo.UserVisit;
import personnel.service.PersonnelService;
import personnel.util.common.Constants;
import personnel.util.common.SessionSave;
import personnel.util.common.UserVisitSingleton;

@Controller
public class ExitController {
	@Autowired
	@Qualifier("PersonnelService")
	private PersonnelService personnelservice;
	
	@RequestMapping(value="/exit")
	public ModelAndView Exit(ModelAndView mv,HttpSession session) {
		mv.setViewName("forward:/loginForm");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println("退出系统时间："+df.format(new Date()));
		UserVisit us = UserVisitSingleton.getInstance();
		us.setExit_time(df.format(new Date()).toString());
		personnelservice.insert_UserVisitInfo(us);
		User user = (User) session.getAttribute(Constants.USER_SESSION);
		SessionSave.getSessionIdSave().remove(user.getLoginname());
		session.invalidate();
		return mv;
	}
}
