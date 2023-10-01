package com.wjp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjp.domain.ResponseResult;
import com.wjp.domain.dto.CategoryDto;
import com.wjp.domain.vo.AdminCategoryVo;
import com.wjp.entity.Category;

/**
 * @author wangjingpeng
 * @description 针对表【wjp_category(分类表)】的数据库操作Service
 * @createDate 2023-09-13 16:18:25
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();

    ResponseResult categoryList(Integer pageNum, Integer pageSize, String name, String status);

    ResponseResult addCategory(CategoryDto categoryDto);

    ResponseResult getCategory(Long id);

    ResponseResult updateCategory(AdminCategoryVo categoryVo);

    ResponseResult deleteCategory(Long id);
}
