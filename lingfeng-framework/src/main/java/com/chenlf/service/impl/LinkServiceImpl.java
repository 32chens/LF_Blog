package com.chenlf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenlf.constans.SystemConstants;
import com.chenlf.entity.Link;
import com.chenlf.mapper.LinkMapper;
import com.chenlf.service.LinkService;
import com.chenlf.utils.BeanCopyUtils;
import com.chenlf.vo.LinkVo;
import com.chenlf.vo.ResponseResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {
    @Override
    public ResponseResult getAllLink() {
        //查询出所有的审核通过的友链
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> list = list(queryWrapper);
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(list, LinkVo.class);
        return ResponseResult.okResult(linkVos);
    }
}
