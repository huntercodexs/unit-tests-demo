package com.huntercodexs.unittestsdemo.mapper.response;

import com.huntercodexs.unittestsdemo.dto.request.UserRequestDto;
import com.huntercodexs.unittestsdemo.dto.response.UserResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minidev.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserResponseMapper {

    public static UserResponseDto mapperResponseUserDto(UserRequestDto userRequestDto) {
        Date now = new Date();
        String currentDate = new SimpleDateFormat("dd/MM/yyy HH:mm:ss").format(now);
        UserResponseDto userResponseDTO = new UserResponseDto();
        userResponseDTO.setName(userRequestDto.getName());
        userResponseDTO.setEmail(userRequestDto.getEmail());
        userResponseDTO.setAddress(userRequestDto.getAddress());
        userResponseDTO.setGender(userRequestDto.getGender());
        userResponseDTO.setAge(userRequestDto.getAge());
        userResponseDTO.setActive("yes");
        userResponseDTO.setCreatedAt(currentDate);
        userResponseDTO.setUpdatedAt(null);
        userResponseDTO.setDeletedAt(null);
        return userResponseDTO;
    }

    public static UserResponseDto mapperResponseUserIdDto() {
        UserResponseDto userResponseDTO = new UserResponseDto();
        userResponseDTO.setName("Username Tester");
        userResponseDTO.setEmail("username@tester.com");
        userResponseDTO.setAddress("Address Username Tester 1234");
        userResponseDTO.setGender("Gender");
        userResponseDTO.setAge(40);
        userResponseDTO.setActive("yes");
        userResponseDTO.setCreatedAt("2022/01/01 10:00:00");
        userResponseDTO.setUpdatedAt(null);
        userResponseDTO.setDeletedAt(null);
        return userResponseDTO;
    }

    public static JSONObject mapperResponseUsersDto() {
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < 3; i++) {
            jsonObject.appendField(String.valueOf(i), mapperResponseUserIdDto());
        }
        return jsonObject;
    }

    public static UserResponseDto mapperResponseCreateUserDto(UserRequestDto userRequestDto) {
        return mapperResponseUserDto(userRequestDto);
    }

    public static UserResponseDto mapperResponseDeleteUserDto() {
        return mapperResponseUserIdDto();
    }

    public static UserResponseDto mapperResponseUpdateUserDto(UserRequestDto userRequestDto) {
        return mapperResponseUserDto(userRequestDto);
    }

    public static UserResponseDto mapperResponsePatchUserDto(UserRequestDto userRequestDto) {
        return mapperResponseUserDto(userRequestDto);
    }
}
