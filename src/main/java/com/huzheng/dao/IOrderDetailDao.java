package com.huzheng.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huzheng.entity.OrderDetail;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (OrderDetail)表数据库访问层
 *
 * @author zheng.hu
 * @since 2020-04-15 16:09:13
 */
public interface IOrderDetailDao extends BaseMapper<OrderDetail> {


}