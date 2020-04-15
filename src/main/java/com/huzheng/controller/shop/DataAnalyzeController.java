package com.huzheng.controller.shop;

import com.huzheng.dto.ChartDataDto;
import com.huzheng.dto.DataSumDto;
import com.huzheng.dto.DataTodayDto;
import com.huzheng.service.IDataAnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 胡正
 * @Date 2020/4/7 14:43
 * @Description 数据分析Controller  dataAnalyze/getTodayData
 */
@RequestMapping("/dataAnalyze")
@RestController
public class  DataAnalyzeController {

    @Autowired
    IDataAnalyzeService dataAnalyzeService;
    /**
     * @author zheng.hu
     * @date 2020/4/7 14:56
     * @description 获取今天数据
     * @param
     */
    @GetMapping("/getTodayData")
    public DataTodayDto getTodayData() {
        return dataAnalyzeService.getTodayData();
    }

    /**
     * @author zheng.hu
     * @date 2020/4/7 14:56
     * @description 获取总的数据
     * @param
     */
    @GetMapping("/getSumData")
    public DataSumDto getSumData() {
        return dataAnalyzeService.getSumData();
    }

    /**
     * @author zheng.hu
     * @date 2020/4/7 14:56
     * @description 获取近七天数据
     * @param
     */
    @GetMapping("/getSevenDayData")
    public ChartDataDto getSevenDayData() {
        return dataAnalyzeService.getSevenDayData();
    }

    /**
     * @author zheng.hu
     * @date 2020/4/7 14:57
     * @description 获取近一年数据
     * @param
     */
    @GetMapping("/getOneYearData")
    public ChartDataDto getOneYearData() {
        return null;
    }
}
