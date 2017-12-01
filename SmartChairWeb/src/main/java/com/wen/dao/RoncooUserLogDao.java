package com.wen.dao;


import com.wen.model.RoncooUserLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoncooUserLogDao extends JpaRepository<RoncooUserLog, Integer>{

	@Query(value="select u from RoncooUserLog u where u.userName=?1")
	RoncooUserLog findByUserName(String string);
	
	RoncooUserLog findByUserNameAndUserIp(String string, String ip);

	Page<RoncooUserLog> findByUserName(String string, Pageable pageable);
}
