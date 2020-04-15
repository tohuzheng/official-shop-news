package com.huzheng.service;

import com.huzheng.dto.ChartDataDto;
import com.huzheng.dto.DataSumDto;
import com.huzheng.dto.DataTodayDto;

/**
 * @Author 胡正
 * @Date 2020/4/7 15:14
 * @Description 数据分析服务层
 */
public interface IDataAnalyzeService {

    DataTodayDto getTodayData();

    DataSumDto getSumData();

    ChartDataDto getSevenDayData();

    ChartDataDto getOneYearData();
}
