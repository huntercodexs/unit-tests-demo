package com.huntercodexs.externalappdemo.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExternalUserRequestDto {
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
