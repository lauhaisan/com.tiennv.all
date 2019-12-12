package com.tiennv.all.api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tiennv.all.entity.UserEntity;
import com.tiennv.all.repository.UserRepository;
import com.tiennv.all.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/user-page/v1/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
	
	@Autowired
	private final UserService service;
	@ApiOperation(value = "List the summary of all the users", notes = "Returns a list of all users")
	  @ApiResponses({@ApiResponse(code = 200, message = "Success", response = UserEntity.class, responseContainer = "List")})
	  @GetMapping("/user")
	  public List<UserEntity> getUsers() {
		System.out.println("user ");
	    return service.getUsers();
	  }

	    @RequestMapping(value = "/user", method = RequestMethod.POST)
	    public UserEntity create(@RequestBody UserEntity user){
	        return service.save(user);
	    }
}
