package personnel.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import personnel.dao.*;
import personnel.pojo.*;
import personnel.service.PersonnelService;
import personnel.util.page.PageModel;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("PersonnelService")
public class PersonnelServiceImpl implements PersonnelService{
//@Autowired注释，依赖注入，自动装载的作用。
	//IoC容器（控制反转）是依赖注入的容器，在容器自动查找需要的bean，并装配给该对象的属性。
	//通过 @Autowired的使用来自动填充了set ，get方法，不用手动输入。
	@Autowired
	private DeptDao deptDao;
	@Autowired
	private JobDao jobDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private CheckworkDao checkworkDao;
	@Autowired
	private SalaryDao salaryDao;
	@Autowired
	private ContractDao contractDao;
	@Autowired
	private InsuranceDao insuranceDao;
	@Autowired
	private NoticeDao noticeDao;
	@Autowired
	private DocumentDao documentDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserVisitDao userVisitDao;
	@Autowired
	private ConfidentialitycontractDao  confidentialitycontractDao;
	@Autowired
	private LaborcontractDao laborcontractDao;
	@Autowired
	private TraincontractDao  traincontractDao;
	@Autowired
	private MedicareinsuranceDao  medicareinsuranceDao;
	@Autowired
	private EndowmentinsuranceDao endowmentinsuranceDao;
	@Autowired
	private AccidentinsuranceDao  accidentinsuranceDao;
	
	@Autowired
	private SexDao  sexDao;
	
	@Autowired
	private StatusDao  statusDao;
	
	@Autowired
	private EducationDao educationDao;
	
	@Autowired
	private RegistCodeDao registCodeDao;
	
	@Autowired
	private CreateStaticIdDao createStaticIdDao;
	
	@Autowired
	private LeaveTypeDao leaveTypeDao;
	
	@Autowired
	private LeaveDao leaveDao;
	
	@Autowired
	private LeaveStatusDao leaveStatusDao;
	
	@Autowired
	private CompletionDao completionDao;
	
	@Autowired
	private TrainDao trainDao;
	
	@Autowired
	private TrainDataDao trainDataDao;
	
	@Autowired
	private  RecruitmentDao recruitmentDao;
	
	@Autowired
	private ResumeDao resumeDao;
	
	@Autowired
	private JobTypeDao jobTypeDao;
	
	@Autowired
	private RecruitmentStatusDao recruitmentStatusDao;

	@Autowired
	private PunchClockDao punchClockDao;

	@Autowired
	private PunchTimeDao punchTimeDao;

	/**
	 * 部门信息的管理
	 */
	@Transactional(readOnly=true)
	@Override
	public List<Dept> findAllDept() {
		return deptDao.selectAllDept();
	}
	@Override
	public void addDept(Dept dept) {
		deptDao.save(dept);
	}
	
	@Override
	public Dept get_Info(Integer id) {
		Dept dept = deptDao.get_Info(id);
		return dept;
	}
	@Override
	public void update_Info(Dept dept) {
		deptDao.update_Info(dept);
	}
	@Override
	public void delete_Info(Integer id) {
		deptDao.delete_Info(id);
	}
	@Override
	public List<Dept> findAllDept(String content) {
		
		return deptDao.selectLikeAllDept(content);
	}
	/**
	 * 职位管理的操作
	 */
	@Override
	public List<Job> findAllJob() {
		return jobDao.get_List();
	}
	@Override
	public List<Job> findAllJob(String content) {
		return jobDao.get_LikeList(content);
	}
	
	@Override
	public Job get_JobInfo(Integer id) {
		return jobDao.get_Info(id);
	}
	@Override
	public void update_JobInfo(Job job) {
		jobDao.update_Info(job);
	}
	@Override
	public void insert_JobInfo(Job job) {
		jobDao.insert_Info(job);
	}
	@Override
	public void delete_JobInfo(Integer id) {
		jobDao.delete_Info(id);
	}
	/**
	 * 员工信息的管理
	 */
	@Override
	public List<Employee> get_EmployeeList(Employee employee,PageModel pageModel) {
		/**
		 * 将部门，职位id中的信息提取出来
		 */
		
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("employee", employee);
		
		int recordCount =employeeDao.count(params);
	    pageModel.setRecordCount(recordCount);
	    
	    if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
	    List<Employee> checkworks = employeeDao.selectByPage(params);
		return checkworks;
		
	}
	
