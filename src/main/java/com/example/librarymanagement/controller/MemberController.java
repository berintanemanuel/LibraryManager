package com.example.librarymanagement.controller;

import com.example.librarymanagement.model.Member;
import com.example.librarymanagement.model.dto.SearchMemberFilter;
import com.example.librarymanagement.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/members")
@RestController
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<Member> getMembers(@RequestParam(required = false) Long id,
                                   @RequestParam(required = false) String firstName,
                                   @RequestParam(required = false) String lastName,
                                   @RequestParam(required = false) String email,
                                   @RequestParam(required = false) LocalDate startDateOfBirth,
                                   @RequestParam(required = false) LocalDate endDateOfBirth,
                                   @RequestParam(required = false) Boolean active){
        return this.memberService.getMembers(new SearchMemberFilter(id, firstName, lastName, active, startDateOfBirth, endDateOfBirth, email));
    }

    @PostMapping
    public void addMember(@RequestBody Member member){
        this.memberService.addMember(member);
    }
}
