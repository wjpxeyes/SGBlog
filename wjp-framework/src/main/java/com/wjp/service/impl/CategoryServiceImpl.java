package com.wjp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjp.domain.ResponseResult;
import com.wjp.domain.vo.CategoryVo;
import com.wjp.entity.Category;
import com.wjp.mapper.CategoryMapper;
import com.wjp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangjingpeng
 * @description 针对表【wjp_category(分类表)】的数据库操作Service实现
 * @createDate 2023-09-13 16:18:25
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
        implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ResponseResult getCategoryList() {
        List<CategoryVo> categoryList = categoryMapper.getCategoryList();
        return ResponseResult.okResult(categoryList);
    }
}