	@Override
	public List<Employee> get_EmployeeLikeList(String content) {
		return employeeDao.get_LikeList(content);
	}
	
	
	@Override
	public Employee get_EmployeeInfo(Integer id) {
		return employeeDao.get_Info(id);
		
	}
	@Override
	public void update_EmployeeInfo(Employee data) {
		employeeDao.update_Info(data);
	}
	@Override
	public void insert_EmployeeInfo(Employee data) {
		
		employeeDao.insert_Info(data);
	}
	
	@Override
	public void delete_EmployeeInfo(Integer id) {
		employeeDao.delete_Info(id);
	}
	/**
	 * 公告管理
	 */
	@Override
	public List<Notice> get_NoticeList(Notice notice,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("notice", notice);
		
		int recordCount =noticeDao.count(params);
	    pageModel.setRecordCount(recordCount);
	    
	    if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
	    List<Notice> checkworks = noticeDao.selectByPage(params);
		return checkworks;
	}
	@Override
	public List<Notice> get_NoticeLikeList(String content) {
		return noticeDao.get_LikeList(content);
	}
	@Override
	public Notice get_NoticeInfo(Integer id) {
		return noticeDao.get_Info(id);
	}
	@Override
	public void update_NoticeInfo(Notice notice) {
		noticeDao.update_Info(notice);
	}
	@Override
	public void insert_NoticeInfo(Notice notice) {
		noticeDao.insert_Info(notice);
	}
	@Override
	public void delete_NoticeInfo(Integer id) {
		noticeDao.delete_Info(id);
	}
	/**
	 * 文档管理
	 */
	@Override
	public List<Document> get_DocumentList(Document document,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("document", document);
		
		int recordCount =documentDao.count(params);
	    pageModel.setRecordCount(recordCount);
	    
	    if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
	    List<Document> checkworks = documentDao.selectByPage(params);
		return checkworks;
	}
	@Override
	public List<Document> get_DocumentLikeList(String content) {
		return documentDao.get_LikeList(content);
	}
	@Override
	public Document get_DocumentInfo(Integer id) {
		return documentDao.get_Info(id);
	}
	@Override
	public void update_DocumentInfo(Document notice) {
		documentDao.update_Info(notice);
	}
	@Override
	public void insert_DocumentInfo(Document notice) {
		documentDao.insert_Info(notice);
	}
	@Override
	public void delete_DocumentInfo(Integer id) {
		documentDao.delete_Info(id);
	}
	
	
	
	@Override
	public User login(String loginname, String password) {
		User user = userDao.get_login(loginname,password);
		return user;
	}
	
	
	
	@Override
	public List<User> get_UserList(User user,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("user", user);
		
		int recordCount =userDao.count(params);
	    pageModel.setRecordCount(recordCount);
	    
	    if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
	    List<User> checkworks = userDao.selectByPage(params);
		return checkworks;
	}
	@Override
	public List<User> get_UserLikeList(String content) {
		return userDao.get_LikeList(content);
	}
	@Override
	public User get_UserInfo(Integer id) {
		return userDao.get_Info(id);
		
	}
	@Override
	public void update_UserInfo(User user) {
		userDao.update_Info(user);
	}
	@Override
	public void insert_UserInfo(User user) {
		userDao.insert_Info(user);
	}
	@Override
	public void delete_UserInfo(Integer id) {
		userDao.delete_Info(id);
	}
	
	@Override
	public User findUserByLoginAndName(String loginname, String username) {
		return userDao.selectByLoginAndName(loginname, username);
	}
	@Override
	public void toUpdatePassword(String loginname, String password) {
		userDao.updatePasswordByLoginname(loginname,password);
		
	}
	
	@Override
	public void insert_UserVisitInfo(UserVisit userVisit) {
		userVisitDao.insert_Info(userVisit);
		
	}
	
	//redis缓存使用
	@Cacheable(value= {"userVisit"},key = "#userVisit.toString()+ #pageModel.toString()")
	@Override
	public List<UserVisit> get_UserVisitList(UserVisit userVisit,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("userVisit", userVisit);
		
		int recordCount =userVisitDao.count(params);
	    pageModel.setRecordCount(recordCount);
	    
	    if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
	    List<UserVisit> checkworks = userVisitDao.selectByPage(params);
		return checkworks;
	}
	
