package com.huntercodexs.externalappdemo.service;

import com.huntercodexs.externalappdemo.security.ExternalAppSimulatorSecurity;
import com.huntercodexs.externalappdemo.dto.ExternalUserRequestDto;
import com.huntercodexs.externalappdemo.mapper.ExternalUserResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class ExternalAppSimulatorService {

    @Autowired
    ExternalAppSimulatorSecurity externalAppSimulatorSecurity;

    private boolean isUpdate(ExternalUserRequestDto externalUserRequestDto) {
        try {
            return
                    !(externalUserRequestDto.getName().equals("") ||
                            externalUserRequestDto.getEmail().equals("") ||
                            externalUserRequestDto.getAddress().equals("") ||
                            externalUserRequestDto.getGender().equals("") ||
                            (externalUserRequestDto.getAge() == null || externalUserRequestDto.getAge() <= 0));
        } catch (RuntimeException re) {
            re.printStackTrace();
            return false;
        }
    }

    private boolean isPatch(ExternalUserRequestDto externalUserRequestDto) {
        try {
            return
                    (externalUserRequestDto.getName() == null || externalUserRequestDto.getName().equals("") ||
                            externalUserRequestDto.getEmail() == null || externalUserRequestDto.getEmail().equals("") ||
                            externalUserRequestDto.getAddress() == null || externalUserRequestDto.getAddress().equals("") ||
                            externalUserRequestDto.getGender() == null || externalUserRequestDto.getGender().equals("") ||
                            externalUserRequestDto.getAge() == null);
        } catch (RuntimeException re) {
            re.printStackTrace();
            return false;
        }
    }

    public ResponseEntity<?> one(HttpServletRequest headers, Integer id) {
        if (!externalAppSimulatorSecurity.authorization(headers)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
        }
        if (id == 0) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(ExternalUserResponseMapper.mapperExternalResponseUserIdDto());
    }

    public ResponseEntity<?> all(HttpServletRequest headers) {
        if (!externalAppSimulatorSecurity.authorization(headers)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
        }
        return ResponseEntity.ok().body(ExternalUserResponseMapper.mapperExternalResponseUsersDto());
    }

    public ResponseEntity<?> create(HttpServletRequest headers, ExternalUserRequestDto externalUserRequestDto) {
        if (!externalAppSimulatorSecurity.authorization(headers)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
        }
        if (externalUserRequestDto.getName().equals("Username Conflict")) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        return ResponseEntity.ok().body(ExternalUserResponseMapper.mapperExternalResponseCreateUserDto(externalUserRequestDto));
    }

    public ResponseEntity<?> delete(HttpServletRequest headers, Integer id) {
        if (!externalAppSimulatorSecurity.authorization(headers)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
        }
        if (id == 0) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(ExternalUserResponseMapper.mapperExternalResponseDeleteUserDto());
    }

    public ResponseEntity<?> update(HttpServletRequest headers, Integer id, ExternalUserRequestDto externalUserRequestDto) {
        if (!externalAppSimulatorSecurity.authorization(headers)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
        }
        if (id == null) return ResponseEntity.badRequest().build();
        if (id == 0) return ResponseEntity.notFound().build();
        if (!isUpdate(externalUserRequestDto)) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        return ResponseEntity.ok().body(ExternalUserResponseMapper.mapperExternalResponseUpdateUserDto(externalUserRequestDto));
    }

    public ResponseEntity<?> patch(HttpServletRequest headers, Integer id, ExternalUserRequestDto externalUserRequestDto) {
        if (!externalAppSimulatorSecurity.authorization(headers)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
        }
        if (id == null) return ResponseEntity.badRequest().build();
        if (id == 0) return ResponseEntity.notFound().build();
        if (!isPatch(externalUserRequestDto)) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        return ResponseEntity.ok().body(ExternalUserResponseMapper.mapperExternalResponsePatchUserDto(externalUserRequestDto));
    }
}
