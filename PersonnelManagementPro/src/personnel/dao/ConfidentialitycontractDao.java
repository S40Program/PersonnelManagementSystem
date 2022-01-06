package personnel.dao;

import static personnel.util.common.Constants.CONFIDENTIALITYCONTRACTTABLE;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import personnel.pojo.Confidentialitycontract;
public interface ConfidentialitycontractDao {
	//查询
		@Select("select * from "+CONFIDENTIALITYCONTRACTTABLE+" ")
		List<Confidentialitycontract> selectAllConfidentialitycontract();
		
		@Select("select * from "+CONFIDENTIALITYCONTRACTTABLE+" where name like CONCAT('%',#{content},'%')")
		List<Confidentialitycontract> selectLikeAllConfidentialitycontract(String content);
	
		@Select("select * from "+CONFIDENTIALITYCONTRACTTABLE+" where id = #{id}")
		Confidentialitycontract get_Info(Integer id);
}
