package com.HM.Hotel.Management.dao;

import com.HM.Hotel.Management.entity.ChecKout;
import com.HM.Hotel.Management.entity.Pwd;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PwdRepository extends CrudRepository<Pwd,Integer> {

    Pwd findByUserid(String userid);
}
