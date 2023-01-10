package org.personal.lemuracg.controller;

import org.personal.lemuracg.entity.Danmaku;
import org.personal.lemuracg.service.DanmakuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class DanmakuController {

    // 注入弹幕库服务层对象
    private final DanmakuService danmakuService;

    public DanmakuController(DanmakuService danmakuService) {
        this.danmakuService = danmakuService;
    }

    /**
     * 该方法用于处理发送弹幕的请求
     *
     * @param danmaku 弹幕JSON实体类对象
     * @see Danmaku
     */
    @RequestMapping(path = "/video/danmaku/", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Integer> sendDanmaku(@RequestBody Danmaku danmaku) {
        return danmakuService.sendDanmaku(danmaku);
    }

    /**
     * 该方法用于处理加载弹幕库的请求
     *
     * @param id 弹幕的查询依据
     * @param max 弹幕的条数上限
     */
    @RequestMapping(path = "/video/danmaku/", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> loadDanmakuLibrary(String id, int max) {
        return danmakuService.loadDanmakuLibrary(id, max);
    }

}
