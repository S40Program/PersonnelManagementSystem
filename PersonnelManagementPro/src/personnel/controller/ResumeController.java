package personnel.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import personnel.pojo.Education;
import personnel.pojo.Recruitment;
import personnel.pojo.Resume;
import personnel.pojo.Sex;
import personnel.pojo.Status;
import personnel.service.PersonnelService;
import personnel.util.page.PageModel;

@Controller
public class ResumeController {

	@Autowired
	@Qualifier("PersonnelService")
	private PersonnelService personnelservice;

	@RequestMapping(value="/resume/add",method = RequestMethod.GET)
    public String add(Model model,Integer id) {
		// 通过招聘信息的id查出该条招聘信息
		Recruitment recruitment = personnelservice.get_RecruitmentById(id);
		
		List<Sex>  sex_list =  personnelservice.findAllSex();
		List<Education>  education_list =  personnelservice.findAllEducation();
		model.addAttribute("sex_list",sex_list);
		model.addAttribute("education_list",education_list);
		model.addAttribute("recruitment",recruitment);
		return "/recruitment/resume/add";
	}
	
	
	//简历投递页面
    @ResponseBody
	@RequestMapping(value="/resume/add",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
    public String resumeadd(ModelAndView mv,@ModelAttribute Resume resume ,Integer id,
    		HttpSession session,Integer sex_id,Integer education_id) throws Exception {
		
		/**
		 * 简历上传
		 */
		 String path = session.getServletContext().getRealPath("/WEB-INF/resume");
		 String filename = resume.getFile().getOriginalFilename();
		
		 File tempFile = new File(path+"/"+filename);
		 tempFile.createNewFile();  
		 resume.getFile().transferTo(tempFile);
		 resume.setFilename(filename);
		 
		 Sex sex = new Sex();
		 sex.setId(sex_id);
		 resume.setSex(sex);
		 
		 Education education = new Education();
		 education.setId(education_id);
		 resume.setEducation(education);
		 //将投递简历的信息存入数据库
		 personnelservice.insert_Resume(resume);
		 
		return "您的简历我们已经收到，我们会在招聘结束后统一组织简历筛选，请您随时关注邮箱信息，我们会在第一时间给你通知面试结果！";
	}
    
    
    //申请情况  /resume/list
    @RequestMapping(value="/resume/list",method=RequestMethod.GET)
    public String recruitment(Integer pageIndex,Model model,String content,Resume resume) {
		
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		List<Resume> resume_list = personnelservice.get_ResumeList(resume,pageModel);
		Integer count = 0;
		if (content!=null&&!content.equals("")){
			count = personnelservice.countResume(content);
			resume_list = personnelservice.get_ResumeLikeList(content);
		}
		model.addAttribute("count", count);
		model.addAttribute("pageModel", pageModel);
		model.addAttribute("resume_list",resume_list);
		
		return "/recruitment/resume/list";  //管理员看到的简历投递页面
	}
    
    
       //简历审核跳转页面  /resume/edit
  		@RequestMapping(value="/resume/edit",method=RequestMethod.GET)
  		 public String edit(Model model,Integer id){
  			if(id!=null){
  				Resume resume = personnelservice.get_ResumeInfo(id);
  				model.addAttribute("resume",resume);
  			}
  			List<Status>  status_list = personnelservice.findAllStatus();
  			model.addAttribute("status_list", status_list);
  			
  			return "/recruitment/resume/edit";
  		}
  		
  		//简历审核结果提交
  		@RequestMapping(value="/resume/edit",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
  		@ResponseBody
  		public String toedit(Integer id,String email,String name,Integer status_id) {
  			
  			//根据申请简历的id更新投递状态，同时给该用户的邮箱发送邮件，通知面试
  			Resume resume = new Resume();
  			Status status = new Status();
  			status.setId(status_id);
  			
  			resume.setId(id);
  			resume.setStatus(status);
  			resume.setName(name);
  			resume.setEmail(email);
  			personnelservice.update_ResumeInfo(resume);

  			return "该应聘者已经成果通过简历筛选，我们已经面试通知发送到了该应聘者邮箱";
  		}
  		
  		
  		//管理员下载简历
  		/**
		 * 处理文档下载请求
		 *
		 * */
		@RequestMapping(value="/resume/downLoad")
		 public ResponseEntity<byte[]>  downLoad(
				 Integer id,
				 HttpSession session) throws Exception{
			// 根据id查询文档
			Resume target = personnelservice.get_ResumeInfo(id);
			String fileName = target.getFilename();
			// 上传文件路径
			String path = session.getServletContext().getRealPath(
					"/WEB-INF/resume");
			// 获得要下载文件的File对象
			File file = new File(path+"/"+ fileName);
			// 创建springframework的HttpHeaders对象
			HttpHeaders headers = new HttpHeaders();  
	        // 下载显示的文件名，解决中文名称乱码问题  
	        String downloadFielName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
	        // 通知浏览器以attachment（下载方式）打开图片
	        headers.setContentDispositionFormData("attachment", downloadFielName); 
	        // application/octet-stream ： 二进制流数据（最常见的文件下载）。
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	        // 201 HttpStatus.CREATED
	        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
	                headers, HttpStatus.CREATED); 
		}
    
}
