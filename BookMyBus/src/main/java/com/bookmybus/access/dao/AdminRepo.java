package com.bookmybus.access.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookmybus.models.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer>{

	public Admin findByAdminUsername(String userName);

}
