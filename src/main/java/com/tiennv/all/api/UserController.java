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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping(path = "/user-page/v1/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
	@Autowired
	private static Logger logger = LogManager.getLogger(UserController.class);
	@Autowired
	private final UserService service;
	@ApiOperation(value = "List the summary of all the users", notes = "Returns a list of all users")
	  @ApiResponses({@ApiResponse(code = 200, message = "Success", response = UserEntity.class, responseContainer = "List")})
	  @GetMapping("/user")
	  public List<UserEntity> getUsers() {
		logger.info("This is an info message");
		logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
        logger.fatal("This is a fatal message");
	    return service.getUsers();
	  }

	    @RequestMapping(value = "/user", method = RequestMethod.POST)
	    public UserEntity create(@RequestBody UserEntity user){
	        return service.save(user);
	    }
}
