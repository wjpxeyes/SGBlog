package com.wjp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjp.domain.ResponseResult;
import com.wjp.entity.Category;

/**
 * @author wangjingpeng
 * @description 针对表【wjp_category(分类表)】的数据库操作Service
 * @createDate 2023-09-13 16:18:25
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}
