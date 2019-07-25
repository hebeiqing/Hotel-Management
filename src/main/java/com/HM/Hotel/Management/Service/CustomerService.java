package com.HM.Hotel.Management.Service;

import com.HM.Hotel.Management.entity.CustomerType;

import java.util.List;

public  interface  CustomerService {

     CustomerType  save(CustomerType customerType);
    void delete(int id);
    CustomerType finById(int id);
    List<CustomerType> findAll();
}
