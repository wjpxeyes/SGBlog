package com.wjp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjp.constants.SystemConstants;
import com.wjp.domain.ResponseResult;
import com.wjp.domain.vo.LinkVo;
import com.wjp.entity.Link;
import com.wjp.mapper.LinkMapper;
import com.wjp.service.LinkService;
import com.wjp.util.BeanCopyUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangjingpeng
 * @description 针对表【wjp_link(友链)】的数据库操作Service实现
 * @createDate 2023-09-23 22:22:55
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link>
        implements LinkService {

    @Override
    public ResponseResult getAllLink() {
        LambdaQueryWrapper<Link> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Link::getStatus, SystemConstants.Link_STATUS_NORMAL);
        List<Link> linkList = list(wrapper);
        List<LinkVo> linkVos = BeanCopyUtil.copyBeanList(linkList, LinkVo.class);
        return ResponseResult.okResult(linkVos);
    }
}




