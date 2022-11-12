package com.bookmybus.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userLoginId;

	@Size(min = 3, max = 20, message = "${user.invalid.userName}")
	@Column(unique = true)
	private String userName;
	
	@Size(min = 8, max = 20, message = "${user.invalid.password}")
//	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,30}$",message = "${user.invalid.password}")
	private String password;

	@Size(min = 3, max = 20, message = "${user.invalid.firstName}")
//	@Pattern(regexp = "^[a-z][A-Z]", message = "${user.invalid.firstName}")
	private String firstName;

	@Size(min = 3, max = 20, message = "${user.invalid.lastName}")
//	@Pattern(regexp = "^[a-z][A-Z]", message = "${user.invalid.lastName}")
	private String lastName;

	@Size(min = 10, max = 10, message = "${user.invalid.contact}")
//	@Pattern(regexp = "^[0-9]", message = "${user.invalid.contact}")
	private String contact;

	@Email(message = "incorrect email")
	@Column(unique = true)
	private String email;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Reservation> reservations;
}
