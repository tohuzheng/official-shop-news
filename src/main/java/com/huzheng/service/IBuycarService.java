package com.huzheng.service;

import com.huzheng.dto.AddCarDto;
import com.huzheng.dto.ClearingBillDto;
import com.huzheng.entity.Buycar;
import com.huzheng.service.base.IBaseService;

import java.util.List;

/**
 * 购物车(Buycar)表服务接口
 *
 * @author zheng.hu
 * @since 2020-04-11 11:05:25
 */
public interface IBuycarService extends IBaseService<Buycar> {

    /**
     * 添加购物车记录
     */
    void addCar(AddCarDto addCarDto,Integer customerId);

    /**
     * @author zheng.hu
     * @date 2020/4/11 11:12
     * @description 删除购物车记录
     * @param ids
     */
    void deleteListByIds(List<Integer> ids);

    /**
     * @author zheng.hu
     * @date 2020/4/11 16:22
     * @description 根据购物车记录生成结算单
     * @param ids
     */
    ClearingBillDto generateClearingBill(Integer[] ids);
}