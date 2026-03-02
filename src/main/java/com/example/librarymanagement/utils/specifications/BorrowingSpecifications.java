package com.example.librarymanagement.utils.specifications;

import com.example.librarymanagement.model.Borrowing;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

public class BorrowingSpecifications {

    public static Specification<Borrowing> byId(@RequestParam Long id) {
        return ((root, query, criteriaBuilder) -> {
            if(id == null) return null;
            return criteriaBuilder.equal(root.get("id"), id);
        });
    }

    public static Specification<Borrowing> byMemberId(Long memberId) {
        return ((root, query, criteriaBuilder) -> {
            if(memberId == null) return null;
            return criteriaBuilder.equal(root.get("member").get("id"), memberId);
        });
    }

    public static Specification<Borrowing> byBookId(Long bookId) {
        return ((root, query, criteriaBuilder) -> {
          if(bookId == null) return null;
          return criteriaBuilder.equal(root.get("book").get("id"), bookId);
        });
    }

    public static Specification<Borrowing> byReturnDate(LocalDate returnDate) {
        return ((root, query, criteriaBuilder) -> {
            if(returnDate == null) return null;
            return criteriaBuilder.lessThanOrEqualTo(root.get("returnDate"), returnDate);
        });
    }

    public static Specification<Borrowing> byBorrowDate(LocalDate borrowDate) {
        return ((root, query, criteriaBuilder) -> {
            if(borrowDate == null) return null;
            return criteriaBuilder.greaterThanOrEqualTo(root.get("borrowDate"), borrowDate);
        });
    }

    public static Specification<Borrowing> byReturned(Boolean returned) {
        return ((root, query, criteriaBuilder) -> {
            if(returned == null) return null;
            return criteriaBuilder.equal(root.get("returned"), returned);
        });
    }

}
