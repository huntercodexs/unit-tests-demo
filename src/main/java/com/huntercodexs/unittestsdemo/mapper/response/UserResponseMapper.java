package com.huntercodexs.unittestsdemo.mapper.response;

import com.huntercodexs.unittestsdemo.dto.response.UserResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserResponseMapper {

    public static UserResponseDto mapperRequestCreateUserDto(String tcn) {
        UserResponseDto userResponseDTO = new UserResponseDto();
        return userResponseDTO;
    }

    public static UserResponseDto mapperResponseCreateUserDto() {
        UserResponseDto userResponseDTO = new UserResponseDto();
        return userResponseDTO;
    }

    public static UserResponseDto mapperResponseUserIdDto() {
        UserResponseDto userResponseDTO = new UserResponseDto();
        return userResponseDTO;
    }

    public static UserResponseDto mapperResponseUsersDto() {
        UserResponseDto userResponseDTO = new UserResponseDto();
        return userResponseDTO;
    }

    public static UserResponseDto mapperResponseDeleteUserDto() {
        UserResponseDto userResponseDTO = new UserResponseDto();
        return userResponseDTO;
    }

    public static UserResponseDto mapperResponseUpdateUserDto() {
        UserResponseDto userResponseDTO = new UserResponseDto();
        return userResponseDTO;
    }

    public static UserResponseDto mapperResponsePatchUserDto() {
        UserResponseDto userResponseDTO = new UserResponseDto();
        return userResponseDTO;
    }
}
