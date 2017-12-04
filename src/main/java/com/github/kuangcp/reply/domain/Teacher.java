package com.github.kuangcp.reply.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by https://github.com/kuangcp on 17-11-26  下午8:51
 * 教师
 * @author kuangcp
 * TODO Id是自动生成OID还是教师卡号
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Teacher implements Serializable{
    @Id
    @GeneratedValue
    private long teacherId;
    private String name;
    private String password;
    private String email;
    private String weixinId;//微信id，用于获取通知
    private short amount;//出题任务数


    @ManyToOne
    @JoinColumn(name = "majorId")
    private Major majorId;

    @ManyToOne
    @JoinColumn(name = "teamId")
    private Team teamId;

    public Teacher(long teacherId){
        this.teacherId = teacherId;
    }
}
