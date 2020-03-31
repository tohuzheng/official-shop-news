package com.huzheng.job;

import com.huzheng.service.INewsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author 胡正
 * @Date 2020/3/31 18:10
 * @Description 更新阅读量脚本
 */
@Component
@EnableScheduling
public class UpdateRreadNumberJob {

    @Autowired
    INewsService newsService;


    static Logger logger = Logger.getLogger(UpdateRreadNumberJob.class);
    /**
     * @author zheng.hu
     * @date 2020/3/31 18:15
     * @description 每30分钟同步一次阅读量
     * @param
     */
    @Scheduled(cron = "0 0/30 * * * ?")
    public void updateNewsReadNumber(){
        Long start = System.currentTimeMillis();
        logger.info("同步阅读数开始");
        newsService.syncNewsReadNumber();
        Long over = System.currentTimeMillis();
        logger.info(String.format("同步阅读数结束,耗时：%d 毫秒", over-start));
    }

}
