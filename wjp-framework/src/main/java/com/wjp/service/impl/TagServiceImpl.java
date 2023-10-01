package com.wjp.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjp.domain.ResponseResult;
import com.wjp.domain.dto.TagDTO;
import com.wjp.domain.vo.TagVo;
import com.wjp.entity.Tag;
import com.wjp.mapper.TagMapper;
import com.wjp.service.TagService;
import com.wjp.util.BeanCopyUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangjingpeng
 * @description 针对表【wjp_tag(标签)】的数据库操作Service实现
 * @createDate 2023-09-29 14:49:30
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
        implements TagService {

    @Override
    public ResponseResult tagList(Integer pageNum, Integer pageSize, TagDTO tagDTO) {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(tagDTO.getName()), Tag::getName, tagDTO.getName());
        wrapper.eq(StrUtil.isNotBlank(tagDTO.getRemark()), Tag::getRemark, tagDTO.getRemark());

        Page<Tag> page = new Page<>(pageNum, pageSize);
        page(page, wrapper);
        List<Tag> tagList = page.getRecords();
        List<TagDTO> tagDTOS = BeanCopyUtil.copyBeanList(tagList, TagDTO.class);
        TagVo tagVo = new TagVo(tagDTOS, page.getTotal());
        return ResponseResult.okResult(tagVo);
    }

    @Override
    public ResponseResult addTag(TagDTO tagDTO) {
        Tag tag = BeanCopyUtil.copyBean(tagDTO, Tag.class);
        save(tag);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteTag(Long id) {
        removeById(id);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getTagInfo(Long id) {
        Tag tag = getById(id);
        TagDTO tagDTO = BeanCopyUtil.copyBean(tag, TagDTO.class);
        return ResponseResult.okResult(tagDTO);
    }

    @Override
    public ResponseResult updateTag(TagDTO tagDTO) {
        Tag tag = BeanCopyUtil.copyBean(tagDTO, Tag.class);
        updateById(tag);
        return ResponseResult.okResult();
    }
}




