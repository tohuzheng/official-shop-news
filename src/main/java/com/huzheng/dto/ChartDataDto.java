package com.huzheng.dto;

import java.util.List;
import java.util.Map;

/**
 * @Author 胡正
 * @Date 2020/4/7 15:06
 * @Description 图表数据dto
 */
public class ChartDataDto {
    /**
     * 横坐标数据
     */
    List<String> xName;
    /**
     * 每条折线数据
     */
    Map<String,List<Integer>> seriesData;

    public List<String> getxName() {
        return xName;
    }

    public void setxName(List<String> xName) {
        this.xName = xName;
    }

    public Map<String, List<Integer>> getSeriesData() {
        return seriesData;
    }

    public void setSeriesData(Map<String, List<Integer>> seriesData) {
        this.seriesData = seriesData;
    }
}
