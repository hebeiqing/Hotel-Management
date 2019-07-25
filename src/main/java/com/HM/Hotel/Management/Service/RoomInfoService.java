package com.HM.Hotel.Management.Service;

import com.HM.Hotel.Management.entity.RoomInfo;

import java.util.List;

public interface RoomInfoService {

    RoomInfo save(RoomInfo roomInfo);
    void  delete(int id);
     RoomInfo finById(int id);
     List<RoomInfo> finAll();
    List<RoomInfo> findByRoom_type(String roomtype);
    List<RoomInfo> findByRoomTypeAndRemark(String RoomType,String Remark);
    RoomInfo findByLocation(String location);

}
