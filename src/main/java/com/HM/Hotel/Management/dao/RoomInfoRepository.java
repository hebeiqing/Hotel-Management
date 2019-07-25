package com.HM.Hotel.Management.dao;

import com.HM.Hotel.Management.entity.ChecKout;
import com.HM.Hotel.Management.entity.RoomInfo;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomInfoRepository extends CrudRepository<RoomInfo,Integer> {


    List<RoomInfo> findByRoomType(String room_type);
    List<RoomInfo> findByRoomTypeAndRemark(String RoomType,String Remark); //根据房间类型查找房间号并筛选可预订房间
    RoomInfo findByLocation(String location); //根据房间号修改房间状态



}
