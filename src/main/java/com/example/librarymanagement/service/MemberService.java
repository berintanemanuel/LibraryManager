package com.example.librarymanagement.service;

import com.example.librarymanagement.exceptions.DuplicateMemberException;
import com.example.librarymanagement.exceptions.MemberNonExistentException;
import com.example.librarymanagement.model.Member;
import com.example.librarymanagement.repository.MemberRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Iterable<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public List<Member> getActiveMembers(Boolean active) {
        return memberRepository.findByActive(active);
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(MemberNonExistentException::new);
    }

    public void addMember(Member member) {
        try {
            memberRepository.save(member);
        }  catch (DataIntegrityViolationException e) {
            throw new DuplicateMemberException();
        }
    }

    public List<Member> getMembersByLastName(String lastName) {
        return memberRepository.findByLastName(lastName);
    }

    public List<Member> getMembers(String lastName) {
        if(lastName !=  null)
            return memberRepository.findByLastName(lastName);
        return memberRepository.findAll();
    }

}
