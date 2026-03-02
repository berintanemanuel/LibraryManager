package com.example.librarymanagement.service;

import com.example.librarymanagement.exceptions.DuplicateMemberException;
import com.example.librarymanagement.exceptions.MemberNonExistentException;
import com.example.librarymanagement.model.Member;
import com.example.librarymanagement.utils.filters.SearchMemberFilter;
import com.example.librarymanagement.repository.MemberRepository;
import com.example.librarymanagement.utils.specifications.MemberSpecifications;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void addMember(Member member) {
        try {
            memberRepository.save(member);
        }  catch (DataIntegrityViolationException e) {
            throw new DuplicateMemberException();
        }
    }

    public List<Member> getMembers(SearchMemberFilter filter){
        Specification<Member> specification = Specification.
                where(MemberSpecifications.byId(filter.id()))
                .and(MemberSpecifications.byFirstName(filter.firstName()))
                .and(MemberSpecifications.byLastName(filter.lastName()))
                .and(MemberSpecifications.byEmail(filter.email()))
                .and(MemberSpecifications.byStartBirthDate(filter.startDateOfBirth()))
                .and(MemberSpecifications.byEndBirthDate(filter.endDateOfBirth())
                .and(MemberSpecifications.byActive(filter.active())));
        return memberRepository.findAll(specification);
    }

    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }

    public void updateMember(Long id, Member newMember) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNonExistentException::new);
        member.setFirstName(newMember.getFirstName());
        member.setLastName(newMember.getLastName());
        member.setEmail(newMember.getEmail());
        member.setDateOfBirth(newMember.getDateOfBirth());
        member.setActive(newMember.getActive());
        memberRepository.save(member);
    }

}
