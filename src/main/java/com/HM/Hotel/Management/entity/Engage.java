package com.HM.Hotel.Management.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "engage") //用户预定信息表[engage]
public class Engage {

    @Id  //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

  //客户姓
    @Column(name = "surname",length = 32)
    private String surName;

    //客户名字
  @Column(name = "name",length = 32)
  private String Name;


    //客户邮箱
    @Column(name = "email",length = 32)
    private String eMail;

  //客户电话
  @Column(name = "phone",length = 32)
  private String phone;


    //房间类型编号
    @Column(name = "r_type_id",length = 32)
    private String rTypeId;


    //预定时间
    @Column(name = "pa_time",length = 32)
    private String paTime;

  //离店时间
  @Column(name = "checkout_time",length = 32)
  private String checKoutTtime;

  //房费
  @Column(name = "money",length = 32)
  private double money;


   //特殊要求
  @Column(name = "request",length = 32)
  private String reQuest;

  //登记信息
  @Column(name = "state",length = 32)
  private Integer state;
}
