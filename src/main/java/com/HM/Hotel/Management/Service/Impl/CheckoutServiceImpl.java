package com.HM.Hotel.Management.Service.Impl;

import com.HM.Hotel.Management.Service.CheckoutService;
import com.HM.Hotel.Management.dao.ChecKoutRepository;
import com.HM.Hotel.Management.entity.ChecKout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class CheckoutServiceImpl implements CheckoutService {
    @Autowired
   private ChecKoutRepository  checKoutRepository;
    @Override
    public ChecKout save(ChecKout checKout) {
        return checKoutRepository.save(checKout);
    }

    @Override
    public void delete(int id) { checKoutRepository.deleteById(id); }

    @Override
    public ChecKout finById(int id) {
        return checKoutRepository.findById(id).get();
    }

    @Override
    public List<ChecKout> findAll() { return (List<ChecKout>) checKoutRepository.findAll(); }

    @Override
    public List<ChecKout> findByNumberLike(String number) {
        return checKoutRepository.findByNumberLike(number);
    }
}
