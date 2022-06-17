package com.huntercodexs.unittestsdemo.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRequestDto {
    @NotNull
    String name;
    @NotNull
    String email;
    @NotNull
    String address;
    @NotNull
    String gender;
    @NotNull
    Integer age;
}
