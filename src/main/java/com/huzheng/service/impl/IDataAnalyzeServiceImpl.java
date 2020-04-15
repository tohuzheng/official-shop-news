package com.huzheng.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huzheng.dto.ChartDataDto;
import com.huzheng.dto.DataSumDto;
import com.huzheng.dto.DataTodayDto;
import com.huzheng.entity.BuyOrder;
import com.huzheng.entity.Customer;
import com.huzheng.entity.VisitCount;
import com.huzheng.service.IBuyOrderService;
import com.huzheng.service.ICustomerService;
import com.huzheng.service.IDataAnalyzeService;
import com.huzheng.service.IVisitCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author 胡正
 * @Date 2020/4/7 15:15
 * @Description 数据分析统计服务实现类
 */
@Service
public class IDataAnalyzeServiceImpl implements IDataAnalyzeService {
    @Autowired
    ICustomerService customerService;
    @Autowired
    IBuyOrderService buyOrderService;
    @Autowired
    IVisitCountService visitCountService;

    @Override
    public DataTodayDto getTodayData() {

        DataTodayDto todayDto = new DataTodayDto();

        // 查询浏览量
        Integer visitCount = visitCountService.queryOneDayView(new Date());
        todayDto.setTodayView(visitCount);
        // 查询今天订单量
        Integer buyOrder = buyOrderService.queryOneDayOrderCount(new Date());
        todayDto.setTodayNewOrder(buyOrder);
        // 查询今天注册量
        Integer count = customerService.queryOneDayNewUserCount(new Date());
        todayDto.setTodayNewUser(count);

        return todayDto;
    }

    @Override
    public DataSumDto getSumData() {
        DataSumDto sumDto = new DataSumDto();
        // 总浏览量
        Integer visit = visitCountService.queryAllView();
        sumDto.setSumView(visit);
        // 总注册量
        QueryWrapper<Customer> queryWrapper2 = new QueryWrapper<>();
        Integer customer = customerService._selectCount(queryWrapper2);
        sumDto.setSumUser(customer);
        // 总订单量
        QueryWrapper<BuyOrder> queryWrapper1 = new QueryWrapper<>();
        Integer buyOrder = buyOrderService._selectCount(queryWrapper1);
        sumDto.setSumOrder(buyOrder);
        return sumDto;
    }

    @Override
    public ChartDataDto getSevenDayData() {
        List<String> xNameList = new LinkedList<>();
        Map<String,List<Integer>> seriesData = new HashMap<>();
        List<Integer> visitList = new LinkedList<>();
        List<Integer> customerList = new LinkedList<>();
        List<Integer> buyOrderList = new LinkedList<>();

        Map<String,Date> sevenDate = getSevenDate();
        Set<String> keys = sevenDate.keySet();
        xNameList.addAll(keys);
        Collections.sort(xNameList);
        for (String itemDate: xNameList) {
            // 获取日期
            Date date = sevenDate.get(itemDate);
            // 浏览量
            int visit = visitCountService.queryOneDayView(date);
            visitList.add(visit);
            // 注册量
            int customer = customerService.queryOneDayNewUserCount(date);
            customerList.add(customer);
            // 订单量
            int buyOrder = buyOrderService.queryOneDayOrderCount(date);
            buyOrderList.add(buyOrder);
        }
        seriesData.put("visitCount", visitList);
        seriesData.put("customerCount", customerList);
        seriesData.put("buyOrderCount", buyOrderList);
        ChartDataDto chartDataDto = new ChartDataDto();
        chartDataDto.setxName(xNameList);
        chartDataDto.setSeriesData(seriesData);
        return chartDataDto;
    }

    @Override
    public ChartDataDto getOneYearData() {
        return null;
    }


    /**
     * @author zheng.hu
     * @date 2020/4/7 15:50
     * @description 获取近七天日期
     * @param
     */
    private Map<String,Date> getSevenDate(){
        String todayStr= DateUtil.today();
        Date todayDate = DateUtil.parse(todayStr, "yyyy-MM-dd");
        Map<String,Date> sevenDay = new HashMap<>();
        sevenDay.put(todayStr,todayDate);
        for (int i=1;i<7;i++) {
            DateTime date = DateUtil.offsetDay(todayDate, -i);
            String dateStr = DateUtil.format(date, "yyyy-MM-dd");
            sevenDay.put(dateStr,date);
        }
        return sevenDay;
    }
}
