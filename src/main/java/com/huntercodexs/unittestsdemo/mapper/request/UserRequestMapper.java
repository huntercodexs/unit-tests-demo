package com.huntercodexs.unittestsdemo.mapper.request;

import com.huntercodexs.unittestsdemo.dto.request.UserRequestDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minidev.json.JSONObject;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRequestMapper {

    public static JSONObject extractUserRequestToJson(UserRequestDto userRequestDTO) {
        String name;
        String email;
        String address;
        String gender;
        String age;

        JSONObject extracted = new JSONObject();
        extracted.appendField("name", userRequestDTO.getName());
        extracted.appendField("email", userRequestDTO.getEmail());
        extracted.appendField("address", userRequestDTO.getAddress());
        extracted.appendField("gender", userRequestDTO.getGender());
        extracted.appendField("age", userRequestDTO.getAge());
        return extracted;
    }

}
