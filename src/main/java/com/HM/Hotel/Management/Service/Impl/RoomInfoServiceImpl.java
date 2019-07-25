package com.HM.Hotel.Management.Service.Impl;


import com.HM.Hotel.Management.Service.RoomInfoService;
import com.HM.Hotel.Management.dao.RoomInfoRepository;
import com.HM.Hotel.Management.entity.RoomInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class RoomInfoServiceImpl implements RoomInfoService {

    @Autowired
    private RoomInfoRepository roomInfoRepository;

    @Override
    public RoomInfo save(RoomInfo roomInfo) {
        return roomInfoRepository.save(roomInfo);
    }

    @Override
    public void delete(int id) {
roomInfoRepository.deleteById(id);
    }

    @Override
    public RoomInfo finById(int id) {
        return roomInfoRepository.findById(id).get();
    }

    @Override
    public List<RoomInfo> finAll() {
        return (List<RoomInfo>) roomInfoRepository.findAll();
    }

    @Override
    public List<RoomInfo> findByRoom_type(String roomtype) {
        return roomInfoRepository.findByRoomType(roomtype);
    }

    @Override
    public List<RoomInfo> findByRoomTypeAndRemark(String RoomType, String Remark) {
        return roomInfoRepository.findByRoomTypeAndRemark(RoomType,Remark);
    }

    @Override
    public RoomInfo findByLocation(String location) {
        return roomInfoRepository.findByLocation(location);
    }

}
