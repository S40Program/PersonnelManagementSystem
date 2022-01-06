package personnel.dao;


import static personnel.util.common.Constants.ACCIDENTINSURANCETABLE;
import static personnel.util.common.Constants.ENDOWMENTINSURANCETABLE;
import static personnel.util.common.Constants.MEDICAREINSURANCETABLE;
import static personnel.util.common.Constants.INSURANCETABLE;
import static personnel.util.common.Constants.DEPTTABLE;
import static personnel.util.common.Constants.EMPLOYEETABLE;
import static personnel.util.common.Constants.JOBTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;

import personnel.dao.provider.InsuranceDynaSqlProvider;
import personnel.pojo.Insurance;


public interface InsuranceDao {
	
	//查询
			@Select("select * from "+INSURANCETABLE+" ")
			@Results({
				@Result(id=true,column="id",property="id"),
				@Result(column="CREATE_DATE",property="createdate"),
				@Result(column="MEDICARE_INSURANCE",property="medicareInsurance",
				one=@One(select="personnel.dao.Medicareinsurance.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="ENDOWMENT_INSURANCE",property="endowmentInsurance",
				one=@One(select="personnel.dao.EndowmentinsuranceDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="ACCIDENT_INSURANCE",property="accidentInsurance",
				one=@One(select="personnel.dao.AccidentinsuranceDao.get_Info",
				fetchType=FetchType.EAGER)),
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
			List<Insurance> get_List();
			@Select("select * from "+INSURANCETABLE+"  where "
					+ "DEPT_ID =(select id from "+DEPTTABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or JOB_ID =(select id from "+JOBTABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or EMP_ID =(select id from "+EMPLOYEETABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or MEDICARE_INSURANCE =(select id from "+MEDICAREINSURANCETABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or ENDOWMENT_INSURANCE =(select id from "+ENDOWMENTINSURANCETABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or ACCIDENT_INSURANCE =(select id from "+ACCIDENTINSURANCETABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or CREATE_DATE like binary CONCAT('%',#{content},'%')")
			
			@Results({
				@Result(id=true,column="id",property="id"),
				@Result(column="CREATE_DATE",property="createdate"),
				@Result(column="MEDICARE_INSURANCE",property="medicareInsurance",
				one=@One(select="personnel.dao.MedicareinsuranceDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="ENDOWMENT_INSURANCE",property="endowmentInsurance",
				one=@One(select="personnel.dao.EndowmentinsuranceDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="ACCIDENT_INSURANCE",property="accidentInsurance",
				one=@One(select="personnel.dao.AccidentinsuranceDao.get_Info",
				fetchType=FetchType.EAGER)),
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
			List<Insurance> get_LikeList(String content);
			
			
			@SelectProvider(type=InsuranceDynaSqlProvider.class,method="insert_Insurance")
			void insert_Info(Insurance insurance);
			
			@Select("select * from "+INSURANCETABLE+" where id = #{id}")
			@Results({
				@Result(id=true,column="id",property="id"),
				@Result(column="CREATE_DATE",property="createdate"),
				@Result(column="MEDICARE_INSURANCE",property="medicareInsurance",
				one=@One(select="personnel.dao.MedicareinsuranceDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="ENDOWMENT_INSURANCE",property="endowmentInsurance",
				one=@One(select="personnel.dao.EndowmentinsuranceDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="ACCIDENT_INSURANCE",property="accidentInsurance",
				one=@One(select="personnel.dao.AccidentinsuranceDao.get_Info",
				fetchType=FetchType.EAGER)),
				
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
			Insurance get_Info(Integer id);

			@SelectProvider(type=InsuranceDynaSqlProvider.class,method="update_Insurance")
			void update_Info(Insurance insurance);
			// 根据id删除部门
			@Delete(" delete from "+INSURANCETABLE+" where id = #{id} ")
			void delete_Info(Integer id);
			
			
			
			@SelectProvider(type=InsuranceDynaSqlProvider.class,method="selectWhitParam")
			@Results({
				@Result(id=true,column="id",property="id"),
				@Result(column="CREATE_DATE",property="createdate"),
				@Result(column="MEDICARE_INSURANCE",property="medicareInsurance",
				one=@One(select="personnel.dao.MedicareinsuranceDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="ENDOWMENT_INSURANCE",property="endowmentInsurance",
				one=@One(select="personnel.dao.EndowmentinsuranceDao.get_Info",
				fetchType=FetchType.EAGER)),
				@Result(column="ACCIDENT_INSURANCE",property="accidentInsurance",
				one=@One(select="personnel.dao.AccidentinsuranceDao.get_Info",
				fetchType=FetchType.EAGER)),
				
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
			List<Insurance> selectByPage(Map<String, Object> params);
			
			@SelectProvider(type=InsuranceDynaSqlProvider.class,method="count")
			public Integer count(Map<String, Object> params);

			
			@Select("select * from "+INSURANCETABLE+" where emp_id = #{id}")
			Insurance getEmp_id(Integer id);
			
			@Select("select count(*) from "+INSURANCETABLE+"  where "
					+ "DEPT_ID =(select id from "+DEPTTABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or JOB_ID =(select id from "+JOBTABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or EMP_ID =(select id from "+EMPLOYEETABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or MEDICARE_INSURANCE =(select id from "+MEDICAREINSURANCETABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or ENDOWMENT_INSURANCE =(select id from "+ENDOWMENTINSURANCETABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or ACCIDENT_INSURANCE =(select id from "+ACCIDENTINSURANCETABLE+" where name like binary CONCAT('%',#{content},'%'))"
					+ "or CREATE_DATE like binary CONCAT('%',#{content},'%')")
			public Integer countInsurance(String content);
			
			
			
}
