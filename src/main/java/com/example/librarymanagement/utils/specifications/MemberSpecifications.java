package com.example.librarymanagement.utils.specifications;

import com.example.librarymanagement.model.Member;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class MemberSpecifications {
    public static Specification<Member> byId(Long id){
        return ((root, query, criteriaBuilder) -> {
            if(id == null) return null;
            return criteriaBuilder.equal(root.get("id"), id);
        });
    }

    public static Specification<Member> byFirstName(String firstName){
        return ((root, query, criteriaBuilder) -> {
            if(firstName == null) return null;
            /// %% - escapes the % character for the like operator
            return criteriaBuilder.like(root.get("firstName"), String.format("%%%s%%",firstName));
        });
    }

    public static Specification<Member> byLastName(String lastName){
        return ((root, query, criteriaBuilder) -> {
            if(lastName == null) return null;
            return criteriaBuilder.like(root.get("lastName"), String.format("%%%s%%",lastName));
        });
    }

    public static Specification<Member> byEmail(String email){
        return ((root, query, criteriaBuilder) -> {
            if(email == null) return null;
            return criteriaBuilder.equal(root.get("email"), email);
        });
    }

    public static Specification<Member> byStartBirthDate(LocalDate startBirthDate){
        return ((root, query, criteriaBuilder) ->  {
            if(startBirthDate == null) return null;
            return criteriaBuilder.greaterThanOrEqualTo(root.get("dateOfBirth"), startBirthDate);
        });
    }

    public static Specification<Member> byEndBirthDate(LocalDate endBirthDate){
        return ((root, query, criteriaBuilder) ->  {
            if(endBirthDate == null) return null;
            return criteriaBuilder.lessThanOrEqualTo(root.get("dateOfBirth"), endBirthDate);
        });
    }

    public static Specification<Member> byActive(Boolean active){
        return ((root, query, criteriaBuilder) -> {
            if(active == null) return null;
            return criteriaBuilder.equal(root.get("active"), active);
        });
    }

}
