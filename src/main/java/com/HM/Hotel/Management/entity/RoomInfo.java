package com.HM.Hotel.Management.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "roominfo") //房间信息表[roominfo]
@Data
public class RoomInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //房间类型
  /*  @Column(name = "room_type" ,length = 32,unique = true)*/
    private String roomType;

    //房间状态
    @Column(name = "state" ,length = 32,unique = false)
    private String state;

    //所处位置
    @Column(name = "location" ,length = 32,unique = false)
    private String location;

   //房间电话
    @Column(name = "r_tel" ,length = 32,unique = false)
    private String r_tel;

    //房间图片
    @Column(name = "r_img" ,length = 32,unique = false)
    private String r_img;

   //备注
    @Column(name = "remark" ,length = 32,unique = false)
    private String remark;

   //状态计时
    @Column(name = "statetime" ,length = 32,unique = false)
    private String statetime;
}
