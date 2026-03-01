package com.example.librarymanagement.utils.specifications;

import com.example.librarymanagement.model.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecifications {
    public static Specification<Book> byId(Long id) {
        return ((root, query, criteriaBuilder) -> {
            if(id == null) return null;
            return criteriaBuilder.equal(root.get("id"), id);
        });
    }

    public static Specification<Book> byTitle(String title) {

        return ((root, query, criteriaBuilder) -> {
            if(title == null) return null;
            return criteriaBuilder.like(root.get("title"), String.format("%%%s%%",title));
        });
    }

    public static Specification<Book> byAuthorId(Long authorId) {
        return ((root, query, criteriaBuilder) -> {
            if(authorId == null) return null;
            return criteriaBuilder.equal(root.get("author").get("id"), authorId);
        });
    }

    public static Specification<Book> byIsbn(String isbn) {
        return ((root, query, criteriaBuilder) -> {
            if(isbn == null) return null;
            return criteriaBuilder.equal(root.get("isbn"), isbn);
        });
    }

    public static Specification<Book> byGenre(String genre) {
        return ((root, query, criteriaBuilder) -> {
            if(genre == null) return null;
            return criteriaBuilder.like(root.get("genre"), String.format("%%%s%%",genre));
        });
    }

    public static Specification<Book> byQuantityInStockLowerBound(Long quantityInStockLowerBound) {
        return ((root, query, criteriaBuilder) -> {
            if(quantityInStockLowerBound == null) return null;
            return criteriaBuilder.greaterThanOrEqualTo(root.get("quantityInStock"), quantityInStockLowerBound);
        });
    }

    public static Specification<Book> byQuantityInStockUpperBound(Long quantityInStockUpperBound) {
        return ((root, query, criteriaBuilder) -> {
            if(quantityInStockUpperBound == null) return null;
            return criteriaBuilder.lessThanOrEqualTo(root.get("quantityInStock"), quantityInStockUpperBound);
        });
    }
}
