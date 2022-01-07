package personnel.dao;

import static personnel.util.common.Constants.EDUCATIONTABLE;
import static personnel.util.common.Constants.RESUMETABLE;
import static personnel.util.common.Constants.SEXTABLE;
import static personnel.util.common.Constants.STATUSTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;

import personnel.dao.provider.ResumeDynaSqlProvider;
import personnel.pojo.Resume;

public interface ResumeDao {

	
	@SelectProvider(type=ResumeDynaSqlProvider.class,method="insert")
	void insert_info(Resume resume);

	@SelectProvider(type=ResumeDynaSqlProvider.class,method="count")
	int count(Map<String, Object> params);

	@SelectProvider(type=ResumeDynaSqlProvider.class,method="selectWhitParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="sex_id",property="sex",
		one=@One(select="personnel.dao.SexDao.get_Info",
		fetchType=FetchType.EAGER)),
		@Result(column="education_id",property="education",
		one=@One(select="personnel.dao.EducationDao.get_Info",
		fetchType=FetchType.EAGER)),
		@Result(column="status_id",property="status",
		one=@One(select="personnel.dao.StatusDao.get_Info",
	fetchType=FetchType.EAGER))
	})
	List<Resume> selectByPage(Map<String, Object> params);

	
	@Select("select * from "+RESUMETABLE+" where id = #{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="sex_id",property="sex",
		one=@One(select="personnel.dao.SexDao.get_Info",
		fetchType=FetchType.EAGER)),
		@Result(column="education_id",property="education",
		one=@One(select="personnel.dao.EducationDao.get_Info",
		fetchType=FetchType.EAGER)),
		@Result(column="status_id",property="status",
		one=@One(select="personnel.dao.StatusDao.get_Info",
	fetchType=FetchType.EAGER))
	})
	Resume getResumeById(Integer id);
	
	@SelectProvider(type=ResumeDynaSqlProvider.class,method="update_Resume")
	void update_Info(Resume resume);


	@Select("select * from "+RESUMETABLE+" where jobtype like CONCAT('%',#{content},'%')"
			+ "or sex_id =(select id from "+SEXTABLE+" where name like CONCAT('%',#{content},'%'))"
			+ "or education_id =(select id from "+EDUCATIONTABLE+" where name like CONCAT('%',#{content},'%'))"
			+ "or status_id =(select id from "+STATUSTABLE+" where name like CONCAT('%',#{content},'%'))"
			+ "or createdate like binary CONCAT('%',#{content},'%')"
			+ "or content like CONCAT('%',#{content},'%')"
			+ "or name like CONCAT('%',#{content},'%')"
			+ "or email like CONCAT('%',#{content},'%')"
			+ "or phone like CONCAT('%',#{content},'%')")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="sex_id",property="sex",
		one=@One(select="personnel.dao.SexDao.get_Info",
		fetchType=FetchType.EAGER)),
		@Result(column="education_id",property="education",
		one=@One(select="personnel.dao.EducationDao.get_Info",
		fetchType=FetchType.EAGER)),
		@Result(column="status_id",property="status",
		one=@One(select="personnel.dao.StatusDao.get_Info",
	fetchType=FetchType.EAGER))
	})
	List<Resume> get_LikeList(String content);

	
	@Select("select count(*) from "+RESUMETABLE+" where jobtype like CONCAT('%',#{content},'%')"
			+ "or sex_id =(select id from "+SEXTABLE+" where name like CONCAT('%',#{content},'%'))"
			+ "or education_id =(select id from "+EDUCATIONTABLE+" where name like CONCAT('%',#{content},'%'))"
			+ "or status_id =(select id from "+STATUSTABLE+" where name like CONCAT('%',#{content},'%'))"
			+ "or createdate like binary CONCAT('%',#{content},'%')"
			+ "or content like CONCAT('%',#{content},'%')"
			+ "or name like CONCAT('%',#{content},'%')"
			+ "or email like CONCAT('%',#{content},'%')"
			+ "or phone like CONCAT('%',#{content},'%')")
	Integer countResume(String content);

}
