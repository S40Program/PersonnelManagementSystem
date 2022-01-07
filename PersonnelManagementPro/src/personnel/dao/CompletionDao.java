package personnel.dao;

import static personnel.util.common.Constants.COMPLETIONTABLE;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import personnel.pojo.Completion;

public interface CompletionDao {
	
	//查询
			@Select("select * from "+COMPLETIONTABLE+" ")
			List<Completion> selectAllLeaveType();
			
			@Select("select * from "+COMPLETIONTABLE+" where name like CONCAT('%',#{content},'%')")
			List<Completion> selectLikeAllLeaveType(String content);

			@Select("select * from "+COMPLETIONTABLE+" where id = #{id}")
			Completion get_Info(Integer id);

}
