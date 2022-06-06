package com.huntercodexs.unittestsdemo.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRequestDto {
    String name;
    String email;
    String address;
    String gender;
    Integer age;
}
