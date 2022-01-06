package personnel.dao;

import static personnel.util.common.Constants.PUNCHTIMETABLE;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import personnel.pojo.PunchTime;

public interface PunchTimeDao {

    @Select("select * from "+PUNCHTIMETABLE+" where id = 1")
    PunchTime getPt();

    @Update("update "+PUNCHTIMETABLE+" set am = #{am},pm = #{pm} where id = #{id}")
    void updatePt(@Param("am") String am, @Param("pm") String pm, @Param("id") Integer id);
}
