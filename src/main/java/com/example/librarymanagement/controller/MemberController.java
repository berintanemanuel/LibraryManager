package com.example.librarymanagement.controller;

import com.example.librarymanagement.utils.filters.SearchMemberFilter;
import com.example.librarymanagement.service.MemberService;
import com.example.librarymanagement.utils.requests.MemberRequestDTO;
import com.example.librarymanagement.utils.responses.MemberResponseDTO;
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
    public List<MemberResponseDTO> getMembers(@RequestParam(required = false) Long id,
                                              @RequestParam(required = false) String firstName,
                                              @RequestParam(required = false) String lastName,
                                              @RequestParam(required = false) String email,
                                              @RequestParam(required = false) LocalDate startDateOfBirth,
                                              @RequestParam(required = false) LocalDate endDateOfBirth,
                                              @RequestParam(required = false) Boolean active){
        return this.memberService.getMembers(new SearchMemberFilter(id, firstName, lastName, active, startDateOfBirth, endDateOfBirth, email));
    }

    @PostMapping
    public void addMember(@RequestBody MemberRequestDTO memberDto){
        this.memberService.addMember(memberDto);
    }

    @DeleteMapping
    public void deleteMember(@RequestParam Long id){
        this.memberService.deleteMember(id);
    }

    @PutMapping
    public void updateMember(@RequestParam Long id,@RequestBody MemberRequestDTO memberDto){
        this.memberService.updateMember(id, memberDto);
    }
}
