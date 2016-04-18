package ru.outofrange.web.model;

import java.util.Date;

import ru.outofrange.model.Gender;

public class SearchCriteria {
	
	String username;
	String email;
	String name;
	Integer lowerAge, upperAge;
	Gender gender;
	Date lowerDate, upperDate;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Date getLowerDate() {
		return lowerDate;
	}
	public void setLowerDate(Date lowerDate) {
		this.lowerDate = lowerDate;
	}
	public Date getUpperDate() {
		return upperDate;
	}
	public void setUpperDate(Date upperDate) {
		this.upperDate = upperDate;
	}
	public Integer getLowerAge() {
		return lowerAge;
	}
	public void setLowerAge(Integer lowerAge) {
		this.lowerAge = lowerAge;
	}
	public Integer getUpperAge() {
		return upperAge;
	}
	public void setUpperAge(Integer upperAge) {
		this.upperAge = upperAge;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Gender " + gender);
		sb.append(" lowerDate " + lowerDate);
		sb.append(" upperDate " + upperDate);
		sb.append(" lowerAge " + lowerAge);
		sb.append(" upperAge " + upperAge);
		return sb.toString();
	}
}
