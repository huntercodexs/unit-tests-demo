package com.huntercodexs.unittestsdemo.mapper.response;

import com.huntercodexs.unittestsdemo.external.ExternalUserResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ExternalUserResponseMapper {

    public static ExternalUserResponseDto mapperExternalResponseUserIdDto(ResponseEntity<ExternalUserResponseDto> userById) {
        ExternalUserResponseDto userResponseDTO = new ExternalUserResponseDto();
        userResponseDTO.setName("MOCK - Username Tester External");
        userResponseDTO.setDoc("MOCK - 1234567890");
        userResponseDTO.setAbout("MOCK - About the username tester external");
        return userResponseDTO;
    }

    public static JSONObject mapperExternalResponseUsersDto(ResponseEntity<ExternalUserResponseDto> users) {
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < 3; i++) {
            jsonObject.appendField(String.valueOf(i), mapperExternalResponseUserIdDto(users));
        }
        return jsonObject;
    }

    public static ExternalUserResponseDto mapperExternalResponseCreateUserDto(ResponseEntity<ExternalUserResponseDto> userRequestDto) {
        return mapperExternalResponseUserIdDto(userRequestDto);
    }

    public static ExternalUserResponseDto mapperExternalResponseDeleteUserDto(ResponseEntity<ExternalUserResponseDto> userResponseDtoResponseEntity) {
        return mapperExternalResponseUserIdDto(userResponseDtoResponseEntity);
    }

    public static ExternalUserResponseDto mapperExternalResponseUpdateUserDto(ResponseEntity<ExternalUserResponseDto> userRequestDto) {
        return mapperExternalResponseUserIdDto(userRequestDto);
    }

    public static ExternalUserResponseDto mapperExternalResponsePatchUserDto(ResponseEntity<ExternalUserResponseDto> userRequestDto) {
        return mapperExternalResponseUserIdDto(userRequestDto);
    }
}