	@Override
	public List<UserVisit> get_UserVisitLinkList(String content) {
		
		return userVisitDao.get_LikeList(content);
	}
	@Override
	public void delete_UserVisitInfo(Integer id) {
		userVisitDao.delete_Info(id);
		
	}
	
	
	@Override
	public List<Checkwork> get_CheckworkList(Checkwork checkwork,PageModel pageModel) {
		
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("checkwork", checkwork);
		
		int recordCount =checkworkDao.count(params);
	    pageModel.setRecordCount(recordCount);
	    
	    if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
	    List<Checkwork> checkworks = checkworkDao.selectByPage(params);
	    return checkworks;
	    
	}
	@Override
	public List<Checkwork> get_CheckworkLikeList(String content) {
		
		return checkworkDao.get_LikeList(content);
	}
	@Override
	public Checkwork get_CheckworkInfo(Integer id) {
		
		return checkworkDao.get_Info(id);
	}
	@Override
	public void update_CheckworkInfo(Checkwork checkwork) {
		checkworkDao.update_Info(checkwork);
		
	}
	@Override
	public void insert_CheckworkInfo(Checkwork checkwork) {
		checkworkDao.insert_Info(checkwork);
		
	}
	@Override
	public void delete_Checkwork(Integer id) {
		checkworkDao.delete_Info(id);
		
	}
	@Override
	public List<Salary> get_SalaryList(Salary salary,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("salary", salary);
		
		int recordCount =salaryDao.count(params);
	    pageModel.setRecordCount(recordCount);
	    
	    if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
	    List<Salary> checkworks = salaryDao.selectByPage(params);
		return checkworks;
	}
	@Override
	public List<Salary> get_SalaryLikeList(String content) {
		
		return salaryDao.get_LikeList(content);
	}
	@Override
	public Salary get_SalaryInfo(Integer id) {
		return salaryDao.get_Info(id);
	}
	@Override
	public void update_SalaryInfo(Salary salary) {
		salaryDao.update_Info(salary);
		
	}
	@Override
	public void insert_SalaryInfo(Salary salary) {
		salaryDao.insert_Info(salary);
		
	}
	@Override
	public void delete_Salary(Integer id) {
		salaryDao.delete_Info(id);
	}
	@Override
	public List<Contract> get_ContractList(Contract contract,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("contract", contract);
		
		int recordCount =contractDao.count(params);
	    pageModel.setRecordCount(recordCount);
	    
	    if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
	    List<Contract> checkworks = contractDao.selectByPage(params);
		return checkworks;
	}
	@Override
	public List<Contract> get_ContractLikeList(String content) {
		
		return contractDao.get_LikeList(content);
	}
	@Override
	public Contract get_ContractInfo(Integer id) {
		
		return contractDao.get_Info(id);
	}
	@Override
	public void update_ContractInfo(Contract contract) {
		contractDao.update_Info(contract);
		
	}
	@Override
	public void insert_ContractInfo(Contract contract) {
		contractDao.insert_Info(contract);
		
	}
	@Override
	public void delete_Contract(Integer id) {
		contractDao.delete_Info(id);
		
	}
//社保
	@Override
	public List<Insurance> get_InsuranceList(Insurance insurance,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("insurance", insurance);
		
		int recordCount =insuranceDao.count(params);
	    pageModel.setRecordCount(recordCount);
	    
	    if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
	    List<Insurance> checkworks = insuranceDao.selectByPage(params);
		return checkworks;
	}
	@Override
	public List<Insurance> get_InsuranceLikeList(String content) {
		
		return insuranceDao.get_LikeList(content);
	}
	@Override
	public Insurance get_InsuranceInfo(Integer id) {
		
		return insuranceDao.get_Info(id);
	}
	@Override
	public void update_InsuranceInfo(Insurance insurance) {
		insuranceDao.update_Info(insurance);
		
	}
	@Override
	public void insert_InsuranceInfo(Insurance insurance) {
		insuranceDao.insert_Info(insurance);
		
	}
	@Override
	public void delete_Insurance(Integer id) {
		insuranceDao.delete_Info(id);
		
	}
//结束	
	@Override
	public List<Map<String, Object>> pieData() {
		 List<Map<String,Object>> data =new ArrayList<>();
	        List<Map<String, Object>> listdata=userDao.countS();
	        if(listdata.size()>0){
	            for(int i=0;i<listdata.size();i++){
	                Map<String,Object> map=new HashMap<>();
	                map.put("name", listdata.get(i).get("SEX"));
	                map.put("value", listdata.get(i).get("SEXCOUNT"));
	                data.add(map);
	            }
	        }
	        return data;
	}

