package com.huntercodexs.unittestsdemo.service;

import com.huntercodexs.unittestsdemo.dto.request.UserRequestDto;
import com.huntercodexs.unittestsdemo.external.ExternalUserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ExternalUserService {

    @Autowired
    ClientService clientService;

    public ResponseEntity<ExternalUserResponseDto> one(String id) {
        return clientService.findUserById(id);
    }

    public ResponseEntity<?> all() {
        return clientService.findUsers();
    }

    public ResponseEntity<ExternalUserResponseDto> create(UserRequestDto userRequestDto) {
        return clientService.createUser(userRequestDto);
    }

    public ResponseEntity<ExternalUserResponseDto> delete(String id) {
        return clientService.deleteUserById(id);
    }

    public ResponseEntity<ExternalUserResponseDto> update(String id, UserRequestDto userRequestDto) {
        return clientService.updateUserById(id, userRequestDto);
    }

    public ResponseEntity<ExternalUserResponseDto> patch(String id, UserRequestDto userRequestDto) {
        return clientService.patchUserById(id, userRequestDto);
    }
}
