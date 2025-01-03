package com.sunny.boot.base.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.function.Supplier;

public interface PermitsService {

    <T> LambdaQueryWrapper<T> buildWrapper(Supplier<T> action);

    <T> QueryWrapper<T> buildQueryWrapper(Supplier<T> action);
}
