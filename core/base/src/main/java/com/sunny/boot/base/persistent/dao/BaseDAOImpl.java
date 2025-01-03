package com.sunny.boot.base.persistent.dao;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunny.boot.base.enums.LogicDeleteEnum;
import com.sunny.boot.base.persistent.entity.BaseDO;
import com.sunny.boot.base.service.PermitsService;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

/**
 * 处理租户等权限逻辑，实现符合框架要求的增删查改
 *
 * @param <M> mapper类型
 * @param <D> 数据库对像类型
 */
public abstract class BaseDAOImpl<M extends BaseMapper<D>, D extends BaseDO> extends ServiceImpl<M, D> {

    static {

        TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), BaseDO.class) ;
    }
    final static String LAST_LIMIT_SQL = "limit %s";

    /**
     * 框架权限处理bean
     */
    private final PermitsService permitsService;

    /**
     * 提供构造函数，为spring 提供注入口
     *
     * @param permitsService
     */
    protected BaseDAOImpl(PermitsService permitsService) {
        this.permitsService = permitsService;
    }

    /**
     * 使用反射机制，根据泛型定义实例化数据库对像
     *
     * @return
     */
    private D newInstance() {
        Type type = ((ParameterizedTypeImpl) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Class<?> clazz = (Class<?>) type;
        Object object = null;
        try {
            object = clazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return (D) object;
    }

    /**
     * 分面查询
     *
     * @param param      查询条件对像
     * @param pageSize
     * @param pageNum
     * @param biConsumer
     * @param <T>
     * @return
     */
    public <T> IPage<D> page(T param, Integer pageSize, Integer pageNum, BiConsumer<T, LambdaQueryWrapper<D>> biConsumer) {
        LambdaQueryWrapper<D> wrapper = permitsService.buildWrapper(() -> newInstance());
        biConsumer.accept(param, wrapper);
        wrapper.eq(BaseDO::getDelFlag, LogicDeleteEnum.未删除);
        IPage<D> page=  page(new Page<>(pageNum, pageSize), wrapper);
        return page;
    }

    /**
     * 根据主键查询对像
     *
     * @param id
     * @return
     */
    public D load(Serializable id) {
        if (!Optional.ofNullable(id).isPresent()) {
            return null;
        }
        if (Optional.ofNullable(id).isPresent() && id instanceof Long) {
            if ((Long) id == 0L) {
                return null;
            }
        }
        QueryWrapper<D> wrapper = permitsService.buildQueryWrapper(() -> newInstance());
        wrapper.eq(BaseDO.Fields.id, id);
        wrapper.eq(StrUtil.toUnderlineCase(BaseDO.Fields.delFlag), LogicDeleteEnum.未删除.getValue());
        return getBaseMapper().selectOne(wrapper);
    }

    /**
     * 根据主键，将参数里的值更新到数据库对像中
     *
     * @param id
     * @param param
     * @param biConsumer
     * @param <T>
     * @return
     */
    public <T> D update(Serializable id, T param, BiConsumer<T, D> biConsumer) {

        D dbData = load(id);
        if (!Optional.ofNullable(dbData).isPresent()) {
            return null;
        }
        biConsumer.accept(param, dbData);

        dbData.setUpdatedTime(LocalDateTime.now());
        updateById(dbData);
        return dbData;
    }

    /**
     * 逻辑删除数据库对像
     *
     * @param id
     * @return
     */
    public Boolean remove(Serializable id) {
        D dbData = load(id);

        if (!Optional.ofNullable(dbData).isPresent()) {
            return false;
        }
        if (Optional.ofNullable(dbData).isPresent()) {
            dbData.setUpdatedTime(LocalDateTime.now());
            //有权限删除
            removeById(dbData);
            return true;
        }
        return false;
    }

    /**
     * 根据条件查询数据库列表，默认最大1000条
     *
     * @param param
     * @param biConsumer
     * @param <T>
     * @return
     */
    public <T> List<D> list(T param, BiConsumer<T, LambdaQueryWrapper<D>> biConsumer) {
        return list(param, biConsumer, 1000L);
    }

    /**
     * 根据条件查询数据库列表
     *
     * @param param
     * @param biConsumer
     * @param maxSize
     * @param <T>
     * @return
     */
    public <T> List<D> list(T param, BiConsumer<T, LambdaQueryWrapper<D>> biConsumer, Long maxSize) {
        LambdaQueryWrapper<D> wrapper = permitsService.buildWrapper(() -> newInstance());
        biConsumer.accept(param, wrapper);
        wrapper.eq(BaseDO::getDelFlag, LogicDeleteEnum.未删除);
        wrapper.last(String.format(LAST_LIMIT_SQL, maxSize));
        return list(wrapper);
    }

    /**
     * 新增数据库对像
     *
     * @param id
     * @param param
     * @param biConsumer
     * @param <T>
     * @return
     */
    public <T> Serializable add(Serializable id, T param, BiConsumer<T, D> biConsumer) {
        D dbData = load(id);
        if (Optional.ofNullable(dbData).isPresent()) {
            return null;
        }
        D dataDO = newInstance();
        biConsumer.accept(param, dataDO);
        if (!Optional.ofNullable(dataDO).isPresent()) {
            return null;
        }
        if (Optional.ofNullable(dataDO.getId()).isPresent() && dataDO.getId() == 0L) {
            dataDO.setId(null);
        }
        dataDO.setDelFlag(LogicDeleteEnum.未删除.getValue());
        dataDO.setCreatedTime(LocalDateTime.now());
        save(dataDO);
        return dataDO.getId();
    }

    /**
     * @param param
     * @param biConsumer
     * @param <T>
     * @return
     */
    public <T> Serializable add(T param, BiConsumer<T, D> biConsumer) {
        return add(null, param, biConsumer);
    }
}
