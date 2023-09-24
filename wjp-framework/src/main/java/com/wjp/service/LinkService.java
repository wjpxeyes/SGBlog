package com.wjp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjp.domain.ResponseResult;
import com.wjp.entity.Link;

/**
 * @author wangjingpeng
 * @description 针对表【wjp_link(友链)】的数据库操作Service
 * @createDate 2023-09-23 22:22:55
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}
