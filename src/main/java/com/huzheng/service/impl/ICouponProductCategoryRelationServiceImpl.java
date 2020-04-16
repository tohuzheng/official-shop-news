package com.huzheng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huzheng.entity.CouponProductCategoryRelation;
import com.huzheng.dao.ICouponProductCategoryRelationDao;
import com.huzheng.service.ICouponProductCategoryRelationService;
import com.huzheng.service.base.IBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * (CouponProductCategoryRelation)表服务实现类
 *
 * @author zheng.hu
 * @since 2020-04-03 16:41:13
 */
@Service("couponProductCategoryRelationService")
public class ICouponProductCategoryRelationServiceImpl extends IBaseServiceImpl<ICouponProductCategoryRelationDao,
        CouponProductCategoryRelation> implements ICouponProductCategoryRelationService {

    @Autowired
    private ICouponProductCategoryRelationDao couponProductCategoryRelationDao;

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
    public CouponProductCategoryRelation queryOneByCouponId(Integer couponId) {
        QueryWrapper<CouponProductCategoryRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("coupon_id",couponId);
        return this._selectOne(queryWrapper);
    }
}