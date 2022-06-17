package com.huntercodexs.unittestsdemo.restcontroller;

import com.huntercodexs.unittestsdemo.dto.request.UserRequestDto;
import com.huntercodexs.unittestsdemo.dto.response.UserResponseDto;
import com.huntercodexs.unittestsdemo.service.ExternalUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("${local.api.prefix}")
public class ExternalUserController {

    @Autowired
    ExternalUserService externalUserService;

    /**
     * Read External User by id
     */
    @GetMapping(path = "/external/users/{id}")
    @ResponseBody
    public ResponseEntity<?> one(@PathVariable("id") String id) {
        log.info("GET Index External Users id is calling");
        return externalUserService.one(id);
    }

    /**
     * Read External Users
     */
    @GetMapping(path = "/external/users")
    @ResponseBody
    public ResponseEntity<?> all() {
        log.info("GET Index External Users All is calling");
        return externalUserService.all();
    }

    /**
     * Create User
     */
    @PostMapping(path = "/external/users")
    @ResponseBody
    public ResponseEntity<?> create(@Valid @RequestBody UserRequestDto userRequestDto) {
        log.info("POST Index External Users is calling");
        return externalUserService.create(userRequestDto);
    }

    /**
     * Delete External User by id
     */
    @DeleteMapping(path = "/external/users/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        log.info("DELETE Index id External Users is calling");
        return externalUserService.delete(id);
    }

    /**
     * Update External User by id
     */
    @PutMapping(path = "/external/users/{id}")
    @ResponseBody
    public ResponseEntity<?> update(
            @Valid @RequestBody(required = true) UserRequestDto userRequestDto,
            @PathVariable("id") String id
    ) {
        log.info("PUT Index id External Users is calling");
        return externalUserService.update(id, userRequestDto);
    }

    /**
     * Patch/Fix External User by id
     */
    @PatchMapping(path = "/external/users/{id}")
    @ResponseBody
    public ResponseEntity<?> patch(
            @Valid @RequestBody(required = false) UserRequestDto userRequestDto,
            @PathVariable("id") String id
    ) {
        log.info("PATCH Index id External Users is calling");
        return externalUserService.patch(id, userRequestDto);
    }

}
