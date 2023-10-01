package com.wjp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjp.constants.SystemConstants;
import com.wjp.domain.ResponseResult;
import com.wjp.domain.dto.LinkDto;
import com.wjp.domain.vo.LinkVo;
import com.wjp.domain.vo.ListVo;
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

    @Override
    public ResponseResult getLinkList(Integer pageNum, Integer pageSize, String name, String status) {
        Page<Link> linkPage = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Link> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Link::getStatus, status)
                .like(Link::getName, name);
        page(linkPage, wrapper);
        return ResponseResult.okResult(new ListVo<>(linkPage.getTotal(), linkPage.getRecords()));
    }

    @Override
    public ResponseResult addLink(LinkDto linkDto) {
        Link link = BeanCopyUtil.copyBean(linkDto, Link.class);
        save(link);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getLink(Long id) {
        Link link = getById(id);
        LinkVo linkVo = BeanCopyUtil.copyBean(link, LinkVo.class);
        return ResponseResult.okResult(linkVo);
    }

    @Override
    public ResponseResult updateLink(LinkVo linkVo) {
        Link link = BeanCopyUtil.copyBean(linkVo, Link.class);
        updateById(link);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteLink(Long id) {
        removeById(id);
        return ResponseResult.okResult();
    }
}




