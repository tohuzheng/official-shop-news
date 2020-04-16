package com.huzheng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huzheng.entity.CouponProductRelation;
import com.huzheng.dao.ICouponProductRelationDao;
import com.huzheng.service.ICouponProductRelationService;
import com.huzheng.service.base.IBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * (CouponProductRelation)表服务实现类
 *
 * @author zheng.hu
 * @since 2020-04-03 16:41:13
 */
@Service("couponProductRelationService")
public class ICouponProductRelationServiceImpl extends IBaseServiceImpl<ICouponProductRelationDao, CouponProductRelation> implements ICouponProductRelationService {

    @Autowired
    private ICouponProductRelationDao couponProductRelationDao;

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this._deleteById(id) > 0;
    }

    @Override
    public CouponProductRelation queryOneByCouponId(Integer couponId) {
        QueryWrapper<CouponProductRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("coupon_id", couponId);
        return this._selectOne(queryWrapper);
    }
}