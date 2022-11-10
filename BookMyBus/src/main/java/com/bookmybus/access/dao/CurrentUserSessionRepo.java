package com.bookmybus.access.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookmybus.access.models.CurrentUserSession;

@Repository
public interface CurrentUserSessionRepo extends JpaRepository<CurrentUserSession, Integer> {

	public CurrentUserSession findByUuid(String uuid);
}
