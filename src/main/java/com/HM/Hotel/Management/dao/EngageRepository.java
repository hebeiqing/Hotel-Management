package com.HM.Hotel.Management.dao;

import com.HM.Hotel.Management.entity.ChecKout;
import com.HM.Hotel.Management.entity.Engage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngageRepository extends CrudRepository<Engage,Integer> {

    Engage findByPhoneLike(String phone);
}