	@Override
	public List<Map<String, Object>> pieData1() {
		 List<Map<String,Object>> data =new ArrayList<>();
	        List<Map<String, Object>> listdata=userDao.countS1();
	        if(listdata.size()>0){
	            for(int i=0;i<listdata.size();i++){
	                Map<String,Object> map=new HashMap<>();
	                map.put("name", listdata.get(i).get("JOB_ID"));
	                map.put("value", listdata.get(i).get("JOB_IDCOUNT"));
	                data.add(map);
	            }
	        }
	        
	        return data;
	        
	}

	@Override
	public List<Map<String, Object>> pieData2() {
		List<Business> businesslist = userDao.countS2();
		List<Map<String,Object>> data =new ArrayList<>();
        if(businesslist.size()>0){
            for(int i=0;i<businesslist.size();i++){
                Map<String,Object> map=new HashMap<>();
                map.put("mailmarketing", businesslist.get(i).getMailmarketing());
                map.put("allianceadvertising", businesslist.get(i).getAllianceadvertising());
                map.put("videoadvertising", businesslist.get(i).getVideoadvertising());
                map.put("directaccess", businesslist.get(i).getDirectaccess());
                map.put("searchengine", businesslist.get(i).getSearchengine());
                data.add(map);
            }
        }
        
        return data;
	}
	
