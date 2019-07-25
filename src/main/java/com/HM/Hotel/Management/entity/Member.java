package com.HM.Hotel.Management.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "member") //会员信息表[member]
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //会员编号
    @Column(name = "m_id" ,length = 32,unique = true)
    private String m_id;

    //会员名称
    @Column(name = "m_name" ,length = 32,unique = true)
    private String m_name;

    //性别
    @Column(name = "sex" ,length = 32,unique = true)
    private String sex;

    //证件编号
    @Column(name = "zj_no" ,length = 32,unique = true)
    private String zj_no;

    //详细地址
    @Column(name = "address" ,length = 32,unique = true)
    private String address;

    //联系电话
    @Column(name = "m_tel" ,length = 32,unique = true)
    private String m_tel;

    //备注
    @Column(name = "remark" ,length = 32,unique = true)
    private String remark;



}
