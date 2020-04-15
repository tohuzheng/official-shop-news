package com.huzheng.service.impl;

import com.huzheng.entity.OrderDetail;
import com.huzheng.dao.IOrderDetailDao;
import com.huzheng.service.IOrderDetailService;
import com.huzheng.service.base.IBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * (OrderDetail)表服务实现类
 *
 * @author zheng.hu
 * @since 2020-04-15 16:09:13
 */
@Service("orderDetailService")
public class IOrderDetailServiceImpl extends IBaseServiceImpl<IOrderDetailDao,OrderDetail> implements IOrderDetailService {


}