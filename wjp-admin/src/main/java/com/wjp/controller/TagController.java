package com.wjp.controller;


import com.wjp.domain.ResponseResult;
import com.wjp.domain.dto.TagDTO;
import com.wjp.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult list(Integer pageNum, Integer pageSize, TagDTO tagDTO) {
        return tagService.tagList(pageNum, pageSize, tagDTO);
    }

    @PostMapping
    public ResponseResult addTag(@RequestBody TagDTO tagDTO) {
        return tagService.addTag(tagDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteTag(@PathVariable("id") Long id) {
        return tagService.deleteTag(id);
    }

    @GetMapping("/{id}")
    public ResponseResult getTagInfo(@PathVariable("id") Long id) {
        return tagService.getTagInfo(id);
    }

    @PutMapping
    public ResponseResult updateTag(@RequestBody TagDTO tagDTO) {
        return tagService.updateTag(tagDTO);
    }


}
