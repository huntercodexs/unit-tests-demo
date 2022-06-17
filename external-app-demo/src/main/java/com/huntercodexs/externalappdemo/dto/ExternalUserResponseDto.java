package com.huntercodexs.externalappdemo.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExternalUserResponseDto {
    String name;
    String doc;
    String about;
}
