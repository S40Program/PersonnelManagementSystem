package personnel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import personnel.pojo.UserVisit;
import personnel.service.PersonnelService;
import personnel.util.page.PageModel;

@Controller
public class UserVisitController {
	
	@Autowired
	@Qualifier("PersonnelService")
	private PersonnelService personnelservice;

	
	//查询所有用户显示用户列表
			@RequestMapping(value="/uservisit/list",method=RequestMethod.GET)
			 public String index(Integer pageIndex,Model model,String content,UserVisit userVisit){
				
				// 创建分页对象
				PageModel pageModel = new PageModel();
				if(pageIndex != null){
					pageModel.setPageIndex(pageIndex);
				}
				List<UserVisit> uservisit_list = personnelservice.get_UserVisitList(userVisit,pageModel);
				Integer count = 0;
				if (content!=null&&!content.equals("")){
					count = personnelservice.countUserVisit(content);
					uservisit_list = personnelservice.get_UserVisitLinkList(content);
				}
				model.addAttribute("count",count);
				model.addAttribute("list",uservisit_list);
				model.addAttribute("pageModel", pageModel);
				return "uservisit/list";
			}
			
			@RequestMapping(value="/uservisit/delete",method=RequestMethod.GET)
			 public void delete(Integer id){
				if(id!=null){
					personnelservice.delete_UserVisitInfo(id);;
				}
			}
}
