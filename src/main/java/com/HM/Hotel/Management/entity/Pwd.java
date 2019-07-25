package com.HM.Hotel.Management.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "pwd")  //操作员信息表[pwd]
@Data
public class Pwd {

    @Id  //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //用户登录账号
    @Column(name = "userid",length = 32,unique = true)
    private String userid;
    //登录密码
    @Column(name = "pwd",length = 50,unique = true)
    private String pwd;

    @Column(name = "salt",length = 50,unique = true)
    private String salt;

    /*//用户权限
    @Column(name = "puis",length = 32,unique = true)
    private int puis;*/









}
