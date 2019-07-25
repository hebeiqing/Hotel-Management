package com.HM.Hotel.Management.Service;

import com.HM.Hotel.Management.entity.Member;

import java.util.List;

public interface MemberService {
     Member save(Member member);
      void delete(int id);
      Member finById(int id);
      List<Member> finAll();

}
