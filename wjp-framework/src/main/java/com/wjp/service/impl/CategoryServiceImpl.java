package com.wjp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjp.domain.ResponseResult;
import com.wjp.domain.dto.CategoryDto;
import com.wjp.domain.vo.AdminCategoryVo;
import com.wjp.domain.vo.CategoryVo;
import com.wjp.domain.vo.ListVo;
import com.wjp.entity.Category;
import com.wjp.mapper.CategoryMapper;
import com.wjp.service.CategoryService;
import com.wjp.util.BeanCopyUtil;
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

    @Override
    public ResponseResult categoryList(Integer pageNum, Integer pageSize, String name, String status) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getStatus, status)
                .like(Category::getName, name)
                .eq(Category::getDelFlag, 0);
        Page<Category> page = new Page<>(pageNum, pageSize);
        page(page, wrapper);
        List<AdminCategoryVo> categoryVos = BeanCopyUtil.copyBeanList(page.getRecords(), AdminCategoryVo.class);
        return ResponseResult.okResult(new ListVo<>(page.getTotal(), categoryVos));
    }

    @Override
    public ResponseResult addCategory(CategoryDto categoryDto) {
        Category category = BeanCopyUtil.copyBean(categoryDto, Category.class);
        save(category);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getCategory(Long id) {
        Category category = getById(id);
        AdminCategoryVo adminCategoryVo = BeanCopyUtil.copyBean(category, AdminCategoryVo.class);
        return ResponseResult.okResult(adminCategoryVo);
    }

    @Override
    public ResponseResult updateCategory(AdminCategoryVo categoryVo) {
        Category category = BeanCopyUtil.copyBean(categoryVo, Category.class);
        updateById(category);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteCategory(Long id) {
        removeById(id);
        return ResponseResult.okResult();
    }
}




