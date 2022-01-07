package personnel.controller;

import java.util.List;



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
import personnel.pojo.Employee;
import personnel.pojo.Job;
import personnel.pojo.Insurance;
import personnel.pojo.Medicareinsurance;
import personnel.pojo.Endowmentinsurance;
import personnel.pojo.Accidentinsurance;
import personnel.service.PersonnelService;
import personnel.util.page.PageModel;

@Controller
public class InsuranceController {
	@Autowired
	@Qualifier("PersonnelService")
	private PersonnelService personnelservice;
	    //如果在目录下输入为空，则跳转到指定链接
		@RequestMapping(value="/insurance/")
		 public ModelAndView index2(ModelAndView mv){
			mv.setViewName("insurance/list");
			return mv;
		}
		// 如果在目录下输入任何不存在的参数，则跳转到list
		@RequestMapping(value="/insurance/{formName}")
		 public String index2(@PathVariable String formName){
			String blank = "/insurance/list";
			return blank;
		}
		
		@RequestMapping(value="/insurance/list",method=RequestMethod.GET)
		public String index(Integer pageIndex,Model model,String content,Insurance insurance){
			
			// 创建分页对象
			PageModel pageModel = new PageModel();
			if(pageIndex != null){
				pageModel.setPageIndex(pageIndex);
			}
						
			List<Insurance> job_list = personnelservice.get_InsuranceList(insurance,pageModel);
			Integer count = 0;
		    if(content!=null&&!content.equals("")){
			count = personnelservice.countInsurance(content);
			job_list = personnelservice.get_InsuranceLikeList(content);
			}
		    model.addAttribute("count",count);
			model.addAttribute("list",job_list);
			model.addAttribute("pageModel", pageModel);
			return "insurance/list";
		}
		
		@RequestMapping(value="/insurance/add",method=RequestMethod.GET)
		 public String add(Model model,Integer id){
			if(id!=null){
				Insurance insurance = personnelservice.get_InsuranceInfo(id);
				model.addAttribute("job",insurance);
			}
			List<Dept> dept_list = personnelservice.findAllDept();
			List<Job> job_list = personnelservice.findAllJob();
			model.addAttribute("job_list", job_list);
			model.addAttribute("dept_list",dept_list);
			return "/insurance/add";
		}
		
		@RequestMapping(value="/insurance/edit",method=RequestMethod.GET)
		 public String edit(Model model,Integer id){
			if(id!=null){
				Insurance insurance = personnelservice.get_InsuranceInfo(id);
				model.addAttribute("insurance",insurance);
			}
			List<Dept> dept_list = personnelservice.findAllDept();
			List<Job> job_list = personnelservice.findAllJob();
			List<Medicareinsurance> medicareinsurance_list = personnelservice.findAllMedicareinsurance();
			List<Endowmentinsurance> endowmentinsurance_list = personnelservice.findAllEndowmentinsurance();
			List<Accidentinsurance> accidentinsurance_list = personnelservice.findAllAccidentinsurance();
			model.addAttribute("job_list", job_list);
			model.addAttribute("dept_list",dept_list);
			model.addAttribute("medicareinsurance_list",medicareinsurance_list);
			model.addAttribute("endowmentinsurance_list",endowmentinsurance_list);
			model.addAttribute("accidentinsurance_list",accidentinsurance_list);
			return "/insurance/edit";
		}
		
		
		@RequestMapping(value="/insurance/add",method=RequestMethod.POST)
		 public ModelAndView add(ModelAndView mv,
				 Integer job_id,Integer dept_id,String employee_name
				 ,Integer medicareInsurance_id,Integer endowmentInsurance_id,Integer accidentInsurance_id,
				 @ModelAttribute Insurance insurance ,Integer id){
			Integer employee_id =null;
			if(id!=null){
				this.genericAssociation(job_id,medicareInsurance_id,endowmentInsurance_id, accidentInsurance_id,dept_id,employee_name,insurance);
				personnelservice.update_InsuranceInfo(insurance);
			}else{
				employee_id = personnelservice.get_EmployeeByName(employee_name);
				if(employee_id!=null&&personnelservice.get_InsuranceEmp_id(employee_id)==null) {
					 //根据employee_id去查询员工的信息，前端只需要添加社保即可。
				    Employee e =  personnelservice.get_EmployeeInfo(employee_id);
				    Integer job_id1 =e.getJob().getId();
				    Integer dept_id1 = e.getDept().getId();
				  //只需要将id设置为员工信息的id（即全局id即可）
				    insurance.setId(employee_id);
					this.genericAssociation1(job_id1,medicareInsurance_id,endowmentInsurance_id, accidentInsurance_id,dept_id1,employee_id,insurance);
					personnelservice.insert_InsuranceInfo(insurance);
					mv.setViewName("redirect:/insurance/list");
					return mv;
				}else{
					mv.addObject("message", "没有查到此员工，请联系管理员先添加此员工!");
					mv.setViewName("redirect:/insurance/add");
					return mv;
				}
				
			}
			mv.setViewName("redirect:/insurance/list");
			return mv;
		}
		
