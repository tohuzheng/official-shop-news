package com.huzheng.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.huzheng.commoms.utils.Page;
import com.huzheng.dto.LoginDto;
import com.huzheng.dto.QueryCustomerDto;
import com.huzheng.entity.Customer;
import com.huzheng.service.base.IBaseService;

/**
 * 顾客信息(Customer)表服务接口
 *
 * @author zheng.hu
 * @since 2020-03-11 15:42:40
 */
public interface ICustomerService extends IBaseService<Customer> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Customer queryById(Integer id);

    /**
     * 分页查询多条数据
     *
     * @param queryCustomerDto 查询条件
     * @return 对象列表
     */
    Page<Customer> queryAllByLimit(QueryCustomerDto queryCustomerDto);

    /**
     * 新增数据
     *
     * @param customer 实例对象
     * @return 实例对象
     */
    Customer insert(Customer customer);

    /**
     * 修改数据
     *
     * @param customer 实例对象
     * @return 实例对象
     */
    Customer update(Customer customer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * @author zheng.hu
     * @date 2020/3/11 15:43
     * @description 登录检查
     */
    Customer checkLogin(LoginDto loginDto);

    /**
     * @author zheng.hu
     * @date 2020/3/16 23:05
     * @description 重置密码
     */
    void resetPass(Integer id);

    /**
     * @author zheng.hu
     * @date 2020/3/16 23:05
     * @description 修改客户状态
     */
    void freezeCustomer(Integer id, Integer status);


}