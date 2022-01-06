package personnel.dao.provider;

import static personnel.util.common.Constants.INSURANCETABLE;

import java.util.Map;


import org.apache.ibatis.jdbc.SQL;

import personnel.pojo.Insurance;

public class InsuranceDynaSqlProvider {
	
	public String selectWhitParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(INSURANCETABLE);
				if(params.get("insurance") != null){
					Insurance insurance = (Insurance) params.get("insurance");
					if(insurance.getDept() != null && insurance.getDept().getId() != null && insurance.getDept().getId() != 0){
						WHERE(" DEPT_ID = #{insurance.dept.id} ");
					}
					if(insurance.getJob() != null && insurance.getJob().getId() != null && insurance.getJob().getId() != 0){
						WHERE(" JOB_ID = #{insurance.job.id} ");
					}
					if(insurance.getEmployee() != null && !insurance.getEmployee().equals("")){
						WHERE("  EMP_ID LIKE CONCAT ('%',#{insurance.employee.id},'%') ");
					}
					if(insurance.getMedicareInsurance()!= null && !insurance.getMedicareInsurance().equals("")){
						WHERE(" MEDICARE_INSURANCE LIKE CONCAT ('%',#{insurance.medicareInsurance},'%') ");
					}
					if(insurance.getEndowmentInsurance()!= null && !insurance.getEndowmentInsurance().equals("") ){
						WHERE(" ENDOWMENT_INSURANCE LIKE CONCAT ('%',#{insurance.endowmentInsurance},'%') ");
					}
					if(insurance.getAccidentInsurance()!= null && !insurance.getAccidentInsurance().equals("") ){
						WHERE(" ACCIDENT_INSURANCE LIKE CONCAT ('%',#{insurance.accidentInsurance},'%') ");
					}
				}
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
				FROM(INSURANCETABLE);
				if(params.get("insurance") != null){
					Insurance insurance = (Insurance) params.get("insurance");
					if(insurance.getDept() != null && insurance.getDept().getId() != null && insurance.getDept().getId() != 0){
						WHERE(" DEPT_ID = #{insurance.dept.id} ");
					}
					if(insurance.getJob() != null && insurance.getJob().getId() != null && insurance.getJob().getId() != 0){
						WHERE(" JOB_ID = #{insurance.job.id} ");
					}
					if(insurance.getEmployee() != null && !insurance.getEmployee().equals("")){
						WHERE("  EMP_ID LIKE CONCAT ('%',#{insurance.employee.id},'%') ");
					}
					if(insurance.getMedicareInsurance()!= null && !insurance.getMedicareInsurance().equals("")){
						WHERE(" MEDICARE_INSURANCE LIKE CONCAT ('%',#{insurance.medicareInsurance},'%') ");
					}
					if(insurance.getEndowmentInsurance()!= null && !insurance.getEndowmentInsurance().equals("") ){
						WHERE(" ENDOWMENT_INSURANCE LIKE CONCAT ('%',#{insurance.endowmentInsurance},'%') ");
					}
					if(insurance.getAccidentInsurance()!= null && !insurance.getAccidentInsurance().equals("") ){
						WHERE(" ACCIDENT_INSURANCE LIKE CONCAT ('%',#{insurance.accidentInsurance},'%') ");
					}
				}
			}
		}.toString();
	}	

	
public String insert_Insurance(Insurance insurance){
		
		return new SQL(){
			{
				INSERT_INTO(INSURANCETABLE);
				if(insurance.getId()!=null) {
					VALUES("id", "#{employee.id}");
				}
				if(insurance.getDept() != null){
					VALUES("DEPT_ID", "#{dept.id}");
				}
				if(insurance.getJob() != null){
					VALUES("JOB_ID", "#{job.id}");
				}
				if(insurance.getEmployee() != null){
					VALUES("EMP_ID", "#{employee.id}");
				}
				if(insurance.getMedicareInsurance() != null){
					VALUES("MEDICARE_INSURANCE", "#{medicareInsurance.id}");
				}
				if(insurance.getEndowmentInsurance() != null){
					VALUES("ENDOWMENT_INSURANCE", "#{endowmentInsurance.id}");
				}
				if(insurance.getAccidentInsurance() != null){
					VALUES("ACCIDENT_INSURANCE", "#{accidentInsurance.id}");
				}
			}
		}.toString();
	}	
	// 动态更新
	public String update_Insurance(Insurance insurance){
		
		return new SQL(){
			{
				UPDATE(INSURANCETABLE);
				if(insurance.getEmployee() != null){
					SET("EMP_ID = #{employee.id}");
				}
				if(insurance.getDept()!= null){
					SET("DEPT_ID = #{dept.id}");
				}
				if(insurance.getJob()!= null){
					SET("JOB_ID = #{job.id}");
				}
				if(insurance.getAccidentInsurance()!= null){
					SET("ACCIDENT_INSURANCE = #{accidentInsurance.id}");
				}
				if(insurance.getEndowmentInsurance()!= null){
					SET("ENDOWMENT_INSURANCE = #{endowmentInsurance.id}");
				}
				if(insurance.getMedicareInsurance()!= null){
					SET("MEDICARE_INSURANCE= #{medicareInsurance.id}");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}
}