	@Override
	public Map<String, Integer> pieData3() {
		/**
		 * 统计各个省份的人数
		 * 先去查找员工表，结果是一个list集合，里面存放所有员工的地址信息即可
		 */
		List<String> list = employeeDao.get_addressList();
		//数据库拿到省份list之后，现在通过map进行统计个数
		Map<String,Integer> map = new HashMap<>();
		for (int i = 0; i < list.size(); i++) {
			   if(map.containsKey(list.get(i))) {  //代码太经典了 map统计个数
				   map.put(list.get(i), map.get(list.get(i))+1);
			   }else {
				   map.put(list.get(i), 1);
			   }	
			}
		return map;
	}
	@Override
	public List<Confidentialitycontract> findAllConfidentialitycontract(String content) {
		
		return confidentialitycontractDao.selectLikeAllConfidentialitycontract(content);
	}
	@Override
	public List<Confidentialitycontract> findAllConfidentialitycontract() {
		
		return confidentialitycontractDao.selectAllConfidentialitycontract();
	}
	@Override
	public List<Laborcontract> findAllLaborcontract(String content) {
		
		return laborcontractDao.selectLikeAllLaborcontract(content);
	}
	@Override
	public List<Laborcontract> findAllLaborcontract() {
		
		return laborcontractDao.selectAllLaborcontract();
	}
	@Override
	public List<Traincontract> findAllTraincontract(String content) {
		
		return traincontractDao.selectLikeAllTraincontract(content);
	}
	@Override
	public List<Traincontract> findAllTraincontract() {
		
		return traincontractDao.selectAllTraincontract();
	}
//加
	@Override
	public List<Medicareinsurance> findAllMedicareinsurance(String content) {
		
		return medicareinsuranceDao.selectLikeAllMedicareinsurance(content);
	}
	@Override
	public List<Medicareinsurance> findAllMedicareinsurance() {
		
		return medicareinsuranceDao.selectAllMedicareinsurance();
	}
	@Override
	public List<Endowmentinsurance> findAllEndowmentinsurance(String content) {
		
		return endowmentinsuranceDao.selectLikeAllEndowmentinsurance(content);
	}
	@Override
	public List<Endowmentinsurance> findAllEndowmentinsurance() {
		
		return endowmentinsuranceDao.selectAllEndowmentinsurance();
	}
	@Override
	public List<Accidentinsurance> findAllAccidentinsurance(String content) {
		
		return accidentinsuranceDao.selectLikeAllAccidentinsurance(content);
	}
	@Override
	public List<Accidentinsurance> findAllAccidentinsurance() {
		
		return accidentinsuranceDao.selectAllAccidentinsurance();
	}
//结束
	@Override
	public List<Sex> findAllSex(String content) {
		
		return sexDao.selectLikeAllSex(content);
	}
	@Override
	public List<Sex> findAllSex() {
		
		return sexDao.selectAllSex();
	}
	@Override
	public List<Status> findAllStatus(String content) {
		
		return statusDao.selectLikeAllStatus(content);
	}
	@Override
	public List<Status> findAllStatus() {
		
		return statusDao.selectAllStatus();
	}
	@Override
	public Integer get_EmployeeByName(String employee_name) {
		
		return employeeDao.getIdByName(employee_name);
	}
	@Override
	public Checkwork get_CheckworkEmp_id(Integer id) {
		
		return checkworkDao.getEmp_id(id);
	}
	@Override
	public Contract get_ContractEmp_id(Integer id) {
		
		return contractDao.getEmp_id(id);
	}
	@Override
//加
	public Insurance get_InsuranceEmp_id(Integer id) {
		
		return insuranceDao.getEmp_id(id);
	}
//结束
	@Override
	public Salary get_SalaryEmp_id(Integer id) {
		
		return salaryDao.getEmp_id(id);
	}
	@Override
	public List<Education> findAllEducation(String content) {
		
		return educationDao.selectLikeAllEducation(content);
	}
	@Override
	public List<Education> findAllEducation() {
		
		return educationDao.selectAllEducation();
	}
	@Override
	public Integer countEmployee(String content) {
		
		return employeeDao.countEmployee(content);
	}
	@Override
	public Integer countCheckwork(String content) {
		
		return checkworkDao.countCheckwork(content);
	}
	@Override
	public Integer countContract(String content) {
		
		return contractDao.countContract(content);
	}
//加
	@Override
	public Integer countInsurance(String content) {
		
		return insuranceDao.countInsurance(content);
	}
//结束
	@Override
	public Integer countSalary(String content) {
		
		return salaryDao.countSalary(content);
	}
	@Override
	public Integer countNotice(String content) {
		
		return noticeDao.countNotice(content);
	}
	@Override
	public Integer countDocument(String content) {
		
		return documentDao.countDocument(content);
	}
	@Override
	public Integer countUser(String content) {
		
		return userDao.countUser(content);
	}
	@Override
	public Integer countUserVisit(String content) {
		
		return userVisitDao.countUserVisit(content);
	}
	@Override
	public Integer findCon_id() {
		
		return employeeDao.togetCon_id()+1;
	}
	@Override
	public Integer findSal_id() {
		
		return employeeDao.togetSal_id()+1;
	}
	@Override
	public Integer findChe_id() {
		
		return employeeDao.togetChe_id()+1;
	}
	@Override
	public User findUserByLogin(String loginname) {
		
		return userDao.selectByLogin(loginname);
	}
	@Override
	public User findUserByName(String username) {
		return userDao.selectByName(username);
	}
	@Override
	public Dept get_deptByname(String name) {
		
		return deptDao.get_dept(name);
	}
	@Override
	public Job get_jobByname(String name) {
		
		return jobDao.get_job(name);
	}
	@Override
	public String findCode(String code) {
		return registCodeDao.get_Info(code);
	}
	@Override
	public void addCode(RegistCode registCode) {
		registCodeDao.insert_Info(registCode);
	}
	@Override
	public User getStaticId(Integer staticid) {
		return userDao.get_StaticInfo(staticid);
	}
	@Override
	public CreateStaticId getStaticId() {
		
		return createStaticIdDao.get_StaticId();
	}
	@Override
	public void updata_staticId(Integer staticId) {
		createStaticIdDao.updata_staticId(staticId);
		
	}
	@Override
	public User findUserMessageByLoginname(String loginname) {
		
		return userDao.selectByLogin(loginname);
	}
	@Override
	public User findUserByEmail(String email) {
		return userDao.get_UserByEmail(email);
	}
	@Override
	public List<User> get_UserEmail() {
		return userDao.get_UserAllEmail();
	}
	@Override
	public Integer getEmpIdByUserId(Integer id) {
		
		return userDao.get_EmpId(id);
	}
	@Override
	public List<LeaveType> findLeaveType() {
		
		return leaveTypeDao.selectAllLeaveType();
	}
	@Override
	public void insert_LeaveInfo(Leave leave) {
		
		leaveDao.insert_Info(leave);
		
	}
	@Override
	public List<Leave> findLeaveListById(Integer id) {
		
		return leaveDao.get_Info(id);
	}
	@Override
	public void delete_LeaveInfo(Integer id) {
		leaveDao.delete_Info(id);
	}
	@Override
	public Leave findLeaveById(Integer id) {
		
		return leaveDao.selectLeave(id);
	}
	@Override
	public void update_LeaveInfo(Leave leave) {
		
		leaveDao.update_Info(leave);
		
	}
	
	
	@Override
	public Integer countLeave(Integer id) {
		return leaveDao.get_Count(id);
	}
	@Override
	public List<Leave> get_LeaveList(Leave leave, PageModel pageModel) {
		
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("leave", leave);
		
		int recordCount =leaveDao.count(params);
	    pageModel.setRecordCount(recordCount);
	    
	    if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
	    
	    List<Leave> leaves = leaveDao.selectByPage(params);
		return leaves;
	}
	
