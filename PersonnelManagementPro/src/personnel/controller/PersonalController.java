package personnel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import personnel.pojo.Checkwork;
import personnel.pojo.Confidentialitycontract;
import personnel.pojo.Contract;
import personnel.pojo.Dept;
import personnel.pojo.Education;
import personnel.pojo.Employee;
import personnel.pojo.Job;
import personnel.pojo.Laborcontract;
import personnel.pojo.Salary;
import personnel.pojo.Sex;
import personnel.pojo.Status;
import personnel.pojo.Traincontract;
import personnel.pojo.User;
import personnel.service.PersonnelService;
import personnel.util.common.ShiroMD5Privacy;
import personnel.util.page.PageModel;

/**
 * 关于登录用户的控制器
 * 包括个人信息修改
 * 完善员工信息，修改信息等操作
 */

@Controller
public class PersonalController {
	
	@Autowired
	@Qualifier("PersonnelService")
	
	private PersonnelService personnelservice;//接口实现类对象
	
	//用户更新页面跳转
			@RequestMapping(value="/user/pedit",method=RequestMethod.GET)
			 public String edit(Model model,Integer id){
				if(id!=null){
					User user = personnelservice.get_UserInfo(id);
					user.setPassword(user.getPassword().substring(0, 6));
					model.addAttribute("user",user);
				}
				
				List<Status>  status_list = personnelservice.findAllStatus();
				model.addAttribute("status_list", status_list);
				return "/user/pedit";
			}
			
			
			//用户更新操作
			@RequestMapping(value="/user/pedit",method=RequestMethod.POST)
			 public String toedit(Integer status_id,Integer pageIndex,Model model,Integer id,@ModelAttribute User user){
				     //对明文密码进行加密
					PageModel pageModel = new PageModel();
					if(pageIndex != null){
							pageModel.setPageIndex(pageIndex);
					}
			    	user.setPassword(ShiroMD5Privacy.privacy(user.getLoginname(),user.getPassword()));
					personnelservice.update_UserInfo(user);
					List<User> user_list = personnelservice.get_UserList(user,pageModel);
					model.addAttribute("list",user_list);
					model.addAttribute("pageModel", pageModel);
					model.addAttribute("message", "个人信息修改成功");
					return "/user/pedit";
			}

			
           //查看个人信息 跳转plist页面，不允许修改
            @RequestMapping(value="/employee/plist",method=RequestMethod.GET)
            public String plist(Model model,Integer id) {
            	Employee employee = personnelservice.get_EmployeeInfo(id);
				model.addAttribute("employee",employee);
				List<Dept> dept_list = personnelservice.findAllDept();
				List<Job> job_list = personnelservice.findAllJob();
				List<Sex>  sex_list =  personnelservice.findAllSex();
				List<Education>  education_list =  personnelservice.findAllEducation();
				model.addAttribute("job_list", job_list);
				model.addAttribute("dept_list",dept_list);
				model.addAttribute("sex_list",sex_list);
				model.addAttribute("education_list",education_list);
            	return "/employee/plist";
            }
           //添加个人信息 跳转添加个人信息页面  跳转padd页面，
            //点击完善个人信息之后，这里需要去判断，如果过登录账号和员工信息已经关联，就不再跳出添加页面，提示信息已经完善
            @RequestMapping(value="/employee/padd",method=RequestMethod.GET) 
            public String padd(Model model,Integer id) {
            	Employee employee = personnelservice.get_EmployeeInfo(id);
            	if(employee!=null) {
            		model.addAttribute("message", "您的个人信息已经完善,请到个人信息页面查看");
            		return "/employee/plist";
            	}else {
            		List<Dept> dept_list = personnelservice.findAllDept();
    				List<Job> job_list = personnelservice.findAllJob();
    				List<Sex>  sex_list =  personnelservice.findAllSex();
    				List<Education>  education_list =  personnelservice.findAllEducation();
    				model.addAttribute("job_list", job_list);
    				model.addAttribute("dept_list",dept_list);
    				model.addAttribute("sex_list",sex_list);
    				model.addAttribute("education_list",education_list);
                	return "/employee/padd";
            	}
            	
            	
            }
            //提交员工信息，进行插入操作  添加成功之后跳转到plist页面
            @RequestMapping(value="/employee/padd",method=RequestMethod.POST)
			 public ModelAndView add(Model model,ModelAndView mv,@ModelAttribute Employee employee ,Integer id,Integer job_id,Integer sex_id,Integer dept_id
					 ,Integer education_id,Integer staticid){
				if(id!=null){
					//提交修改后的个人信息，完成修改业务，修改成功之后，跳转到plist页面
					this.genericAssociation(job_id, dept_id,sex_id,education_id,employee);
					personnelservice.update_EmployeeInfo(employee);
					mv.addObject("message", "成功完成更新操作！！！刷新个人信息查看");
				}else{
					//1.员工添加首先需要判断此员工是否存在，如果存在，将不能进行插入操作
					Integer n = personnelservice.get_EmployeeByName(employee.getName());
					if(n==null) {
						employee.setId(staticid);
						employee.setUser_id(staticid);
						this.genericAssociation(job_id, dept_id,sex_id,education_id,employee);
						personnelservice.insert_EmployeeInfo(employee);
						mv.addObject("message", "成功完成更新操作！！！刷新个人信息查看");
					}else {
						mv.addObject("message", "此员工已经存在！！！");
						mv.setViewName("redirect:/employee/padd");
						return mv;
					}
				}
				
				mv.setViewName("redirect:/employee/pedit");
				
				return mv;
			}
            
