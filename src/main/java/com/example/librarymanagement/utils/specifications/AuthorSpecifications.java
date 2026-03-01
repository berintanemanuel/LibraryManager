package com.example.librarymanagement.utils.specifications;

import com.example.librarymanagement.model.Author;
import org.springframework.data.jpa.domain.Specification;

public class AuthorSpecifications {

    public static Specification<Author> byId(Long id) {
        return ((root, query, criteriaBuilder) -> {
            if(id == null) return null;
            return criteriaBuilder.equal(root.get("id"), id);
        });
    }

    public static Specification<Author> byFirstName(String firstName) {
        return ((root, query, criteriaBuilder) -> {
            if(firstName == null) return null;
            return criteriaBuilder.like(root.get("firstName"), String.format("%%%s%%",firstName));
        });
    }

    public static Specification<Author> byLastName(String lastName) {
        return ((root, query, criteriaBuilder) -> {
            if(lastName == null) return null;
            return criteriaBuilder.like(root.get("lastName"), String.format("%%%s%%",lastName));
        });
    }
}
