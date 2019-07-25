package com.HM.Hotel.Management.Service.Impl;

import com.HM.Hotel.Management.Service.LivreinService;
import com.HM.Hotel.Management.dao.LivreinRepository;
import com.HM.Hotel.Management.entity.Livein;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class LivreinServiceImpl implements LivreinService {
    @Autowired
    private LivreinRepository livreinRepository;

    @Override
    public Livein save(Livein Livein) {
        return livreinRepository.save(Livein);
    }

    @Override
    public void delete(int id) {
livreinRepository.deleteById(id);
    }

    @Override
    public Livein finById(int id) {
        return livreinRepository.findById(id).get();
    }

    @Override
    public List<Livein> findAll() {
        return (List<Livein>) livreinRepository.findAll();
    }

    @Override
    public List<Livein> findByPhonesLike(String phones) {
        return livreinRepository.findByPhonesLike(phones);
    }

    @Override
    public Livein findByNameLike(String name) {
        return livreinRepository.findByNameLike(name);
    }



}
