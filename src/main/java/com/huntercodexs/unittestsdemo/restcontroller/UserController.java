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
    public ResponseEntity<UserResponseDto> one(@PathVariable("id") String id) {
        log.info("GET Index Users id is calling");
        return userService.one(id);
    }

    /**
     * Read Users
     */
    @GetMapping(path = "/users")
    @ResponseBody
    public ResponseEntity<?> all() {
        log.info("GET Index Users All is calling");
        return userService.all();
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
    public ResponseEntity<UserResponseDto> delete(@PathVariable("id") String id) {
        log.info("DELETE Index id Users is calling");
        return userService.delete(id);
    }

    /**
     * Update User by id
     */
    @PutMapping(path = "/users/{id}")
    @ResponseBody
    public ResponseEntity<UserResponseDto> update(
            @Valid @RequestBody(required = true) UserRequestDto userRequestDto,
            @PathVariable("id") String id
    ) {
        log.info("PUT Index id Users is calling");
        return userService.update(id, userRequestDto);
    }

    /**
     * Patch/Fix User by id
     */
    @PatchMapping(path = "/users/{id}")
    @ResponseBody
    public ResponseEntity<UserResponseDto> patch(
            @Valid @RequestBody(required = false) UserRequestDto userRequestDto,
            @PathVariable("id") String id
    ) {
        log.info("PATCH Index id Users is calling");
        return userService.patch(id, userRequestDto);
    }

}
