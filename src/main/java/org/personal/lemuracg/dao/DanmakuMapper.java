package org.personal.lemuracg.dao;

import org.apache.ibatis.annotations.Mapper;
import org.personal.lemuracg.entity.Danmaku;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DanmakuMapper {

    void insertDanmaku(Danmaku danmaku);

    List<Danmaku> selectByDanmakuID(String danmakuID, int maximum);

}
