package com.tiennv.all.service;

import java.util.List;


import com.tiennv.all.entity.UserEntity;

public interface UserService {

	public List<UserEntity> getUsers();
	
	public UserEntity save(UserEntity user); 
	
}