	@Override
	public Integer countAllLeave(String content) {
		
		return leaveDao.countLeave(content);
		
	}
	@Override
	public List<Leave> get_LeaveLikeList(String content) {
		
		return leaveDao.get_LikeList(content);
	}
	@Override
	public List<LeaveStatus> findLeaveStatus() {
		
		return leaveStatusDao.selectAllLeaveType();
	}
	@Override
	public Integer getEmpIdById(Integer id) {
		
		return leaveDao.selectEmpId(id);
	}
	@Override
	public List<Completion> findCompletion() {
		
		return completionDao.selectAllLeaveType();
	}
	
	//  培训管理方法实现
	@Override
	public List<Train> findTrainListById(Integer id) {
		
		return trainDao.get_Info(id);
	}
	@Override
	public List<Train> get_TrainList(Train train, PageModel pageModel) {
		
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("leave", train);
		
		int recordCount =trainDao.count(params);
	    pageModel.setRecordCount(recordCount);
	    
	    if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
	    
	    List<Train> trains = trainDao.selectByPage(params);
		return trains;
	}
	
	@Override
	public Integer countAllTrain(String content) {
		
		return trainDao.countTrain(content);
	}
	@Override
	public List<Train> get_TrainLikeList(String content) {
		
		return trainDao.get_LikeList(content);
	}
	
	@Override
	public void update_TrainInfo(Train train) {
		
		trainDao.update_Info(train);
	}
	
	@Override
	public void insert_TrainInfo(Train train) {
		trainDao.insert_Info(train);
	}
	
	@Override
	public void delete_Train(Integer id) {
		trainDao.delete_Info(id);
		
	}
	@Override
	public Train findTrainById(Integer id) {
		
		return trainDao.selectTrainById(id);
	}
	@Override
	public Integer countTrain(Integer id) {
		
		return trainDao.get_Count(id);
	}
	
	
	/**
	 * 文档管理
	 */
	@Override
	public List<TrainData> get_TrainDataList(TrainData trainData,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("trainData", trainData);
		
		int recordCount =trainDataDao.count(params);
	    pageModel.setRecordCount(recordCount);
	    
	    if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
	    List<TrainData> trainDatas = trainDataDao.selectByPage(params);
		return trainDatas;
	}
	@Override
	public List<TrainData> get_TrainDataLikeList(String content) {
		return trainDataDao.get_LikeList(content);
	}
	@Override
	public TrainData get_TrainDataInfo(Integer id) {
		return trainDataDao.get_Info(id);
	}
	@Override
	public void update_TrainDataInfo(TrainData notice) {
		trainDataDao.update_Info(notice);
	}
	@Override
	public void insert_TrainDataInfo(TrainData notice) {
		trainDataDao.insert_Info(notice);
	}
	@Override
	public void delete_TrainDataInfo(Integer id) {
		trainDataDao.delete_Info(id);
	}
	@Override
	public Integer countTrainData(String content) {
		
		return trainDataDao.countTrainData(content);
	}
	
