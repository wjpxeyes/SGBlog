package com.wjp.controller;


import com.wjp.domain.ResponseResult;
import com.wjp.domain.dto.CategoryDto;
import com.wjp.domain.vo.AdminCategoryVo;
import com.wjp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("content/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResponseResult categoryList(Integer pageNum, Integer pageSize, String name, String status) {
        return categoryService.categoryList(pageNum, pageSize, name, status);
    }

    @PostMapping
    public ResponseResult addCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.addCategory(categoryDto);
    }

    @GetMapping("/{id}")
    public ResponseResult getCategory(@PathVariable("id") Long id) {
        return categoryService.getCategory(id);
    }

    @PutMapping
    public ResponseResult updateCategory(@RequestBody AdminCategoryVo categoryVo) {
        return categoryService.updateCategory(categoryVo);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteCategory(@PathVariable("id") Long id) {
        return categoryService.deleteCategory(id);
    }

    @GetMapping("/listAllCategory")
    public ResponseResult getArticleCategoryList() {
        return categoryService.getArticleCategoryList();
    }
}
