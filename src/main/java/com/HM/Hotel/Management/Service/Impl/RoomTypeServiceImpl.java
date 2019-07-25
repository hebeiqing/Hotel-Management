package com.HM.Hotel.Management.Service.Impl;

import com.HM.Hotel.Management.Service.RoomTypeService;
import com.HM.Hotel.Management.dao.RoomTypeRepository;
import com.HM.Hotel.Management.entity.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoomTypeServiceImpl implements RoomTypeService {
    @Autowired
    private RoomTypeRepository roomTypeRepository;
    @Override
    public RoomType save(RoomType RoomType) {
        return roomTypeRepository.save(RoomType);
    }

    @Override
    public void delete(int id) {
roomTypeRepository.deleteById(id);
    }

    @Override
    public RoomType finById(int id) {
        return roomTypeRepository.findById(id).get();
    }

    @Override
    public List<RoomType> findAll() {
        return (List<RoomType>) roomTypeRepository.findAll();
    }

    @Override
    public RoomType findByRType(String RType) {
        return roomTypeRepository.findByRType(RType);
    }

    @Override
    public List<RoomType> pege(int pageNum, int pageSize) {
        return roomTypeRepository.findLimit(pageNum,pageSize);
    }

    @Override
    public int findCont() {
        return roomTypeRepository.findCont();
    }


}
