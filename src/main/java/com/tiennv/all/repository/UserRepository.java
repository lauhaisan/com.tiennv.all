package com.tiennv.all.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiennv.all.entity.UserEntity;

@Repository
public  interface UserRepository extends JpaRepository<UserEntity, Long> {
	
}
