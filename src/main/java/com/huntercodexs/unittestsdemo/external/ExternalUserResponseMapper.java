package com.huntercodexs.unittestsdemo.external;

import com.huntercodexs.unittestsdemo.dto.request.UserRequestDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minidev.json.JSONObject;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ExternalUserResponseMapper {

    public static ExternalUserResponseDto mapperExternalResponseUserIdDto() {
        ExternalUserResponseDto userResponseDTO = new ExternalUserResponseDto();
        userResponseDTO.setName("Username Tester External");
        userResponseDTO.setDoc("1234567890");
        userResponseDTO.setAbout("About the username tester external");
        return userResponseDTO;
    }

    public static JSONObject mapperExternalResponseUsersDto() {
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < 3; i++) {
            jsonObject.appendField(String.valueOf(i), mapperExternalResponseUserIdDto());
        }
        return jsonObject;
    }

    public static ExternalUserResponseDto mapperExternalResponseCreateUserDto(UserRequestDto userRequestDto) {
        return mapperExternalResponseUserIdDto();
    }

    public static ExternalUserResponseDto mapperExternalResponseDeleteUserDto() {
        return mapperExternalResponseUserIdDto();
    }

    public static ExternalUserResponseDto mapperExternalResponseUpdateUserDto(UserRequestDto userRequestDto) {
        return mapperExternalResponseUserIdDto();
    }

    public static ExternalUserResponseDto mapperExternalResponsePatchUserDto(UserRequestDto userRequestDto) {
        return mapperExternalResponseUserIdDto();
    }
}
