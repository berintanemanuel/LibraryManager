package com.example.librarymanagement.repository;

import com.example.librarymanagement.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member> {
    List<Member> findByFirstName(String firstName);
    List<Member> findByLastName(String lastName);
    Member findByEmail(String email);
    List<Member> findByActive(Boolean active);
    List<Member> findByDateOfBirth(Date dateOfBirth);
}
