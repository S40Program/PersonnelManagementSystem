package personnel.dao;

import static personnel.util.common.Constants.JOBTYPETABLE;
import static personnel.util.common.Constants.RECRUITMENTSTATUSTABLE;
import static personnel.util.common.Constants.RECRUITMENTTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;

import personnel.dao.provider.RecruitmentDynaSqlProvider;
import personnel.pojo.Recruitment;

public interface RecruitmentDao {
	
	//查询
		@SelectProvider(type=RecruitmentDynaSqlProvider.class,method="selectWhitParam")
		@Results({
			@Result(id=true,column="id",property="id"),
			@Result(column="jobtype",property="jobtype",
			one=@One(select="personnel.dao.JobTypeDao.get_Info",
			fetchType=FetchType.EAGER)),
			@Result(column="status",property="status",
			one=@One(select="personnel.dao.RecruitmentStatusDao.get_Info",
		fetchType=FetchType.EAGER))
		})
		List<Recruitment> selectByPage();

		@SelectProvider(type=RecruitmentDynaSqlProvider.class,method="count")
		public Integer count(Map<String, Object> params);

		@Select("select * from "+RECRUITMENTTABLE+" where id = #{id}")
		@Results({
			@Result(id=true,column="id",property="id"),
			@Result(column="jobtype",property="jobtype",
			one=@One(select="personnel.dao.JobTypeDao.get_Info",
			fetchType=FetchType.EAGER)),
			@Result(column="status",property="status",
			one=@One(select="personnel.dao.RecruitmentStatusDao.get_Info",
		fetchType=FetchType.EAGER))
		})
		Recruitment selectRecruitmentById(Integer id);

		
		@SelectProvider(type=RecruitmentDynaSqlProvider.class,method="selectWhitParam1")
		@Results({
			@Result(id=true,column="id",property="id"),
			@Result(column="jobtype",property="jobtype",
			one=@One(select="personnel.dao.JobTypeDao.get_Info",
			fetchType=FetchType.EAGER)),
			@Result(column="status",property="status",
			one=@One(select="personnel.dao.RecruitmentStatusDao.get_Info",
		fetchType=FetchType.EAGER))
		})
		List<Recruitment> selectByPage1(Map<String, Object> params);

		@Delete(" delete from "+RECRUITMENTTABLE+" where id = #{id} ")
		void delete_Info(Integer id);

		
		
		@Select("select * from "+RECRUITMENTTABLE+" where content like CONCAT('%',#{content},'%')"
				+ "or jobtype =(select id from "+JOBTYPETABLE+" where name like CONCAT('%',#{content},'%'))"
				+ "or status =(select id from "+RECRUITMENTSTATUSTABLE+" where name like CONCAT('%',#{content},'%'))"
				+ "or peoplenum like CONCAT('%',#{content},'%')"
				+ "or enddate like CONCAT('%',#{content},'%')")
		@Results({
			@Result(id=true,column="id",property="id"),
			@Result(column="jobtype",property="jobtype",
			one=@One(select="personnel.dao.JobTypeDao.get_Info",
			fetchType=FetchType.EAGER)),
			@Result(column="status",property="status",
			one=@One(select="personnel.dao.RecruitmentStatusDao.get_Info",
		fetchType=FetchType.EAGER))
		})
		List<Recruitment> get_LikeList(String content);

		
		
		@Select("select count(*) from "+RECRUITMENTTABLE+" where content like CONCAT('%',#{content},'%')"
				+ "or jobtype =(select id from "+JOBTYPETABLE+" where name like CONCAT('%',#{content},'%'))"
				+ "or status =(select id from "+RECRUITMENTSTATUSTABLE+" where name like CONCAT('%',#{content},'%'))"
				+ "or peoplenum like CONCAT('%',#{content},'%')"
				+ "or enddate like CONCAT('%',#{content},'%')")
		Integer countRecruitment(String content);

		
		@SelectProvider(type=RecruitmentDynaSqlProvider.class,method="insert_Recruitment")
		void insert_info(Recruitment recruitment);

		@SelectProvider(type=RecruitmentDynaSqlProvider.class,method="update_Recruitment")
		void update_info(Recruitment recruitment);

}
