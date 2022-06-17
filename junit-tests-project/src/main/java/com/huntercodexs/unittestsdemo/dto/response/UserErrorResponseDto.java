package com.huntercodexs.unittestsdemo.dto.response;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserErrorResponseDto {
    int codeError;
    String message;
}
