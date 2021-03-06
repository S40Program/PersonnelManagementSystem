package personnel.dao;

import static personnel.util.common.Constants.BUSINESSTABLE;
import static personnel.util.common.Constants.EMPLOYEETABLE;
import static personnel.util.common.Constants.STATUSTABLE;
import static personnel.util.common.Constants.USERTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import personnel.dao.provider.UserDynaSqlProvider;
import personnel.pojo.Business;
import personnel.pojo.User;

public interface UserDao {

	@Select("select * from "+USERTABLE+" ")
	
	List<User> get_List();
	
	@Select("select * from "+USERTABLE+"  where username like CONCAT('%',#{content},'%')"
			+ "or status_id =(select id from "+STATUSTABLE+" where name like CONCAT('%',#{content},'%'))"
			+ "or createdate like binary CONCAT('%',#{content},'%')"
			+ "or loginname like CONCAT('%',#{content},'%')")
	List<User> get_LikeList(String content);
	
	
	@Select("select count(*) from "+USERTABLE+"  where username like CONCAT('%',#{content},'%')"
			+ "or status_id =(select id from "+STATUSTABLE+" where name like CONCAT('%',#{content},'%'))"
			+ "or createdate like binary CONCAT('%',#{content},'%')"
			+ "or loginname like CONCAT('%',#{content},'%')")
	public Integer countUser(String content);

	@Select("select * from "+USERTABLE+"  where loginname = #{loginname} AND password = #{password}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="status_id",property="status",
				one=@One(select="personnel.dao.StatusDao.get_Info",
				fetchType=FetchType.EAGER))
	})
	User get_login(@Param("loginname") String loginname,
                   @Param("password") String password);
	//??????
	@SelectProvider(type=UserDynaSqlProvider.class,method="insert_Notice")
	void insert_Info(User user);
	//??????
	@Select("select * from "+USERTABLE+" where id = #{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="status_id",property="status",
				one=@One(select="personnel.dao.StatusDao.get_Info",
				fetchType=FetchType.EAGER))
	})
	User get_Info(Integer id);
	//??????
	@SelectProvider(type=UserDynaSqlProvider.class,method="update_Notice")
	void update_Info(User user);
	//??????
	@Delete(" delete from "+USERTABLE+" where id = #{id} ")
	void delete_Info(Integer id);
	
	@Select("select * from "+USERTABLE+" where loginname = #{loginname} and username = #{username}")
	User selectByLoginAndName(
            @Param("loginname") String loginname,
            @Param("username") String username);

	@Select("select * from "+USERTABLE+" where loginname = #{loginname} ")
	User selectByLogin(
            @Param("loginname") String loginname);
	
	@Select("select * from "+USERTABLE+" where username = #{username}")
	User selectByName(
            @Param("username") String username);
	
	@Update("update "+USERTABLE+" set password=#{password} where loginname = #{loginname}")
	void updatePasswordByLoginname(@Param("loginname") String loginname,
                                   @Param("password") String password);

	//????????????????????????
    @Select("select case when sex_id='2' then '???' when sex_id='1' then '???' end as SEX,count(sex_id) as SEXCOUNT from "+EMPLOYEETABLE+" group by sex_id")
    List<Map<String,Object>> countS();
    
    //????????????
    @Select("select case "
    		+ "when job_id='1' then '??????' "
    		+ "when job_id='2' then '?????????' "
    		+ "when job_id='3' then '???????????????' "
    		+ "when job_id='4' then '???????????????' "
    		+ "when job_id='5' then '????????????' "
    		+ "when job_id='6' then '???????????????' "
    		+ "when job_id='7' then '?????????' "
    		+ "when job_id='8' then '????????????' "
    		+ "when job_id='9' then '????????????' "
    		+ "end as JOB_ID,count(job_id) as JOB_IDCOUNT from "+EMPLOYEETABLE+" group by JOB_ID")
    List<Map<String,Object>> countS1();
    //????????????
    @Select("select mailmarketing,allianceadvertising,videoadvertising,directaccess,searchengine"
    		+ " from "+BUSINESSTABLE)
    List<Business> countS2();

    //????????????
 	@SelectProvider(type=UserDynaSqlProvider.class,method="selectWhitParam")
 	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="status_id",property="status",
				one=@One(select="personnel.dao.StatusDao.get_Info",
				fetchType=FetchType.EAGER))
	})
    List<User> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="count")
	public Integer count(Map<String, Object> params);
	
	@Select("select * from "+USERTABLE+" where emp_id = #{id}")
	User get_StaticInfo(Integer staticid);
	
	@Select("select loginname,email from "+USERTABLE+" where emp_id = #{emp_id}")
	User get_StaticInfoLoginname(Integer id);
	
	@Select("select email from "+USERTABLE+" where email = #{email}")
	User get_UserByEmail(String email);
	
	//?????????????????????????????????
	@Select("select loginname,email from "+USERTABLE+" where id > "+2)
	List<User> get_UserAllEmail();
	
	//??????emp_id
	@Select("select emp_id from "+USERTABLE+" where id = #{id} ")
	Integer get_EmpId(Integer id);
}