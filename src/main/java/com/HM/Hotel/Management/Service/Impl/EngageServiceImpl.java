package com.HM.Hotel.Management.Service.Impl;

import com.HM.Hotel.Management.Service.EngageService;
import com.HM.Hotel.Management.dao.EngageRepository;
import com.HM.Hotel.Management.entity.Engage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EngageServiceImpl implements EngageService {
    @Autowired
    private EngageRepository engageRepository;

    @Override
    public Engage save(Engage engage) {
        return engageRepository.save(engage);
    }

    @Override
    public void delete(int id) {
    engageRepository.deleteById(id);
    }

    @Override
    public Engage finById(int id) {
        return engageRepository.findById(id).get();
    }

    @Override
    public List<Engage> findAll() {
        return (List<Engage>) engageRepository.findAll();
    }

    @Override
    public Engage findByPhoneLike(String phone) {
        return engageRepository.findByPhoneLike(phone);
    }
}
