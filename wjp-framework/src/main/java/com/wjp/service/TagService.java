package com.wjp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjp.domain.ResponseResult;
import com.wjp.domain.dto.TagDTO;
import com.wjp.entity.Tag;

/**
 * @author wangjingpeng
 * @description 针对表【wjp_tag(标签)】的数据库操作Service
 * @createDate 2023-09-29 14:49:30
 */
public interface TagService extends IService<Tag> {

    ResponseResult tagList(Integer pageNum, Integer pageSize, TagDTO tagDTO);

    ResponseResult addTag(TagDTO tagDTO);

    ResponseResult deleteTag(Long id);

    ResponseResult getTagInfo(Long id);

    ResponseResult updateTag(TagDTO tagDTO);

    ResponseResult getArticleTagList();

}
