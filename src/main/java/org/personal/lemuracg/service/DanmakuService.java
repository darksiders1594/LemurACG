package org.personal.lemuracg.service;

import org.personal.lemuracg.dao.DanmakuMapper;
import org.personal.lemuracg.entity.Danmaku;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 该类用于处理与弹幕相关的业务逻辑
 *
 * @author KJulien
 * @since 1.8
 */
@Service
public class DanmakuService {

    // 注入一个数据访问接口的实现对象, 主要处理弹幕数据
    private final DanmakuMapper danmakuMapper;

    public DanmakuService(DanmakuMapper danmakuMapper) {
        this.danmakuMapper = danmakuMapper;
    }

    /**
     * 该方法用于发送一条弹幕
     *
     * @param danmaku 弹幕库的实体类对象
     * @return 以 Map 类型封装, 返回一个 JSON 文件
     * @see Danmaku
     */
    public Map<String, Integer> sendDanmaku(Danmaku danmaku) {
        Map<String, Integer> map = new HashMap<>();

        // todo: 加入 token 验证机制

        // 校验 Danmaku 对象是否成功传入
        if (danmaku == null) {
            throw new IllegalArgumentException("error: 弹幕库实体类对象不能为空值");
        }

        // 记录创建时间
        danmaku.setCreateTime(new Date());

        // 调用弹幕库数据访问层接口, 向 MySQL 插入一条弹幕数据记录
        danmakuMapper.insertDanmaku(danmaku);

        // code: 0 弹幕发送成功, code: 1 弹幕发送失败
        map.put("code", 0);
        return map;
    }

    /**
     * 该方法用于加载弹幕库
     *
     * @param danmakuID 弹幕的查询依据
     * @param maximum 弹幕的条数上限
     * @return 以 Map 类型封装, 返回一个 JSON 文件
     */
    public Map<String, Object> loadDanmakuLibrary(String danmakuID, int maximum) {
        Map<String, Object> map = new HashMap<>();

        // 弹幕接口对每条弹幕的解析格式是二维数组
        Object[][] danmakuArray = null;

        // 调用弹幕库数据访问层接口, 根据 danmakuID 查询弹幕数据
        List<Danmaku> danmakuList = danmakuMapper.selectByDanmakuID(danmakuID, maximum);

        if (danmakuList != null) {
            danmakuArray = new Object[danmakuList.size()][5];

            // 声明 danmakuArray 的下标索引, 用于向数组迭代存放 danmaku 数据
            int index = 0;
            for (Danmaku danmaku : danmakuList) {
                danmakuArray[index][0] = danmaku.getTime();
                danmakuArray[index][1] = danmaku.getType();
                danmakuArray[index][2] = danmaku.getColor();
                danmakuArray[index][3] = danmaku.getUserID();
                danmakuArray[index][4] = danmaku.getText();
                index++;
            }
        }

        // code: 0 弹幕加载成功, code: 1 弹幕加载失败
        map.put("code", 0);
        map.put("data", danmakuArray);

        return map;
    }

}
