package com.example.librarymanagement.controller;

import com.example.librarymanagement.model.Member;
import com.example.librarymanagement.service.MemberService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/members")
@RestController
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    public List<Member> getMembers(@RequestParam(required = false) String lastName){
        return this.memberService.getMembers(lastName);
    }

    public Member getMemberById(@RequestParam Long id){
        return this.memberService.getMemberById(id);
    }

    public void addMember(@RequestBody Member member){
        this.memberService.addMember(member);
    }
}
