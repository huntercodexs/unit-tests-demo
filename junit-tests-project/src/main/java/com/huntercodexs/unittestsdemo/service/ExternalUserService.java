package com.huntercodexs.unittestsdemo.service;

import com.huntercodexs.unittestsdemo.dto.request.UserRequestDto;
import com.huntercodexs.unittestsdemo.external.ExternalUserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.huntercodexs.unittestsdemo.mapper.response.ExternalUserResponseMapper.*;

@Service
public class ExternalUserService {

    @Autowired
    ClientService clientService;

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

    public ResponseEntity<ExternalUserResponseDto> one(String id) {
        return ResponseEntity.ok().body(mapperExternalResponseUserIdDto(clientService.findUserById(id)));
    }

    public ResponseEntity<?> all() {
        return ResponseEntity.ok().body(mapperExternalResponseUsersDto(clientService.findUsers()));
    }

    public ResponseEntity<ExternalUserResponseDto> create(UserRequestDto userRequestDto) {
        return ResponseEntity.ok().body(mapperExternalResponseCreateUserDto(clientService.createUser(userRequestDto)));
    }

    public ResponseEntity<ExternalUserResponseDto> delete(String id) {
        return ResponseEntity.ok().body(mapperExternalResponseDeleteUserDto(clientService.deleteUserById(id)));
    }

    public ResponseEntity<ExternalUserResponseDto> update(String id, UserRequestDto userRequestDto) {
        if (!isUpdate(userRequestDto)) {
            throw new RuntimeException("Request is not an update !");
        }
        return ResponseEntity.ok().body(mapperExternalResponseUpdateUserDto(clientService.updateUserById(id, userRequestDto)));
    }

    public ResponseEntity<ExternalUserResponseDto> patch(String id, UserRequestDto userRequestDto) {
        if (!isPatch(userRequestDto)) {
            throw new RuntimeException("Request is not an patch !");
        }
        return ResponseEntity.ok().body(mapperExternalResponsePatchUserDto(clientService.patchUserById(id, userRequestDto)));
    }
}
