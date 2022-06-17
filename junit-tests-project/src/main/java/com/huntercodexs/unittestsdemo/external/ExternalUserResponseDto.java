package com.huntercodexs.unittestsdemo.external;

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
