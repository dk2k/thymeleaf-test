package ru.outofrange.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import ru.outofrange.jsonview.Views;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1687L;

	private Long id;

	// with @JsonView(Views.Public.class) we control which
	// fields are exposed via JSON
	
	@JsonView(Views.Public.class)
	@NotEmpty
	private String name;
	
	@JsonView(Views.Public.class)
	@NotEmpty
	private String username;
	
	@NotEmpty(message = "Please enter your password.")
	@Size(min = 6, message = "Your password must be 6 characters or longer")
	private String password;
	
	@JsonView(Views.Public.class)
	@NotEmpty
	@Email
	private String email;
	
	@JsonView(Views.Public.class)
	//@Range(min=16, max=100)
	private Integer age;
	
	@JsonView(Views.Public.class)
	private Gender gender;
	
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@JsonView(Views.Public.class)
	private Date registered;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getRegistered() {
		return registered;
	}

	public void setRegistered(Date registered) {
		this.registered = registered;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

}
