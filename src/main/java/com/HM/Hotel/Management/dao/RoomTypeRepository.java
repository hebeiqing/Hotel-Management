package com.HM.Hotel.Management.dao;

import com.HM.Hotel.Management.entity.Engage;
import com.HM.Hotel.Management.entity.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomTypeRepository extends CrudRepository<RoomType,Integer> {

    RoomType  findByRType(String RType);

    @Query(value = "select *from roomtype limit ?1,?2",nativeQuery =true)
    List<RoomType> findLimit(int pageNum,int pageSize);
    @Query(value = "select count(*) from roomtype",nativeQuery = true)
    int findCont();


}
