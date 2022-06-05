package com.huntercodexs.unittestsdemo.restcontroller;

import com.huntercodexs.unittestsdemo.dto.request.UserRequestDto;
import com.huntercodexs.unittestsdemo.dto.response.UserResponseDto;
import com.huntercodexs.unittestsdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("${api.prefix}")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * Read User by id
     */
    @GetMapping(path = "/users/{id}")
    @ResponseBody
    public ResponseEntity<UserResponseDto> one(@Valid @RequestBody UserRequestDto userRequestDto, @PathVariable("id") String id) {
        log.info("GET Index Users id is calling");
        return userService.one(id, userRequestDto);
    }

    /**
     * Read Users
     */
    @GetMapping(path = "/users")
    @ResponseBody
    public ResponseEntity<UserResponseDto> all(@Valid @RequestBody UserRequestDto userRequestDto) {
        log.info("GET Index Users All is calling");
        return userService.all(userRequestDto);
    }

    /**
     * Create User
     */
    @PostMapping(path = "/users")
    @ResponseBody
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserRequestDto userRequestDto) {
        log.info("POST Index Users is calling");
        return userService.create(userRequestDto);
    }

    /**
     * Delete User by id
     */
    @DeleteMapping(path = "/users/{id}")
    @ResponseBody
    public ResponseEntity<UserResponseDto> delete(@Valid @RequestBody UserRequestDto userRequestDto, @PathVariable("id") String id) {
        log.info("DELETE Index id Users is calling");
        return userService.delete(id, userRequestDto);
    }

    /**
     * Update User by id
     */
    @PutMapping(path = "/users/{id}")
    @ResponseBody
    public ResponseEntity<UserResponseDto> update(@Valid @RequestBody UserRequestDto user, @PathVariable("id") String id) {
        log.info("PUT Index id Users is calling");
        return userService.update(id, user);
    }

    /**
     * Patch/Fix User by id
     */
    @PatchMapping(path = "/users/{id}")
    @ResponseBody
    public ResponseEntity<UserResponseDto> patch(@Valid @RequestBody UserRequestDto userResponseDto, @PathVariable("id") String id) {
        log.info("PATCH Index id Users is calling");
        return userService.patch(id, userResponseDto);
    }

}