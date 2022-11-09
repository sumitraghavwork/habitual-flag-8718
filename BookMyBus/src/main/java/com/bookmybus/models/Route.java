package com.bookmybus.models;

import java.util.List;

import javax.persistence.Entity;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Repository
@Entity
public class Route {
	
	private Integer routeid;
	private String routeFrom;
	private String routeTo;
	private Integer distance;
	private List<Bus> bus;

}
