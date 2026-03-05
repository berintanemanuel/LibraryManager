package com.example.librarymanagement.service;

import com.example.librarymanagement.exceptions.BookNonExistentException;
import com.example.librarymanagement.exceptions.BookNotInStockException;
import com.example.librarymanagement.exceptions.BorrowingNonExistentException;
import com.example.librarymanagement.exceptions.MemberNonExistentException;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.Borrowing;
import com.example.librarymanagement.model.Member;
import com.example.librarymanagement.utils.filters.SearchBorrowingFilter;
import com.example.librarymanagement.repository.BookRepository;
import com.example.librarymanagement.repository.BorrowingRepository;
import com.example.librarymanagement.repository.MemberRepository;
import com.example.librarymanagement.utils.responses.BorrowingResponseDTO;
import com.example.librarymanagement.utils.specifications.BorrowingSpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowingService {
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final BorrowingRepository borrowingRepository;

    public BorrowingService(BookRepository bookRepository, MemberRepository memberRepository, BorrowingRepository borrowingRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.borrowingRepository = borrowingRepository;
    }

    @Transactional
    public BorrowingResponseDTO borrowBook(Long memberId, Long bookId) {
        Member member = memberRepository.findById(memberId).orElseThrow(MemberNonExistentException::new);
        Book book = bookRepository.findById(bookId).orElseThrow(BookNonExistentException::new);

        ///  Check to see if the book is in stock
        if(book.getQuantityInStock() == 0)
            throw new BookNotInStockException("Book Not In Stock");

        /// Borrow the book, decrement its stock and add into the borrowingRepository
        book.decrementQuantityInStock();

        Borrowing borrowing = new Borrowing(book, member, LocalDate.now());
        borrowingRepository.save(borrowing);
        return BorrowingResponseDTO.createFromBorrowing(borrowing);
    }

    public List<BorrowingResponseDTO> getBorrowings(SearchBorrowingFilter filter) {
        Specification<Borrowing> specification = Specification.
                where(BorrowingSpecifications.byId(filter.id())).
                and(BorrowingSpecifications.byMemberId(filter.memberId()))
                .and(BorrowingSpecifications.byBookId(filter.bookId()))
                .and(BorrowingSpecifications.byBorrowDate(filter.borrowDate()))
                .and(BorrowingSpecifications.byReturnDate(filter.returnDate()))
                .and(BorrowingSpecifications.byReturned(filter.returned()));
        List<Borrowing> borrowings = borrowingRepository.findAll(specification);
        return borrowings.stream().map(BorrowingResponseDTO::createFromBorrowing).toList();
    }

    public void deleteBorrowing(Long borrowingId) {
        borrowingRepository.deleteById(borrowingId);
    }

    @Transactional
    public BorrowingResponseDTO closeBorrowing(Long borrowingId) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId).orElseThrow(BorrowingNonExistentException::new);
        borrowing.setReturned(true);
        borrowing.setReturnDate(LocalDate.now());
        borrowingRepository.save(borrowing);
        return BorrowingResponseDTO.createFromBorrowing(borrowing);
    }
}
