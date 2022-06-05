package com.huntercodexs.unittestsdemo.service;

import com.huntercodexs.unittestsdemo.dto.request.UserRequestDto;
import com.huntercodexs.unittestsdemo.dto.response.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    ClientService clientService;

    public boolean testBool(String val) {
        return !val.equals("false");
    }

    public String testString(String text) {
        if (text.isEmpty()) {
            return "The string is empty";
        }
        return "The string is: "+ text;
    }

    public ResponseEntity<UserResponseDto> one(String id, UserRequestDto userRequestDto) {
        return null;
    }

    public ResponseEntity<UserResponseDto> all(UserRequestDto userRequestDto) {
        return null;
    }

    public ResponseEntity<UserResponseDto> create(UserRequestDto userRequestDto) {
        return null;
    }

    public ResponseEntity<UserResponseDto> delete(String id, UserRequestDto userRequestDto) {
        return null;
    }

    public ResponseEntity<UserResponseDto> update(String id, UserRequestDto user) {
        return null;
    }

    public ResponseEntity<UserResponseDto> patch(String id, UserRequestDto userResponseDto) {
        return null;
    }
}
