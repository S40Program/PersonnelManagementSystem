package personnel.dao.provider;

import static personnel.util.common.Constants.RESUMETABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import personnel.pojo.Resume;

public class ResumeDynaSqlProvider {
	
	// 动态插入
			public String insert(Resume resume){
				
				return new SQL(){
					{
						INSERT_INTO(RESUMETABLE);
						if(resume.getJobtype() != null ){
							VALUES("jobtype", "#{jobtype}");
						}
						if(resume.getContent()!= null ){
							VALUES("content", "#{content}");
						}
						if(resume.getName()!= null ){
							VALUES("name", "#{name}");
						}
						if(resume.getEmail() != null ){
							VALUES("email", "#{email}");
						}
						if(resume.getSex()!= null ){
							VALUES("sex_id", "#{sex.id}");
						}
						if(resume.getEducation() != null ){
							VALUES("education_id", "#{education.id}");
						}
						if(resume.getPhone() != null ){
							VALUES("phone", "#{phone}");
						}
						if(resume.getFilename()!= null ){
							VALUES("filename", "#{filename}");
						}
					}
				}.toString();
			}
			
			
			public String selectWhitParam(Map<String, Object> params){
				String sql =  new SQL(){
					{
						SELECT("*");
						FROM(RESUMETABLE);
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
						FROM(RESUMETABLE);
					}
				}.toString();
			}	
			
			
			// 动态更新
						public String update_Resume(Resume resume){
							return new SQL(){
								{
									UPDATE(RESUMETABLE);
									if(resume.getStatus()!=null){
										SET("status_id = #{status.id}");
									}
									WHERE(" id = #{id} ");
								}
							}.toString();
						}
}
