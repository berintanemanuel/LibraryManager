package com.example.librarymanagement.service;

import com.example.librarymanagement.exceptions.DuplicateMemberException;
import com.example.librarymanagement.exceptions.MemberNonExistentException;
import com.example.librarymanagement.model.Member;
import com.example.librarymanagement.utils.filters.SearchMemberFilter;
import com.example.librarymanagement.repository.MemberRepository;
import com.example.librarymanagement.utils.requests.MemberRequestDTO;
import com.example.librarymanagement.utils.responses.MemberResponseDTO;
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

    public void addMember(MemberRequestDTO memberDto) {
        try {
            Member member = new Member(
                    memberDto.lastName(),
                    memberDto.firstName(),
                    memberDto.email(),
                    memberDto.active(),
                    memberDto.dateOfBirth()
            );
            memberRepository.save(member);
        }  catch (DataIntegrityViolationException e) {
            throw new DuplicateMemberException("Member already exists");
        }
    }

    public List<MemberResponseDTO> getMembers(SearchMemberFilter filter){
        Specification<Member> specification = Specification.
                where(MemberSpecifications.byId(filter.id()))
                .and(MemberSpecifications.byFirstName(filter.firstName()))
                .and(MemberSpecifications.byLastName(filter.lastName()))
                .and(MemberSpecifications.byEmail(filter.email()))
                .and(MemberSpecifications.byStartBirthDate(filter.startDateOfBirth()))
                .and(MemberSpecifications.byEndBirthDate(filter.endDateOfBirth())
                .and(MemberSpecifications.byActive(filter.active())));
        List<Member> members =  memberRepository.findAll(specification);
        return members.stream().map(MemberResponseDTO::createFromMember).toList();
    }

    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }

    public void updateMember(Long id, MemberRequestDTO newMember) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNonExistentException::new);
        member.setFirstName(newMember.firstName());
        member.setLastName(newMember.lastName());
        member.setEmail(newMember.email());
        member.setDateOfBirth(newMember.dateOfBirth());
        member.setActive(newMember.active());
        memberRepository.save(member);
    }

}
