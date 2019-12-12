package com.tiennv.all.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiennv.all.entity.UserEntity;
import com.tiennv.all.repository.UserRepository;
@Service
public class UserServiceImp implements UserService {
	@Autowired
	UserRepository repository;
	
	@Override
	public UserEntity save(UserEntity user) {
		System.out.println("[UserServiceImp - save] "+user);
		return repository.save(user);
	}

	@Override
	public List<UserEntity> getUsers() {
		System.out.println("getUsers getUsers ");
		List<UserEntity> list=repository.findAll();
		System.out.println("[list] "+list);
		return repository.findAll();
	}

}