	/**
	 * 招聘
	 */
	
	@Override
	public List<Recruitment> get_RecruitmentList() {
		
	    List<Recruitment> recruitments = recruitmentDao.selectByPage();
	    
		return recruitments;
	}
	@Override
	public Recruitment get_RecruitmentById(Integer id) {
		
		Recruitment recruitment = recruitmentDao.selectRecruitmentById(id);
		
		return recruitment;
	}
	@Override
	public List<Recruitment> get_RecruitmentList1(Recruitment recruitment, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("recruitment", recruitment);
		
		int recordCount =recruitmentDao.count(params);
	    pageModel.setRecordCount(recordCount);
	    
	    if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
	    List<Recruitment> recruitments = recruitmentDao.selectByPage1(params);
		return recruitments;
	}
	
	@Override
	public List<Recruitment> get_RecruitmentLikeList(String content) {
		return recruitmentDao.get_LikeList(content);
	}
	@Override
	public void delete_RecruitmentInfo(Integer id) {
		recruitmentDao.delete_Info(id);
		
	}
	
	@Override
	public void insert_Resume(Resume resume) {
		 
		resumeDao.insert_info(resume);
	}
	
	@Override
	public List<Resume> get_ResumeLikeList(String content) {
		
		return resumeDao.get_LikeList(content);
	}
	@Override
	public List<Resume> get_ResumeList(Resume resume, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("resume", resume);
		
		int recordCount =resumeDao.count(params);
	    pageModel.setRecordCount(recordCount);
	    
	    if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
	    List<Resume> resumes = resumeDao.selectByPage(params);
		return resumes;
	}
	@Override
	public Resume get_ResumeInfo(Integer id) {
		
		return resumeDao.getResumeById(id);
	}
	
	@Override
	public void update_ResumeInfo(Resume resume) {
		resumeDao.update_Info(resume);
		
	}
	@Override
	public Integer countResume(String content) {
		return resumeDao.countResume(content);
	}
	@Override
	public Integer countRecruitment(String content) {
		
		return recruitmentDao.countRecruitment(content);
	}
	@Override
	public List<JobType> get_JobType() {
		
		return jobTypeDao.selectAllJobType();
	}
	@Override
	public List<RecruitmentStatus> get_RecruitmentStatusList() {
		
		return recruitmentStatusDao.selectAllRecruitmentStatus();
	}
	@Override
	public void insert_Recruitment(Recruitment recruitment) {
		recruitmentDao.insert_info(recruitment);
		
	}
	@Override
	public void update_Recruitment(Recruitment recruitment) {
		recruitmentDao.update_info(recruitment);
		
	}

	@Override
	public PunchClock getPunchClockByDateAndUserId(String date, Integer userID) {
		return punchClockDao.getPunchClockByDateAndUserId(date, userID);
	}

	@Override
	public void insert_PunchClock(PunchClock punchClock) {
		punchClockDao.insert_PunchClock(punchClock);
	}

	@Override
	public void update_PunchClock(Integer id, String date, Date date1, int status) {
		punchClockDao.update_PunchClock(id,date,date1,status);
	}

	@Override
	public List<PunchClock> findAllPunchById(Integer id) {
		return punchClockDao.findAllPunchById(id);
	}

	@Override
	public void update_PunchClocks(Integer id, String date, Date date1, int status, int offwork_status_id) {
		punchClockDao.update_PunchClocks(id,date,date1,status,offwork_status_id);
	}

	@Override
	public PunchClock getPunchClockById(Integer id) {
		return punchClockDao.getPunchClockById(id);
	}

	@Override
	public void update_PunchClockContent(Integer id, String content) {
		punchClockDao.update_PunchClockContent(id,content);
	}

	@Override
	public List<PunchClock> getPunchClockByUserID(Integer id, String date) {
		return punchClockDao.getPunchClockByUserID(id,date);
	}

	@Override
	public PunchTime getPt() {
		return punchTimeDao.getPt();
	}

	@Override
	public void updatePt(String am, String pm, Integer id) {
		punchTimeDao.updatePt(am,pm,id);
	}


}
