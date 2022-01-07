package personnel.dao;


import static personnel.util.common.Constants.ENDOWMENTINSURANCETABLE;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import personnel.pojo.Endowmentinsurance;


public interface EndowmentinsuranceDao {
	//查询
	@Select("select * from "+ENDOWMENTINSURANCETABLE+" ")
	List<Endowmentinsurance> selectAllEndowmentinsurance();
	
	@Select("select * from "+ENDOWMENTINSURANCETABLE+" where name like CONCAT('%',#{content},'%')")
	List<Endowmentinsurance> selectLikeAllEndowmentinsurance(String content);
	
	@Select("select * from "+ENDOWMENTINSURANCETABLE+" where id = #{id}")
	Endowmentinsurance get_Info(Integer id);


	
}