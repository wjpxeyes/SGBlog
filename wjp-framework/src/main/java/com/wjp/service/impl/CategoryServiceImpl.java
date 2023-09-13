package com.wjp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjp.domain.ResponseResult;
import com.wjp.entity.Category;
import com.wjp.mapper.CategoryMapper;
import com.wjp.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * @author wangjingpeng
 * @description 针对表【wjp_category(分类表)】的数据库操作Service实现
 * @createDate 2023-09-13 16:18:25
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
        implements CategoryService {

    @Override
    public ResponseResult getCategoryList() {

        return null;
    }
}




