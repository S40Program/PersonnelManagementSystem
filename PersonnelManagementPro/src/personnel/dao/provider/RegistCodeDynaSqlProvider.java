package personnel.dao.provider;

import static personnel.util.common.Constants.REGISTCODETABLE;

import org.apache.ibatis.jdbc.SQL;

import personnel.pojo.RegistCode;

public class RegistCodeDynaSqlProvider {

	
	
	public String insert_Code(RegistCode registCode){
		return new SQL(){
			{
				INSERT_INTO(REGISTCODETABLE);
				if(registCode.getCode()!= null ){
					VALUES("code", "#{code}");
				}
			}
		}.toString();
	}	
}
