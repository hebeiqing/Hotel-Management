package com.HM.Hotel.Management.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "customertype") //客户类型表[customertype]
@Data
public class CustomerType {

    @Id  //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //客户类型
    @Column(name = "c_type",length = 32,unique = true)
    private String c_type;

    //折扣属性
    @Column(name = "dis_attr",length = 32,unique = true)
    private String dis_attr;

    //折扣比例
    @Column(name = "discount",length = 32,unique = true)
    private int discount;
    //原价格
    @Column(name = "price",length = 32,unique = true)
    private float price;
   //折扣价格
    @Column(name = "dis_price",length = 32,unique = true)
    private String dis_price;
   //备注
    @Column(name = "remark",length = 32,unique = true)
    private String remark;
   //删除标记
    @Column(name = "delmark",length = 32,unique = true)
    private int delmark;


}
