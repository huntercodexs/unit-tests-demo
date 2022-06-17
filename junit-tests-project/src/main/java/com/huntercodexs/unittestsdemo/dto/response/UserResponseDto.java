package com.huntercodexs.unittestsdemo.dto.response;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponseDto {
    String name;
    String email;
    String address;
    String gender;
    Integer age;
    String active;
    String createdAt;
    String updatedAt;
    String deletedAt;
}
