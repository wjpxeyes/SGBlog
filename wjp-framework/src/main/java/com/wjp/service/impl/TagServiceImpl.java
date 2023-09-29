package com.wjp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjp.domain.ResponseResult;
import com.wjp.entity.Tag;
import com.wjp.mapper.TagMapper;
import com.wjp.service.TagService;
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
    public ResponseResult tagList() {
        List<Tag> tagList = list();
        return ResponseResult.okResult(tagList);
    }
}




