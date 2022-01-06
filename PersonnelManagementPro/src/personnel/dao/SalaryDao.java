package personnel.dao;


import static personnel.util.common.Constants.DEPTTABLE;
import static personnel.util.common.Constants.EMPLOYEETABLE;
import static personnel.util.common.Constants.JOBTABLE;
import static personnel.util.common.Constants.SALARYTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;

import personnel.dao.provider.SalaryDynaSqlProvider;
import personnel.pojo.Salary;

public interface SalaryDao {
	//查询工资
		@Select("select * from "+SALARYTABLE+" ")
		@Results({
			@Result(id=true,column="id",property="id"),
			@Result(column="BASE_PAY",property="basePay"),
			@Result(column="STATION_SALARY",property="stationSalary"),
			@Result(column="SENIORITY_PAY",property="seniorityPay"),
			@Result(column="PERFORMANCE",property="performance"),
			@Result(column="INDIVIDUAL_INCOME",property="individualIncome"),
			@Result(column="CREATE_DATE",property="createdate"),
			@Result(column="DEPT_ID",property="dept",
			one=@One(select="personnel.dao.DeptDao.get_Info",
			fetchType=FetchType.EAGER)),
			@Result(column="EMP_ID",property="employee",
			one=@One(select="personnel.dao.EmployeeDao.get_Info",
		fetchType=FetchType.EAGER)),
			@Result(column="JOB_ID",property="job",
			one=@One(select="personnel.dao.JobDao.get_Info",
		fetchType=FetchType.EAGER))
		})
		List<Salary> get_List();
		@Select("select * from "+SALARYTABLE+"  where BASE_PAY like binary CONCAT('%',#{content},'%')"
				+ "or DEPT_ID =(select id from "+DEPTTABLE+" where name like binary CONCAT('%',#{content},'%'))"
				+ "or JOB_ID =(select id from "+JOBTABLE+" where name like binary CONCAT('%',#{content},'%'))"
				+ "or EMP_ID =(select id from "+EMPLOYEETABLE+" where name like binary CONCAT('%',#{content},'%'))"
				+ "or STATION_SALARY like binary CONCAT('%',#{content},'%')"
				+ "or CREATE_DATE like binary CONCAT('%',#{content},'%')"
				+ "or INDIVIDUAL_INCOME like binary CONCAT('%',#{content},'%')"
				+ "or SENIORITY_PAY like binary CONCAT('%',#{content},'%')")
		@Results({
			@Result(id=true,column="id",property="id"),
			@Result(column="BASE_PAY",property="basePay"),
			@Result(column="STATION_SALARY",property="stationSalary"),
			@Result(column="SENIORITY_PAY",property="seniorityPay"),
			@Result(column="PERFORMANCE",property="performance"),
			@Result(column="INDIVIDUAL_INCOME",property="individualIncome"),
			@Result(column="CREATE_DATE",property="createdate"),
			@Result(column="DEPT_ID",property="dept",
			one=@One(select="personnel.dao.DeptDao.get_Info",
			fetchType=FetchType.EAGER)),
			@Result(column="EMP_ID",property="employee",
			one=@One(select="personnel.dao.EmployeeDao.get_Info",
		fetchType=FetchType.EAGER)),
			@Result(column="JOB_ID",property="job",
			one=@One(select="personnel.dao.JobDao.get_Info",
		fetchType=FetchType.EAGER))
		})
		//添加工资
		List<Salary> get_LikeList(String content);
		@Select("select count(*) from "+SALARYTABLE+"  where BASE_PAY like binary CONCAT('%',#{content},'%')"
				+ "or DEPT_ID =(select id from "+DEPTTABLE+" where name like binary CONCAT('%',#{content},'%'))"
				+ "or JOB_ID =(select id from "+JOBTABLE+" where name like binary CONCAT('%',#{content},'%'))"
				+ "or EMP_ID =(select id from "+EMPLOYEETABLE+" where name like binary CONCAT('%',#{content},'%'))"
				+ "or STATION_SALARY like binary CONCAT('%',#{content},'%')"
				+ "or CREATE_DATE like binary CONCAT('%',#{content},'%')"
				+ "or INDIVIDUAL_INCOME like binary CONCAT('%',#{content},'%')"
				+ "or SENIORITY_PAY like binary CONCAT('%',#{content},'%')")
		
		public Integer countSalary(String content);
		
		@SelectProvider(type=SalaryDynaSqlProvider.class,method="insert_Salary")
		void insert_Info(Salary salary);
		
		@Select("select * from "+SALARYTABLE+" where id = #{id}")
		@Results({
			@Result(id=true,column="id",property="id"),
			@Result(column="BASE_PAY",property="basePay"),
			@Result(column="STATION_SALARY",property="stationSalary"),
			@Result(column="SENIORITY_PAY",property="seniorityPay"),
			@Result(column="PERFORMANCE",property="performance"),
			@Result(column="INDIVIDUAL_INCOME",property="individualIncome"),
			@Result(column="CREATE_DATE",property="createdate"),
			@Result(column="DEPT_ID",property="dept",
			one=@One(select="personnel.dao.DeptDao.get_Info",
			fetchType=FetchType.EAGER)),
			@Result(column="EMP_ID",property="employee",
			one=@One(select="personnel.dao.EmployeeDao.get_Info",
		fetchType=FetchType.EAGER)),
			@Result(column="JOB_ID",property="job",
			one=@One(select="personnel.dao.JobDao.get_Info",
		fetchType=FetchType.EAGER))
		})
		//删除工资
		Salary get_Info(Integer id);

		@SelectProvider(type=SalaryDynaSqlProvider.class,method="update_Salary")
		void update_Info(Salary salary);
		// 根据id删除
		@Delete(" delete from "+SALARYTABLE+" where id = #{id} ")
		void delete_Info(Integer id);

		@SelectProvider(type=SalaryDynaSqlProvider.class,method="selectWhitParam")
		@Results({
			@Result(id=true,column="id",property="id"),
			@Result(column="BASE_PAY",property="basePay"),
			@Result(column="STATION_SALARY",property="stationSalary"),
			@Result(column="SENIORITY_PAY",property="seniorityPay"),
			@Result(column="PERFORMANCE",property="performance"),
			@Result(column="INDIVIDUAL_INCOME",property="individualIncome"),
			@Result(column="CREATE_DATE",property="createdate"),
			@Result(column="DEPT_ID",property="dept",
			one=@One(select="personnel.dao.DeptDao.get_Info",
			fetchType=FetchType.EAGER)),
			@Result(column="EMP_ID",property="employee",
			one=@One(select="personnel.dao.EmployeeDao.get_Info",
		fetchType=FetchType.EAGER)),
			@Result(column="JOB_ID",property="job",
			one=@One(select="personnel.dao.JobDao.get_Info",
		fetchType=FetchType.EAGER))
		})
		List<Salary> selectByPage(Map<String, Object> params);
		
		@SelectProvider(type=SalaryDynaSqlProvider.class,method="count")
		public Integer count(Map<String, Object> params);

		
		@Select("select * from "+SALARYTABLE+" where emp_id = #{id}")
		Salary getEmp_id(Integer id);
}
