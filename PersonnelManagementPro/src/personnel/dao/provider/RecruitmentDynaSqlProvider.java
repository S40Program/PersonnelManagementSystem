package personnel.dao.provider;

import static personnel.util.common.Constants.RECRUITMENTTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import personnel.pojo.Recruitment;

public class RecruitmentDynaSqlProvider {
	

	public String selectWhitParam(){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(RECRUITMENTTABLE);
			}	
		}.toString();
		return sql;
	  }	
	
	
	public String selectWhitParam1(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(RECRUITMENTTABLE);
			}	
		}.toString();
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		return sql;
	  }	
	
	// 动态查询总数量
	public String count(Map<String, Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(RECRUITMENTTABLE);
			}
		}.toString();
	}	
	
	//动态插入  insert_Recruitment
	// 动态插入
				public String insert_Recruitment(Recruitment recruitment){
					
					return new SQL(){
						{
							INSERT_INTO(RECRUITMENTTABLE);
							
							if(recruitment.getJobtype()!= null ){
								VALUES("jobtype", "#{jobtype.id}");
							}
							if(recruitment.getContent()!= null ){
								VALUES("content", "#{content}");
							}
							if(recruitment.getPeoplenum()!= null ){
								VALUES("peoplenum", "#{peoplenum}");
							}
							if(recruitment.getStatus()!= null ){
								VALUES("status", "#{status.id}");
							}
							if(recruitment.getEnddate()!= null ){
								VALUES("enddate", "#{enddate}");
							}
							
							
						}
					}.toString();
				}	
				// 动态更新
				public String update_Recruitment(Recruitment recruitment){
					
					return new SQL(){
						{
							UPDATE(RECRUITMENTTABLE);
							
							if(recruitment.getJobtype()!= null ){
								SET("jobtype = #{jobtype.id}");
							}
							if(recruitment.getContent()!= null ){
								SET("content = #{content}");
							}
							if(recruitment.getPeoplenum()!= null ){
								SET("peoplenum = #{peoplenum}");
							}
							if(recruitment.getStatus()!= null ){
								SET("status = #{status.id}");
							}
							if(recruitment.getEnddate()!= null ){
								SET("enddate = #{enddate}");
							}
							WHERE(" id = #{id} ");
						}
					}.toString();
				}
	
}
