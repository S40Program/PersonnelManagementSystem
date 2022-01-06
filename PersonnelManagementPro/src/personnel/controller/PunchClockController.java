package personnel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import personnel.pojo.PunchClock;
import personnel.pojo.PunchTime;
import personnel.pojo.User;
import personnel.service.PersonnelService;
import personnel.util.common.Constants;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class PunchClockController {
    @Autowired
    private PersonnelService personnelService;
    @RequestMapping(value = "sign")
    //将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，写入到response对象的body区，通常用来返回JSON数据或者是XML数据。
    //表示该方法的返回结果直接写入 HTTP response body 中，一般在异步获取数据时使用，比如ajax异步请求。
    @ResponseBody
    public String sign(HttpServletResponse response, HttpSession session){
        response.setContentType("text/javascript;charset=utf-8");
        // 获取管理员设定的上班时间
        PunchTime punchTime = personnelService.getPt();
        String am = punchTime.getAm();
        // 获取系统当前时间
        Calendar cal = Calendar.getInstance();
        String t2 = cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE);
        // 对时间进行格式化处理切分
        String[] t11=am.split(":");
        String[] t21=t2.split(":");
        //获取小时
        int hour1 = Integer.parseInt(t11[0]);
        int hour2 = Integer.parseInt(t21[0]);
        //获取分钟
        String minute1 = t11[1];
        String minute2= t21[1];
             if (hour1>hour2){
//                打卡成功
                 PunchClock punchClock = new PunchClock();
                 String date = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE);
                 User user = (User) session.getAttribute(Constants.USER_SESSION);
                 punchClock.setDate(date);
                 punchClock.setUserId(user.getId());
                 personnelService.insert_PunchClock(punchClock);
                 String data = "1";
                 return data;
             }else if (hour1==hour2){
                 if (minute1.compareTo(minute2)>=0){
                     //打卡成功
                     PunchClock punchClock = new PunchClock();
                     String date = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE);
                     User user = (User) session.getAttribute(Constants.USER_SESSION);
                     punchClock.setDate(date);
                     punchClock.setUserId(user.getId());
                     personnelService.insert_PunchClock(punchClock);
                     String data = "1";
                     return data;
                 }else {
                     // 迟到
                     PunchClock punchClock = new PunchClock();
                     String date = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE);
                     User user = (User) session.getAttribute(Constants.USER_SESSION);
                     punchClock.setDate(date);
                     punchClock.setUserId(user.getId());
                     punchClock.setSgin_status_id(2);
                     personnelService.insert_PunchClock(punchClock);
                     String data = "2";
                     return data;
                 }
             }else {
                 // 迟到
                 PunchClock punchClock = new PunchClock();
                 String date = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE);
                 User user = (User) session.getAttribute(Constants.USER_SESSION);
                 punchClock.setDate(date);
                 punchClock.setUserId(user.getId());
                 punchClock.setSgin_status_id(2);
                 personnelService.insert_PunchClock(punchClock);
                 String data = "2";
                 return data;
             }
    }

    @RequestMapping(value = "offwork")
    @ResponseBody
    public String offwork(HttpServletResponse response, HttpSession session){
        response.setContentType("text/javascript;charset=utf-8");

        // 获取管理员设定的下班时间
        PunchTime punchTime = personnelService.getPt();
        String pm = punchTime.getPm();
        // 获取系统当前时间
        Calendar cal = Calendar.getInstance();
        String t2 = cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE);
        String[] t11=pm.split(":");
        String[] t21=t2.split(":");
        int hour1 = Integer.parseInt(t11[0]);
        int hour2 = Integer.parseInt(t21[0]);
        String minute1 = t11[1];
        String minute2= t21[1];
        if (hour1>hour2) {
            //早退
            String date = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE);
            User user = (User) session.getAttribute(Constants.USER_SESSION);
            int status = 2;
            int offwork_status_id = 2;
            personnelService.update_PunchClocks(user.getId(),date,new Date(),status,offwork_status_id);
            String data = "1";
            return data;
        }else if (hour1==hour2){
            if (minute1.compareTo(minute2)>=0){
                // 下班打卡成功
                String date = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE);
                User user = (User) session.getAttribute(Constants.USER_SESSION);
                int status = 2;
                personnelService.update_PunchClock(user.getId(),date,new Date(),status);
                String data = "2";
                return data;
            }else {
                //早退
                String date = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE);
                User user = (User) session.getAttribute(Constants.USER_SESSION);
                int status = 2;
                int offwork_status_id = 2;
                personnelService.update_PunchClocks(user.getId(),date,new Date(),status,offwork_status_id);
                String data = "1";
                return data;
            }
        }else {
            // 下班打卡成功
            String date = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE);
            User user = (User) session.getAttribute(Constants.USER_SESSION);
            int status = 2;
            personnelService.update_PunchClock(user.getId(),date,new Date(),status);
            String data = "2";
            return data;
        }
    }

    @RequestMapping(value = "/checkwork/updatePunchContent")
    public String updatePunchContent(Integer id, Model model){
        PunchClock punchClock = personnelService.getPunchClockById(id);
        model.addAttribute("list",punchClock);
        return "checkwork/punchedit";
    }

    @RequestMapping(value = "/checkwork/toupdatePunchContent")
    public String toupdatePunchContent(Integer id,String content){
        personnelService.update_PunchClockContent(id,content);
        return "redirect:/checkwork/punchlist";
    }

    @RequestMapping(value = "/checkwork/punchtime")
    public String punchtime(Model model){
        PunchTime punchTime = personnelService.getPt();
        model.addAttribute("list",punchTime);
        return "/checkwork/punchtime";
    }

    @RequestMapping(value = "/checkwork/topunchtime")
    public String punchtime(Integer id,String am,String pm){
        personnelService.updatePt(am,pm,id);
        return "redirect:/checkwork/punchtime";
    }

    @RequestMapping(value = "/checkwork/punchlist")
    public String punchlist(Model model,HttpSession session){
        // 根据用户id查询用户的打卡记录
        User user = (User) session.getAttribute(Constants.USER_SESSION);
        List<PunchClock> punch = personnelService.findAllPunchById(user.getId());
        model.addAttribute("punch",punch);
        model.addAttribute("name",user.getUsername());
        return "checkwork/punchlist";
    }
    // 按照月份进行筛选
    @RequestMapping(value = "/checkwork/statistics")
    public String statistics(String date,Model model,HttpSession session){
        User user = (User) session.getAttribute(Constants.USER_SESSION);
        List<PunchClock> punch =  personnelService.getPunchClockByUserID(user.getId(),date);
        model.addAttribute("punch",punch);
        model.addAttribute("name",user.getUsername());
        // 统计迟到，早退天数
        int late = 0;
        int leave_early = 0;
        for (PunchClock punchClock : punch) {
            if (punchClock.getSgin_status_id()==2){
                late++;
            }
            if (punchClock.getOffwork_status_id() == 2){
                leave_early++;
            }
        }
        model.addAttribute("late",late);
        model.addAttribute("leave_early",leave_early);
        return "checkwork/punchlist";
    }
}