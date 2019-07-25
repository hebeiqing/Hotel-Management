package com.HM.Hotel.Management.Service;

import com.HM.Hotel.Management.entity.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoomTypeService {

    RoomType save(RoomType RoomType);
    void delete(int id);
    RoomType finById(int id);
    List<RoomType> findAll();
    RoomType  findByRType(String RType);

    //分页
    List<RoomType> pege(int pageNum,int pageSize);
    int findCont();


}
