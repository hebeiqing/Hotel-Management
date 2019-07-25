package com.HM.Hotel.Management.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roomtype") //房间类型表[roomtype]
public class RoomType {
    @Id  //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //房间类型
    @Column(name = "r_type",length = 32,unique = true)
    private String rType;
    //床位数
    @Column(name = "bed" ,length = 32,unique = true)
    private String bed;
    //单价
    @Column(name = "price" ,length = 32,unique = true)
    private  float price;
    //押金
    @Column(name = "foregift",length = 32,unique = true)
    private float foregift;
    //是否钟点房
    @Column(name = "cl_room" ,length = 32,unique = true)
    private  String cl_room;
    //钟点房价
    @Column(name = "cl_price" ,length = 32,unique = true)
    private  float cl_price;
    //备注
    @Column(name = "remark",length = 32,unique = true)
    private  String remark;
    //房间数
    @Column(name = "delmark" ,length = 32,unique = true)
    private  int delmark;

}
