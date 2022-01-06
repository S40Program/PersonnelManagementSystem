package personnel.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import personnel.dao.provider.PunchClockDynaSqlProvider;
import personnel.pojo.PunchClock;

import static personnel.util.common.Constants.PUNCHCLOCKTABLE;

import java.util.Date;
import java.util.List;

public interface PunchClockDao {


    @Select("select * from "+PUNCHCLOCKTABLE+" where date = #{date} and userId = #{userID}")
    PunchClock getPunchClockByDateAndUserId(@Param("date") String date, @Param("userID") Integer userID);

    @SelectProvider(type= PunchClockDynaSqlProvider.class,method="insert_PunchClock")
    void insert_PunchClock(PunchClock punchClock);

    @Update("update "+PUNCHCLOCKTABLE+" set offwork_status=#{status},offwork = #{date1} where userId = #{id} and date = #{date}")
    void update_PunchClock(@Param("id") Integer id, @Param("date") String date, @Param("date1") Date date1, @Param("status") int status);

    @Select("select * from "+PUNCHCLOCKTABLE+" where userId = #{id}")
    List<PunchClock> findAllPunchById(Integer id);

    @Update("update "+PUNCHCLOCKTABLE+" set offwork_status=#{status},offwork = #{date1},offwork_status_id = #{offwork_status_id} where userId = #{id} and date = #{date}")
    void update_PunchClocks(@Param("id") Integer id, @Param("date") String date, @Param("date1") Date date1,  @Param("status") int status,  @Param("offwork_status_id") int offwork_status_id);

    @Select("select * from "+PUNCHCLOCKTABLE+" where id = #{id}")
    PunchClock getPunchClockById(Integer id);

    @Update("update "+PUNCHCLOCKTABLE+" set content= #{content} where id = #{id} ")
    void update_PunchClockContent(@Param("id") Integer id, @Param("content") String content);


    @Select("select * from "+PUNCHCLOCKTABLE+" where date like  CONCAT('%',#{date},'%') and userId = #{id} " )
    List<PunchClock> getPunchClockByUserID(@Param("id") Integer id, @Param("date") String date);
}
