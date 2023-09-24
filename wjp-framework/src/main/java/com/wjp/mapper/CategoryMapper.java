package com.wjp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjp.domain.vo.CategoryVo;
import com.wjp.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wangjingpeng
 * @description 针对表【wjp_category(分类表)】的数据库操作Mapper
 * @createDate 2023-09-13 16:18:25
 * @Entity com.wjp.entity.Category
 */

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    List<CategoryVo> getCategoryList();

}




