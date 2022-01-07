package personnel.dao;


import static personnel.util.common.Constants.MEDICAREINSURANCETABLE;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import personnel.pojo.Medicareinsurance;


public interface MedicareinsuranceDao {
	//查询
	@Select("select * from "+MEDICAREINSURANCETABLE+" ")
	List<Medicareinsurance> selectAllMedicareinsurance();
	
	@Select("select * from "+MEDICAREINSURANCETABLE+" where name like CONCAT('%',#{content},'%')")
	List<Medicareinsurance> selectLikeAllMedicareinsurance(String content);
	
	
	@Select("select * from "+MEDICAREINSURANCETABLE+" where id = #{id}")
	Medicareinsurance get_Info(Integer id);


	
}