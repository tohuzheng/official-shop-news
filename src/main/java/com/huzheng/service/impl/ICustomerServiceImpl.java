package com.huzheng.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huzheng.commoms.utils.Page;
import com.huzheng.dao.IBuyOrderDao;
import com.huzheng.dto.LoginDto;
import com.huzheng.entity.BuyOrder;
import com.huzheng.entity.Customer;
import com.huzheng.dao.CustomerDao;
import com.huzheng.service.ICustomerService;
import com.huzheng.service.base.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 顾客信息(Customer)表服务实现类
 *
 * @author zheng.hu
 * @since 2020-03-11 15:42:40
 */
@Service("customerService")
public class ICustomerServiceImpl extends IBaseServiceImpl<CustomerDao, Customer> implements ICustomerService {
    @Autowired
    private CustomerDao customerDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Customer queryById(Integer id) {
        return this.customerDao.queryById(id);
    }

    /**
     * 无条件限制，分页查询多条数据
     *
     * @param page 分页
     * @return 对象列表
     */
    @Override
    public Page<Customer> queryAllByLimit(Page page) {

        int offset = page.getPageSize()*(page.getPageNo()-1);
        int limit = offset+page.getPageSize();
        List<Customer> customerList = this.customerDao.queryAllByLimit(offset, limit);
        Integer count = customerDao.selectCount(new QueryWrapper<>());
        page.setCount(count);
        page.setList(customerList);
        return page;
    }

    /**
     * 新增数据
     *
     * @param customer 实例对象
     * @return 实例对象
     */
    @Override
    public Customer insert(Customer customer) {
        if (StrUtil.isNotEmpty(customer.getEmail())
                || StrUtil.isNotEmpty(customer.getPassword())
                || StrUtil.isNotEmpty(customer.getUsername())) {
            customer.setCreateDate(new Date());
            this.customerDao.insert(customer);
            return customer;
        }else {
            throw new RuntimeException("邮件，用户名，密码不允许为空");
        }

    }

    /**
     * 通过id修改数据
     *
     * @param customer 实例对象
     * @return 实例对象
     */
    @Override
    public Customer update(Customer customer) {
        if (customer.getId() == null){
            throw new RuntimeException("id为空，无法修改数据");
        }
        this.customerDao.update(customer);
        return this.queryById(customer.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        if (id == null){
            throw new RuntimeException("参数错误");
        }
        return this.customerDao.deleteById(id) > 0;
    }

    /**
     * @author zheng.hu
     * @date 2020/3/11 15:44
     * @description 顾客登录检查
     */
    @Override
    public Customer checkLogin(LoginDto loginDto) {
        Customer customer = customerDao.loginCheck(loginDto);
        return customer;
    }
}