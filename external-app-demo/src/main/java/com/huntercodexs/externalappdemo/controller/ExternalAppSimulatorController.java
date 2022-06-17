package com.huntercodexs.externalappdemo.controller;

import com.huntercodexs.externalappdemo.dto.ExternalUserRequestDto;
import com.huntercodexs.externalappdemo.service.ExternalAppSimulatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("${api.prefix}")
public class ExternalAppSimulatorController {

    @Autowired
    ExternalAppSimulatorService externalAppSimulatorService;

    /**
     * Read External User by id
     */
    @GetMapping(path = "/users/{id}")
    @ResponseBody
    public ResponseEntity<?> one(HttpServletRequest headers, @PathVariable("id") Integer id) {
        log.info("GET Index External Users id is calling");
        return externalAppSimulatorService.one(headers, id);
    }

    /**
     * Read External Users
     */
    @GetMapping(path = "/users")
    @ResponseBody
    public ResponseEntity<?> all(HttpServletRequest headers) {
        log.info("GET Index External Users All is calling");
        return externalAppSimulatorService.all(headers);
    }

    /**
     * Create External User
     */
    @PostMapping(path = "/users")
    @ResponseBody
    public ResponseEntity<?> create(
            HttpServletRequest headers,
            @Valid @RequestBody(required = true) ExternalUserRequestDto externalUserRequestDto
    ) {
        log.info("POST Index External Users is calling");
        return externalAppSimulatorService.create(headers, externalUserRequestDto);
    }

    /**
     * Delete External User by id
     */
    @DeleteMapping(path = "/users/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(HttpServletRequest headers, @PathVariable("id") Integer id) {
        log.info("DELETE Index id External Users is calling");
        return externalAppSimulatorService.delete(headers, id);
    }

    /**
     * Update External User by id
     */
    @PutMapping(path = "/users/{id}")
    @ResponseBody
    public ResponseEntity<?> update(
            HttpServletRequest headers,
            @Valid @RequestBody(required = false) ExternalUserRequestDto externalUserRequestDto,
            @PathVariable("id") Integer id
    ) {
        log.info("PUT Index id External Users is calling");
        return externalAppSimulatorService.update(headers, id, externalUserRequestDto);
    }

    /**
     * Patch/Fix External User by id
     */
    @PatchMapping(path = "/users/{id}")
    @ResponseBody
    public ResponseEntity<?> patch(
            HttpServletRequest headers,
            @RequestBody(required = false) ExternalUserRequestDto externalUserRequestDto,
            @PathVariable("id") Integer id
    ) {
        log.info("PATCH Index id External Users is calling");
        return externalAppSimulatorService.patch(headers, id, externalUserRequestDto);
    }

}