		@RequestMapping(value="/insurance/delete",method=RequestMethod.GET)
		 public void delete(Integer id){
			System.out.println(id);
			if(id!=null){
				personnelservice.delete_Insurance(id);
			}
		}
		
		
		private void genericAssociation(Integer job_id,Integer medicareInsurance_id,Integer endowmentInsurance_id,Integer accidentInsurance_id,
				Integer dept_id,String employee_name,Insurance insurance){
			if(job_id != null){
				Job job = new Job();
				job.setId(job_id);
				insurance.setJob(job);
			}
			if(dept_id != null){
				Dept dept = new Dept();
				dept.setId(dept_id);
				insurance.setDept(dept);
			}
			
			if(medicareInsurance_id != null){
				Medicareinsurance medicareinsurance = new Medicareinsurance();
				medicareinsurance.setId(medicareInsurance_id);
				insurance.setMedicareInsurance(medicareinsurance);
			}
			if(endowmentInsurance_id != null){
				Endowmentinsurance endowmentinsurance = new Endowmentinsurance();
				endowmentinsurance.setId(endowmentInsurance_id);
				insurance.setEndowmentInsurance(endowmentinsurance);
			}
			if(accidentInsurance_id != null){
				Accidentinsurance accidentinsurance = new Accidentinsurance();
				accidentinsurance.setId(accidentInsurance_id);
				insurance.setAccidentInsurance(accidentinsurance);
			}
			if(employee_name != null){
				Employee employee= new Employee();
				employee.setName(employee_name);
				insurance.setEmployee(employee);
			}
			
		}
		
		private void genericAssociation1(Integer job_id,Integer medicareInsurance_id,Integer endowmentInsurance_id,Integer accidentInsurance_id,
				Integer dept_id,Integer employee_id,Insurance insurance){
			if(job_id != null){
				Job job = new Job();
				job.setId(job_id);
				insurance.setJob(job);
			}
			if(dept_id != null){
				Dept dept = new Dept();
				dept.setId(dept_id);
				insurance.setDept(dept);
			}
			
			if(medicareInsurance_id != null){
				Medicareinsurance medicareinsurance = new Medicareinsurance();
				medicareinsurance.setId(medicareInsurance_id);
			    insurance.setMedicareInsurance(medicareinsurance);
			}
			if(endowmentInsurance_id != null){
				Endowmentinsurance endowmentinsurance = new Endowmentinsurance();
				endowmentinsurance.setId(endowmentInsurance_id);
				insurance.setEndowmentInsurance(endowmentinsurance);
			}
			if(accidentInsurance_id != null){
				Accidentinsurance accidentinsurance = new Accidentinsurance();
				accidentinsurance.setId(accidentInsurance_id);
				insurance.setAccidentInsurance(accidentinsurance);
			}
			if(employee_id != null){
				Employee employee= new Employee();
				employee.setId(employee_id);
				insurance.setEmployee(employee);
			}
			
		}
			
}
