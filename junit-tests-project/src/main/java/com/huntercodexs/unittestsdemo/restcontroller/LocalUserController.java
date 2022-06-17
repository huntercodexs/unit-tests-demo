package com.huntercodexs.unittestsdemo.restcontroller;

import com.huntercodexs.unittestsdemo.dto.request.UserRequestDto;
import com.huntercodexs.unittestsdemo.dto.response.UserResponseDto;
import com.huntercodexs.unittestsdemo.service.LocalUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("${local.api.prefix}")
public class LocalUserController {

    @Autowired
    LocalUserService localUserService;

    /**
     * Read User by id
     */
    @GetMapping(path = "/users/{id}")
    @ResponseBody
    public ResponseEntity<UserResponseDto> one(@PathVariable("id") Integer id) {
        log.info("GET Index Users id is calling - LOCAL");
        return localUserService.one(id);
    }

    /**
     * Read Users
     */
    @GetMapping(path = "/users")
    @ResponseBody
    public ResponseEntity<?> all() {
        log.info("GET Index Users All is calling");
        return localUserService.all();
    }

    /**
     * Create User
     */
    @PostMapping(path = "/users")
    @ResponseBody
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody(required = true) UserRequestDto userRequestDto) {
        log.info("POST Index Users is calling");
        return localUserService.create(userRequestDto);
    }

    /**
     * Delete User by id
     */
    @DeleteMapping(path = "/users/{id}")
    @ResponseBody
    public ResponseEntity<UserResponseDto> delete(@PathVariable("id") Integer id) {
        log.info("DELETE Index id Users is calling");
        return localUserService.delete(id);
    }

    /**
     * Update User by id
     */
    @PutMapping(path = "/users/{id}")
    @ResponseBody
    public ResponseEntity<UserResponseDto> update(
            @Valid @RequestBody(required = true) UserRequestDto userRequestDto,
            @PathVariable("id") Integer id
    ) {
        log.info("PUT Index id Users is calling");
        return localUserService.update(id, userRequestDto);
    }

    /**
     * Patch/Fix User by id
     */
    @PatchMapping(path = "/users/{id}")
    @ResponseBody
    public ResponseEntity<UserResponseDto> patch(
            @Valid @RequestBody(required = false) UserRequestDto userRequestDto,
            @PathVariable("id") Integer id
    ) {
        log.info("PATCH Index id Users is calling");
        return localUserService.patch(id, userRequestDto);
    }

}
