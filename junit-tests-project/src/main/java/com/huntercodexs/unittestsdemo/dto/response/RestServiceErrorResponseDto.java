package com.huntercodexs.unittestsdemo.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestServiceErrorResponseDto {
    int codeError;
    String message;
}
