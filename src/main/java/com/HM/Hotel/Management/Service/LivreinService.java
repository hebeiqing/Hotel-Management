package com.HM.Hotel.Management.Service;

import com.HM.Hotel.Management.entity.Livein;

import java.util.List;

public interface LivreinService {

    Livein save(Livein   Livein );
    void delete(int id);
    Livein  finById(int id);
    List< Livein> findAll();

    List<Livein> findByPhonesLike(String phones);
    Livein findByNameLike(String name);

}
