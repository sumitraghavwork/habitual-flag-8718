package com.bookmybus.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Route {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer routeId;

	@NotNull(message = "Starting point of Route cannot be null")
	@NotBlank(message = "Starting point of Route cannot be blank")
	private String routeFrom;

	@NotNull(message = "Destination point of Route cannot be null")
	@NotBlank(message = "Destination point of Route cannot be blank")
	private String routeTo;

	private Integer distance;
	


    
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "route")
	@JsonIgnore
	private List<Bus> busList = new ArrayList<>();

}
