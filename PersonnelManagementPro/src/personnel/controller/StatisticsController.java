package personnel.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import personnel.service.PersonnelService;

@Controller
public class StatisticsController {
	@Autowired
	@Qualifier("PersonnelService")
	private PersonnelService personnelservice;
	@RequestMapping(value="/statistics")
    public String toShows(Model model,HttpSession session) {
		return "/statistics/statistics";
	}
    
	/**
             * 获取饼状图数据
     */
   
	@RequestMapping(value="/echartsData")
    @ResponseBody
    public List<Map<String, Object>> initChart(){
        return personnelservice.pieData();
    }
	
	 /**
	    * 员工人数柱状图
	  */
	@RequestMapping(value="/echartsData1")
	@ResponseBody
	public List<Map<String, Object>> initChart1(){
	     return personnelservice.pieData1();
	 }
	
	/**
	 *  一周业务折线图 
	 */
	
	@RequestMapping(value="/echartsData2")
	@ResponseBody
	public List<Map<String, Object>> initChart2(){
	     return personnelservice.pieData2();
	 }
	
	/**
	 *  地图 
	 */
	@RequestMapping(value="/echartsData3")
	@ResponseBody
	public Map<String, Integer> initChart3(){
	     return personnelservice.pieData3();
	 }
}
