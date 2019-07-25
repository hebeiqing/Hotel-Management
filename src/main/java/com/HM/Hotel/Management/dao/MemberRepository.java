package com.HM.Hotel.Management.dao;

import com.HM.Hotel.Management.entity.ChecKout;
import com.HM.Hotel.Management.entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member,Integer> {
}
