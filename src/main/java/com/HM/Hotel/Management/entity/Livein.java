package com.HM.Hotel.Management.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "live") //入住信息表[livein]
public class Livein {

    @Id  //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

     //房间号
    @Column(name = "r_no",length = 32)
    private String rNo;

    //房间类型编号
    @Column(name = "r_type_id",length = 32)
    private String rTypeId;

    //客户姓氏
    @Column(name = "c_name",length = 32)
    private String cName;
    //性别
    @Column(name = "sex",length = 32)
    private String sex;
    //证件类型
    @Column(name = "zj_type",length = 32)
    private String zjType;

   //入住时间
    @Column(name = "in_time",length = 32)
    private Date inTime;

   //押金
    @Column(name = "foregift",length = 32)
    private String foregift;
    //备注
    @Column(name = "remark",length = 64)
    private String remark;
    //客人备注
    @Column(name = "note",length = 64)
    private String require;

    //客人电话
    @Column(name = "phones",length = 64)
    private String phones;
    //客人名字
    @Column(name = "name",length = 64)
    private String name;

    //操作员
    @Column(name = "userid",length = 32)
    private String userid;






}
