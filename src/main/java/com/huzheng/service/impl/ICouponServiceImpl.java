package com.huzheng.service.impl;

import com.huzheng.entity.Coupon;
import com.huzheng.dao.ICouponDao;
import com.huzheng.service.ICouponService;
import com.huzheng.service.base.IBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * (Coupon)表服务实现类
 *
 * @author zheng.hu
 * @since 2020-04-03 16:41:13
 */
@Service("couponService")
public class ICouponServiceImpl extends IBaseServiceImpl<ICouponDao, Coupon> implements ICouponService {

    @Autowired
    private ICouponDao couponDao;

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {

        return this._deleteById(id)>0;
    }
    
}