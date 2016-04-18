package ru.outofrange.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class UserEntitySpecifications implements Specification<UserEntity> {
	
	private String username;
	private String email;
	private String name;
	private Integer lowerAge;
	private Integer upperAge;
	private Date lowerDate;
	private Date upperDate;
	private Gender gender;
	
	public UserEntitySpecifications(
    		String username,
			String email,
			String name,
			Integer lowerAge,
			Integer upperAge,
			Date lowerDate,
			Date upperDate,
			Gender gender){
		
		this.username = username;
		this.email = email;
		this.name = name;
		this.lowerAge = lowerAge;
		this.upperAge = upperAge;
		this.lowerDate = lowerDate;
		this.upperDate = upperDate;
		this.gender = gender;
	};
	
	private static final String CONST_NAME = "name";
	private static final String CONST_USERNAME = "username";
	private static final String CONST_AGE = "age";
	private static final String CONST_GENDER = "gender";
	private static final String CONST_DATE = "registered";
	private static final String CONST_EMAIL = "email";

	@Override
	public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
	    List<Predicate> predicates = new ArrayList<Predicate>();
	    
	    if (StringUtils.isNotBlank(name)) {
	    	predicates.add(cb.like(root.<String>get(CONST_NAME), "%" + name + "%"));
	    }
	    
	    if (StringUtils.isNotBlank(username)) {
	    	predicates.add(cb.like(root.<String>get(CONST_USERNAME), "%" + username + "%"));
	    }
	    
	    if (StringUtils.isNotBlank(email)) {
	    	predicates.add(cb.like(root.<String>get(CONST_EMAIL), "%" + email + "%"));
	    }
	    
	    if (lowerDate != null) {
	    	predicates.add(cb.greaterThanOrEqualTo(root.<Date>get(CONST_DATE), lowerDate));
	    }
	    
	    if (upperDate != null) {
	    	predicates.add(cb.lessThanOrEqualTo(root.<Date>get(CONST_DATE), upperDate));
	    }
	    
	    if (gender != null) {
	    	predicates.add(cb.equal(root.<Gender>get(CONST_GENDER), gender));
	    }
	    
	    if (lowerAge != null) {
	    	predicates.add(cb.greaterThanOrEqualTo(root.<Integer>get(CONST_AGE), lowerAge));
	    }
	    
	    if (upperAge != null) {
	    	predicates.add(cb.lessThanOrEqualTo(root.<Integer>get(CONST_AGE), upperAge));
	    }
	    
	    return andTogether(predicates, cb);
	    }
	
	
	private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb) {
	    return cb.and(predicates.toArray(new Predicate[0]));
	 }
	
}
