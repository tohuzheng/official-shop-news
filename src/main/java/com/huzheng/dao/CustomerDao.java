package com.huzheng.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huzheng.dto.LoginDto;
import com.huzheng.entity.Customer;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 顾客信息(Customer)表数据库访问层
 *
 * @author zheng.hu
 * @since 2020-03-11 15:42:40
 */
public interface CustomerDao extends BaseMapper<Customer> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Customer queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Customer> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param customer 实例对象
     * @return 对象列表
     */
    List<Customer> queryAll(Customer customer);

    /**
     * 新增数据
     *
     * @param customer 实例对象
     * @return 影响行数
     */
    int insert(Customer customer);

    /**
     * 修改数据
     *
     * @param customer 实例对象
     * @return 影响行数
     */
    int update(Customer customer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * @author zheng.hu
     * @date 2020/3/11 15:45
     * @description 登录检查
     */
    Customer loginCheck(LoginDto loginDto);

}