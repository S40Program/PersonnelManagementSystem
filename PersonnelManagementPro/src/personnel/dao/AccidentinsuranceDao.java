package personnel.dao;


import static personnel.util.common.Constants.ACCIDENTINSURANCETABLE;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import personnel.pojo.Accidentinsurance;


public interface AccidentinsuranceDao {
	//查询
	@Select("select * from "+ACCIDENTINSURANCETABLE+" ")
	List<Accidentinsurance> selectAllAccidentinsurance();
	
	@Select("select * from "+ACCIDENTINSURANCETABLE+" where name like CONCAT('%',#{content},'%')")
	List<Accidentinsurance> selectLikeAllAccidentinsurance(String content);
	
	@Select("select * from "+ACCIDENTINSURANCETABLE+" where id = #{id}")
	Accidentinsurance get_Info(Integer id);


	
}