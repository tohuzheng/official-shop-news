package com.huzheng.service.impl;

import com.huzheng.entity.ProductClass;
import com.huzheng.dao.IProductClassDao;
import com.huzheng.service.IProductClassService;
import com.huzheng.service.base.IBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品类目(ProductClass)表服务实现类
 *
 * @author zheng.hu
 * @since 2020-03-13 23:48:22
 */
@Service("productClassService")
public class IProductClassServiceImpl extends IBaseServiceImpl<IProductClassDao,ProductClass> implements IProductClassService {
    @Autowired
    private IProductClassDao productClassDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ProductClass queryById(Integer id) {
        return this.productClassDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<ProductClass> queryAllByLimit(int offset, int limit) {
        return this.productClassDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param productClass 实例对象
     * @return 实例对象
     */
    @Override
    public ProductClass insert(ProductClass productClass) {
        this.productClassDao.insertProductClass(productClass);
        return productClass;
    }

    /**
     * 修改数据
     *
     * @param productClass 实例对象
     * @return 实例对象
     */
    @Override
    public ProductClass update(ProductClass productClass) {
        this.productClassDao.updateProductClass(productClass);
        return this.queryById(productClass.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.productClassDao.deleteByIdProductClass(id) > 0;
    }
    
     /**
     * 通过实体作为where条件查询
     *
     * @param productClass 条件
     * @return 查询结果集合
     */
    @Override
    public List<ProductClass> queryAllByCondition(ProductClass productClass) {
        return this.productClassDao.queryAll(productClass);
    }

    @Override
    public List<String> queryAllProductClass() {
        List<ProductClass> list = this.queryAllByCondition(new ProductClass());
        List<String> productClassList = new ArrayList<>();
        for (int i=0;i<list.size();i++) {
            productClassList.add(list.get(i).getClassName());
        }

        return productClassList;
    }
}