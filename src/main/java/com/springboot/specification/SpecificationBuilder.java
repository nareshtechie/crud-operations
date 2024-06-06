package com.springboot.specification;

import com.springboot.constant.Constants;
import com.springboot.util.Util;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;
import java.util.Date;

public class SpecificationBuilder<T>{
    public static Sort sortBy(String orderBy, String[] sortProperties) {
        if (sortProperties == null || sortProperties.length == 0) {
            sortProperties = new String[]{"id"};
        }
        if (orderBy == null || orderBy.equals(Constants.ASC))
            return Sort.by(Sort.Direction.ASC, sortProperties);

        return Sort.by(Sort.Direction.DESC, sortProperties);
    }

    public Pageable constructPageSpecification(Integer pageIndex, Integer pageSize, String[] sortProperties, String orderBy) {
        return PageRequest.of(pageIndex, pageSize, sortBy(orderBy, sortProperties));
    }

    public Specification<T> contains(String columnName, String expression) {
        return (root, query, builder) -> builder.like(root.get(columnName), Util.contains(expression));
    }

    public Specification<T> containsIgnoreCase(String columnName, String expression) {
        return (root, query, builder) -> builder.like(builder.lower(root.get(columnName)), Util.containsIgnoreCase(expression));
    }

    public Specification<T> startWith(String columnName, String expression) {
        return (root, query, builder) -> builder.like(root.get(columnName), Util.startWith(expression));
    }

    public Specification<T> equal(String columnName, Object value) {
        return (root, query, builder) -> builder.equal(root.get(columnName), value);
    }

    public Specification<T> equalJson(String columnName, String fieldName, String value) {
        return (root, query, builder) -> builder.equal(builder.function("jsonb_extract_path_text", String.class,
                root.get(columnName), builder.literal(fieldName)), value);
    }

    public Specification<T> equalDate(String columnName, Date value) {
        return (root, query, builder) -> builder.equal(root.get(columnName), value);
    }

    public Specification<T> equalsInChildTable(String columnName, Object value, String joinColumName) {
        return (root, query, builder) -> builder.equal(
                joinColumName == null ? root.get(columnName) : root.join(joinColumName, JoinType.INNER).get(columnName),
                value);
    }

    public Specification<T> likeInChildTable(String columnName, Object value, String joinColumName) {
        return (root, query, builder) -> builder.like(
                joinColumName == null ? root.get(columnName) : root.join(joinColumName, JoinType.INNER).get(columnName),
                Util.contains(value.toString()));
    }

    public Specification<T> likeInChildTableIgnoreCase(String columnName, Object value, String joinColumName) {
        return (root, query, builder) -> builder.like(
                builder.lower(joinColumName == null ? root.get(columnName) : root.join(joinColumName, JoinType.INNER).get(columnName)),
                Util.containsIgnoreCase(value.toString()));
    }

    public Specification<T> greaterThanOrEqualTo(String columnName, String value) {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get(columnName), value);
    }

    public Specification<T> greaterThanOrEqualToJson(String columnName, String fieldName, String value) {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(builder.function("jsonb_extract_path_text",
                String.class, root.get(columnName), builder.literal(fieldName)), builder.literal(value));
    }

    public Specification<T> greaterThanOrEqualToDate(String columnName, Date value) {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get(columnName), value);
    }

    public Specification<T> lessThanOrEqualTo(String columnName, String value) {
        return (root, query, builder) -> builder.lessThanOrEqualTo(root.get(columnName), value);
    }
}
