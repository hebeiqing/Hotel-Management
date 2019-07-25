package com.HM.Hotel.Management.Service;

import com.HM.Hotel.Management.entity.ChecKout;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CheckoutService {

    ChecKout save(ChecKout checKout);
    void delete(int id);
    ChecKout finById(int id);
    List<ChecKout> findAll();
    List<ChecKout> findByNumberLike(String number);

}
