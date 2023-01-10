package org.personal.lemuracg.entity;

import lombok.Setter;
import lombok.Getter;

import java.util.Date;

@Setter
@Getter
public class Danmaku {

    private int id;
    private String danmakuID;
    private int userID;
    private float time;
    private String text;
    private int color;
    private int type;
    private Date createTime;

}
