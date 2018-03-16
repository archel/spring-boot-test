package com.codurance.ddddemo.controller;

import com.codurance.ddddemo.application.user.GetUserById;
import com.codurance.ddddemo.infrastructure.user.UserDto;
import com.codurance.ddddemo.domain.user.GetUserByIdRequest;
import com.codurance.ddddemo.domain.user.UserNotFoundException;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final ConversionService conversionService;
    private  final GetUserById getUserById;

    public UserController(ConversionService conversionService, GetUserById getUserById) {
        this.conversionService = conversionService;
        this.getUserById = getUserById;
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public UserDto getUserById(@PathVariable String userId) {
        GetUserByIdRequest getUserByIdRequest = new GetUserByIdRequest(userId);

        return this.conversionService.convert(getUserById.execute(getUserByIdRequest), UserDto.class);
    }
}
