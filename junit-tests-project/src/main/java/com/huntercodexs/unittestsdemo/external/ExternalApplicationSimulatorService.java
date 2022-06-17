package com.huntercodexs.unittestsdemo.external;

import com.huntercodexs.unittestsdemo.dto.request.UserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class ExternalApplicationSimulatorService {

    @Autowired
    ExternalApplicationSimulatorSecurity externalApplicationSimulatorSecurity;

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

    public ResponseEntity<?> one(HttpServletRequest headers, Integer id) {
        if (!externalApplicationSimulatorSecurity.authorization(headers)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
        }
        if (id == 0) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(ExternalUserResponseMapper.mapperExternalResponseUserIdDto());
    }

    public ResponseEntity<?> all(HttpServletRequest headers) {
        if (!externalApplicationSimulatorSecurity.authorization(headers)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
        }
        return ResponseEntity.ok().body(ExternalUserResponseMapper.mapperExternalResponseUsersDto());
    }

    public ResponseEntity<?> create(HttpServletRequest headers, UserRequestDto userRequestDto) {
        if (!externalApplicationSimulatorSecurity.authorization(headers)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
        }
        if (userRequestDto.getName().equals("Username Conflict")) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        return ResponseEntity.ok().body(ExternalUserResponseMapper.mapperExternalResponseCreateUserDto(userRequestDto));
    }

    public ResponseEntity<?> delete(HttpServletRequest headers, Integer id) {
        if (!externalApplicationSimulatorSecurity.authorization(headers)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
        }
        if (id == 0) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(ExternalUserResponseMapper.mapperExternalResponseDeleteUserDto());
    }

    public ResponseEntity<?> update(HttpServletRequest headers, Integer id, UserRequestDto userRequestDto) {
        if (!externalApplicationSimulatorSecurity.authorization(headers)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
        }
        if (id == null) return ResponseEntity.badRequest().build();
        if (id == 0) return ResponseEntity.notFound().build();
        if (!isUpdate(userRequestDto)) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        return ResponseEntity.ok().body(ExternalUserResponseMapper.mapperExternalResponseUpdateUserDto(userRequestDto));
    }

    public ResponseEntity<?> patch(HttpServletRequest headers, Integer id, UserRequestDto userRequestDto) {
        if (!externalApplicationSimulatorSecurity.authorization(headers)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
        }
        if (id == null) return ResponseEntity.badRequest().build();
        if (id == 0) return ResponseEntity.notFound().build();
        if (!isPatch(userRequestDto)) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        return ResponseEntity.ok().body(ExternalUserResponseMapper.mapperExternalResponsePatchUserDto(userRequestDto));
    }
}
