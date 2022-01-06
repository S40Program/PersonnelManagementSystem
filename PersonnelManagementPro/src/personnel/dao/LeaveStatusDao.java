package personnel.dao;

import static personnel.util.common.Constants.LEAVESTATUSTABLE;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import personnel.pojo.LeaveStatus;;

public interface LeaveStatusDao {
	
	//查询
			@Select("select * from "+LEAVESTATUSTABLE+" ")
			List<LeaveStatus> selectAllLeaveType();
			
			@Select("select * from "+LEAVESTATUSTABLE+" where name like CONCAT('%',#{content},'%')")
			List<LeaveStatus> selectLikeAllLeaveType(String content);

			@Select("select * from "+LEAVESTATUSTABLE+" where id = #{id}")
			LeaveStatus get_Info(Integer id);

}
