package com.HM.Hotel.Management.Service.Impl;

import com.HM.Hotel.Management.Service.CustomerService;
import com.HM.Hotel.Management.dao.CustomerTypeRepository;
import com.HM.Hotel.Management.entity.CustomerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerTypeRepository customerTypeRepository;

    @Override
    public CustomerType save(CustomerType customerType) {
        return customerTypeRepository.save(customerType);
    }

    @Override
    public void delete(int id) {
        customerTypeRepository.deleteById(id);

    }

    @Override
    public CustomerType finById(int id) {
        return customerTypeRepository.findById(id).get();
    }

    @Override
    public List<CustomerType> findAll() {
        return (List<CustomerType>) customerTypeRepository.findAll();
    }
}
