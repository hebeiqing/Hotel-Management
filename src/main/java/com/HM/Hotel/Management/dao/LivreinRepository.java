package com.HM.Hotel.Management.dao;

import com.HM.Hotel.Management.entity.ChecKout;
import com.HM.Hotel.Management.entity.Livein;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivreinRepository extends CrudRepository<Livein,Integer> {


/*   @Query(value = " from Livein WHERE phones or name like ?1")*/
    List<Livein> findByPhonesLike(String phones);
    Livein findByNameLike(String name);


}
