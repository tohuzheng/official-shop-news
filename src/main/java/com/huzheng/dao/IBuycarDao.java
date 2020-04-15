package com.huzheng.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huzheng.entity.Buycar;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 购物车(Buycar)表数据库访问层
 *
 * @author zheng.hu
 * @since 2020-04-11 11:05:25
 */
public interface IBuycarDao extends BaseMapper<Buycar> {


}