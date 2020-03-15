package com.huzheng.service.impl;

import com.huzheng.entity.Product;
import com.huzheng.dao.IProductDao;
import com.huzheng.service.IProductService;
import com.huzheng.service.base.IBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * (Product)表服务实现类
 *
 * @author zheng.hu
 * @since 2020-03-11 21:24:11
 */
@Service("productService")
public class IProductServiceImplI extends IBaseServiceImpl< IProductDao, Product> implements IProductService {
    @Autowired
    private IProductDao productDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Product queryById(Integer id) {
        return this.productDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Product> queryAllByLimit(int offset, int limit) {
        return this.productDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    public Product insert(Product product) {
        this.productDao.insertProduct(product);
        return product;
    }

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    public Product update(Product product) {
        this.productDao.updateProduct(product);
        return this.queryById(product.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.productDao.deleteByIdProduct(id) > 0;
    }
    
     /**
     * 通过实体作为where条件查询
     *
     * @param product 条件
     * @return 查询结果集合
     */
    @Override
    public List<Product> queryAllByCondition(Product product) {
        return this.productDao.queryAll(product);
    }
}