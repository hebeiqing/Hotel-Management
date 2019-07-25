package com.HM.Hotel.Management.Service;
import com.HM.Hotel.Management.entity.Engage;
import java.util.List;
public interface EngageService {

    Engage save(Engage  engage );
    void delete(int id);
    Engage  finById(int id);
    List<Engage> findAll();
    Engage findByPhoneLike(String phone);

}
