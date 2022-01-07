package personnel.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import personnel.pojo.Dept;
import personnel.pojo.Education;
import personnel.pojo.Employee;
import personnel.pojo.Job;
import personnel.pojo.Sex;
import personnel.pojo.User;
import personnel.service.PersonnelService;
import personnel.util.page.PageModel;

@Controller
public class EmployeeController {
	@Autowired
	@Qualifier("PersonnelService")
	private PersonnelService personnelservice;
	   //如果在目录下输入为空，则跳转到指定链接
		@RequestMapping(value="/employee/")
		 public ModelAndView index2(ModelAndView mv){
			mv.setViewName("employee/list");
			return mv;
		}
		// 如果在目录下输入任何不存在的参数，则跳转到list
		@RequestMapping(value="/employee/{formName}")
		 public String index2(@PathVariable String formName){
			String blank = "/employee/list";
			return blank;
		}
		
		@RequestMapping(value="/employee/list",method=RequestMethod.GET)
		 public String index(Integer pageIndex,Model model,String content,Employee employee){
			
			PageModel pageModel = new PageModel();
			if(pageIndex != null){
				pageModel.setPageIndex(pageIndex);
			}
			List<Employee> job_list = personnelservice.get_EmployeeList(employee,pageModel);
			Integer count = 0;
			if (content!=null&&!content.equals("")){
				count = personnelservice.countEmployee(content);
				job_list = personnelservice.get_EmployeeLikeList(content);
				
			}
			model.addAttribute("count", count);
			model.addAttribute("list",job_list);
			model.addAttribute("pageModel", pageModel);
			return "employee/list";
		}
		
		
		//先做关联校验，如果过失败，不在展示员工增加页面
		@RequestMapping(value="/employee/association")
		public String association() {
			//先去跳转到关联页面
			
			return "employee/association";
		}	
		
		//先做关联校验，如果过失败，不在展示员工增加页面
		@RequestMapping(value="/employee/toassociation",method=RequestMethod.POST)
		    public ModelAndView toassociation(String loginname,ModelAndView mv,HttpSession session,Model model) {
			
			User user = personnelservice.findUserByLogin(loginname);
			Employee employee=null;
			if(user!=null) {
				 employee = personnelservice.get_EmployeeInfo(user.getChe_id());
			}
			
		     //通过登录名去关联查找全局id
			if(user!=null&&employee==null) {
				//核心代码，如果关联成功，就需要拿到全局id，这个id给员工信息，将这个id存在mv中，前端页面直接从mv中获取全局id
				mv.addObject("user", user);
				System.out.println(user.getEmp_id());
				List<Dept> dept_list = personnelservice.findAllDept();
				List<Job> job_list = personnelservice.findAllJob();
				List<Sex>  sex_list =  personnelservice.findAllSex();
				List<Education>  education_list =  personnelservice.findAllEducation();
				model.addAttribute("job_list", job_list);
				model.addAttribute("dept_list",dept_list);
				model.addAttribute("sex_list",sex_list);
				model.addAttribute("education_list",education_list);
				mv.setViewName("/employee/add");
			}else {
				mv.addObject("message", "关联失败，没有此登录账号或此登录账号已经被添加");
				mv.setViewName("redirect:/employee/association");
				
			}
			return mv;	
		     
		}	
		
		
		
		@RequestMapping(value="/employee/add",method=RequestMethod.GET)
		 public String add(Model model,Integer id){
			if(id!=null){
				Employee employee = personnelservice.get_EmployeeInfo(id);
				model.addAttribute("employee",employee);
			}
			List<Dept> dept_list = personnelservice.findAllDept();
			List<Job> job_list = personnelservice.findAllJob();
			List<Sex>  sex_list =  personnelservice.findAllSex();
			List<Education>  education_list =  personnelservice.findAllEducation();
			model.addAttribute("job_list", job_list);
			model.addAttribute("dept_list",dept_list);
			model.addAttribute("sex_list",sex_list);
			model.addAttribute("education_list",education_list);
			return "/employee/add";
		}
		
		@RequestMapping(value="/employee/edit",method=RequestMethod.GET)
		 public String edit(Model model,Integer id){
			
			if(id!=null){
				System.out.println(id);
				Employee employee = personnelservice.get_EmployeeInfo(id);
				model.addAttribute("employee",employee);
			}
			
			List<Dept> dept_list = personnelservice.findAllDept();
			List<Job> job_list = personnelservice.findAllJob();
			List<Sex>  sex_list =  personnelservice.findAllSex();
			List<Education>  education_list =  personnelservice.findAllEducation();
			model.addAttribute("job_list", job_list);
			model.addAttribute("dept_list",dept_list);
			model.addAttribute("sex_list",sex_list);
			model.addAttribute("education_list",education_list);
			return "/employee/edit";
		}
		
		
		@RequestMapping(value="/employee/add",method=RequestMethod.POST)
		 public ModelAndView add(Model model,ModelAndView mv,@ModelAttribute Employee employee ,Integer id,Integer job_id,Integer sex_id,Integer dept_id
				 ,Integer education_id,Integer staticid){
			if(id!=null){
				this.genericAssociation(job_id, dept_id,sex_id,education_id,employee);
				personnelservice.update_EmployeeInfo(employee);
			}else{
				//1.员工添加首先需要判断此员工是否存在，如果存在，将不能进行插入操作
				Integer n = personnelservice.get_EmployeeByName(employee.getName());
				if(n==null) {
					employee.setId(staticid);
					employee.setUser_id(staticid);
					this.genericAssociation(job_id, dept_id,sex_id,education_id,employee);
					personnelservice.insert_EmployeeInfo(employee);
				}else {
					mv.addObject("message", "此员工已经存在！！！");
					mv.setViewName("redirect:/employee/add");
					return mv;
				}
				
			}
			mv.setViewName("redirect:/employee/list");
			return mv;
		}
		
		
		
		@RequestMapping(value="/employee/delete",method=RequestMethod.GET)
		 public void delete(Integer id){
			System.out.println(id);
			if(id!=null){
				personnelservice.delete_EmployeeInfo(id);
			}
		}
		
		
		//建立关联
		private void genericAssociation(Integer job_id,
				Integer dept_id,Integer sex_id,Integer education_id, Employee employee){
			if(job_id != null){
				Job job = new Job();
				job.setId(job_id);
				employee.setJob(job);
			}
			if(dept_id != null){
				Dept dept = new Dept();
				dept.setId(dept_id);
				employee.setDept(dept);
			}
			if(sex_id != null){
				Sex sex = new Sex();
				sex.setId(sex_id);
				employee.setSex(sex);
			}
			
			if(education_id != null){
				Education education = new Education();
				education.setId(education_id);
				employee.setEducation(education);
			}
			
		}
		
		
}
