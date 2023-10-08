package com.wjp.controller;


import com.wjp.domain.ResponseResult;
import com.wjp.domain.dto.LinkDto;
import com.wjp.domain.vo.LinkVo;
import com.wjp.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @GetMapping("/list")
    public ResponseResult getLinkList(Integer pageNum, Integer pageSize, String name, String status) {
        return linkService.getLinkList(pageNum, pageSize, name, status);
    }

    @PostMapping
    public ResponseResult addLink(@RequestBody LinkDto linkDto) {
        return linkService.addLink(linkDto);
    }

    @GetMapping("/{id}")
    public ResponseResult getLink(@PathVariable("id") Long id) {
        return linkService.getLink(id);
    }

    @PutMapping
    public ResponseResult updateLink(@RequestBody LinkVo linkVo) {
        return linkService.updateLink(linkVo);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteLink(@PathVariable("id") Long id) {
        return linkService.deleteLink(id);
    }
}
