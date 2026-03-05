package com.example.librarymanagement.controller;

import com.example.librarymanagement.utils.filters.SearchMemberFilter;
import com.example.librarymanagement.service.MemberService;
import com.example.librarymanagement.utils.requests.MemberRequestDTO;
import com.example.librarymanagement.utils.responses.MemberResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<MemberResponseDTO>> getMembers(@RequestParam(required = false) Long id,
                                                              @RequestParam(required = false) String firstName,
                                                              @RequestParam(required = false) String lastName,
                                                              @RequestParam(required = false) String email,
                                                              @RequestParam(required = false) LocalDate startDateOfBirth,
                                                              @RequestParam(required = false) LocalDate endDateOfBirth,
                                                              @RequestParam(required = false) Boolean active){
        List<MemberResponseDTO> members = memberService.getMembers(new SearchMemberFilter(id, firstName, lastName, active, startDateOfBirth, endDateOfBirth, email));
        return ResponseEntity.ok().body(members);
    }

    @PostMapping
    public ResponseEntity<MemberResponseDTO> addMember(@RequestBody MemberRequestDTO memberDto){
        MemberResponseDTO member = memberService.addMember(memberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMember(@RequestParam Long id){
        this.memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<MemberResponseDTO> updateMember(@RequestParam Long id,@RequestBody MemberRequestDTO memberDto){
        MemberResponseDTO member = this.memberService.updateMember(id, memberDto);
        return ResponseEntity.ok().body(member);
    }
}
