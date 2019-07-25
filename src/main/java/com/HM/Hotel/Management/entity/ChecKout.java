package com.HM.Hotel.Management.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "checkout") //结算表[checkout]
@Data
@Entity
public class ChecKout {

    @Id  //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //入住手机号
    @Column(name = "number",length = 32)
    private String number;

    //实住天数
    @Column(name = "days",length = 32)
    private int days;

    //金额
    @Column(name = "money",length = 32)
    private float money;

    //结算时间
    @Column(name = "checkTime",length = 32)
    private String checkTime;

    //备注
    @Column(name = "remark",length = 32)
    private String remark;



}
