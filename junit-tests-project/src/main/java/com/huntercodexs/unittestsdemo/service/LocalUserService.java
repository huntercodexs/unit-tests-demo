package com.huntercodexs.unittestsdemo.service;

import com.huntercodexs.unittestsdemo.dto.request.UserRequestDto;
import com.huntercodexs.unittestsdemo.dto.response.UserResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.huntercodexs.unittestsdemo.mapper.response.UserResponseMapper.*;

@Service
public class LocalUserService {

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

    public ResponseEntity<UserResponseDto> one(Integer id) {
        if (id == null) return ResponseEntity.badRequest().build();
        if (id == 0) return ResponseEntity.notFound().build();
        if (!id.toString().replaceAll("\\d", "").equals("")) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().body(mapperResponseUserIdDto());
    }

    public ResponseEntity<?> all() {
        return ResponseEntity.ok().body(mapperResponseUsersDto());
    }

    public ResponseEntity<UserResponseDto> create(UserRequestDto userRequestDto) {
        if (userRequestDto.getName().equals("Username Conflict")) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        return ResponseEntity.ok().body(mapperResponseCreateUserDto(userRequestDto));
    }

    public ResponseEntity<UserResponseDto> delete(Integer id) {
        if (id == null) return ResponseEntity.badRequest().build();
        if (id == 0) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(mapperResponseDeleteUserDto());
    }

    public ResponseEntity<UserResponseDto> update(Integer id, UserRequestDto userRequestDto) {
        if (id == null) return ResponseEntity.badRequest().build();
        if (id == 0) return ResponseEntity.notFound().build();
        if (!isUpdate(userRequestDto)) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        return ResponseEntity.ok().body(mapperResponseUpdateUserDto(userRequestDto));
    }

    public ResponseEntity<UserResponseDto> patch(Integer id, UserRequestDto userRequestDto) {
        if (id == null) return ResponseEntity.badRequest().build();
        if (id == 0) return ResponseEntity.notFound().build();
        if (!isPatch(userRequestDto)) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        return ResponseEntity.ok().body(mapperResponsePatchUserDto(userRequestDto));
    }
}