		   //修改个人信息界面 跳转到pedit页面
            @RequestMapping(value="/employee/pedit",method=RequestMethod.GET)
            public  String pedit(Model model,Integer id) {
            	Employee employee = personnelservice.get_EmployeeInfo(id);
				model.addAttribute("employee",employee);
				List<Dept> dept_list = personnelservice.findAllDept();
				List<Job> job_list = personnelservice.findAllJob();
				List<Sex>  sex_list =  personnelservice.findAllSex();
				List<Education>  education_list =  personnelservice.findAllEducation();
				model.addAttribute("job_list", job_list);
				model.addAttribute("dept_list",dept_list);
				model.addAttribute("sex_list",sex_list);
				model.addAttribute("education_list",education_list);
            	return "/employee/pedit";
            }
            
            
            
			//个人考勤
			@RequestMapping(value="/checkwork/pedit",method=RequestMethod.GET)
			 public String checkworkedit(Model model,Integer id){
				if(id!=null){
					Checkwork checkwork = personnelservice.get_CheckworkInfo(id);
					model.addAttribute("checkwork",checkwork);
				}
				List<Dept> dept_list = personnelservice.findAllDept();
				List<Job> job_list = personnelservice.findAllJob();
				model.addAttribute("job_list", job_list);
				model.addAttribute("dept_list",dept_list);
				return "/checkwork/pedit";
			}
			//个人薪酬
			@RequestMapping(value="/salary/pedit",method=RequestMethod.GET)
			 public String salaryedit(Model model,Integer id){
				if(id!=null){
					Salary salary = personnelservice.get_SalaryInfo(id);
					model.addAttribute("salary",salary);
				}
				List<Dept> dept_list = personnelservice.findAllDept();
				List<Job> job_list = personnelservice.findAllJob();
				model.addAttribute("job_list", job_list);
				model.addAttribute("dept_list",dept_list);
				return "/salary/pedit";
			}
			
			//个人合同
			@RequestMapping(value="/contract/pedit",method=RequestMethod.GET)
			 public String contractedit(Model model,Integer id){
				if(id!=null){
					Contract contract = personnelservice.get_ContractInfo(id);
					model.addAttribute("contract",contract);
				}
				List<Dept> dept_list = personnelservice.findAllDept();
				List<Job> job_list = personnelservice.findAllJob();
				List<Traincontract> traincontract_list = personnelservice.findAllTraincontract();
				List<Laborcontract> laborcontract_list = personnelservice.findAllLaborcontract();
				List<Confidentialitycontract> confidentialitycontract_list = personnelservice.findAllConfidentialitycontract();
				model.addAttribute("job_list", job_list);
				model.addAttribute("dept_list",dept_list);
				model.addAttribute("traincontract_list",traincontract_list);
				model.addAttribute("laborcontract_list",laborcontract_list);
				model.addAttribute("confidentialitycontract_list",confidentialitycontract_list);
				return "/contract/pedit";
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
