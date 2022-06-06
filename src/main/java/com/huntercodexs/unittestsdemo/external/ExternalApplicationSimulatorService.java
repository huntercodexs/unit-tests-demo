package com.huntercodexs.unittestsdemo.external;

import com.huntercodexs.unittestsdemo.dto.request.UserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.huntercodexs.unittestsdemo.external.ExternalUserResponseMapper.*;

@Service
public class ExternalApplicationSimulatorService {

    @Autowired
    ExternalApplicationSimulatorSecurity externalApplicationSimulatorSecurity;

    public ResponseEntity<?> one(HttpServletRequest headers, String id) {
        if (!externalApplicationSimulatorSecurity.authorization(headers)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
        }
        if (id.equals("0")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(mapperExternalResponseUserIdDto());
    }

    public ResponseEntity<?> all(HttpServletRequest headers) {
        if (!externalApplicationSimulatorSecurity.authorization(headers)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
        }
        return ResponseEntity.ok().body(mapperExternalResponseUsersDto());
    }

    public ResponseEntity<?> create(HttpServletRequest headers, UserRequestDto userRequestDto) {
        if (!externalApplicationSimulatorSecurity.authorization(headers)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
        }
        return ResponseEntity.ok().body(mapperExternalResponseCreateUserDto(userRequestDto));
    }

    public ResponseEntity<?> delete(HttpServletRequest headers, String id) {
        if (!externalApplicationSimulatorSecurity.authorization(headers)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
        }
        if (id.equals("0")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(mapperExternalResponseDeleteUserDto());
    }

    public ResponseEntity<?> update(HttpServletRequest headers, String id, UserRequestDto userRequestDto) {
        if (!externalApplicationSimulatorSecurity.authorization(headers)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
        }
        if (id.equals("0")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(mapperExternalResponseUpdateUserDto(userRequestDto));
    }

    public ResponseEntity<?> patch(HttpServletRequest headers, String id, UserRequestDto userRequestDto) {
        if (!externalApplicationSimulatorSecurity.authorization(headers)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
        }
        if (id.equals("0")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(mapperExternalResponsePatchUserDto(userRequestDto));
    }
}
