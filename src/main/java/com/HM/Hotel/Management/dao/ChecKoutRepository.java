package com.HM.Hotel.Management.dao;

import com.HM.Hotel.Management.entity.ChecKout;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChecKoutRepository  extends CrudRepository<ChecKout,Integer> {

    List<ChecKout> findByNumberLike(String number);
}
