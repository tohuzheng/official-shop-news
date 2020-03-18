package com.huzheng.service.base;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.huzheng.commoms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Author 胡正
 * @Date 2020/3/14 17:12
 * @Description 基础服务类实现类
 * 泛型D 为dao持久层对象
 * 泛型T 为实体类对象
 */
public class IBaseServiceImpl<D extends BaseMapper<T>,T> implements IBaseService<T>{

    @Autowired
    private D baseMapper;

    @Override
    public int _insert(T entity) {

        return baseMapper.insert(entity);
    }

    @Override
    public int _deleteById(Serializable id) {

        return baseMapper.deleteById(id);
    }

    @Override
    public int _deleteByMap(Map<String, Object> columnMap) {

        return baseMapper.deleteByMap(columnMap);
    }

    @Override
    public int _delete(Wrapper<T> wrapper) {

        return baseMapper.delete(wrapper);
    }

    @Override
    public int _deleteBatchIds(Collection<? extends Serializable> idList) {

        return baseMapper.deleteBatchIds(idList);
    }

    @Override
    public int _updateById(T entity) {

        return baseMapper.updateById(entity);
    }

    @Override
    public int _update(T entity, Wrapper<T> updateWrapper) {

        return baseMapper.update(entity, updateWrapper);
    }

    @Override
    public T _selectById(Serializable id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<T> _selectBatchIds(Collection<? extends Serializable> idList) {

        return baseMapper.selectBatchIds(idList);
    }

    @Override
    public List<T> _selectByMap(Map<String, Object> columnMap) {

        return baseMapper.selectByMap(columnMap);
    }

    @Override
    public T _selectOne(Wrapper<T> queryWrapper) {

        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer _selectCount(Wrapper<T> queryWrapper) {

        return baseMapper.selectCount(queryWrapper);
    }

    @Override
    public List<T> _selectList(Wrapper<T> queryWrapper) {

        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> _selectMaps(Wrapper<T> queryWrapper) {

        return baseMapper.selectMaps(queryWrapper);
    }

    @Override
    public List<Object> _selectObjs(Wrapper<T> queryWrapper) {

        return baseMapper.selectObjs(queryWrapper);
    }

    @Override
    public IPage<T> _selectPage(IPage<T> page, Wrapper<T> queryWrapper) {

        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<Map<String, Object>> _selectMapsPage(IPage<T> page, Wrapper<T> queryWrapper) {
        return baseMapper.selectMapsPage(page, queryWrapper);
    }

}
