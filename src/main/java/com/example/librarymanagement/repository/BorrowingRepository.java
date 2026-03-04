package com.example.librarymanagement.repository;

import com.example.librarymanagement.model.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BorrowingRepository extends JpaRepository<Borrowing,Long>, JpaSpecificationExecutor<Borrowing> {
}
