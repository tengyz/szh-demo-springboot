package com.sunny.boot.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunny.boot.base.service.PermitsService;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class PermitsServiceImpl implements PermitsService {
    @Override
    public <T> LambdaQueryWrapper<T> buildWrapper(Supplier<T> action) {
        return new LambdaQueryWrapper<>();
    }

    @Override
    public <T> QueryWrapper<T> buildQueryWrapper(Supplier<T> action) {
        return new QueryWrapper<>();
    }
}
