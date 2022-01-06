package personnel.dao.provider;

import static personnel.util.common.Constants.PUNCHCLOCKTABLE;

import org.apache.ibatis.jdbc.SQL;

import personnel.pojo.PunchClock;

public class PunchClockDynaSqlProvider {

    // 动态插入
    public String insert_PunchClock(PunchClock punchClock){

        return new SQL(){
            {
                INSERT_INTO(PUNCHCLOCKTABLE);
                if(punchClock.getId()!=null) {
                    VALUES("id", "#{id}");
                }
                if(punchClock.getUserId() != null){
                    VALUES("userId", "#{userId}");
                }
                if(punchClock.getDate()!= null){
                    VALUES("date", "#{date}");
                }
                if(punchClock.getSgin_status_id()!= null){
                    VALUES("sgin_status_id", "#{sgin_status_id}");
                }
            }
        }.toString();

    }

}
