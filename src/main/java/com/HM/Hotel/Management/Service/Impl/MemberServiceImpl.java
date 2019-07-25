package com.HM.Hotel.Management.Service.Impl;

import com.HM.Hotel.Management.Service.MemberService;
import com.HM.Hotel.Management.dao.MemberRepository;
import com.HM.Hotel.Management.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public void delete(int id) {
    memberRepository.deleteById(id);
    }

    @Override
    public Member finById(int id) {
        return memberRepository.findById(id).get();
    }

    @Override
    public List<Member> finAll() {
        return (List<Member>) memberRepository.findAll();
    }
}
