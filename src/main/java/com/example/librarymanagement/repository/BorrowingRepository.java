package com.example.librarymanagement.repository;

import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.Borrowing;
import com.example.librarymanagement.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BorrowingRepository extends JpaRepository<Borrowing,Long>, JpaSpecificationExecutor<Borrowing> {
    public List<Borrowing> findByBookId(Long BookId);
    public List<Borrowing> findByMemberId(Long memberId);
    public List<Borrowing> findByReturned(Boolean returned);
    public List<Borrowing> findByBorrowDateAfter(LocalDate borrowDate);
    public List<Borrowing> findByBorrowDateBetween(LocalDate borrowDateAfter, LocalDate borrowDateBefore);
    public List<Borrowing> findByBorrowDateBefore(LocalDate borrowDateBefore);
}
