package personnel.dao;


import static personnel.util.common.Constants.SEXTABLE;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import personnel.pojo.Sex;
public interface SexDao {
	//查询
		@Select("select * from "+SEXTABLE+" ")
		List<Sex> selectAllSex();
		
		@Select("select * from "+SEXTABLE+" where name like CONCAT('%',#{content},'%')")
		List<Sex> selectLikeAllSex(String content);
		
		@Select("select * from "+SEXTABLE+" where id = #{id}")
		Sex get_Info(Integer id);
}
