package com.huntercodexs.unittestsdemo.service;

import com.huntercodexs.unittestsdemo.dto.request.UserRequestDto;
import com.huntercodexs.unittestsdemo.dto.response.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.huntercodexs.unittestsdemo.mapper.response.UserResponseMapper.*;

@Service
public class UserService {

    public boolean testBool(String val) {
        return !val.equals("false");
    }

    public String testString(String text) {
        if (text.isEmpty()) {
            return "The string is empty";
        }
        return "The string is: "+ text;
    }

    private boolean isUpdate(UserRequestDto userRequestDto) {
        try {
            return
                    !(userRequestDto.getName().equals("") ||
                    userRequestDto.getEmail().equals("") ||
                    userRequestDto.getAddress().equals("") ||
                    userRequestDto.getGender().equals("") ||
                    (userRequestDto.getAge() == null || userRequestDto.getAge() <= 0));
        } catch (RuntimeException re) {
            re.printStackTrace();
            return false;
        }
    }

    private boolean isPatch(UserRequestDto userRequestDto) {
        try {
            return
                    (userRequestDto.getName() == null || userRequestDto.getName().equals("") ||
                    userRequestDto.getEmail() == null || userRequestDto.getEmail().equals("") ||
                    userRequestDto.getAddress() == null || userRequestDto.getAddress().equals("") ||
                    userRequestDto.getGender() == null || userRequestDto.getGender().equals("") ||
                    userRequestDto.getAge() == null);
        } catch (RuntimeException re) {
            re.printStackTrace();
            return false;
        }
    }

    public ResponseEntity<UserResponseDto> one(String id) {
        return ResponseEntity.ok().body(mapperResponseUserIdDto());
    }

    public ResponseEntity<?> all() {
        return ResponseEntity.ok().body(mapperResponseUsersDto());
    }

    public ResponseEntity<UserResponseDto> create(UserRequestDto userRequestDto) {
        return ResponseEntity.ok().body(mapperResponseCreateUserDto(userRequestDto));
    }

    public ResponseEntity<UserResponseDto> delete(String id) {
        return ResponseEntity.ok().body(mapperResponseDeleteUserDto());
    }

    public ResponseEntity<UserResponseDto> update(String id, UserRequestDto userRequestDto) {
        if (!isUpdate(userRequestDto)) {
            throw new RuntimeException("Request is not an update !");
        }
        return ResponseEntity.ok().body(mapperResponseUpdateUserDto(userRequestDto));
    }

    public ResponseEntity<UserResponseDto> patch(String id, UserRequestDto userRequestDto) {
        if (!isPatch(userRequestDto)) {
            throw new RuntimeException("Request is not an patch !");
        }
        return ResponseEntity.ok().body(mapperResponsePatchUserDto(userRequestDto));
    }
}
